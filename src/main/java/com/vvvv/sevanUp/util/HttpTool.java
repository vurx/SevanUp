package com.vvvv.sevanUp.util;

import com.vvvv.basicConfiguration.enums.CharsetType;
import com.vvvv.basicConfiguration.enums.ReturnInfoEnum;
import com.vvvv.basicConfiguration.exception.VurxException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpTool {
    private static Integer timeout = 5 * 1000;

    @SneakyThrows
    private static HttpURLConnection getCommConn(String url, RequestType requestType) {
        URL realUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
        connection.setConnectTimeout(timeout);// 连接主机的超时时间
        connection.setReadTimeout(timeout);// 从主机读取数据的超时时间
        if (RequestType.POST.equals(requestType)) {
            /* 3. 设置请求参数等 */
            // 请求方式
            connection.setRequestMethod("POST");
            // 设置是否输出
            connection.setDoOutput(true);
            // 设置是否读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
        }
        return connection;
    }

    private static String getDataFromConnection(HttpURLConnection conn, CharsetType charsetType) {
        StringBuilder result = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(conn.getInputStream(), charsetType.getCharset());
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line=br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            log.error("data handle error: {},error info: {}", conn.getURL(), e.getMessage());
        }
        return result.toString();
    }

    public static String doPost(String url, String data, CharsetType charsetType) {
        String result = null;
        HttpURLConnection conn = null;
        try {
            conn = getCommConn(url, RequestType.POST);
            conn.connect();

            try (OutputStream out = conn.getOutputStream()) {
                out.write(data.getBytes());
                out.flush();
            }
            result = getDataFromConnection(conn, charsetType);
        } catch (IOException e) {
            log.error("do get error: {}\nerror info: {}", url, e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    public static String doGet(String url, CharsetType charsetType) {
        String result = null;
        HttpURLConnection conn = null;
        try {
            conn = getCommConn(url, RequestType.GET);
            conn.connect();
            if (conn.getResponseCode() != 200) {
                throw new VurxException(ReturnInfoEnum.NET_ERROR);
            }
            result = getDataFromConnection(conn, charsetType);
        } catch (IOException e) {
            log.error("do get error: {}\nerror info: {}", url, e.getMessage());
        } finally {
            if (conn!=null) {
                conn.disconnect();
            }
        }
        return result;
    }

    enum RequestType {
        POST,
        GET
    }
}