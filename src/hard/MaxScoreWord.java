package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2023-02-27 16:04
 * @description 1255 状态压缩
 */
public class MaxScoreWord {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(1); A.add(0);
        hanota(A, new ArrayList<>(), new ArrayList<>());
    }

    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        hanota(n, A, B, C);
    }

    public static void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }
        //先将上面盘子移到B
        hanota(n - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        //再将B上盘子移到C;
        hanota(n - 1, B, A, C);
    }

    //
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length, res = 0;
        int[] count = new int[26];
        for (char c : letters) {
            count[c - 'a']++;
        }
        for (int s = 1; s < (1 << n); s++) {
            int[] wordCount = new int[26]; // 统计子集 s 所有单词的字母数目
            //不同单词组合共2^n种，统计每种组合需要字符数
            for (int k = 0; k < n; k++) {
                if ((s & (1 << k)) == 0) { // words[k] 不在子集 s 中
                    continue;
                }
                for (int i = 0; i < words[k].length(); i++) {
                    char c = words[k].charAt(i);
                    wordCount[c - 'a']++;
                }
            }
            //符合规则结果记录
            boolean ok = true; // 判断子集 s 是否合法
            int sum = 0; // 保存子集 s 的得分
            for (int i = 0; i < 26; i++) {
                sum += score[i] * wordCount[i];
                ok = ok && (wordCount[i] <= count[i]);
            }
            if (ok) {
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}
