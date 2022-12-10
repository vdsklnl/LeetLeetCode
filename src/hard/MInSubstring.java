package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-12 12:27
 * @description
 */
public class MInSubstring {
    public static void main(String[] args) {

    }

    public String minWindow(String s, String t) {
        if (t == "" || t.length() > s.length())
            return  "";

        int first = 0, mid = 0, last = 0;
        int tf = 0, tl = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(tl)) {
                first = i;
                if (tl < t.length())
                    tl++;

            }
        }
        return "";
    }
}
