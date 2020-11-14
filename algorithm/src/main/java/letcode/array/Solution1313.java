package letcode.array;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author louis
 * @Date 2020/1/15
 * description:
 */
public class Solution1313 {

    public static int[] decompressRLElist(int[] nums) {
        int length = nums.length;
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (2 * i + 1 < length) {
                int a = nums[2 * i], b = nums[2 * i + 1];
                for (int j = 0; j < a; j++) {
                    numList.add(b);
                }
            }
        }
        int[] numArray = new int[numList.size()];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = numList.get(i);
        }
        return numArray;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Arrays.stream(decompressRLElist(nums)).forEach(System.out::println);

    }
}
