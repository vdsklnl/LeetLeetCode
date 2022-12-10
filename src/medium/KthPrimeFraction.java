package medium;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-11-03 21:10
 * @description 786 优先队列 + 双指针
 */
public class KthPrimeFraction {
    public static void main(String[] args) {

    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        //优先队列
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                arr[a[0]] * arr[b[1]] - arr[a[1]] * arr[b[0]]);
        for (int i = 1; i < arr.length; i++) {
            pq.offer(new int[] {0, i});
        }
        for (int i = 1; i < k; i++) {
            int[] poll = pq.poll();
            if (poll[0] + 1 < poll[1])
                pq.offer(new int[] {poll[0] + 1, poll[1]});
        }
        return new int[] {arr[pq.peek()[0]], arr[pq.peek()[1]]};
    }

    public int[] kthSmallestPrimeFractionII(int[] arr, int k) {
        //双指针 + 二分查找
        int n = arr.length;
        double left = 0.0, right = 1.0;
        while (true) {
            //mid是对应第k小分数，小于mid纳入统计
            double mid = (left + right) / 2;
            int i = -1, count = 0;
            //记录最接近mid分子分母位置，mid为第k个，那么最接近mid在排列中为第k个
            int x = 0, y = 1;
            for (int j = 1; j < n; j++) {
                while ((double) arr[i + 1] / arr[j] < mid) {
                    i++;
                    if (arr[i] * y > arr[j] * x) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                //循环结束时，[0, i]之间元素均被统计
                count += i + 1;
            }
            if (count == k)
                return new int[] {x, y};
            else if (count < k)
                left = mid;
            else
                right = mid;
        }
    }
}
