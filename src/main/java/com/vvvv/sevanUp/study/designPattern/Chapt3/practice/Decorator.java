package com.vvvv.sevanUp.study.designPattern.Chapt3.practice;

public class Decorator implements Component {

    private Component comp;

    public void setComp(Component comp) {
        this.comp = comp;
    }

    @Override
    public void show() {
        if (comp != null) {
            comp.show();
        }
    }
}
