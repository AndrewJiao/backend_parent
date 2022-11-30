package com.ypjiao.mettingfilm.backend_show_hystrix.command;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class ObserveCommandTest {
    @Test
    public void testObserveCommand() throws InterruptedException {
        long starTime = System.currentTimeMillis();
        ObserveCommand command = new ObserveCommand("observe");
        Observable<String> observe = command.observe();
        //阻塞式调用
//        String result = observe.toBlocking().single();
//        long endTime = System.currentTimeMillis();
//        System.out.println("result="+result+"    durringTime="+(endTime-starTime));

        //非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe="+"onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("observe="+"onError, "+throwable.getMessage());
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.err.println("observe="+"onNext result={"+result+"} , "+"durringTime="+(endTime-starTime));
            }
        });
        Thread.sleep(1002);

    }
}
