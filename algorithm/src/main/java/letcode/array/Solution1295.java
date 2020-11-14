package letcode.array;

/**
 * @author louis
 * @Date 2020/1/15
 * description:
 */
public class Solution1295 {

    public static void main(String[] args) {

    }
    public int findNumbers(int[] nums) {
        int length = nums.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            int tmp = nums[i];
            int size = 0;
            while (tmp >= 1) {
                tmp /= 10;
                size++;
            }
            if (size % 2 == 0) {
                count++;
            }

        }
        return count;

    }

}
