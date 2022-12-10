package medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-10-30 20:51
 * @description 373 优先队列 + 多路并归
 */
public class MinSumKCouple {
    public static void main(String[] args) {

    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //以某一数组0为基础，建立容量为k的优先队列
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, ((o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        }));
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.add(new int[] {i, 0});
        }

        List<List<Integer>> res = new ArrayList<>();
        while (k-- > 0 && !pq.isEmpty()) {
            int[] poll = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[poll[0]]);
            list.add(nums2[poll[1]]);
            res.add(list);
            if (poll[1] + 1 < n) {
                pq.offer(new int[] {poll[0], poll[1] + 1});
            }
        }
        return res;
    }
}
