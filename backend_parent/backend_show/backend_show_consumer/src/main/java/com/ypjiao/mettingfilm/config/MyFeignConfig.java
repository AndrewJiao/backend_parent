package com.ypjiao.mettingfilm.config;

import feign.Contract;
import feign.MethodMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 自定义Feaign默认配置，用于为特定的Feign设置特殊配置，例如编码等
 * 注意：不能让Spring容器加载到这个配置，因此包路径放在springBoot扫描范围之外
 */
@Configuration
public class MyFeignConfig {

    @Bean
    Contract getContract(){
        return new Contract.Default();
    }
}
