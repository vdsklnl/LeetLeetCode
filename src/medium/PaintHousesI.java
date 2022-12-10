package medium;

/**
 * @author vdsklnl
 * @create 2022-10-22 15:17
 * @description 剑指091 序列dp
 */
public class PaintHousesI {
    public static void main(String[] args) {

    }

    public int minCost(int[][] costs) {
        int m = costs.length, n = costs[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = costs[0][i];
        }
        for (int i = 1; i < m; i++) {
            //记录上一次结果
            int a = dp[0], b = dp[1], c = dp[2];
            dp[0] = Math.min(b, c) + costs[i][0];
            dp[1] = Math.min(a, c) + costs[i][1];
            dp[2] = Math.min(a, b) + costs[i][2];
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}
