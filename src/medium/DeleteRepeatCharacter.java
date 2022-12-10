package medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-11-01 15:52
 * @description 316 贪心 + 单调栈
 */
public class DeleteRepeatCharacter {
    public static void main(String[] args) {
        String s = "cbacdcbc";
        removeDuplicateLetters(s);
    }

    public static String removeDuplicateLetters(String s) {
        //int数组记录字母数，boolean数组记录是否出现过
        int[] chars = new int[26];
        boolean[] isAppear = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        //使用单调栈维护字符顺序(此时sb模拟为单调栈)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!isAppear[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    //保证后面还有才能弹出
                    if (chars[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        isAppear[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                isAppear[ch - 'a'] = true;
                sb.append(ch);
            }
            chars[ch - 'a']--;
        }

        return sb.toString();
    }
}
