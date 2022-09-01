package com.vvvv.sevanUp.study.designPattern.Chapt3.practice.Decorators;

import com.vvvv.sevanUp.study.designPattern.Chapt3.practice.Decorator;

public class up extends Decorator {
    @Override
    public void show() {
        System.out.println("上衣");
        super.show();
    }
}
