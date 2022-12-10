package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-19 14:05
 * @description 1382
 */
public class BalanceBinarySortTree {
    //先将二叉排序树变成有序数组，再构建平衡二叉树
    List<Integer> list = new ArrayList<>();

    //转换有序序列
    public void travesal(TreeNode root) {
        if (root == null)
            return;
        travesal(root.left);
        list.add(root.val);
        travesal(root.right);
        return;
    }

    //构建平衡二叉树，左闭右闭
    public TreeNode getTree(List<Integer> nums, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = getTree(nums, start, mid - 1);
        root.right = getTree(nums, mid + 1, end);
        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        travesal(root);
        return getTree(list, 0, list.size() - 1);
    }
}
