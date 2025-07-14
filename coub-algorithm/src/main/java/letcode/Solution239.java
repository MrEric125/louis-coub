package letcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution239 {

    public static void main(String[] args) {
        Solution239 solution239 = new Solution239();

//        int[] window = solution239.maxSlidingWindow1(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        int[] window = solution239.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 2);
        for (int i : window) {
            System.out.println(i);
        }
    }

    /**
     * 时间复杂度稳定在O(n)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k){

        /**
         * 这个优先队列不能直接存int值，是因为当狂口往后面滑动的时候，是需要将窗口外的数据移除
         */
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                // 如果新入队的数据和上一个队列中最大数据一样大，那么将后面异位数据放在队列最前面
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        int n = nums.length;
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});

        }

        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;




    }

    /**
     * 时间复杂度：最坏的情况下，是O(n^2) 最好的情况下是O(n)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int firstMax = nums[0];
        int len = nums.length;
        int[] result = new int[len - k + 1];
        for (int i = 0; i < k; i++) {
            firstMax = Math.max(nums[i], firstMax);
        }
        result[0] = firstMax;

        for (int i = k; i < len; i++) {
            if (k == 1) {
                result[i - k + 1] = nums[i - k + 1];
                continue;

            }
            int currentMax = result[i - k];

            int upIndex = i - k;
            if (nums[i] > currentMax) {
                result[i - k + 1] = nums[i];
            } else if (currentMax > nums[upIndex]) {
                result[i - k + 1] = currentMax;
            } else {
                //上一位数据==max
                int compareMax = nums[i];
                for (int j = i - k + 1; j < i + 1; j++) {
                    compareMax = Math.max(compareMax, nums[j]);

                }
                result[i - k + 1] = compareMax;
            }

        }

        return result;


    }

    /**
     * 这种时间复杂度为n^3
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;

        int[] result = new int[len - k + 1];

        for (int i = 0; i < len - k + 1; i++) {

            int[] subArr = new int[k];

            for (int j = i; j < k + i; j++) {
                    subArr[j - i] = nums[j];
            }
            int maxValue = findMaxValue(subArr);

            result[i] = maxValue;

        }
        return result;
    }


    /**
     * 首先将一个大数组拆分为多个小数组，
     * 最后的问问题就是寻找这个小数组中的最大值
     *
     * @param subArr
     * @return
     */
    public int findMaxValue(int[] subArr) {

        int max = subArr[0];

        for (int i = 0; i < subArr.length; i++) {
            max = Math.max(subArr[i], max);
        }
        return max;
    }
}
