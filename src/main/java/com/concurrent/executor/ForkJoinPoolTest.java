package com.concurrent.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author louis
 * <p>
 * Date: 2019/8/26
 * Description:
 */
public class ForkJoinPoolTest {


    public static void main(String[] args) {
        PrintTask task = new PrintTask(0, 300);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(task);
        pool.awaitQuiescence(2, TimeUnit.SECONDS);
        pool.shutdown();

    }

}

class Calculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = -1812835340478767238L;

    private long start;
    private long end;

    private static final long THURSHOLD = 10000L;  //临界值

    public Calculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        //小于临界值，则不进行拆分，直接计算初始值到结束值之间所有数之和
        if(length <= THURSHOLD){
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }else{  //大于临界值，取中间值进行拆分，递归调用
            long middle = (start + end) / 2;

            Calculate left = new Calculate(start, middle);
            left.fork(); //进行拆分，同时压入线程队列

            Calculate right = new Calculate(middle+1, end);
            right.fork(); //

            return left.join() + right.join();
        }
    }
}

class PrintTask extends RecursiveAction{

    private static final int THRESHOLD = 50;

    private int start;

    private int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < THRESHOLD){
            for(int i=start;i<end;i++){
                System.out.println(Thread.currentThread().getName()+"的i值："+i);
            }
        }else {
            int middle =(start+end)/2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }

    }
}
