package com.algorithm;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author John·Louis
 * @date create in 2019/11/5
 * description:
 */
public class Solution {

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(2);
        b.next = a;
        c.next = b;
        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);
        e.next = new ListNode(4);
        d.next = e;


        ListNode listNode = addTwoNumbers(c, d);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;

    }

    @Test
    public void test1() {
        System.out.println(isAnagram("zhangsan", "sanzhango"));
    }
    public boolean isAnagram(String s, String t) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        char[] chart = t.toCharArray();
        Arrays.sort(chart);
        System.out.println();

        return new String(chars).equals(new String(chart));
    }
    public int[][] merge(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int[] ints = intervals[i + 1];
            if (interval[1] > ints[0]) {
                ints = new int[]{interval[0], ints[1]};
            }

        }
        return null;

    }

}

class ListNode {
       int val;

      ListNode next;

      ListNode(int x) { val = x; }

}
