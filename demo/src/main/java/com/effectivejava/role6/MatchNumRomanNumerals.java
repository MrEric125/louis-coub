package com.effectivejava.role6;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 */
@Slf4j
public class MatchNumRomanNumerals {

    /**
     *  performace can be greatly improved!
     *
     * @param s
     * @return
     */
    static boolean isRomanNumeral(String s) {
//        matches可能会有一定的性能问题
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" +
                "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    }

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" +
            "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeral2(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        Long sum1=sum();
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        long sum2=sum2();
        long end2 = System.currentTimeMillis();
        log.info("message sum 1: sum={}; totalTime:{}", sum1, end1 - start1);
        log.info("message sum 1: sum={}; totalTime:{}", sum2, end2 - start2);

    }

    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    private static long sum2() {
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
