package letcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> matchResult = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
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
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[k]);
                        matchResult.add(arr);
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(matchResult);
        return result;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.threeSum(new int[]{0,0,0}));
    }
}
