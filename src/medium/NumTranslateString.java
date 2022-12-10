package medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-11-12 15:27
 * @description 剑指offer46 DP
 */
public class NumTranslateString {
    public static void main(String[] args) {
        translateNum(12258);
    }

    public static int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] += dp[i - 1];
            int newNum = Integer.parseInt(str.substring(i - 1, i + 1));
            if (newNum >= 10 && newNum <= 25) {
                dp[i] += i > 1 ? dp[i - 2]:1;
            }
        }
        return dp[n - 1];
    }

    public static int translateNumII(int num) {
        //改进，dp[i]只与i - 1，i - 2有关，只需三个数
        String str = String.valueOf(num);
        int n = str.length();
        int a = 0, b = 0, c = 1;
        for (int i = 1; i < n; i++) {
            a = b;
            b = c;
            int newNum = Integer.parseInt(str.substring(i - 1, i + 1));
            if (newNum >= 10 && newNum <= 25) {
                c += i > 1 ? a:1;
            }
        }
        return c;
    }
}