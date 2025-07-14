package com.louis.longagocode.concurrent.atomic;

import com.concurrent.User;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis
 * <p>
 * Date: 2019/8/20
 * Description:
 */
public class AbaDemo {

    private static User userA = new User("zhangsan", 11);
    private static User userB = new User("lisi", 21);
    private static AtomicStampedReference<User> reference = new AtomicStampedReference<>(userA, 0);
    private static final int stamp = 1;



    public static void main(String[] args) {
        ABA();
        System.out.println("===================================ABA优化之后================================");
        solveABA();


    }
    private static void ABA() {
        userA.setAge(21);
        boolean resultA = reference.attemptStamp(userA, stamp);
        System.out.println("判断是否是同一个对象"+resultA);

        boolean resultB = reference.compareAndSet(userA, userB, stamp, stamp);
        System.out.println("第一次执行结果"+resultB+"  current stamp："+reference.getStamp());
//        userA.setAge(41);
        boolean resultC = reference.compareAndSet(userB, userA, stamp, stamp);
        System.out.println("第二次执行结果"+resultC+"  current stamp："+reference.getStamp());
        boolean resultD = reference.compareAndSet(userA, userB, stamp, stamp);
        System.out.println("第二次执行结果"+resultD+"  current stamp："+reference.getStamp());
        System.out.println("实际最后age 值 为" + reference.getReference().getUsername());
    }

    private static void solveABA() {
        boolean resultA = reference.attemptStamp(userA, stamp);
        System.out.println("判断是否是同一个对象"+resultA);

        boolean resultB = reference.compareAndSet(userA, userB, reference.getStamp(), reference.getStamp()+1);
        System.out.println("第一次执行结果"+resultB+"  current stamp："+reference.getStamp());
//        userA.setAge(41);
        boolean resultC = reference.compareAndSet(userB, userA, reference.getStamp(), reference.getStamp()+1);
        System.out.println("第二次执行结果"+resultC+"  current stamp："+reference.getStamp());
        boolean resultD = reference.compareAndSet(userA, userB,stamp, reference.getStamp()+1);
        System.out.println("第三次执行结果"+resultD+"  current stamp："+reference.getStamp());

        System.out.println("实际最后age 值 为" + reference.getReference().getUsername());
    }
    public static void test5() {
        Lock lock = new ReentrantLock();

    }






}
