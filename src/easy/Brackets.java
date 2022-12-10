package easy;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-08-09 16:10
 * @description
 */
public class Brackets {
    public static void main(String[] args) {
//        String s = "()";
//        String s = "()[]{}";
//        String s = "(]";
//        String s = "([)]";
        String s = "{[]}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.empty()) {
                stack.push(s.charAt(i));
                continue;
            }
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    if (stack.peek().equals('('))
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                case ']':
                    if (stack.peek().equals('['))
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                case '}':
                    if (stack.peek().equals('{'))
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                default:
                    break;
            }
        }
        if (stack.empty())
            return true;
        else
            return false;
    }

}
