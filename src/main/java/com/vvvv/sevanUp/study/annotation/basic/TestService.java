package com.vvvv.sevanUp.study.annotation.basic;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private List<IUser> newUser;

    @Test
    public void test(){
        newUser.forEach(IUser::test);
    }
}
