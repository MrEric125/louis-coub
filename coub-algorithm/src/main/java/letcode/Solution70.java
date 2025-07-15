package letcode;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

public class Solution70 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs2(int n) {
        Map<Integer, Integer> calculate = Maps.newHashMap();

        int result = climbStairsByCache(n, calculate);

        System.out.println(calculate);

        return result;
    }

    public int climbStairsByCache(int n, Map<Integer, Integer> memo) {
        Integer result = memo.get(n);
        if (Objects.nonNull(result)) {
            return result;
        }

        if (n == 1) {
            memo.put(1, 1);
            return 1;
        }
        if (n == 2) {
            memo.put(2, 2);
            return 2;
        }
        int result1 = climbStairsByCache(n - 1, memo);
        memo.put(n - 1, result1);
        int result2 = climbStairsByCache(n - 2, memo);
        memo.put(n - 2, result1);
        return result2 + result1;
    }

    public static void main(String[] args) {
        Solution70 solution70 = new Solution70();
        long s1 = System.currentTimeMillis();

        System.out.println(solution70.climbStairs(45));
        long r1 = System.currentTimeMillis();
        System.out.println("耗時：" + (r1 - s1));
        long s2 = System.currentTimeMillis();
        System.out.println(solution70.climbStairs2(45));
        long r2 = System.currentTimeMillis();
        System.out.println("耗時：" + (r2 - s2));
    }
}
