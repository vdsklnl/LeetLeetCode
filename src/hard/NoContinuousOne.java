package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-10-01 15:20
 * @description 600 数位dp
 */
public class NoContinuousOne {
    public static void main(String[] args) {

    }

    char[] chars;
    int[][] dp;

    public int findIntegers(int n) {
        chars = Integer.toBinaryString(n).toCharArray();
        int len = chars.length;
        //dp中，用0、1来记录本层为0或1时，合法的方案数
        dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        return backtracking(0, false, true);
    }

    //本题使用三个参数：位数，前一位是否为1，是否受限
    public int backtracking(int i, boolean pre1, boolean isLimit) {
        //放完，返回合法方案1
        if (i == chars.length)
            return 1;
        //pre1为真，则此位不能放1，下一位可以放1，读取dp数组中下一位为1的情况
        //当dp数组已被更新，直接读取对应数值
        if (!isLimit && dp[i][pre1 ? 1:0] >= 0)
            return dp[i][pre1 ? 1:0];
        int up = isLimit ? chars[i] - '0':1;
        //当此位为0，合法方案数
        //本位受限，则下一位也受限
        int res = backtracking(i + 1, false, isLimit && up == 0);
        //只有当前一位不为1，且上限为1时，才存在本位为1的合法方案
        if (!pre1 && up == 1)
            res += backtracking(i + 1, true, isLimit);
        //不存在限制时，记录方案数
        if (!isLimit)
            dp[i][pre1 ? 1:0] = res;
        return res;
    }
}
