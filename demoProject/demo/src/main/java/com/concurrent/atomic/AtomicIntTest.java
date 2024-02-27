package com.concurrent.atomic;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author John·Louis
 * @date create in 2019/9/28
 * description:
 */
public class AtomicIntTest {
    private static AtomicInteger integer = new AtomicInteger(0);
    @Test
   public void test() {
        integer.getAndAdd(10);
        System.out.println(integer.get());

        int andSet = integer.getAndSet(5);

        System.out.println(integer.get());

    }
}
