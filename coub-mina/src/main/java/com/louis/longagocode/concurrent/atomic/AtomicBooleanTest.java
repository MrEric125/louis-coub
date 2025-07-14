package com.louis.longagocode.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.*;

/**
 * @author louis
 * <p>
 * Date: 2019/9/2
 * Description:
 * 将第三个参数和第一个参数作对比，如果相等就将value  更新到第四个参数
 * unsafe.compareAndSwapInt(this, valueOffset, e, u); 操作成功返回true 否则返回false
 */
@Slf4j
public class AtomicBooleanTest {

    private static AtomicBoolean atomicBoolean = new AtomicBoolean();

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static AtomicReference<String> atomicReference = new AtomicReference<>();
    private static int initNum = 0;

    private static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("test4", initNum);

    private static AtomicMarkableReference<String> markableReference = new AtomicMarkableReference<>("test7", false);

    public static void main(String[] args) {
        boolean andSet = atomicBoolean.getAndSet(true);
//       通过自旋的方式调用compareAndSwapInt(var1, var2, var5, var5 + var4)
        int andIncrement = atomicInteger.getAndIncrement();
        atomicReference.set("test1");
        String test2 = atomicReference.getAndSet("test2");
        String test3 = atomicReference.getAndSet("test3");
        boolean b = stampedReference.compareAndSet("test4", "test5", initNum, ++initNum);
        log.info("num====>{}", initNum);
        boolean b2 = stampedReference.compareAndSet("test5", "test6", initNum, ++initNum);

        boolean b1 = markableReference.compareAndSet("test8", "test8", false, true);
        boolean marked = markableReference.isMarked();
        log.info("boolean==>{}", b);
        log.info("boolean2==>{}", b2);

    }

}
