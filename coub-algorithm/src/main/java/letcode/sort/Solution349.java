package letcode.sort;

import java.util.HashSet;

/**
 * @author louis
 * @Date 2020/1/15
 * description:
 */
public class Solution349 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }
        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int ids = 0;
        for (Integer integer : set1)
            output[ids++] = integer;
        return output;


    }

    public static void main(String[] args) {

    }
}
