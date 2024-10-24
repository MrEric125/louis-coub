package letcode.string;


/**
 *
 * https://leetcode.cn/problems/greatest-common-divisor-of-strings/solutions/143956/zi-fu-chuan-de-zui-da-gong-yin-zi-by-leetcode-solu/?envType=study-plan-v2&envId=leetcode-75
 *
 * 既能被第一个数整除，也能被第二个数整除，其实就是求的公约数，
 *
 * 求的是最大字符串，也就是求最大公约数
 */
class Solution1071 {
    public String gcdOfStrings(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();
        for (int i = Math.min(len1,len2); i >0 ; i--) {
            if (len1 % i == 0 && len2 % i == 0) {
                String X = str1.substring(0, i);
                // 如果str1 是由多个X 组成，并且str2 也是由多个X组成，
                if ((X + str1).equals(str1 + X) && (X + str2).equals(str2 + X)) {
                    return X;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Solution1071().gcdOfStrings("ABCABC","ABC"));
    }


}