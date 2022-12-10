package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-06 14:20
 * @description
 */
public class UniqueCharacterSet {
    /*
        原问题为求所有子数组的唯一字符数量和，其可等价为求每个s[i]对答案的贡献，即每个s[i]可作为多少个子数组的唯一元素。
        假定我们能预处理出两数组 l 和 r 分别代表 s[i] 作为子数组唯一字符时，其所能到达的最远两端：
            l[i] = a 代表下标 a 为 s[i] 能够作为子数组唯一字符时的最远左边界，
            即为 s[i] 左边第一个与 s[i] 值相同的位置（若不存在，则为 a = -1）
            r[i] = b 代表下标 b 为 s[i] 能够作为子数组唯一字符时的最远右边界，
            即为 s[i] 右边第一个与 s[i] 值相同的位置（若不存在，则为 b = n）
        子数组左端点个数为(i−a)个，右端点个数为(b−i)个，根据乘法原理可知，子数组个数为两者乘积。
        预处理 l 和 r 只需要使用遍历计数即可
     */

    public int uniqueLetterString(String s) {
        int n = s.length();
        int[] l = new int[n];
        int[] r = new int[n];

        //对元素左右进行处理
        int[] appears = new int[26];

        Arrays.fill(appears, -1);
        for (int i = 0; i < n; i++) {
            l[i] = appears[s.charAt(i) - 'A'];
            appears[s.charAt(i) - 'A'] = i;
        }

        Arrays.fill(appears, n);
        for (int i = n - 1; i >= 0; i--) {
            r[i] = appears[s.charAt(i) - 'A'];
            appears[s.charAt(i) - 'A'] = i;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += (i - l[i]) * (r[i] - i);
        }
        return result;
    }
}
