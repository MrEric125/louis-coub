package letcode;

import java.util.HashMap;
import java.util.Map;

public class Solution2789 {

    public long maxArrayValue(int[] nums) {

        int len = nums.length;

        Map<Integer, Boolean> cache = new HashMap<>();

        int max = 0;

        for (int i = 0; i < len-1; i++) {

            if (nums[i] <= nums[i + 1]) {
                max = nums[i + 1] = nums[i + 1] + nums[i];
            }

        }

        int i = 0, j = len - 1;

        while (i < j) {
            if (nums[i] <= nums[i + 1]) {
                max = nums[i + 1] = nums[i + 1] + nums[i];
                i++;
            }else {


            }
        }
        return 0L;
    }
}
