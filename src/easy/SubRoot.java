package easy;

/**
 * @author vdsklnl
 * @create 2022-08-26 15:28
 * @description
 */
public class SubRoot {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(2);
//        root.left.right.left = new TreeNode(0);
//
//        TreeNode subRoot = new TreeNode(4);
//        subRoot.left = new TreeNode(1);
//        subRoot.right = new TreeNode(2);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        TreeNode subRoot = new TreeNode(1);
        System.out.println(isSubtree(root, subRoot));
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return compare(root, subRoot);
    }

    public static boolean compare(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (root.val == subRoot.val) {
            if (match(root, subRoot))
                return true;
        }

        boolean left = compare(root.left, subRoot);
        boolean right = compare(root.right, subRoot);
        return left || right;
    }

    public static boolean match(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;
        if (root == null || subRoot == null || root.val != subRoot.val)
            return false;
        boolean left = match(root.left, subRoot.left);
        boolean right = match(root.right, subRoot.right);
        return left && right;
    }
}
