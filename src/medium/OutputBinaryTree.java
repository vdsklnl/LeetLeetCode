package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-29 20:45
 * @description 655
 */
public class OutputBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        OutputBinaryTree outputBinaryTree = new OutputBinaryTree();
        outputBinaryTree.printTree(root);
    }

    public List<List<String>> printTree(TreeNode root) {
        int height = findHeight(root);
        int width = (1 << height) - 1;
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            list.add("");
        }
        for (int i = 0; i < height; i++) {
            res.add(new ArrayList<>(list));
        }
        dfs(res, height, 1, (width - 1) / 2, root);
        return res;
    }

    //计算树深
    public int findHeight(TreeNode root) {
        int h = 1;
        if (root.left != null)
            h = Math.max(h, findHeight(root.left) + 1);
        if (root.right != null)
            h = Math.max(h, findHeight(root.right) + 1);
        return h;
    }

    //dfs填充位置，每次递归将空间分为左右两部分
    //height为树高，h为递归层数，location为树节点所在位置
    public void dfs(List<List<String>> res, int height, int h, int location, TreeNode root) {
        res.get(h - 1).set(location, String.valueOf(root.val));
        if (root.left != null) {
            dfs(res, height, h + 1, location - (1 << (height - h - 1)), root.left);
        }
        if (root.right != null) {
            dfs(res, height, h + 1, location + (1 << (height - h - 1)), root.right);
        }
    }
}
