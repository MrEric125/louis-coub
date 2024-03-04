package letcode.dp;

import java.util.HashMap;
import java.util.Map;

public class Solution1137 {

    public int tribonacciCache(int n, Map<Integer,Integer> cache) {
        if (n <=1) {
            cache.put(n, n);
            return n;
        }
        if (n == 2) {
            int i = tribonacciCache(n - 1, cache);
            cache.put(n, i);
            return i;
        }
        int i = tribonacciCache(n - 3, cache) + tribonacciCache(n - 2, cache) + tribonacciCache(n - 1, cache);
//        cache.put(n, i);
        return i;
    }

    /**
     * 这种解法，结果其实是满足的，但是时间复杂度超过题目要求
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        Map<Integer, Integer> cache = new HashMap<>();

        return tribonacciCache(n, cache);
    }

    public static void main(String[] args) {
        Solution1137 solution1137 = new Solution1137();
        long l = System.currentTimeMillis();
        System.out.println(solution1137.tribonacci(1));
        System.out.println(System.currentTimeMillis() - l);
        long l2 = System.currentTimeMillis();
        System.out.println(solution1137.tribonacci2(1));
        System.out.println(System.currentTimeMillis() - l2);
    }

    public int tribonacci2(int n) {
        int[] result =  new int[n+3];
        result[0] = 0;
        result[1] = 1;
        result[2] = 1;
        if (n <= 2) {
            return result[n];
        }
        for (int i = 3; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
        }
        return result[n];
    }
}
