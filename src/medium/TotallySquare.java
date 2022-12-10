package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-09 16:15
 * @description
 */
public class TotallySquare {
    public static void main(String[] args) {

    }

    public int numSquares(int n) {
        //完全背包问题
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        //先遍历物品后遍历背包
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max)
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        // //先遍历背包后遍历物品
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j * j <= i; j++) {
        //         dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        //     }
        // }

        return dp[n];
    }
}
