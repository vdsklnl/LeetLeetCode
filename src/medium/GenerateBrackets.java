package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-05 21:37
 * @description 22 回溯法
 */
public class GenerateBrackets {
    public static void main(String[] args) {

    }

    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        backtracking(0, 0,new StringBuilder(), n);
        return res;
    }

    public void backtracking(int open, int end, StringBuilder sb, int max) {
        if (sb.length() == 2 * max) {
            res.add(sb.toString());
            return;
        }
        //先放左括号
        if (open < max) {
            sb.append('(');
            backtracking(open + 1, end, sb, max);
            sb.deleteCharAt(sb.length() - 1);
        }
        //再放右括号，但不能超过左括号
        if (end < open) {
            sb.append(')');
            backtracking(open, end + 1, sb, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
