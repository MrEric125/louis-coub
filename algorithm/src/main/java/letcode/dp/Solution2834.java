package letcode.dp;

import java.util.Arrays;

public class Solution2834 {


    /**
     * 求解是要求取最小数据和： 也就是  num[i]+num[j] 最小
     *
     * 这样解其实有问题
     *
     * 条件： num[i]+num[j]!=target
     * @param n
     * @param target
     * @return
     */
    public int minimumPossibleSum(int n, int target) {
        // 定义一个有序数组，这个数组由不同的数组组成
        int[] nums = new int[n];
        if (n > 1 && target == 1) {
            return 0;
        }
//        for (int i = 0; i < n; i++) {
//            nums[i] = i + 1;
//        }
        int max = n,j = n - 1,i = 0, sum = 0;
        nums[0] = 1;
        if (n == 1) {
            return nums[0];
        }

        while (i < j) {
            nums[i] = i + 1;
            nums[j] = j + 1;

            if (nums[i] + nums[j] == target) {
                nums[j] = max = max + 1;
//                sum = sum + nums[j];
                j--;
                continue;
            }
            if (nums[i] + nums[j] > target) {
//                sum = sum + nums[j];
                j--;
                continue;
            }
            if (nums[i] + nums[j] < target) {
//                sum = sum + nums[i];
                i++;
            }

        }
        for (int k = 0; k < n; k++) {
            sum = sum + nums[k];
            sum = sum % ((int) Math.pow(10, 9) + 7);

        }
        return sum;
    }

    public static void main(String[] args) {
        Solution2834 solution2834 = new Solution2834();
        //162
        System.out.println(solution2834.minimumPossibleSum(37,46));
    }
}
