package com.ypjiao.mettingfilm.backend_common;

import com.ypjiao.mettingfilm.backend_utils.AutoResponseTest;
import org.junit.Test;

public class TestResponseJar {
    @Test
    public void testJarUtils(){
        String ypjiao = AutoResponseTest.responseName("ypjiao");
        System.out.println(ypjiao);
    }
}
