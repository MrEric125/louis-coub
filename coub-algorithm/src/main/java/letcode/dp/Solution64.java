package letcode.dp;

public class Solution64 {


    /**
     * dp[m-1][n-1]=grid[m-1][n-1]+min(dp[m-1][n-2],dp[m-2][n-1])
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution64 solution63 = new Solution64();
        int[][] grad = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution63.minPathSum(grad));

    }
}
