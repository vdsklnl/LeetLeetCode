package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-12-11 2:36
 * @description 1691 动态规划 + 记忆化搜索
 */
public class BuildCuboid {
    public static void main(String[] args) {

    }

    public int maxHeight(int[][] cuboids) {
        //先将长方体排序，再将长方体组排序
        int n = cuboids.length;
        for (int[] cuboid:cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> a[0] + a[1] + a[2] - b[0] - b[1] - b[2]);
        int res = 0;
        //dp[i]：以第i个长方体为底最大高度
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[j][0] <= cuboids[i][0] && cuboids[j][1] <= cuboids[i][1]
                        && cuboids[j][2] <= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //记忆化搜索
    public int maxHeightII(int[][] cuboids) {
        //先将长方体排序，再将长方体组排序
        int n = cuboids.length;
        for (int[] cuboid:cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> a[0] + a[1] + a[2] - b[0] - b[1] - b[2]);
        //dp[i]：以第i个长方体为底最大高度
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return dfs(cuboids, dp, -1, 0);
    }

    public int dfs(int[][] cuboids, int[] memo, int top, int index) {
        if (index == cuboids.length) {
            return 0;
        }
        if (top != -1 && memo[top] != -1) {
            return memo[top];
        }
        int height = dfs(cuboids, memo, top, index + 1);
        if (top == -1 || check(cuboids[top], cuboids[index])) {
            height = Math.max(height, cuboids[index][2] + dfs(cuboids, memo, index, index + 1));
        }
        if (top != -1) {
            memo[top] = height;
        }
        return height;
    }

    public boolean check(int[] a, int[] b) {
        return a[0] <= b[0] && a[1] <= b[1] && a[2] <= b[2];
    }
}
