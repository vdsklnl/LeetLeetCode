package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-10-18 17:24
 * @description 30 滑动窗口 + 哈希表
 */
public class SubstringSerializeAllWords {
    public static void main(String[] args) {

    }

    public List<Integer> findSubstring(String s, String[] words) {
        /*
        List<Integer> res = new ArrayList<>();
        int m = words.length, n = words[0].length(), ls = s.length();

        //记录words出现频率
        Map<String, Integer> diff = new HashMap<>();
        for (int i = 0; i < m; i++) {
            diff.put(words[i], diff.getOrDefault(words[i], 0) + 1);
        }

        for (int i = 0; i < ls - (m * n) + 1; i++) {
            String substr = s.substring(i, i + (m * n));
            Map<String, Integer> map = new HashMap<>(diff);
            for (int j = 0; j < (m * n); j += n) {
                //每次取word长度出来判断
                String key = substr.substring(j, j + n);
                if (!map.containsKey(key))
                    break;
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0)
                    map.remove(key);
            }
            //map为空时，表示成功
            if (map.isEmpty())
                res.add(i);
        }
        return res;
         */

        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            //每次维持一个differ
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    //增加新word
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    //减去移除的word
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }
}
