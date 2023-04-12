package hard;

/**
 * @author vdsklnl
 * @create 2023-03-29 19:35
 * @description
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
//        shortestCommonSupersequence("abac", "cab");
        String str = "ABR%";
        StringBuilder sb = new StringBuilder();

        System.out.println(str);
    }

    public static String shortestCommonSupersequence(String str1, String str2) {
        //动态规划，dp[i][j] 表示同时以字符串str1[i:n]和str2[j:m]部分为子串的最小公共串长度
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        //初始化 i = m || j = n 另一子串为空串
        for (int i = 0; i < m; i++) {
            dp[i][n] = m - i;
        }
        for (int j = 0; j < n; j++) {
            dp[m][j] = n - j;
        }
        //倒序处理dp[i][j]
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                else
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
            }
        }
        //形成结果子串
        StringBuilder res = new StringBuilder();
        int t1 = 0, t2 = 0;
        while (t1 < m && t2 < n) {
            if (str1.charAt(t1) == str2.charAt(t2)) {
                res.append(str1.charAt(t1));
                t1++;
                t2++;
            } else if (dp[t1 + 1][t2] == dp[t1][t2] - 1) {
                res.append(str1.charAt(t1));
                t1++;
            } else if (dp[t1][t2 + 1] == dp[t1][t2] - 1) {
                res.append(str2.charAt(t2));
                t2++;
            }
        }
        if (t1 < m)
            res.append(str1.substring(t1));
        else if (t2 < n)
            res.append(str2.substring(t2));
        return res.toString();
    }
}
