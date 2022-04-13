package com.vvvv.sevanUp.study.concurrency.example._7_threadLocal;

/**
 * @ClassName ThreadLocalController
 * @Description
 * @Author vvvv
 * @Date 2020/5/26 16:03
 * @Version V1.0
 */
//@RestController
//@RequestMapping("/threadLocal")
public class ThreadLocalController {

//    @RequestMapping("/test")
    public Long test() {
        return RequestHolder.get();
    }
}