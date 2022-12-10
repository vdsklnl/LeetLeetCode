package medium;

/**
 * @author vdsklnl
 * @create 2022-08-27 20:01
 * @description
 */
public class CountNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        System.out.println(countNodes(root));
        System.out.println(1 << 0);
    }

    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) { // 左子树是满二叉树
            //左子树加根节点 + 对右节点进行计数
            //对0 -> 1 << 0 = 1
            return (1 << leftDepth) + countNodes(root.right);
        } else { // 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    public static int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
}
