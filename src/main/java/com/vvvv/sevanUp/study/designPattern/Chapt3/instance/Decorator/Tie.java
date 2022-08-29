package com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Finery;

public class Tie extends Finery {

    @Override
    public void show() {
        System.out.print("领带");
        super.show();
    }
}
