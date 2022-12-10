package hard;

/**
 * @author vdsklnl
 * @create 2022-08-23 14:47
 * @description
 */
public class TreeNode {
    public int val;

    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
