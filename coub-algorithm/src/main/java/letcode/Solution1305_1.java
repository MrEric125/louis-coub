package letcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author John·Louis
 * @date create in 2020/1/12
 * description:
 * <p>
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * <p>
 * 一下为示例
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * <p>
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * 输出：[-10,0,0,1,2,5,7,10]
 * <p>
 * 输入：root1 = [], root2 = [5,1,7,0,2]
 * 输出：[0,1,2,5,7]
 * <p>
 * 输入：root1 = [0,-10,10], root2 = []
 * 输出：[-10,0,10]
 */
public class Solution1305_1 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(4);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(0);
        t2.right = new TreeNode(3);
        Solution1305_1 solution1305_1 = new Solution1305_1();
        solution1305_1.solution1(t1, t2).forEach(System.out::println);
//        solution1305_1.solution2(t1, t2).forEach(System.out::println);


    }


    /**
     * 用到了归并排序，所以时间复杂度为O(nlogn)
     * 空间复杂度O(n)
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> solution1(TreeNode root1, TreeNode root2) {
        List<Integer> ansList = new ArrayList<>();
        dfs(root1, ansList);
        dfs(root2, ansList);
        Collections.sort(ansList);
        return ansList;
    }

    /**
     * 目前还有问题，后期在优化
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> solution2(TreeNode root1, TreeNode root2) {
        List<Integer> ansList1 = new ArrayList<>();
        List<Integer> ansList2 = new ArrayList<>();
        dfs(root1, ansList1);
        dfs(root2, ansList2);
        return merge(ansList1, ansList2);
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> ansList = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int index1, index2;
        for (index1 = 0, index2 = 0; index1 < size1 && index2 < size2; ) {
            int num1 = list1.get(index1);
            int num2 = list2.get(index2);
            if (num1 < num2) {
                ansList.add(num1);
                index1++;
            } else {
                ansList.add(num2);
                index2++;
            }
        }
        while (index1 < size1) {
            ansList.add(list1.get(index1++));
        }
        while (index2 < size2) {
            ansList.add(list2.get(index2++));
        }
        return ansList;

    }

    private void dfs(TreeNode node, List<Integer> ansList) {
        if (Objects.isNull(node)) {
            return;
        }
        ansList.add(node.val);
        dfs(node.left, ansList);
        dfs(node.right, ansList);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
