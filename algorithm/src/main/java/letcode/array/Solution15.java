package letcode.array;

import java.util.*;

/**
 * https://leetcode.cn/problems/3sum/
 * 1. nums 中的元素可以重复利用
 * 2. 子数组(排序后)本身不能重复
 */
public class Solution15 {


    /**
     * 三数之和，暴力求解 时间复杂度为O(n^3 + nLog(n)) 提交代码超时
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        nums = Arrays.stream(nums).sorted().toArray();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                break;
            }
            for (int j = 0; j < nums.length; j++) {
                if(i == j){
                    continue;
                }
                for (int k = 0; k < nums.length; k++) {
                    if(j == k || i == k ){
                        continue;
                    }
                    if (  nums[i] + nums[j] + nums[k] == 0  ) {
                        List<Integer> arr = new ArrayList<>();
                        if (nums[j] < nums[i]) {
                            continue;
                        }
                        if (nums[k] < nums[j]){
                            continue;

                        }
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[k]);
                        if (result.contains(arr)) {
                            continue;
                        }
                        result.add(arr);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 这个解法也是用了双指针，但是提交还是提示超时了
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len-2; i++) {
            if (nums[i]>0) {
                return result;
            }
            int first = nums[i];
            int preIdex = i + 1;

            // 如果当前位置作为首位，但是当前位置在上一次出现过，可能出现重复计算的问题，需要将这条数据排除
            if (i > 0 && first == nums[i - 1]) {
                continue;
            }
            for (int j = len-1; j > preIdex;) {
                int sum = first + nums[preIdex] + nums[j];
                if (sum < 0) {
                    preIdex++;
                    if (preIdex >= j) {
                        break;
                    }
                    continue;
                }
                if (sum > 0) {
                    j--;
                    if (preIdex >= j) {
                        break;
                    }
                    continue;
                }

                // 以下判断为满足条件，但是数据去重操作
                // preIndex: 是我们要找的第二位数，如果num[preIndex]=nums[preIndex+1];避免重复计算那么我们就直接用num[preIndex+1]
                while (j>preIdex &&  nums[preIdex]==nums[preIdex+1]){
                    preIdex++;
                }
//                避免重复计算那么我们就直接用num[j - 1]
                while (j>preIdex && nums[j] == nums[j - 1] ){
                    j--;
                }
                List<Integer> arr = new ArrayList<>();
                arr.add(first);
                arr.add(nums[preIdex]);
                arr.add(nums[j]);
                result.add(arr);

                j--;
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        String[] split = args[0].split(",");
        long s2 = System.currentTimeMillis();
//        System.out.println(solution15.threeSum3(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(solution15.threeSum2(new int[]{0,0,0,0}));
        long e2 = System.currentTimeMillis();
        System.out.println("2-cost time" + (e2 - s2));

    }


//    public static void main(String[] args) {
//        Solution15 solution15 = new Solution15();
//        String[] split = args[0].split(",");
//        int[] a1 = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
//        int[] a2 = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
//        int[] a3 = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
//        long start = System.currentTimeMillis();
//        System.out.println(solution15.threeSum2(a1));
//        long end1 = System.currentTimeMillis();
//
//        long s2 = System.currentTimeMillis();
//        System.out.println(solution15.threeSum3(a2));
//        long e2 = System.currentTimeMillis();
//        System.out.println("my-cost time" + (end1 - start) + ";2-cost time" + (e2 - s2));
//
//    }
}
