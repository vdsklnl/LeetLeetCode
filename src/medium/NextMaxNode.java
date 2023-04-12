package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-04-11 15:06
 * @description 1019 单调栈
 */
public class NextMaxNode {
    public static void main(String[] args) {

    }

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        int n = nums.size();
        int[] res = new int[n];
        Stack<int[]> deque = new Stack<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            while (deque.size() > 0 && deque.peek()[0] < num) {
                res[deque.peek()[1]] = num;
                deque.pop();
            }
            deque.add(new int[]{num, i});
        }
        return res;
    }
}
