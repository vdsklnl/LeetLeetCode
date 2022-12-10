package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-08 10:35
 * @description 同698：切分k个相等子集
 */
public class SquareSticks {
    public static void main(String[] args) {
        int[] sticks = {8,16,24,32,40,48,56,64,72,80,88,96,104,112,60};
        System.out.println(makesquareDP(sticks));
    }

    public static boolean makesquareDP(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
        int len = totalLen / 4, n = matchsticks.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int s = 1; s < (1 << n); s++) {
            for (int k = 0; k < n; k++) {
                if ((s & (1 << k)) == 0) {
                    continue;
                }
                int s1 = s & ~(1 << k);
                if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                    dp[s] = (dp[s1] + matchsticks[k]) % len;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

    public static boolean makesquare(int[] matchsticks) {
        //回溯问题
        if (matchsticks.length <= 3)
            return false;

        int sum = 0;
        for (int stick:matchsticks) {
            sum += stick;
        }
        if (sum % 4 != 0)
            return false;

        int target = sum / 4;
        for (int i = 0; i < matchsticks.length; i++) {
            if (matchsticks[i] > target)
                return false;
        }

        //排序后，从大到小进行剪枝提高运行效率
        Arrays.sort(matchsticks);
        return dfs(matchsticks.length - 1, matchsticks, new int[4], target);
    }

    public static boolean dfs(int index, int[] sticks, int[] edges, int target) {
        if (index == -1)
            return true;

        out:for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < i; j++) {
                if (edges[j] == edges[i])
                    continue out;
            }
            if (edges[i] + sticks[index] > target)
                continue;
            edges[i] += sticks[index];
            if (dfs(index - 1, sticks, edges, target))
                return true;
            edges[i] -= sticks[index];
        }

        return false;
    }
}
