package com.luois.test;

import java.util.Arrays;

/**
 * @author louis
 * <p>
 * Date: 2019/11/1
 * Description:
 *  -XX:+UseG1GC
 *  -XX:InitialHeapSize=266562496 -XX:MaxHeapSize=4264999936 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
 */
public class JVMTest {
    public static void main(String[] args) {
        Arrays.asList(args).forEach(System.out::println);
        long a = 4271011840L;
        double b = (a / 1024 )/ 1024;
        System.out.println(b);


    }
}
