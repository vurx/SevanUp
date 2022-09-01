package com.vvvv.sevanUp.study.annotation.basic;

import org.springframework.stereotype.Service;

@Service("usera")
public class UserA implements IUser{
    @Override
    public void test() {
        System.out.println("i am user a");
    }
}
