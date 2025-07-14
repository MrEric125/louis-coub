package letcode.dp;

/**
 * https://leetcode.cn/problems/maximal-square/?envType=study-plan-v2&envId=dynamic-programming
 */
public class Solution221 {


    /**
     * 要找到最大面积：
     * 如果直接算面积，其实不太好算，将面积的二维问题转换成 N*N 求N 的一位问题(正方向长宽相等)
     *
     * 找到符合正方形的规则是都为1，没有0；
     * 如果上一排，或者上一列为0，当前位置不为0； 则记录当前N=1
     * 如果上一排,或者上一列为1 但是当前位置为0； 则记录当前N=0
     * 如果上一排，或者上一列不为1;当前位置为1；则记录当前N=N+1
     *
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int row = matrix.length, col = matrix[0].length,max=0;

        int[][] dp = new int[row][col];

        // 找到第一行的最大行数
        for (int i = 0; i < col; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(dp[0][i], max);

        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int  min =dp[i - 1][j];
                // 第一列的最行
                if (j == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                }else {
                    if (matrix[i][j] == '1') {
                        min = Math.min(min,  dp[i][j-1]);
                        min = Math.min(min, dp[i - 1][j - 1]);
                        dp[i][j] = 1 + min;
                    }
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }

    public static void main(String[] args) {

//        char[][] matrix = new char[][]{{'1','0','1','0','0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] matrix = new char[][]{{'1'}};


//        char[][] matrix = JSON.parseObject(args[0], char[][].class);

        Solution221 solution221 = new Solution221();
        System.out.println(solution221.maximalSquare(matrix));

    }
}
