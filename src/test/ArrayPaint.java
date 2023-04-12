package test;

/**
 * @author vdsklnl
 * @create 2023-03-29 19:38
 * @description
 */
public class ArrayPaint {
    public static void main(String[] args) {
        System.out.println(paint(4, new int[]{-1, 2, 3, 4}));
    }

    public static int paint(int n, int[] nums) {
        //动态规划
        //dp[i][0]表示不染色，dp[i][1]表示染色
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + nums[i];
            } else {
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 2][1]);
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 2][0] + nums[i - 1]) + nums[i];
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
