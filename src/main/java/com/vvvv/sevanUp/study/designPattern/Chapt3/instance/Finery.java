package com.vvvv.sevanUp.study.designPattern.Chapt3.instance;

/**
 * 装饰抽象类
 */
public class Finery implements Component {
    protected Component component;

    public void decorate(Component component) {
        this.component = component;
    }

    public void show(){
        if (component!=null) {
            component.show();
        }
    }
}
