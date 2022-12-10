package medium;

/**
 * @author vdsklnl
 * @create 2022-09-15 14:34
 * @description 5/647
 */
public class PalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(countSubstringsDP("fdsklf"));
    }

    //动态规划
    public static int countSubstringsDP(String s) {
        //布尔类型dp[i][j]：表示区间范围[i,j](左闭右闭)的子串是否是回文子串，是为true，否为false
        char[] chars = s.toCharArray();
        int n = chars.length, res = 0;
        boolean[][] dp = new boolean[n][n];
//        //后续遍历使每一个dp[i + 1][j - 1]已经过计算
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i; j < n; j++) {
//                //相等时，才有可能
//                if (chars[i] == chars[j]) {
//                    //两者相等、无间隙、差一个元素则为回文
//                    if (j - i <= 1) {
//                        res++;
//                        dp[i][j] = true;
//                    } else if (dp[i + 1][j - 1]) {
//                        //相差多个元素时，需要收缩区间[i, j]
//                        res++;
//                        dp[i][j] = true;
//                    }
//                }
//            }
//        }
        //[i, j]，所以j >= i
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 1) {
                        res++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {
                        res++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return res;
    }

    //双指针
    public static int countSubstrings(String s) {
        //判断是否为回文子串，以一个字母为中心或者以两个字母为中心
        //总共有2 * len - 1个中心点(2 * (len - 1) + 1)
        int len = s.length(), res = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            //通过遍历每个回文中心，向两边扩散，并判断是否回文字串
            //有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //如果当前是一个回文串，则记录数量
                res++;
                left--;
                right++;
            }
        }
        return res;
    }

    public String longestPalindrome(String s) {
        //双指针，子串需连续
        //中心点是一个字符或两个字符，总共有2*(len - 1) + 1
        char[] chars = s.toCharArray();
        int len = chars.length, res = 1;
        int low = 0, high = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && chars[left] == chars[right]) {
                if (res < right - left + 1) {
                    res = right - left + 1;
                    low = left;
                    high = right;
                }
                left--;
                right++;
            }
        }
        return s.substring(low, high + 1);
    }
}
