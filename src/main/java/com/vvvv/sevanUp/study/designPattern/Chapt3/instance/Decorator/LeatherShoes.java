package com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Finery;

public class LeatherShoes extends Finery {

    @Override
    public void show() {
        System.out.print("皮鞋");
        super.show();
    }
}
