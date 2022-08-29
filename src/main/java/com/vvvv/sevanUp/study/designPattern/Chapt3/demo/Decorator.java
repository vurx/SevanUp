package com.vvvv.sevanUp.study.designPattern.Chapt3.demo;

public class Decorator extends Component {
    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
