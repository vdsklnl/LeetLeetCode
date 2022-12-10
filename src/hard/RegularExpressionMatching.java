package hard;

/**
 * @author vdsklnl
 * @create 2022-11-10 17:48
 * @description 10 字符串匹配DP
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {

    }

    /*
            . * b b c *
          T F T F F F F
        a F T T F F F F
        a F F T F F F F
        b F F T T F F F
        b F F T T T F T
        c F F T F T T T
        c F F T F F T T
        c F F T F F F T
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        //dp[i][j] 表示s前i个字符与p前j个字符匹配情况，dp[0][0] = true，空字符串匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    //可以匹配0|多个前一字符，将其与前一元素拉出
                    dp[i][j] = dp[i][j - 2];
                    //当j - 1与i相匹配时，
                    if (i != 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    //正常匹配
                    if (i != 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)))
                        dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
