package easy;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-08-11 22:24
 * @description
 */
public class StringCompare {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "bbcd";
        System.out.println(backspaceCompare(s, t));
    }

    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> chars = new Stack<>();
        Stack<Character> chart = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (chars.size() != 0)
                    chars.pop();
                continue;
            }
            chars.push(s.charAt(i));
        }

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if (chart.size() != 0)
                    chart.pop();
                continue;
            }
            chart.push(t.charAt(i));
        }

        if (chars.size() != chart.size())
            return false;

        int len = chars.size();

        for (int i = 0; i < len;i++) {
            if (chars.pop() != chart.pop())
                return false;
        }

        return true;
    }
}
