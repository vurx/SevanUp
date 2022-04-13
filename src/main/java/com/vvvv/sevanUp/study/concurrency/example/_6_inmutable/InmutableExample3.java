package com.vvvv.sevanUp.study.concurrency.example._6_inmutable;

/**
 * @ClassName InmutableExample3
 * @Description final修饰方法 不可被子类重写
 * @Author vvvv
 * @Date 2020/5/26 14:10
 * @Version V1.0
 */
public class InmutableExample3 {
    public final void CantBeOverride() {

    }
}

class test extends InmutableExample3{
//    @Override
//    public void CantBeOverride() {
//        super.CantBeOverride();
//    }
}