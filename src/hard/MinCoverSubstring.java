package hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-10-18 17:56
 * @description
 */
public class MinCoverSubstring {
    public static void main(String[] args) {

    }

    Map<Character, Integer> map = new HashMap<>();
    Map<Character, Integer> count = new HashMap<>();

    public String minWindow(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        if (lenS < lenT)
            return "";

        for (int i = 0; i < lenT; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = -1;
        int len = Integer.MAX_VALUE, ansl = -1, ansR = -1;
        while (right < lenS) {
            right++;
            if (right < lenS && map.containsKey(s.charAt(right)))
                count.put(s.charAt(right), count.getOrDefault(s.charAt(right), 0) + 1);

            //收缩窗口
            while (check() && left <= right) {
                if (right - left + 1 < len) {
                    len = right - left + 1;
                    ansl = left;
                    ansR = left + len;
                }

                if (map.containsKey(s.charAt(left))) {
                    count.put(s.charAt(left), count.getOrDefault(s.charAt(left), 0) - 1);
                }

                left++;
            }
        }
        return ansl == -1 ? "":s.substring(ansl, ansR);
    }

    //验证是否全部包含
    public boolean check() {
        Iterator ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry next = (Map.Entry) ite.next();
            Character key = (Character) next.getKey();
            Integer value = (Integer) next.getValue();
            if (count.getOrDefault(key, 0) < value)
                return false;
        }
        return true;
    }
}
