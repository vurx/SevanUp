package com.vvvv.sevanUp.study.designPattern.Chapt3.instance;

import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Decorator.*;
import com.vvvv.sevanUp.study.designPattern.Chapt3.instance.Person;
import org.junit.Test;

public class TestDemo {
    @Test
    public void test() {
        Person xc = new Person();
        xc.setName("小菜");

        Sneakers sneakers = new Sneakers();
        BigTrouser bigTrouser = new BigTrouser();
        TShirts tShirts = new TShirts();
        sneakers.decorate(xc);
        bigTrouser.decorate(sneakers);
        tShirts.decorate(bigTrouser);
        tShirts.show();

        System.out.println("=====");
        LeatherShoes leatherShoes = new LeatherShoes();
        Tie tie = new Tie();
        Suit suit = new Suit();
        leatherShoes.decorate(xc);
        tie.decorate(leatherShoes);
        suit.decorate(tie);
        suit.show();


    }
}
