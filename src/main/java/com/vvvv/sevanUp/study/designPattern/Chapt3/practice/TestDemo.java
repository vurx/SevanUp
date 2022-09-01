package com.vvvv.sevanUp.study.designPattern.Chapt3.practice;

import com.vvvv.sevanUp.study.designPattern.Chapt3.practice.Decorators.down;
import com.vvvv.sevanUp.study.designPattern.Chapt3.practice.Decorators.shoes;
import com.vvvv.sevanUp.study.designPattern.Chapt3.practice.Decorators.up;
import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        Person person = new Person();
        person.setName("vvvv");

        shoes shoes = new shoes();
        up up = new up();
        down down = new down();

        shoes.setComp(person);
        down.setComp(shoes);
        up.setComp(down);
        up.show();

    }
}
