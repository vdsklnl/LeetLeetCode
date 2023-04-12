package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-02-10 13:20
 * @description 1223 动态规划
 */
public class DieSimulation {
    public static void main(String[] args) {

    }

    /*
        有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
        不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
        现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
        假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
     */

    /*
        使用dp[i][j][k]来表示投第i次骰子，第i次点数为j，连续出现k次的情况
        初始值设置：dp[1][j][1] = 1;
        更新状态时，枚举上一次投骰子情况，再更新第i次，分为两种情况：
            与上次不同：dp[i][l][1] = dp[i][l][1] + dp[i - 1][j][k]
            与上次相同且未超过限制：dp[i][j][k + 1] = dp[i][j][k + 1] + dp[i - 1][j][k]
     */
    public int dieSimulator(int n, int[] rollMax) {
        int mod = (int) 1e9 + 7;
        int[][][] dp = new int[n + 1][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    //枚举第i次骰子点数
                    for (int l = 0; l < 6; l++) {
                        if (l != j) {
                            dp[i][l][1] = (dp[i][l][1] + dp[i - 1][j][k]) % mod;
                        } else if (k + 1 <= rollMax[j]) {
                            dp[i][j][k + 1] = (dp[i][j][k + 1] + dp[i - 1][j][k]) % mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + dp[n][j][k]) % mod;
            }
        }
        return res;
    }

    //空间优化：状态仅与前一时刻有关，需要将现时刻清零，使用 i & 1 表示新状态
    public int dieSimulatorII(int n, int[] rollMax) {
        int mod = (int) 1e9 + 7;
        int[][][] dp = new int[2][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            int t = i & 1;
            for (int j = 0; j < 6; j++) {
                Arrays.fill(dp[t][j], 0);
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    //枚举第i次骰子点数
                    for (int l = 0; l < 6; l++) {
                        if (l != j) {
                            dp[t][l][1] = (dp[t][l][1] + dp[1 - t][j][k]) % mod;
                        } else if (k + 1 <= rollMax[j]) {
                            dp[t][j][k + 1] = (dp[t][j][k + 1] + dp[1 - t][j][k]) % mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + dp[n & 1][j][k]) % mod;
            }
        }
        return res;
    }
}
