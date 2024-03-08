package letcode.dp;

/**
 * https://leetcode.cn/problems/delete-and-earn/solutions/758416/shan-chu-bing-huo-de-dian-shu-by-leetcod-x1pu/
 * 关键点：
 * 1.删除nums[i] 并获取点数，
 * 2.之后你必须删除所有 num[i]-1 和nums[i]+1 ,同时无法获取num[i]-1 和nums[i]+1的点数
 * 求解过程：
 *  如果只是暴力求解，效率非常低，需要比较数值大小，还需要删除数组位置那么
 * 观察 num[i]-1,num[i]，nums[i]+1, 有什么规律
 * 结果是递增的，
 * 设num[i]==a,a出现了x次，那么删除nun[i] 获取的点数就是 x*a
 *a-1 出现了 y次
 * a+1 出现了z次
 * 最大点数 Max{(a-1)*y+(a+1)*z,x*a}
 * 递增则可以将其转换为数组下标，这个数组定义为一个对应下标位置上的数据之和 sum[i]
 * 则最后结果可以表示为f(n)=Max(f(n-2)+sum[n-1],f(n-1))
 *
 */
public class Solution740 {

    public int deleteAndEarn(int[] nums) {

        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        // 能出现多少组数字
        int[] sum = new int[maxVal + 1];
        for (int val : nums) {
            sum[val] += val;
        }
        Solution198 solution198 = new Solution198();
        return solution198.rob3(sum);

    }

    public static void main(String[] args) {
        Solution740 solution740 = new Solution740();
        System.out.println(solution740.deleteAndEarn(new int[]{2,2,3,3,3,4}));
    }
}
