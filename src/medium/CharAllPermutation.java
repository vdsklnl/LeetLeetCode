package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-30 17:17
 * @description 784 DFS
 */
public class CharAllPermutation {
    public static void main(String[] args) {

    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        backtracking(chars, 0, res);
        return res;
        /*
        //记录位置，穷举排列
            int n = s.length();
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(s.charAt(i))) {
                m++;
            }
        }
        List<String> ans = new ArrayList<String>();
        for (int mask = 0; mask < (1 << m); mask++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0, k = 0; j < n; j++) {
                if (Character.isLetter(s.charAt(j)) && (mask & (1 << k++)) != 0) {
                    sb.append(Character.toUpperCase(s.charAt(j)));
                } else {
                    sb.append(Character.toLowerCase(s.charAt(j)));
                }
            }
            ans.add(sb.toString());
        }
        return ans;
         */
    }

    private void backtracking(char[] chars, int index, List<String> res) {
        while (index < chars.length && Character.isDigit(chars[index])) {
            index++;
        }

        if (index == chars.length) {
            res.add(new String(chars));
            return;
        }

        //每个字符的大小写形式相差32，大小写装换时可以用 c⊕32 || c⊕32 来进行转换和恢复。
        chars[index] ^= 32;
        backtracking(chars, index + 1, res);
        chars[index] ^= 32;
        backtracking(chars, index + 1, res);
    }
}
