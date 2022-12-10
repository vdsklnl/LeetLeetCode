package easy;

/**
 * @author vdsklnl
 * @create 2022-09-09 13:28
 * @description 爬楼梯进阶版，使用dp，每次可以选择（1-m）阶
 */
public class UpstairsPlus {
    public static void main(String[] args) {

    }

    public int climbStairs(int n, int m) {
        //dp，转化为用1-m组合成n，有多少种排列方式
        int[] dp = new int[n + 1];
        dp[0] = 1;

        //求排列方式要先遍历背包，再遍历物品，求组合则要先物品（后物品永远在后）
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i >= j)
                    dp[i] += dp[i - j];
            }
        }

        return dp[n];
    }
}
