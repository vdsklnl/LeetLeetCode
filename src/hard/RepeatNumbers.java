package hard;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author vdsklnl
 * @create 2022-10-04 14:33
 * @description 1012 数位dp 与2376相反，只要按照2376求解并用整数减即可
 */
public class RepeatNumbers {
    public static void main(String[] args) {

    }

    char[] chars;
    int[][] dp;

    public int numDupDigitsAtMostN(int n) {
        chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        dp = new int[len][1 << 10];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        return n - backtracking(0, 0, true, false);
    }

    /*
        返回从i开始填入，i之前填的数字集合为mask（十进制-二进制转换，可以用int代替），能构造出特殊整数数目
        isLimit 表示是否前面填的数字为对应位上的，true 则本位数字最大为chars[i]，否则可以到9
        isNum 表示前面是否填入了数字（跳过），true，当前位可以从0开始，false，当前位可以同样跳过或从1开始填
     */
    public int backtracking(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == chars.length)
            return isNum ? 1:0;
        //dp[i][mask]记录当前位数i，已经填的数字集合为mask对应在不受到约束时的合法方案数
        if (!isLimit && isNum && dp[i][mask] >= 0)
            return dp[i][mask];

        int res = 0;
        if (!isNum) //未填数字
            res = backtracking(i + 1, mask, false, false);

        int up = isLimit ? chars[i] - '0':9;
        for (int j = isNum ? 0:1; j <= up; j++) {
            //表示j此时对应数不在mask中，如果mask = 2，那么只有1已被使用，= 3，则1和0均被使用
            //mask | (1 << j) 表示mask + 2^对应数字，也即该对应数字被使用过
            //此时i位已填入j，isNum即为true，后续位可以从0开始
            if ((mask >> j & 1) == 0) {
                res += backtracking(i + 1, mask | (1 << j), isLimit && j == up, true);
            }
        }

        //不受约束且已填入数字时，记录结果
        if (!isLimit && isNum)
            dp[i][mask] = res;

        return res;
    }
}
