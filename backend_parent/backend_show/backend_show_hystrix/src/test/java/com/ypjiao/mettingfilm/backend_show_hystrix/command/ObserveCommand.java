package com.ypjiao.mettingfilm.backend_show_hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

public class ObserveCommand extends HystrixObservableCommand {
    private String name;

    public ObserveCommand(String name){
        super(HystrixCommandGroupKey.Factory.asKey(name));
        this.name = name;
    }
    @Override
    protected Observable construct() {
        String result = "CommandHelloWorld name = {"+ name+"}";
        System.err.println(result+"   CurrentThreadName-"+Thread.currentThread().getName());
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("Action1,name="+name);
                subscriber.onNext("Action2,name="+name);
                subscriber.onNext("Action3,name="+name);
                subscriber.onNext("Action4,name="+name);

                subscriber.onCompleted();
            }
        });
    }
}
