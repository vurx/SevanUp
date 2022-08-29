package com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Finery;

public class TShirts extends Finery {

    @Override
    public void show() {
        System.out.print("大T恤");
        super.show();
    }
}
