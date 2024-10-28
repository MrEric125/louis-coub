package letcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/12/3
 * Description:给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度:
 * 不含有重复字符： 字符唯一性
 */
public class Solution3 {


    @Test
    public void test() {

        int i = lengthOfLongestSubstring2("abcdaabcdaabcda");
        System.out.println(i);

    }


    // todo 滑动窗口
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                int temp = map.get(s.charAt(i)) + 1;
                left = Math.max(left, temp);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;


    }
    public int lengthOfLongestSubstring2(String s) {
        int length = s.length();
        int max = 0;
        int middle = (length + 1) / 2;
        for (int i = 0; i < length; i++) {

            for (int j = i + 1; j < length; j++) {
                String subStr = s.substring(i, j);

                if (subStr.length() > middle) {
                    return max;
                }
                String compareStr = s.substring(j, s.length() - 1);
                System.out.println("subStr:" + subStr);
                System.out.println("compareStr:" + compareStr);
                if (compareStr.contains(subStr)) {
                    max = Math.max(max, subStr.length());
                }
            }
        }
        return max;

    }

}
