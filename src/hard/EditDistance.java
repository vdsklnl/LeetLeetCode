package hard;

/**
 * @author vdsklnl
 * @create 2022-09-15 11:33
 * @description
 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("leetcode", "etxco"));
    }

    public static int minDistance(String word1, String word2) {
        //dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int n1 = w1.length; int n2 = w2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];

        //初始化：dp[i][0]表示空串变成以i-1为下标的w1编辑距离，即最大距离，dp[0][i]相同
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (w1[i - 1] == w2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[w1.length][w2.length];
    }
}
