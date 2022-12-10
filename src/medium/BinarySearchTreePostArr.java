package medium;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-11-07 15:49
 * @description 剑指offer 33 递归 || 单调栈
 */
public class BinarySearchTreePostArr {
    public static void main(String[] args) {

    }

    public boolean verifyPostorder(int[] postorder) {
        //递归：二叉树问题均可用递归解决
//        return backtracking(postorder, 0, postorder.length - 1);
        //单调栈：从后向前遍历
        Stack<Integer> stack = new Stack<>();
        int head = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (head < postorder[i])
                return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                head = stack.pop();
            stack.push(postorder[i]);
        }
        return true;
    }

    private boolean backtracking(int[] postorder, int l, int r) {
        //进行左右子树分割
        if (l >= r)
            return true;
        int pos = l;
        while (postorder[pos] < postorder[r])
            pos++;
        int mid = pos;
        while (postorder[pos] > postorder[r])
            pos++;
        return pos == r && backtracking(postorder, l, mid - 1) && backtracking(postorder, mid, r - 1);
    }
}
