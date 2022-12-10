package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-11-01 17:25
 * @description 95 DFS || 96 DP
 */
public class DifferentBinarySearchTree {
    public static void main(String[] args) {

    }

    public int numTrees(int n) {
        //对每个值，其作为头节点时树结构数量 = 左子树数量 x 右子树数量
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<TreeNode>();
        return backtracking(1, n);
    }

    public List<TreeNode> backtracking(int start, int end) {
        //给定构建数的起始和结束，将其分解为左右两部分递归生成
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        //列举所有可以作为根节点的位置
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = backtracking(start, i - 1);
            List<TreeNode> rightTrees = backtracking(i + 1, end);
            //穷举所有左右子树可能，并拼到当前节点
            for (TreeNode left:leftTrees) {
                for (TreeNode right:rightTrees) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right = right;
                    allTrees.add(cur);
                }
            }
        }
        return allTrees;
    }
}
