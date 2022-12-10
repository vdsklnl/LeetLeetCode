package hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author vdsklnl
 * @create 2022-10-11 16:23
 * @description
 */
public class EffectiveBrackets {
    public static void main(String[] args) {
    }

    //dp
    public int longestValidParentheses(String s) {
        //dp，对每两个位置进行判断，取dp数组最大值
        //两种情况，() || ))，后一情况需要判断之前还存不存在左
        int n = s.length();
        if (s == null || n < 2)
            return 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[n];
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = ((i - 2 >= 0) ? dp[i - 2]:0) + 2;
                } else if (i - dp[i - 1] > 0 && chars[i - dp[i - 1] - 1] == '(') {
                    //只有（对应位置记录个数
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2]:0) + 2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    //贪心算法
    public int longestValidParenthesesII(String s) {
        //贪心算法，当left = right时记录结果
        //需要前后两次遍历，排除右大于左和左大于右的情况
        if (s == null || s.length() < 2)
            return 0;
        char[] chars = s.toCharArray();
        int res = 0, left = 0, right = 0;
        //前遍历，left >= right
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right)
                res = Math.max(res, 2 * right);
            if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0; right = 0;
        //后遍历，left <= right
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right)
                res = Math.max(res, 2 * left);
            if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}
