package medium;

/**
 * @author vdsklnl
 * @create 2022-10-30 20:17
 * @description
 */
public class GuessNumber {
    public static void main(String[] args) {
        System.out.println(0x3f3f3f3f);
    }

    /*
        猜数字，dp[i][j] 表示范围[i, j]中最小金额
        (1,n)中猜x错，则dp[i][j] = x + Math.max(dp[i][x - 1], dp[x + 1][j])
        对dp[1][n]，应该遍历(1, n)，求最小值，初始化:dp[i][j] = dp[i][j - 1] + j
     */
    public int getMoneyAmount(int n) {
        /*
        int[][] dp = new int[n + 2][n + 2];
        //控制区间长度，从小到大遍历，给右区间一个大值(纵向更新)
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                for (int x = l; x <= r; x++) {
                    dp[l][r] = Math.min(dp[l][r], x + Math.max(dp[l][x - 1], dp[x + 1][r]));
                }
            }
        }
         */
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = j + dp[i][j - 1];
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
