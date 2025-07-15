package letcode.dp;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.cn/problems/house-robber/description/?envType=study-plan-v2&envId=dynamic-programming
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 解题思路
 * 定义子问题
 * 写出子问题的递推关系
 * 确定 DP 数组的计算顺序
 * 空间优化（可选）
 * <p>
 * 取到达最后一位结果与之前一位结果比较，取最大值
 * <p>
 * f(n)=Max(f(n-2)+nums[n-1],f(n-1))
 * n最大值为nums.length+1
 * 动态规划为题解决方案，注意边界问题
 */
public class Solution198 {

    public int rob3(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        int dubblePre = 0, pre = 0, cu = 0;
        for (int i = 0; i < len; i++) {
            cu = Math.max(nums[i] + dubblePre, pre);
            dubblePre = pre;
            pre = cu;
        }
        return cu;
    }

    public int rob(int[] nums) {
        int len = nums.length;


        return rob(len, nums);
    }

    private int rob(int n, int[] nums) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
//        Max(f(n-2)+nums[n-1],f(n-1))
        return Math.max(nums[n - 1] + rob(n - 2, nums), rob(n - 1, nums));
    }

    public int rob2(int[] nums) {
        int len = nums.length;

        Map<Integer, Integer> cache = new HashMap<>();


        return rob2(len, nums, cache);
    }

    private int rob2(int n, int[] nums, Map<Integer, Integer> cache) {
        Integer result = cache.get(n);
        if (Objects.nonNull(result)) {
            return result;
        }
        if (n == 0) {
            cache.put(n, n);
            return 0;
        }
        if (n == 1) {
            cache.put(n, nums[0]);
            return nums[0];
        }
//        Max(f(n-2)+nums[n-1],f(n-1))
        int max = Math.max(nums[n - 1] + rob2(n - 2, nums, cache), rob2(n - 1, nums, cache));
        cache.put(n, max);
        return max;
    }


    public static void main(String[] args) {
        Solution198 solution198 = new Solution198();
        System.out.println(solution198.rob3(new int[]{1, 2, 3, 1}));
    }

}
