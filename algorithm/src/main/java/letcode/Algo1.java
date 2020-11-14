package letcode;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/11/5
 * description:
 */
public class Algo1 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 9, 15};
        int[] ints = twoSum(nums, 17);
        Arrays.stream(ints).forEach(System.out::println);

    }
    public static int[] twoSum(int[] nums, int target) {
        int[] backNum = new int[2];
        int len=nums.length;
        for (int i = 0; i < len; i++) {
            int a = nums[i];
            for (int j = i+1; j < len; j++) {
                int b = nums[j];
                if (a + b == target) {
                    backNum= new int[]{i, j};

                }

            }
        }
        return backNum;



    }
}
