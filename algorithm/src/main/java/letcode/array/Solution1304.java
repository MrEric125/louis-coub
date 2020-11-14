package letcode.array;

import java.util.Arrays;

/**
 * @author louis
 * @Date 2020/1/15
 * description:
 */
public class Solution1304 {

    public static int[] sumZero(int n) {
        int[] ans = new int[n];
        int index = 0;
        int length = n / 2;
        for (int i = 0; i <length; i++) {
//            ans[index++] = -i;
//            ans[index++] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        Arrays.stream(sumZero(5)).forEach(System.out::println);

    }

}
