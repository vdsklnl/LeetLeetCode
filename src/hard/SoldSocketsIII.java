package hard;

/**
 * @author vdsklnl
 * @create 2022-09-13 14:59
 * @description 买卖两次股票/k次股票
 */
public class SoldSocketsIII {
    public static void main(String[] args) {

    }

    public int maxProfit(int k, int[] prices) {
        //动态规划：k次买卖
        int[] dp = new int[2 * k];
        for (int i = 0; i < k; i++) {
            dp[2 * i] = - prices[0];
        }

        for (int i = 1; i <= prices.length; i++) {
            dp[0] = Math.max(dp[0], - prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
            for (int j = 2; j < dp.length; j += 2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i - 1]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i - 1]);
            }
        }

        return dp[2 * k - 1];
    }

    public int maxProfit(int[] prices) {
        //动态规划
        //两次买卖，使用四个状态记录：不买卖，买一次，买两次
        //dp[0]:第一次持有 || dp[1]:第一次卖出 || dp[2]:第二次持有 || dp[3]:第二次卖出
        int[] dp = new int[4];
        dp[0] = - prices[0]; dp[1] = 0; dp[2] = - prices[0]; dp[3] = 0;

        for (int i = 1; i <= prices.length; i++) {
            /*
                第一次持有为dp[0]与当天价格较小值(负值较大大)
                第一次卖出为dp[1]与当天卖出较大值
                第二次持有为dp[2]与(dp[1] - 当天价格)较大值
                第二次卖出为dp[3]与当天卖出较大值
             */
            dp[0] = Math.max(dp[0], - prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
            dp[2] = Math.max(dp[2], dp[1] - prices[i - 1]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i - 1]);
        }

        return dp[3];
    }
}
