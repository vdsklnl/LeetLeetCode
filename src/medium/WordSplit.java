package medium;

import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-09 17:25
 * @description 139
 */
public class WordSplit {
    public static void main(String[] args) {

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //完全背包问题
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        //先遍历背包（s），再遍历物品（wordDict）
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j] == true) {
                    dp[i] = true;
                }
            }
        }

        return dp[len];
    }
}
