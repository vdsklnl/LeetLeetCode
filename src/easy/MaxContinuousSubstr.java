package easy;

/**
 * @author vdsklnl
 * @create 2022-11-03 14:51
 * @description
 */
public class MaxContinuousSubstr {
    public static void main(String[] args) {
        String s = "aaabaaaabaaabaaaabaaaabaaaabaaaaba", w = "aaaba";
        System.out.println(maxRepeating(s, w));
    }

    public static int maxRepeating(String sequence, String word) {
        int m = sequence.length(), n = word.length();
        if (n > m)
            return 0;
        int res = 0, cur = 0;
        for (int i = 0; i + n <= m; i++) {
            String str = sequence.substring(i, i + n);
            if (word.equals(str)) {
                int index = i + n;
                cur = 1;
                while (index + n <= m) {
                    String s = sequence.substring(index, index + n);
                    if (word.equals(s)) {
                        cur++;
                        index = index + n;
                    } else
                        break;
                }
                res = Math.max(res, cur);
                if (index >= m)
                    break;
            }
        }
        return res;
    }
}
