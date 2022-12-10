package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-08-24 15:01
 * @description
 */
public class SequenceTraverse {
    public static int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        Deque<TreeNode> que = new LinkedList<>();
        que.offerLast(root);
        int depth = 0;
        while (!que.isEmpty()) {
            depth++;
            int len = que.size();
            while (len > 0) {
                TreeNode tmpNode = que.pollFirst();
                if (tmpNode.left == null && tmpNode.right == null)
                    return depth;
                if (tmpNode.left != null)
                    que.offerLast(tmpNode.left);
                if (tmpNode.right != null)
                    que.offerLast(tmpNode.right);
                len--;
            }
        }
        return depth;
    }

    /*
     * pollFirst、offerLast等方法在Queue中并未声明，
     * 而是声明在Deque类中，LinkedList也实现了Deque
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();

        if (root == null)
            return list;

        que.offerLast(root);
        while (!que.isEmpty()) {
            int len = que.size();
            for (int i = 0; i < len; i++) {
                TreeNode tmpNode = que.pollFirst();
                if (tmpNode.left != null)
                    que.offerLast(tmpNode.left);
                if (tmpNode.right != null)
                    que.offerLast(tmpNode.right);
                if (i == len - 1) {
                    list.add(tmpNode.val);
                }
            }
        }

        return list;
    }

    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    //DFS--递归方式
    public void recursion(TreeNode node, int layer) {
        if (node == null)
            return;

        layer++;

        //层级增加时，resList也随之增加
        if (resList.size() < layer) {
            List<Integer> item = new ArrayList<>();
            resList.add(item);
        }

        resList.get(layer - 1).add(node.val);

        recursion(node.left, layer);
        recursion(node.right, layer);
    }

    //BFS--迭代方式--借助队列
    public void iteration(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = que.size();

            while (len > 0) {
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) que.offer(tmpNode.left);
                if (tmpNode.right != null) que.offer(tmpNode.right);
                len--;
            }
            resList.add(itemList);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.left.left = new TreeNode(1);
        System.out.println(minDepth(root));
    }
}
