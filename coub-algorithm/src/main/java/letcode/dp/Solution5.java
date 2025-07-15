package letcode.dp;

public class Solution5 {


    public String longestPalindrome(String s) {
        int max = 0;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int expanded = expandAroundCenter(s, i, i);
            max = Math.max(max, expanded);
            if (expanded > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {

        System.out.println(new Solution5().longestPalindrome("bab"));

    }


}
