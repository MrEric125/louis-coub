package com.louis.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author louis
 * <p>
 * Date: 2019/8/5
 * Description:
 */
public class HystrixDemo extends HystrixCommand<String > {

    private String name;

    public HystrixDemo(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("demo"));
        this.name = name;
    }
    public static void main(String[] args) {
        HystrixCommand<String> hystrixCommand = new HystrixDemo("zhangsan");
        String execute = hystrixCommand.execute();

        System.out.println(execute);
    }

    @Override
    protected String run() throws Exception {
        return "hello  "+name+"!";
    }
}
