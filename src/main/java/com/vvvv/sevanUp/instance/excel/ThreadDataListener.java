package com.vvvv.sevanUp.instance.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.vvvv.sevanUp.instance.excel.threadPool.RspVo;
import com.vvvv.sevanUp.instance.excel.threadPool.VTask;
import com.vvvv.sevanUp.instance.excel.threadPool.VThreadPoolExecutor;
import com.vvvv.sevanUp.mapper.excel.SubsinstSynTempMapper;
import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

// 有个很重要的点 DataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class ThreadDataListener extends AnalysisEventListener<SubsinstSynTemp> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5000;
    //每个线程的处理量
    private static final int MaxHandingNum = 5000;
    List<SubsinstSynTemp> list = new ArrayList<SubsinstSynTemp>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private SubsinstSynTempMapper excelMapper;

    private ThreadDataListener() {
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */
    public ThreadDataListener(SubsinstSynTempMapper demoDAO) {
        this.excelMapper = demoDAO;
    }

    @Override
    public void invoke(SubsinstSynTemp data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSONObject.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("===================================所有数据入库完成！===================================");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("----------------------------------{}条数据，开始存储数据库！----------------------------------", list.size());
        // 使用多线程存表
        try {
            useThreadInsert(list);
        } catch (InterruptedException e) {
            log.info("多线程出现错误：{}", e.getMessage());
        }
        log.info("----------------------------------{}条数据，入库成功！----------------------------------",list.size());
    }

    public void useThreadInsert(List<SubsinstSynTemp> list) throws InterruptedException {
        ThreadPoolExecutor instance = null;
        try {
            instance = VThreadPoolExecutor.getInstance();
        } catch (Exception e) {
            log.debug("初始化获取线程池实例发生异常:", e);
        }
        ArrayList<FutureTask<RspVo>> tasks = new ArrayList<>();
        List<SubsinstSynTemp> subsinstSynTemps = null;
        int count = list.size();
        // 根据 获取的这次要处理数量/单个线程处理量 来计算需要执行的线程数
        int batch = count % MaxHandingNum == 0 ? count / MaxHandingNum : count / MaxHandingNum + 1;
        log.info("总线程数为：{},每个线程的处理量为：{}", batch, MaxHandingNum);
        for (int i = 0; i < batch; i++) {
            int endSize = Math.min((i + 1) * MaxHandingNum, list.size());
            subsinstSynTemps = list.subList(i * MaxHandingNum, endSize);
            VTask task = new VTask(subsinstSynTemps, excelMapper);
            // 封装FutureTask线程任务
            FutureTask<RspVo> futureTask = new FutureTask<RspVo>(task);
            // 执行线程任务
            instance.submit(futureTask);
            // 获取线程结果
            tasks.add(futureTask);
        }
        for (FutureTask<RspVo> task : tasks) {
            try {
                RspVo rspVo = task.get();
                if (!"0".equals(rspVo.getResultCode())) {
                    log.error("id={}，插入失败", rspVo.getResultMsg());
                }
            } catch (Exception e) {
                log.error("i出错={}", e.getMessage());
            }
        }
    }
}