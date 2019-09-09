package com.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author louis
 * <p>
 * Date: 2019/8/27
 * Description:
 */
public class CompletableFutureTest {


    @Test
    public void thenApplyExample() {
        CompletableFuture<String> message = CompletableFuture.completedFuture(message(()->"zz"))
                .thenApply(String::toUpperCase);
        System.out.println(message.join());
        CompletableFuture completableFuture = new CompletableFuture();

        String join = CompletableFuture
                .supplyAsync(()->"method")
                .whenComplete((x, y) -> System.out.println(x.substring(1)))
                .join();
        System.out.println(join);


    }

    public String message(Supplier<String> a) {
        return "message" + method();
    }
    private String method() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "method";
    }
}
