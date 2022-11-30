package com.ypjiao.mettingfilm.backend_show_hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.SneakyThrows;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.observables.BlockingObservable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandTest {

    @Test
    public void TestExcute(){
        long starTime = System.currentTimeMillis();
        CommandDemo commond = new CommandDemo("execute");
        //同步执行command
        String result = commond.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("result="+result+"    durringTime="+(endTime-starTime));
    }
    @Test
    public void TestQueue() throws ExecutionException, InterruptedException {
        long starTime = System.currentTimeMillis();
        CommandDemo commond = new CommandDemo("queue");
        //异步执行command
        Future<String> queue = commond.queue();
        long endTime = System.currentTimeMillis();
        System.out.println("further= queue"+"    durringTime="+(endTime-starTime));
        String result = queue.get();
        long endQueueTime = System.currentTimeMillis();
        System.out.println("result="+result+"    durringTime="+(endQueueTime-starTime));
    }
    @Test
    public void TestObserve() throws InterruptedException {
        long starTime = System.currentTimeMillis();
        CommandDemo commond = new CommandDemo("Observe");
        Observable<String> observe = commond.observe();
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

    /**
     * 测试请求合并
     */
    @Test
    public void testCommandCollapser() throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        CommandCollapser test1 = new CommandCollapser("test1");
        CommandCollapser test2 = new CommandCollapser("test1");
        CommandCollapser test3 = new CommandCollapser("test1");
        CommandCollapser test4 = new CommandCollapser("test4");

        Future<String> queue1 = test1.queue();
        Future<String> queue2 = test2.queue();
        Future<String> queue3 = test3.queue();
        Future<String> queue4 = test4.queue();

        String s1 = queue1.get();
        String s2 = queue2.get();
        String s3 = queue3.get();
        String s4 = queue4.get();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        hystrixRequestContext.close();
        Thread.sleep(2002);
    }

    /**
     * 测试线程池
     */
    @Test
    public void testCommandThreadPool() throws ExecutionException, InterruptedException {
        CommandDemo command1 = new CommandDemo("test01");
        CommandDemo command2 = new CommandDemo("test02");
        CommandDemo command3 = new CommandDemo("test03");
        CommandDemo command4 = new CommandDemo("test04");
        CommandDemo command5 = new CommandDemo("test05");

        Future<String> result1 = command1.queue();
        Future<String> result2 = command2.queue();
        Future<String> result3 = command3.queue();
        Future<String> result4 = command4.queue();
        Future<String> result5 = command5.queue();

        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println(result3.get());
        System.out.println(result4.get());
        System.out.println(result5.get());
    }

    /**
     * 测试信号量
     * 信号量测试需要利用线程来启动测试，不然无法打到并发数量
     */
    @Test
    public void testSemaphore() throws InterruptedException {
        TamplateCommand test01 = new TamplateCommand("test01");
        TamplateCommand test02 = new TamplateCommand("test02");
        TamplateCommand test03 = new TamplateCommand("test03");
        TamplateCommand test04 = new TamplateCommand("test04");
        TamplateCommand test05 = new TamplateCommand("test05");
        test01.start();
        test02.start();
        test03.start();
        test04.start();
        test05.start();
        Thread.sleep(2000l);
    }
    class TamplateCommand extends Thread {
        private String name;
        TamplateCommand(String name){
            this.name = name;
        }
        public void run(){
            CommandDemo command1 = new CommandDemo(name);
            String execute = command1.execute();
            System.out.println(execute);
        }
    }

    /**
     * 测试熔断器熔断机制
     */
    @Test
    public void testCircuit() throws InterruptedException {
        //首次成功测试
        CommandDemo test01 = new CommandDemo("test01");
        test01.execute();
        //首次失败，熔断器开启

        CommandDemo error01 = new CommandDemo("error01");
        error01.execute();
        Thread.sleep(5000l);
        System.out.println("五秒后");
        //五秒后启动半开状态再次成功成功
        CommandDemo test02 = new CommandDemo("test02");
        test02.execute();
    }

}
