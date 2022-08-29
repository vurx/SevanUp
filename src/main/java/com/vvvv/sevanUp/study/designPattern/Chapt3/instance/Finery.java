package com.vvvv.sevanUp.study.designPattern.Chapt3.instance;

/**
 * 装饰抽象类
 */
public class Finery implements Component {
    protected Component comp;

    public void decorate(Component component) {
        this.comp = component;
    }

    public void show() {
        if (comp != null) {
            comp.show();
        }
    }
}
