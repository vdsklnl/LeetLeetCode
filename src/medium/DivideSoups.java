package medium;

/**
 * @author vdsklnl
 * @create 2022-11-21 11:07
 * @description 808 动态规划 || 记忆化搜索
 */
public class DivideSoups {
    public static void main(String[] args) {

    }

    /*
         汤A先分配完的概率 +  汤A和汤B同时分配完的概率 / 2。返回值在正确答案 10-5 的范围内将被认为是正确的。
         当n过大时，概率接近1，直接返回1就行 n >= 4475 -> 179 * 25
     */
    public double soupServings(int n) {
        //操作以25ml为单位，可简化为(4,0),(3,1),(2,2),(1,3)四种操作
        //dp[i][j]表示a = i, b = j时的概率
        n = (int) Math.ceil((double) n / 25.0);
        if (n >= 179)
            return 1.0;
        double[][] dp = new double[n + 1][n + 1];
        //初始化，a = 0, b = 0 -> 0.5 || a > 0, b = 0 -> 1.0 || a = 0, b > 0 -> 0.0
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1.0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[Math.max(i - 4, 0)][j]
                        + dp[Math.max(i - 3, 0)][Math.max(j - 1, 0)]
                        + dp[Math.max(i - 2, 0)][Math.max(j - 2, 0)]
                        + dp[Math.max(i - 1, 0)][Math.max(j - 3, 0)]) / 4.0;
            }
        }
        return dp[n][n];
    }

    //记忆化搜索
    double[][] dp;

    public double soupServingsII(int n) {
        //操作以25ml为单位，可简化为(4,0),(3,1),(2,2),(1,3)四种操作
        //dp[i][j]表示a = i, b = j时的概率
        n = (int) Math.ceil((double) n / 25.0);
        if (n >= 179)
            return 1.0;
        double[][] dp = new double[n + 1][n + 1];
        return backtracking(n, n);
    }

    private double backtracking(int a, int b) {
        if (a <= 0 && b <= 0)
            return 0.5;
        else if (a <= 0)
            return 1.0;
        else if (b <= 0)
            return 0;
        if (dp[a][b] == 0) {
            dp[a][b] = (backtracking(a - 4, b) + backtracking(a - 3, b - 1)
                    + backtracking(a - 2, b - 2) + backtracking(a - 1, b - 3)) / 4.0;
        }
        return dp[a][b];
    }
}
