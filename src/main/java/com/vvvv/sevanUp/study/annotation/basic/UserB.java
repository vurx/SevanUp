package com.vvvv.sevanUp.study.annotation.basic;

import org.springframework.stereotype.Service;

@Service("userb")
public class UserB implements IUser{
    @Override
    public void test() {
        System.out.println("i am user b");
    }
}
