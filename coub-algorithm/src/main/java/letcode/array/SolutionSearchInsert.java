package letcode.array;

public class SolutionSearchInsert {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 3, 4, 8};
        int target = 5;
        SolutionSearchInsert ssi = new SolutionSearchInsert();
        int result = ssi.searchInsert(nums, target);
        System.err.println(result);

    }

    /**
     * 二分法解决方案
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = (right - left) / 2 + left;
            if (target == nums[middle]) {
                return middle;
            }
            //todo 新的数据集必须不能包含原来那个中间值
            if (target > nums[middle]) {
                left = middle+1;
            }
            if (target < nums[middle]) {
                right = middle-1;
            }
        }
        return left;

    }
}
