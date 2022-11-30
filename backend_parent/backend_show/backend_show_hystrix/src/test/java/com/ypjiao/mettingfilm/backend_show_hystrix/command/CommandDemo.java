package com.ypjiao.mettingfilm.backend_show_hystrix.command;

import com.netflix.hystrix.*;

public class CommandDemo extends HystrixCommand<String> {
    String name;
    public CommandDemo(String name){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withCircuitBreakerSleepWindowInMilliseconds(1)
                        .withRequestCacheEnabled(false)
//                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties
                        .defaultSetter()
                        .withCoreSize(2)
                        .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
                        .withMaxQueueSize(0)
                )
        );
        this.name = name;
    }
    @Override
    protected String run() {
//        Thread.sleep(500l);
        if (name.startsWith("error")) {
            int a = 6/0;
        }
        String result = "CommandHelloWorld name = {"+ name+"}";
        System.err.println(result+"   CurrentThreadName-"+Thread.currentThread().getName());
        return result;
    }

    @Override
    protected String getFallback() {
        System.out.println("this is the fallback");
        return null;
    }
}
