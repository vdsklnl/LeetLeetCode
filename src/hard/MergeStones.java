package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-04-04 16:24
 * @description 1000 区间动态规划 + 状态优化
 */
public class MergeStones {

    public static void main(String[] args) {

    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0)
            return -1;

        //dp[l][r]：表示将区间[l, r]的石头合成为小于k堆时最小消耗
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }

        //记录前缀和，初始化dp数组
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            sum[i + 1] = sum[i] + stones[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l < n && l + len - 1 < n; l++) {
                int r = l + len - 1;
                for (int i = l; i < r; i += k - 1) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][i] + dp[i + 1][r]);
                }
                //合并为一堆，需要将全部价值加上
                if ((r - l) % (k - 1) == 0)
                    dp[l][r] += sum[r + 1] - sum[l];
            }
        }

        return dp[0][n - 1];
    }

}
