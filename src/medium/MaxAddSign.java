package medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vdsklnl
 * @create 2022-11-09 13:08
 * @description 764 图DP
 */
public class MaxAddSign {
    public static void main(String[] args) {

    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        //对每个点，向四个方向进行搜索，记录最小值作为对应结果
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        //使用HashSet记录矩阵为0的点
        Set<Integer> set = new HashSet<>();
        for (int[] mine:mines) {
            set.add(mine[0] * n + mine[1]);
        }
        int res = 0;
        //左右
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (set.contains(i * n + j))
                    count = 0;
                else
                    count++;
                dp[i][j] = Math.min(dp[i][j], count);
            }
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (set.contains(i * n + j))
                    count = 0;
                else
                    count++;
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        //上下
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (set.contains(j * n + i))
                    count = 0;
                else
                    count++;
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (set.contains(j * n + i))
                    count = 0;
                else
                    count++;
                dp[j][i] = Math.min(dp[j][i], count);
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }
}
