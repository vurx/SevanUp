package com.vvvv.sevanUp.instance.realUse;

import com.alibaba.fastjson.JSONObject;
import com.vvvv.sevanUp.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PracticeThread {
    private final static Logger log = LoggerFactory.getLogger(PracticeThread.class);

    final Connection connection;

    // 类初建时建立连接
    public PracticeThread() {
        connection = GetConnection();
    }

    // 每个线程执行的任务量
    private final int MaxHandingNum = 5;


    public ExecutorService go() throws Exception {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet executeQuery = statement.executeQuery("select count(1) count from knell");
            while (executeQuery.next()) {
                count = executeQuery.getInt("count");
            }
        } catch (Exception e) {
            log.error("sql执行异常：", e);
        }
        // 根据 获取的sql数量/单个线程处理量 来计算需要执行的线程数
        int batch = count % MaxHandingNum == 0 ? count / MaxHandingNum : count / MaxHandingNum + 1;
        log.info("begin Time = {}", new Date());
        // 建立线程池
        ExecutorService newFixedThreadPool = Executors.newCachedThreadPool();
        // 信号量设置
        final Semaphore sema = new Semaphore(16);
        final CountDownLatch countDownLatch = new CountDownLatch(batch);
        for (int i = 0; i < batch; i++) {
            int start = i * MaxHandingNum;
            newFixedThreadPool.execute(() -> {
                try {
                    sema.acquire();
                    MultithreadUploadMessage(start, MaxHandingNum);
                    countDownLatch.countDown();
                    sema.release();
                } catch (InterruptedException e) {
                    log.warn("exception", e);
                }
            });
        }
        countDownLatch.await();

        connection.close();
        log.info("end Time = {}", new Date());

        return newFixedThreadPool;
    }

    /**
     * @Description 多线程执行的方法
     * @Author vvvv
     * @Date 16:24 2020/6/5
     * @Params [start, MaxHandingNum]
     * @Return void
     */
    private void MultithreadUploadMessage(int start, int range) {
        Statement statement = null;
        ResultSet rs = null;
        if (connection != null) {
            try {
                // 读取数据
                statement = connection.createStatement();
                String sql = "select * from knell limit " + start + "," + range;
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Message message = new Message();
                    int msg_id = rs.getInt("id");
                    message.setId(rs.getInt("id"));
                    message.setUserName(rs.getString("userName"));
                    message.setPassWord(rs.getString("passWord"));
                    message.setUserSex(rs.getString("user_sex"));
                    message.setNickName(rs.getString("nick_name"));
                    String s = JSONObject.toJSONString(message);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = simpleDateFormat.format(new Date());
                    String index = "crm-ubp-message-" + format;
                    try {
                        String documentByManually = createDocumentByManually("", "", "", index, "message", msg_id + "", s);
                        log.info("es数据记录 = {}", msg_id + ":" + documentByManually);
                    } catch (Exception e) {
                        log.error("存取执行异常，异常原因：", e);
                    }
                }
            } catch (SQLException e) {
                log.error("sql执行异常，异常原因={}", e);
            }
        }
    }


    /**
     * @Description 写入es
     * @Author vvvv
     * @Date 16:23 2020/6/5
     * @Params [ip, port, password, index, name, id, message]
     * @Return java.lang.String
     */
    public String createDocumentByManually(String ip, String port, String password, String index, String name, String id, String message) throws Exception {
        final int outTimes = 3000;
        String url = "http://192.168.12.70:9200/" + index;
        if (name != null) {
            url = url + "/" + name;
            if (id != null) {
                url = url + "/" + id;
            }
        }
        Map headDatas = new HashMap();
        if (password != null && !"".equals(password)) {
            headDatas.put("Authorization", password);
        }
        String returnMsg = HttpClientUtil.doPost(url, message, "UTF-8", outTimes, headDatas);
        JSONObject returnJson = JSONObject.parseObject(returnMsg);
        if (returnJson.getJSONObject("error") != null && !returnJson.getJSONObject("error").isEmpty()) {
            throw new Exception(returnJson.getJSONObject("error").toJSONString());
        }
        return returnMsg;
    }

    /**
     * @Description 建立数据连接
     * @Author vvvv
     * @Date 16:17 2020/6/5
     * @Param
     * @Return java.sql.Connection
     */
    public Connection GetConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// 注册驱动
            connection = DriverManager.getConnection("jdbc:mysql://47.96.238.1:3306/timo", "root",
                    "Abc123!_");
        } catch (Exception e) {
            log.error("建立连接，异常原因：{}", e.getMessage());
        }
        return connection;
    }
}
