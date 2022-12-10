package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-07 10:46
 * @description
 */
public class RearrangeWords {
    public static void main(String[] args) {

    }

    public String reorderSpaces(String text) {
        int len = text.length();

        //切割并统计空格数
        String[] words = text.trim().split("\\s+");
        int spaces = len;
        for (String word:words) {
            spaces -= word.length();
        }

        StringBuilder sb = new StringBuilder();
        if (words.length == 1) {
            sb.append(words[0]);
            while (spaces > 0) {
                sb.append(" ");
                spaces--;
            }
            return sb.toString();
        }

        int space = spaces / (words.length - 1);
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i]);
            int temp = space;
            while (temp > 0) {
                sb.append(" ");
                temp--;
            }
        }
        sb.append(words[words.length - 1]);

        while (sb.length() < len) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public String reorderSpacesDoublePointer(String text) {
        List<String> words = new ArrayList<>();
        //统计空格数
        int spaces = 0;
        for (int i = 0; i < text.length(); ) {
            if (text.charAt(i) == ' ') {
                i++;
                spaces++;
                continue;
            }
            int j = i;
            while (j < text.length() && text.charAt(j) != ' ')
                j++;
            words.add(text.substring(i, j));
            i = j;
        }

        StringBuilder sb = new StringBuilder();
        int space = spaces / Math.max(1, words.size() - 1);
        String temp = "";
        while (space-- > 0)
            temp += " ";
        for (int i = 0; i < words.size() - 1; i++) {
            sb.append(words.get(i));
            sb.append(temp);
        }
        sb.append(words.get(words.size() - 1));

        while (sb.length() < text.length()) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
