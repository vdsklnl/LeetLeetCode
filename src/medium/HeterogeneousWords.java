package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-18 15:50
 * @description 438 滑动窗口
 */
public class HeterogeneousWords {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        //滑动窗口
        int lenS = s.length(), lenP = p.length();
        if (lenS < lenP)
            return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        int[] cnts = new int[26], cntp = new int[26];
        for (int i = 0; i < lenP; i++) {
            cnts[s.charAt(i) - 'a']++;
            cntp[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(cnts, cntp))
            res.add(0);

        for (int i = 0; i < lenS - lenP; i++) {
            cnts[s.charAt(i) - 'a']--;
            cnts[s.charAt(i + lenP) - 'a']++;
            if (Arrays.equals(cnts, cntp))
                res.add(i + 1);
        }
        return res;
    }

    //优化，使用diff判断
    public List<Integer> findAnagramsII(String s, String p) {
        //滑动窗口
        int lenS = s.length(), lenP = p.length();
        if (lenS < lenP)
            return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        int[] cnts = new int[26];
        for (int i = 0; i < lenP; i++) {
            cnts[s.charAt(i) - 'a']++;
            cnts[p.charAt(i) - 'a']--;
        }

        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (cnts[i] != 0)
                diff++;
        }
        if (diff == 0)
            res.add(0);

        for (int i = 0; i < lenS - lenP; i++) {
            //1:表示从不同变成相同 || 0:表示从相同变成不同
            if (cnts[s.charAt(i) - 'a'] == 1)
                diff--;
            else if (cnts[s.charAt(i) - 'a'] == 0)
                diff++;
            cnts[s.charAt(i) - 'a']--;

            //-1:表示从不同变成相同 || 0:表示从相同变成不同
            if (cnts[s.charAt(i + lenP) - 'a'] == -1)
                diff--;
            else if (cnts[s.charAt(i + lenP) - 'a'] == 0)
                diff++;
            cnts[s.charAt(i + lenP) - 'a']++;

            if (diff == 0)
                res.add(i + 1);
        }
        return res;
    }
}
