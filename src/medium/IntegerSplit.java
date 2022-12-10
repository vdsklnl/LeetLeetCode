package medium;

/**
 * @author vdsklnl
 * @create 2022-09-07 16:39
 * @description
 */
public class IntegerSplit {
    public static void main(String[] args) {

    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j ; j++) {
                /*  这里的 j 其实最大值为 i-j，在数乘中，一半与另一半对应
                    j 最大到 i-j,就不会用到 dp[0]与dp[1]，这两个数无实际意义
                    j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                    而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
                */
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int integerBreakProve(int n) {
        //贪心，分成多个3相乘最大，余4则保留
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        if (n == 4)
            return 4;

        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        return result * n;
    }
}
