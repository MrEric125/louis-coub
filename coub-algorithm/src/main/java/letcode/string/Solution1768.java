package letcode.string;

class Solution1768 {

    /**
     * 时间复杂度为Min(m,n)
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        String tail = "";
        if (len2 > len1) {
            tail = word2.substring(len1, len2);
        } else if (len2 < len1) {
            tail = word1.substring(len2, len1);
        }
        int minLen = Math.min(len1, len2);
        String result = "";
        for (int i = 0; i < minLen; i++) {
            result = result + word1.charAt(i) + word2.charAt(i);
        }
        result = result + tail;
        return result;

    }

    /**
     * 双向指针优化
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        String tail = "";
        if (len2 > len1) {
            tail = word2.substring(len1, len2);
        } else if (len2 < len1) {
            tail = word1.substring(len2, len1);
        }
        int minLen = Math.min(len1, len2);
        String head = "";
        String middle = "";

        int j = minLen - 1;

        for (int i = 0; i < minLen; i++) {

            if (i <= j) {
                head = head + word1.charAt(i) + word2.charAt(i);
            }
            if (j - 1 > i) {
                middle = String.valueOf(word1.charAt(j)) + String.valueOf(word2.charAt(j)) + middle;
                j--;
            }
        }
        return head + middle + tail;
    }

    public static void main(String[] args) {
//        apbqcrds
        System.out.println(new Solution1768().mergeAlternately2("abcd", "pqrs"));
    }
}