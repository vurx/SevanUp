package com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Finery;

public class Sneakers extends Finery {
    @Override
    public void show() {
        System.out.print("Yeezy boost 350");
        super.show();
    }
}
