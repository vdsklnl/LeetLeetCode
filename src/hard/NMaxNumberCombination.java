package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-10-03 23:04
 * @description 902 数位dp
 */
public class NMaxNumberCombination {
    public static void main(String[] args) {

    }

    char[] chars;
    int[] select;
    int[][] dp;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        //dp[i][0]:表示第i位未填数字的合法方案数，dp[i][1]:表示第i位填数字的合法方案数
        dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        select = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            select[i] = Integer.parseInt(digits[i]);
        }
        return backtracking(0, true, false);
    }

    /*
        返回从i开始填入，能构造出整数数目
        isLimit 表示是否前面填的数字为对应位上的，true 则本位数字最大为chars[i]，否则可以到9
        isNum 表示前面是否填入了数字（跳过），true，当前位可以从0开始，false，当前位可以同样跳过或从1开始填
     */
    public int backtracking(int i, boolean isLimit, boolean isNum) {
        if (i == chars.length)
            return isNum ? 1:0;
        if (!isLimit && dp[i][isNum ? 1:0] >= 0)
            return dp[i][isNum ? 1:0];

        //未填入数字
        int res = 0;
        if (!isNum)
            res = backtracking(i + 1, false, false);

        int up = isLimit ? chars[i] - '0':9;
        for (int dig:select) {
            if (dig > up)
                break;
            res += backtracking(i + 1, isLimit && dig == up, true);
        }

        //记录结果，根据前位是否填入数字划分
        if (!isLimit)
            dp[i][isNum ? 1:0] = res;

        return res;
    }
}
