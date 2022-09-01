package com.vvvv.sevanUp.study.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class List2Map {
    private List<Student> stuList = new ArrayList(){{
        add(new Student(1, "张一", "组一"));
        add(new Student(2, "张二", "组二"));
        add(new Student(3, "张三", "组二"));
        add(new Student(4, "张三", "组一"));
        add(new Student(5, null, "组一"));
    }};

    /**
     * k->id
     * v->student
     */
    @Test
    public void test(){
        System.out.println(stuList.stream().collect(Collectors.toMap(Student::getId, Function.identity())));
    }

    /**
     * k->name
     * v->student
     * 需要去重，保留第一个值，不去重报错Duplicate
     */
    @Test
    public void test2(){
        System.out.println(stuList.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (o1, o2) -> o1)));
    }

    /**
     * k->id
     * v->name
     * 需要判空
     */
    @Test
    public void test3(){
        System.out.println(stuList.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (o1, o2) -> o1)));
    }
}
