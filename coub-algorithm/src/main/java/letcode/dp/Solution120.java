package letcode.dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/triangle/?envType=study-plan-v2&envId=dynamic-programming
 */
public class Solution120 {

    /**
     * 将不规则的三角形 转变成一个矩形
     * 设最后一行最小路径为 dp[i][n] 则可以表示为
     * dp[i][n]=num[i][n]+min{dp[i-1][n-1],dp[i][n-1]}
     *
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {


        // row
        int n = triangle.size();
        // column
        int m = triangle.get(triangle.size() - 1).size();

        int[][] dp = new int[n][m];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < m; j++) {

                // 逻辑很清晰，主要就是确认边界问题
                if (j == 0) {
                    dp[i - 1][0] = triangle.get(i - 1).get(0) + dp[i - 2][0];
                    continue;
                }

                int preRowSize = triangle.get(i - 2).size();
                if (j == preRowSize) {
                    dp[i - 1][j] = triangle.get(i - 1).get(j) + dp[i - 2][j - 1];
                    break;
                }

                dp[i - 1][j] = triangle.get(i - 1).get(j) + Math.min(dp[i - 2][j - 1], dp[i - 2][j]);
            }

        }
        int result = dp[n - 1][0];
        for (int i = 1; i < m; i++) {
            result = Math.min(dp[n - 1][i], result);
        }
        return result;
    }


    public static void main(String[] args) {
        Solution120 solution120 = new Solution120();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Lists.newArrayList(2));
        triangle.add(Lists.newArrayList(3, 4));
        triangle.add(Lists.newArrayList(6, 5, 7));
        triangle.add(Lists.newArrayList(4, 1, 8, 3));
        System.out.println(solution120.minimumTotal1(triangle));
    }
}
