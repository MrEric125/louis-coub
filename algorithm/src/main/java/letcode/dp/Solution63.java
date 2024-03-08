package letcode.dp;

import com.alibaba.fastjson2.JSON;

import java.util.Arrays;

public class Solution63 {

    /**
     * 如果这里面只有一个障碍物，则与62题基本相似,但是需要考虑跳过有障碍物的那个路线，解法如下
     *
     * 但事实是可能有多个障碍物，并且这些障碍物可能会连成一条线，导致动态规划没法往前溯源
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        // 两个特殊情况，如果障碍在目标位置
//        if (obstacleGrid[m - 1][n - 1] == 1) {
//            return 0;
//        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        dp[0][0] = 1;
        // 第一列
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                continue;
            }
            dp[i][0] = dp[i - 1][0];
        }
        // 第一行
        for (int i = 1; i < n; i++) {
            // 如果这个位置上有障碍，那么这条路走不通，后面这条路为必经之路的地方也走不通
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                continue;
            }
            dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Solution63 solution63 = new Solution63();

        String[] split = args[0].split("],\\[");

        int[][] obstacleGrid = JSON.parseObject(args[0], int[][].class);

//        int[][] obstacleGrid = {{0, 1, 0,0}, {0,1, 0, 0}, {0,1,0, 0, 0}};
        System.out.println(solution63.uniquePathsWithObstacles1(obstacleGrid));

    }
}
