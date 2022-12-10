package medium;

/**
 * @author vdsklnl
 * @create 2022-09-15 21:53
 * @description 516
 */
public class PalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("cbbdde"));
    }

    public static int longestPalindromeSubseq(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
