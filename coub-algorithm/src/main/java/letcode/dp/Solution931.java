package letcode.dp;

public class Solution931 {

    public int minFallingPathSum(int[][] matrix) {

        int row= matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        System.arraycopy(matrix[0], 0, dp[0], 0, col);
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int  min = 0;
                    // upLeft
                    min=dp[i - 1][j];
                    if (j - 1 >= 0) {
                        min = Math.min(min, dp[i - 1][j - 1]);
                    }
                    // 寻找upRight
                    if (j + 1 < col) {
                        min = Math.min(min, dp[i - 1][j + 1]);
                    }
                dp[i][j] = matrix[i][j] + min;
            }
        }
        int min = dp[row-1][0];

        for (int i = 1; i < col; i++) {
            min = Math.min(min, dp[row - 1][i]);
        }
        return min;
    }

    public static void main(String[] args) {
        Solution931 solution931 = new Solution931();
        int[][] nums = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(solution931.minFallingPathSum(nums));
    }


}
