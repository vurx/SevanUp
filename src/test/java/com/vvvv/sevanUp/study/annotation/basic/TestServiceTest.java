package com.vvvv.sevanUp.study.annotation.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {
//    @Autowired
//    private List<IUser> users;

    @Qualifier("usera")
    @Autowired
    private IUser usera;

    @Test
    public void test(){
//        users.forEach(IUser::test);
        usera.test();
    }
}