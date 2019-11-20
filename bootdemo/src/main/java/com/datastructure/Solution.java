package com.datastructure;

import org.junit.Test;

/**
 * @author louis
 * <p>
 * Date: 2019/11/12
 * Description:
 */
public class Solution {
    public static void main(String[] args) {
        int[] muns = new int[]{1, 3, 5, 7, 9};
        System.out.println(searchInsert2(muns, 2));
    }

    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public static int searchInsert2(int[] nums, int target) {
        int length = nums.length;
        if (nums[length - 1] < target) {
            return length;
        }
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }

    public int mySqrt(int x) {
        long left = 0;
        long right = (x >>> 1) + 1;
        while (left < right) {
//            这个地方的+1操作非常重要，否则(3+4)>>>1还是为3会陷入死循环
            long mid = (left + right + 1) >>> 1;
            long tmpSeq = mid * mid;
            if (tmpSeq > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

    public boolean isPerfectSquare(int num)  {
        long left = 0;
        long right = (num >>> 1) + 1;
        while (left <= right) {
//            这个地方的+1操作非常重要，否则(3+4)>>>1还是为3会陷入死循环
            long mid = (left + right + 1) >>> 1;
            long tmpSeq = mid * mid;
            if (tmpSeq < num) {
                left = mid + 1;
            } else if (tmpSeq == num) {
                return true;
            } else if (tmpSeq > num) {
                right = mid - 1;
            }
        }
//        return (int) left;
        return false;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;


        int left = 0;
        int right = len-1 ;
        while (left < right) {
            int mid = (left + right+1) >>> 1;
            if (letters[mid]<=target)
                left = mid + 1;
            else right = mid;
        }
        return letters[left % len];
    }

    /**
     * 既然是两个数字相加得target那么只有这么一种情况，一个比target/2大，一个比target/2小（题目中已经排除了不能重复使用元素）
     * 那么就是从这两个子数组中找元素[lo,index[target/2]),[index[target/2],index[target]]
     * 所依我们首先需要找到index[target/2]和index[target]
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum==target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }

        return new int[]{-1, -1};
    }

    public int firstBadVersion(int n) {
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = (lo + hi + 1) >>> 1;
            if (isBadVersion(mid)) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }
        return lo;

    }

    public boolean isBadVersion(int version) {
        boolean[] booleans = new boolean[]{true};
//        boolean[] booleans = new boolean[]{false, false,false, true, true, true,true,true,true};
        return booleans[version - 1];
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    @Test
    public void test() {
//        System.out.println(mySqrt(2147395599));
//        System.out.println(isPerfectSquare(100));
//        char[] letters = new char[]{'a', 'c', 'f', 'g', 'k'};
//        System.out.println(nextGreatestLetter(letters, 'c'));
//        int i = firstBadVersion(1);
//        System.out.println(i);
        int reverse = reverse(123456);
        System.out.println(reverse);

    }

}
