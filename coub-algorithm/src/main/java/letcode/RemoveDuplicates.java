package letcode;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/11/5
 * description:
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3, 3, 5, 5, 8};
        int[] ints = removeDuplicates(nums);
        Arrays.stream(ints).forEach(System.out::println);

    }

    private static int[] removeDuplicates(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i + 1] >= nums[i] && nums[j - 1] != nums[i]) {
                nums[j++] = nums[i];
            }
            if (i == nums.length - 1 && nums[i - 1] < nums[i] && nums[j - 1] != nums[i]) {
                nums[j++] = nums[i];
            }
        }
        return nums;
    }
}
