package letcode;

import org.junit.Test;

/**
 * @author louis
 * <p>
 * Date: 2019/12/5
 * Description:
 */
public class Solution852 {

    @Test
    public void test() {
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2};


        System.out.println(peakIndexInMountainArray(A));
    }

    private int peakIndexInMountainArray(int[] A) {
        int length = A.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = (left + right ) >>> 1;
//           如果数据很大，刚好中位数就是需要寻找的，那么可以一次命中
            if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
                return mid;
            }else if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private int peakIndexInMountainArray2(int[] A) {
        int flag = 0;
        while (A[flag] < A[flag + 1]) {
            flag++;
        }
        return flag;
    }
}
