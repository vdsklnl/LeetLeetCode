package hard;

/**
 * @author vdsklnl
 * @create 2022-09-21 15:30
 * @description
 */
public class SplitStringII {
    public static void main(String[] args) {

    }

    public int minCut(String s) {
        if (null == s || "".equals(s))
            return 0;

        int len = s.length();
        // 记录子串[i..j]是否是回文串
        boolean[][] isPalindromic = new boolean[len][len];
        char[] chars = s.toCharArray();
        /*
        // 从下到上，从左到右
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i <= 1){
                        isPalindromic[i][j] = true;
                    } else{
                        isPalindromic[i][j] = isPalindromic[i + 1][j - 1];
                    }
                } else{
                    isPalindromic[i][j] = false;
                }
            }
        }
         */

        //从上到下，从左至右   上面代码遍历完是矩阵右上作为依据，下同
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 2) {
                        isPalindromic[i][j] = true;
                    } else {
                        isPalindromic[i][j] = isPalindromic[i + 1][j - 1];
                    }
                } else {
                    isPalindromic[i][j] = false;
                }
            }
        }

        //统计完后，进行分割行为，dp[i]，记录[0, i]需要分割的次数
        //若[0, i]不是回文串，而[j + 1, i]是回文，则其分割次数为dp[j] + 1;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            //更新初始值，最坏情况：n个字符分割n-1次
            dp[i] = i;
        }

        for (int i = 1; i < len; i++) {
            if (isPalindromic[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (isPalindromic[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }
}
