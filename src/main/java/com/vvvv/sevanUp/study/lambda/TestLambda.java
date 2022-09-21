package com.vvvv.sevanUp.study.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLambda {


    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /*
        归约
        reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("----------------------------------------");
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test2() {
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(TestLambda::filterCharacter)
                .map((ch) -> {
                    if (ch.equals('六'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(sum.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test3() {
        // Collectors.toList()
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(list);
        System.out.println("----------------------------------");
        // Collectors.toSet()
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("----------------------------------");
        // 自定义collect收集
        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hs);
    }

    @Test
    public void test4() {
        // maxBy(最大的工资)
//        System.out.println(emps.stream().map(Employee::getSalary).collect(Collectors.maxBy(Double::compareTo)).get());
//        System.out.println(emps.stream().map(Employee::getSalary).max(Double::compareTo).get());
//        System.out.println(emps.stream().mapToDouble(Employee::getSalary).max().getAsDouble());
//        System.out.println(emps.stream().max(Comparator.comparingDouble(Employee::getSalary)).get().getSalary());
        // minBy(最小的年龄，不使用map映射) 同max


        // summingDouble(薪资总和)
//        System.out.println(emps.stream().collect(Collectors.summingDouble(Employee::getSalary)));
//        System.out.println(emps.stream().mapToDouble(Employee::getSalary).sum());


//        // averagingDouble(平均年龄)
//        System.out.println(emps.stream().collect(Collectors.averagingDouble(Employee::getAge)));
//        System.out.println(emps.stream().mapToInt(Employee::getAge).average().getAsDouble());
//
//        // counting(年龄大于50的员工的数量)
        System.out.println(emps.stream().filter(t -> t.getAge() < 50).collect(Collectors.counting()));
        System.out.println(emps.stream().filter(t -> t.getAge() < 50).count());

//
//        // summarizingDouble
//        DoubleSummaryStatistics collect = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
//        // 获取薪资平均值
//        System.out.println(collect.getAverage());
//        // 获取薪资最大值
//        System.out.println(collect.getMax());
//        // 获取薪资最小值
//        System.out.println(collect.getMin());
//        // 获取collect里面的数量
//        System.out.println(collect.getCount());
//        // 获取薪资总和
//        System.out.println(collect.getSum());
    }

    //分组
    @Test
    public void test5() {
        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test6() {
        Map<Employee.Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test7() {
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        System.out.println(map);
    }

    //
    @Test
    public void test8() {
//        System.out.println(emps.stream().map(Employee::getName).collect(Collectors.joining(",", "[", "]")));
        ArrayList<List<String>> strings = new ArrayList<>();
        strings.add(Arrays.asList("1", "2", "3", "4"));
        strings.add(Arrays.asList("2", "3", "4", "5"));
        strings.add(Arrays.asList("3", "4", "5", "6"));

//        // 非并行流
//        System.out.println(strings.stream().flatMap(Collection::stream).collect(Collectors.toList()));
////        System.out.println("reduce1:" + reduce1);
        List<Integer> strings1 = Arrays.asList(1, 2, 3, 4);
        System.out.println(strings1.stream().reduce(0, (x, y) -> {
            System.out.println("currentThreadId：["+Thread.currentThread().getId()+"]x----:" + x + ",y----:" + y);
            return x + y;
        }));
//        System.out.println(strings1.parallelStream().reduce(0,
//                (a, b) -> {
//                    System.out.println("currentThreadId：["+Thread.currentThread().getId()+"]a----:" + a + ",b----:" + b);
//                    return a + b;
//                },
//                (x, y) -> {
//                    System.out.println("currentThreadId：["+Thread.currentThread().getId()+"]x----:" + x + ",y----:" + y);
//                    return x + y;
//                }));
    }

    @Test
    public void test9() {
        System.out.println(emps.stream().map(Employee::getSalary).collect(Collectors.reducing(Double::sum)).get());
        System.out.println(emps.stream().map(Employee::getSalary).reduce(Double::sum).get());
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void test10(){
        List<String> strList1 = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        List<String> strList2 = Arrays.asList("aaa", "bbbb", "cccc", "dddd", "eeee");
//        Set<String> collect = Stream.of(strList1, strList2)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toSet());
//        System.out.println(collect);
        strList1.addAll(strList2);
        System.out.println(strList1);
    }


}
