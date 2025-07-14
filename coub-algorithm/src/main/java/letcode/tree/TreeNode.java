package letcode.tree;


import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/**
 * 读题需要的前置知识： 树用数组表达的方式：
 * [1,2,3,4,5,6,7,8,9,10]
 *           1
 *        2     3
 *     4     5   6  7
 *   8  9  10
 */
class FindElements{

    private Set<Integer> set;

    public FindElements(TreeNode root) {
        set = new HashSet<>();
        init(root);
    }

    private void init(TreeNode root) {
        dfs(root,0);
    }

    private void dfs(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        set.add(val);
        dfs(node.left, val * 2 + 1);
        dfs(node.right, val * 2 + 2);

    }

    public boolean find(int target) {
        return set.contains(target);
    }
}
