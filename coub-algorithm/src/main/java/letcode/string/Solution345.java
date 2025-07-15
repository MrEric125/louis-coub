package letcode.string;

import java.util.ArrayList;
import java.util.List;

public class Solution345 {

    public String reverseVowels(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        List<Character> e = new ArrayList<>();
        e.add('a');
        e.add('e');
        e.add('i');
        e.add('o');
        e.add('u');
        e.add('A');
        e.add('E');
        e.add('I');
        e.add('O');
        e.add('U');
        int i = 0, j = len - 1;
        while (i < j) {
            char at = charArray[i];
            if (!e.contains(at)) {
                i++;
                continue;
            }
            if (!e.contains(charArray[j])) {
                j--;
                continue;
            }
            char tmp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = tmp;
            i++;
            j--;
        }
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        System.out.println(new Solution345().reverseVowels("leetcode"));
    }

}
