package medium;

/**
 * @author vdsklnl
 * @create 2022-09-08 18:18
 * @description
 */
public class OneAndZero {
    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxForm(strs, 5, 3));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        //两个一维背包
        int[][] dp = new int[m + 1][n + 1];
        for (String str:strs) {
            int zero = 0;
            int one = 0;
            for (char ch:str.toCharArray()) {
                if (ch == '0')
                    zero++;
                else
                    one++;
            }
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
