package com.vvvv.sevanUp.study.concurrency.howToCreateThread;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CreatingThread07
 * @Description 并行计算（Java8+）
 * @Author vvvv
 * @Date 2020/7/22 14:21
 * @Version V1.0
 */
public class CreatingThread07 {

    @Test
    public void test() {
        List<Integer> alist = Arrays.asList(1, 2, 3, 4, 5);
        alist.stream().forEach(System.out::println);
        alist.parallelStream().forEach(System.out::println);
    }
}