package letcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1431 {


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int len = candies.length;
        int max=Arrays.stream(candies).max().getAsInt();
        List<Boolean> results = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Boolean result = candies[i] + extraCandies >= max;
            results.add(result);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1431().kidsWithCandies(new int[]{2,3,5,1,3},3));
    }

}
