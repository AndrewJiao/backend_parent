package com.ypjiao.mettingfilm.backend_show_hystrix.command;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CommandCollapser extends HystrixCollapser<List<String>,String,String> {
    private String name;
    CommandCollapser(String name){
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("collapserKey"+name)));
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
//        System.out.println("getReaquestArgument");
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collection) {

//        System.out.println("command,before="+collection);
        List<String> collect = collection.stream().map(e -> e.getArgument()).collect(Collectors.toList());

//        System.out.println("list,after="+collect);
        InnerCommand resultCommand = new InnerCommand(collect);
        return resultCommand;
    }

    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, String>> collection) {
        AtomicInteger count = new AtomicInteger();
//        System.out.println("before="+collection);
        collection.stream().forEach(e->e.setResponse(strings.get(count.getAndIncrement())));
//        System.out.println("after="+collection);
    }
    class InnerCommand extends HystrixCommand{
        private List<String> names;
        InnerCommand(List<String> names){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("innerClass")));
            this.names = names;
        }
        @Override
        protected Object run() throws Exception {
            System.err.println("-----------------run<<"+Thread.currentThread().getName()+">>---------------");
            List<String> collect = names.stream().map(e -> {
                System.out.println("before=" + e + "->after=" + (e + "<<result>>"));
                return e + "<<result>>";
            }).collect(Collectors.toList());
            return collect;
        }
    }
}
