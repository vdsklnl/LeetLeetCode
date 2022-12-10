package hard;

/**
 * @author vdsklnl
 * @create 2022-09-14 19:47
 * @description 115
 */
public class DifferentSubList {
    public static void main(String[] args) {
        System.out.println(numDistinct("babgbag", "bag"));
    }

    public static int numDistinct(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        //dp[i][j]:以i-1为结尾的s子序列中出现以j-1为结尾的t的个数
        int[][] dp = new int[s1.length + 1][t1.length + 1];

        //初始化条件：dp[i][0]，以i-1为结尾的s可以随便删除元素，出现空字符串(t = "")的个数
        //dp[0][j]: 当s为空字符串，与t != ""的交集始终为0
        for (int i = 0; i <= s1.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= t1.length; j++) {
                if (s1[i - 1] == t1[j - 1]) {
                    //当元素相同时，可以选择用此元素作为序列组成，也可以不选
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //元素不同，只能继承上一结果，不选此元素
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[s1.length][t1.length];
    }
}
