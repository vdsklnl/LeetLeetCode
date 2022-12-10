package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-30 20:46
 * @description 17.06 数位dp
 */
public class TwoExistsNumbers {
    public static void main(String[] args) {

    }

    /*
        将n转换成字符串s，定义f(i,cnt2,isLimit,isNum) 表示构造从左往右第i位及其之后数位中的2的个数，其余参数的含义为：
            cnt2:表示前面填了多少个2。
            isLimit:表示当前是否受到了n的约束。若为真，则第i位填入的数字至多为s[i]，否则可以是9。
                    如果在受到约束的情况下填了s[i]，那么后续填入的数字仍会受到n的约束。
            isNum:表示i前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为1；
                    若为真，则要填入的数字可以从0开始。
     */
    char[] chars;
    //不受约束时合法方案数
    int[][] dp;

    public int numberOf2sInRange(int n) {
        chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        //数2个数，dp[i][j]表示i位，包含j个2的合法方案
        dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        return backtracking(0, 0, true);
    }

    public int backtracking(int i, int cnt2, boolean isLimit) {
        if (i == chars.length)
            return cnt2;
        //已经计算完成，不用重复计算，调用结果
        if (!isLimit && dp[i][cnt2] >= 0)
            return dp[i][cnt2];

        int res = 0;
        int up = isLimit ? chars[i] - '0':9;
        for (int j = 0; j <= up; j++) {
            //当j == 2时，2的数目加1
            res += backtracking(i + 1, cnt2 + (j == 2 ? 1:0), isLimit && j == up);
        }
        if (!isLimit)
            dp[i][cnt2] = res;

        return res;
    }
}
