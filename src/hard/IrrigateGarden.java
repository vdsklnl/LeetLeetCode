package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-02-21 14:03
 * @description 1326 动态规划 || 贪心
 */
public class IrrigateGarden {
    public static void main(String[] args) {

    }

    //贪心
    public int minTaps(int n, int[] ranges) {
        //记录每一个点能够到达最右区域，使用最左位置来更新
        int[] right = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            right[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);
            right[l] = Math.max(right[l], r);
        }
        //不断枚举上一次能达到的最远边界，同时记录左端位置
        int last = 0, pre = 0, res = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, right[i]);
            //表示i为这次的极限，覆盖不了整个园区
            if (i == last)
                return -1;
            //表示需要增加数量，并更新新的边界
            if (i == pre) {
                res++;
                pre = last;
            }
        }
        return res;
    }

    //动态规划
    public int minTapsII(int n, int[] ranges) {
        //记录每一个点能够到达最右区域，使用区域存放结果
        int[][] intervals = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);
            intervals[i] = new int[] {l, r};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval:intervals) {
            int l = interval[0], r = interval[1];
            if (dp[l] == Integer.MAX_VALUE)
                return -1;
            for (int i = l; i <= r; i++) {
                dp[i] = Math.min(dp[i], dp[l] + 1);
            }
        }
        return dp[n];
    }
}
