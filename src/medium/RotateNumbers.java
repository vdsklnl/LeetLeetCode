package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-25 21:09
 * @description 数位DP 788
 */
public class RotateNumbers {
    public static void main(String[] args) {

    }

    /*
        将n转换成字符串s，定义f(i,hasDiff,isLimit,isNum) 表示构造从左往右第i位及其之后数位的合法方案数，其余参数的含义为：
            hasDiff 表示前面填的数字是否包含 2,5,6,9（至少一个）。
            isLimit 表示当前是否受到了n的约束。若为真，则第i位填入的数字至多为s[i]，否则可以是9。
                    如果在受到约束的情况下填了s[i]，那么后续填入的数字仍会受到n的约束。
            isNum 表示i前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为1；
                    若为真，则要填入的数字可以从0开始。
     */

    /*
        枚举要填入的数字，对于本题来说，由于前导零对答案无影响，isNum 可以省略。
        代码中只需要记忆化(i,hasDiff) 这个状态，因为：
            对于一个固定的(i,hasDiff)，这个状态受到isLimit的约束在整个递归过程中至多会出现一次，没必要记忆化。
            如果只记忆化 (i,hasDiff)，dp数组的含义就变成在不受到约束时的合法方案数，所以要在!isLimit成立时才去记忆化。
     */

    //表示0-9中，0、1、8待定，3、4、7不行，必须包含2、5、6、9
    int[] diff = {0, 0, 1, -1, -1, 1 , 1, -1, 0, 1};

    char[] s;
    int[][] dp;

    public int rotatedDigits(int n) {
        s = String.valueOf(n).toCharArray();
        int len = s.length;

        //dp数组:在不受到约束时的合法方案数，方便遍历累加
        //dp[i][0]:表示此位数为0、1、8时合法方案数、dp[i][1]:表示此位数为2、5、6、9时合法方案数
        dp = new int[len][2];
        //初始化
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }

        //每一位数从0开始存放，默认不包含2、5、6、9，一轮轮遍历，首先个位数，肯定受限制
        return backtracking(0, 0, true);
    }

    public int backtracking(int i, int hasDiff, boolean limit) {
        //遍历到最低位，只有包含2、5、6、9才为好数
        if (i == s.length)
            return hasDiff;

        //不受限制，且dp数组被更新过，直接返回对应数
        if (!limit && dp[i][hasDiff] >= 0)
            return dp[i][hasDiff];

        int res = 0;
        //表示所考虑位数是否受原数字约束，受原数字约束时，上限为对应数字
        int up = limit ? s[i] - '0':9;

        //枚举要填入的数字
        for (int j = 0; j <= up; j++) {
            //数字不为3、4、7才可能为好数，往下一位存放，回溯整个过程顺序相反
            if (diff[j] != -1) {
                //只要包含2、5、6、9，表示为好数，因此用 或
                //当前位数受约束时，下一位也受约束，同步，约束条件为是否到达上限
                res += backtracking(i + 1, hasDiff | diff[j], limit && j == up);
            }
        }

        //更新dp数组
        if (!limit)
            dp[i][hasDiff] = res;

        return res;
    }
}
