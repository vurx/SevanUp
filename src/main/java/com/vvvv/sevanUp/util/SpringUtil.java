package com.vvvv.sevanUp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *@Author: CJ
 *@Date: 2021-11-2 14:39
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    // 通过name获取 Bean.
    
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    // 通过class获取Bean.
    
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
