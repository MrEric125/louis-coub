package com.codeeffective.threadlocal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author louis
 * @Date 2020/1/15
 * description:
 */
public class CSGameByThreadLocal {
    private static final Integer Bullet_Number = 1500;

    private static final Integer Killed_ENEMIES = 0;

    private static final Integer Life_value = 10;

    private static final Integer total_players = 10;

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final ThreadLocal<Integer> bullet_number_threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Bullet_Number;
        }
    };

    private static final ThreadLocal<Integer> killed_enemies_threadlocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Killed_ENEMIES;
        }
    };
}
