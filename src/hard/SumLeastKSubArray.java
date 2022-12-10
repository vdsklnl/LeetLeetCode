package hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author vdsklnl
 * @create 2022-10-27 10:38
 * @description
 */
public class SumLeastKSubArray {
    public static void main(String[] args) {

    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }
        int res = n + 1;
        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSums[i];
            //当前前缀和 - 列表前缀和判断peekFirst -> i - 1和大小
            //一旦满足条件可弹出，表示以peekFirst为起始最短长度已找到
            while (!que.isEmpty() && curSum - preSums[que.peekFirst()] >= k)
                res = Math.min(res, i - que.pollFirst());
            //添加i进队列之前，将大于i前缀和的位置弹出，保持单调递增
            //队列中的前缀和在判断时是需要减去的，在较前位置更大的对结果不影响
            while (!que.isEmpty() && preSums[que.peekLast()] >= curSum)
                que.pollLast();
            que.offerLast(i);
        }
        return res < n + 1 ? res:-1;
    }
}
