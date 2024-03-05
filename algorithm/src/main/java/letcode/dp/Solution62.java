package letcode.dp;

public class Solution62 {


    /**
     * 之前做的动态规划都是线性的，都是可以用一维数组表示，本条路线优化是二维的，
     * 需要用二维数组表示
     * dp[i][j]=dp[i-1][j]+dp[i][j-1]
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
//        之前做的动态规划都是线性的，都是可以用一维数组表示，本条路线优化是二维的，
        int[][] dp=new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Solution62 solution62 = new Solution62();
        System.out.println(solution62.uniquePaths(3, 7));
    }
}
