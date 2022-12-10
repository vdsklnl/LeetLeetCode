package hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vdsklnl
 * @create 2022-10-14 13:28
 * @description 940
 */
public class DifferentSublistII {
    public static void main(String[] args) {
//        System.out.println(distinctSubseqII("abc"));
    }

    //回溯法超时
    /*
    static Set<String> set;

    public static int distinctSubseqII(String s) {
        char[] chars = s.toCharArray();
        set = new HashSet<>();
        backtracking(0, chars, new StringBuilder());
        return (int) (set.size() % (1e9 + 7));
    }

    public static void backtracking(int index, char[] chars, StringBuilder sb) {
        if (index == chars.length)
            return;

        for (int i = index; i < chars.length; i++) {
            sb.append(chars[i]);
            if (!set.contains(sb.toString()))
                set.add(sb.toString());
            backtracking(i + 1, chars, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    */

    //dp
    public static int distinctSubseqII(String s) {
        final int mod = (int) 1e9 + 7;
        char[] chars = s.toCharArray();
        int n = chars.length;
        /*
        //以s[i]为最后一个字符子串
        int[] dp = new int[n];
        int[] last = new int[26];
        Arrays.fill(dp, 1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (last[j] != -1) {
                    dp[i] = (dp[i] + dp[last[j]]) % mod;
                }
            }
            last[chars[i] - 'a'] = i;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (last[i] != -1)
                res = (res + dp[last[i]]) % mod;
        }
        return res;
        */

        /*
        //优化I
        //以s[i]为最后一个字符子串
        int[] last = new int[26];

        for (int i = 0; i < n; i++) {
            int total = 1;
            for (int j = 0; j < 26; j++) {
                if (last[j] != -1) {
                    total = (total + last[j]) % mod;
                }
            }
            last[chars[i] - 'a'] = total;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res + last[i]) % mod;
        }
        return res;
         */

        //优化II 使用total，更新last数组
        //以s[i]为最后一个字符子串
        int[] last = new int[26];
        int total = 0;
        for (int i = 0; i < n; i++) {
            int pre = last[chars[i] - 'a'];
            last[chars[i] - 'a'] = (total + 1) % mod;
            total = ((total + last[chars[i] - 'a'] - pre) % mod + mod) % mod;
        }
        return total;
    }
}
