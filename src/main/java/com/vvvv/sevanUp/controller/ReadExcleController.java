package com.vvvv.sevanUp.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.vvvv.sevanUp.instance.excel.ThreadDataListener;
import com.vvvv.sevanUp.instance.excel.threadPool.VThreadPoolExecutor;
import com.vvvv.sevanUp.mapper.excel.SubsinstSynTempMapper;
import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ReadExcle
 * @Description 读取excel
 * @Author vvvv
 * @Date 2020/7/27 09:13
 * @Version V1.0
 */
@RestController
@Slf4j
public class ReadExcleController {
    @Autowired
    private SubsinstSynTempMapper excelMapper;

    @GetMapping("threadReadExcel")
    public void threadRead(@RequestParam String name) {
        long time = new Date().getTime();
        String fileName = Objects.requireNonNull(ReadExcleController.class.getClassLoader().getResource(name)).getPath();
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, SubsinstSynTemp.class, new ThreadDataListener(excelMapper)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
            ThreadPoolExecutor instance = VThreadPoolExecutor.getInstance();
            if (!instance.isShutdown()) {
                instance.shutdown();
            }
        }
        long end = new Date().getTime();
        log.info("总耗时为：{}ms",end-time);

    }
}
