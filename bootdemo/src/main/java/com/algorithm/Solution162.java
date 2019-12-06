package com.algorithm;

import org.junit.Test;

/**
 * @author louis
 * <p>
 * Date: 2019/12/5
 * Description:
 */
public class Solution162 {

    @Test
    public void test() {
        int[] nums = new int[]{3,2, 1};
        System.out.println(findPeakElement(nums));

    }
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        int lo = 0;
        int hi = length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }


}
