package com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Finery;

public class BigTrouser extends Finery {

    @Override
    public void show() {
        System.out.print("垮裤");
        super.show();
    }
}
