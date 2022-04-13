package com.vvvv.sevanUp.study.concurrency.example._7_threadLocal;

/**
 * @ClassName RequestHolder
 * @Description
 * @Author vvvv
 * @Date 2020/5/26 15:38
 * @Version V1.0
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}