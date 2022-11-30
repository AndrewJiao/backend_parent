package com.ypjiao.testng.test;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/12 23:26
 * @description：自动化测试类
 * @modified By：
 */
@Slf4j
public class TestNG {
    @Test
    public void test(){
        log.warn("this is test()");
    }
    @BeforeMethod
    public void beforeMethod(){
        log.info("this is beforeMethod()");
    }
    @AfterMethod
    public void afterMethod(){
        log.info("this is afterMethod()");
    }
    @BeforeClass
    public void beforeClass(){
        log.info("this is beforeClass()");
    }
    @AfterClass
    public void afterClass(){
        log.info("this is afterClass()");
    }
    @BeforeSuite
    public void beforeSuite(){
        log.info("this is beforeSuite()");
    }
    @AfterSuite
    public void afterSuite(){
        log.info("this is afterSuite()");
    }
}
