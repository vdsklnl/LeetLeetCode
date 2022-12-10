package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-11-07 9:09
 * @description 816
 */
public class FuzzyCoordinates {
    public static void main(String[] args) {

    }

    public List<String> ambiguousCoordinates(String s) {
        //划分两部分分别统计，再叠加
        s = s.substring(1, s.length() - 1);
        int n = s.length();
        List<String> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            List<String> prefix = getPos(s.substring(0, i));
            if (prefix.isEmpty())
                continue;
            List<String> suffix = getPos(s.substring(i));
            if (suffix.isEmpty())
                continue;
            for (String pre:prefix) {
                for (String suf:suffix) {
                    res.add("(" + pre + ", " + suf + ")");
                }
            }
        }
        return res;
    }
    
    public List<String> getPos(String s) {
        //将字符分成两部分添加.
        List<String> list = new ArrayList<>();
        if (s.charAt(0) != '0' || "0".equals(s))
            list.add(s);
        for (int i = 1; i < s.length(); i++) {
            if (i != 1 && s.charAt(0) == '0' || s.charAt(s.length() - 1) == '0')
                continue;
            list.add(s.substring(0, i) + "." + s.substring(i));
        }
        return list;
    }
}
