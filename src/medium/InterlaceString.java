package medium;

/**
 * @author vdsklnl
 * @create 2022-11-11 21:15
 * @description 97 字符串DP
 */
public class InterlaceString {
    public static void main(String[] args) {
        String s1 = "abc", s2 = "dba", s3 = "adbbac";
        isInterleave(s1, s2, s3);
    }

    /*
        abc dba -> adbbac
        -1  T F F F
         0  T F F F
         1  T T T F
         2  F T T T
         3  F F F T
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t)
            return false;
        //dp[j]表示s2前j个字符是否能与s1前i个字符构成s3前i+j个字符
        //取决于s2前j个字符是否能与s1前i-1个字符构成s3前i+j-1个字符
        //或者s2前j-1个字符是否能与s1前i字符构成s3前i+j-1个字符
        //初始化：dp[0] = true
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n];
    }
}
