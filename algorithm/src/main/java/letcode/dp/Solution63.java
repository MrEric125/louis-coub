package letcode.dp;

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
        // 两个特殊情况
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i - 1][j] == 1) {
                    dp[i - 1][j] = 0;
                }
                if (obstacleGrid[i][j - 1] == 1) {
                    dp[i][j - 1] = 0;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Solution63 solution63 = new Solution63();
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(solution63.uniquePathsWithObstacles1(obstacleGrid));

    }
}
