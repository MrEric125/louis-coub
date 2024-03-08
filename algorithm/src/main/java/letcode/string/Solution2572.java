package letcode.string;

import com.alibaba.fastjson.JSON;

public class Solution2572 {

    /**
     * 1 <= n <= 10^5
     * word.length == n
     * word 由数字 0 到 9 组成
     * 1 <= m <= 10^9
     * @param word
     * @param m
     * @return
     */
    public int[] divisibilityArray(String word, int m) {
        long remain = 0;
        int length = word.length();
        int[] div = new int[length];

        for (int i = 0; i < length; i++) {
            int c = Integer.valueOf(word.substring(i, i + 1));
            remain = (remain * 10 + c) % m;
//            String subString = word.substring(pre, i);
            // 小数子可以这么操作，数字大了就操作不了了
            if (remain==0) {
                div[i] = 1;
            }else {
                div[i] = 0;
            }
        }
        return div;
    }

    public static void main(String[] args) {
        Solution2572 solution2572 = new Solution2572();
        System.out.println(JSON.toJSONString(solution2572.divisibilityArray("8917171717276217174131", 17)));
    }
}
