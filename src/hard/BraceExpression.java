package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-03-07 14:34
 * @description 1096 栈操作
 */
public class BraceExpression {
    public static void main(String[] args) {
        braceExpansionII("{a,b}{c,{d,e}}");
        Stack<String> s = new Stack<>();
    }

    //使用栈存放字符串和运算符号，不断弹出进行运算，运算符为'+' '*'；
    public static List<String> braceExpansionII(String expression) {
        Deque<Character> deque = new ArrayDeque<>();
        List<Set<String>> list = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '{') {
                //考虑是否要添加 * 号
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1))))
                    deque.add('*');
                deque.add('{');
            } else if (expression.charAt(i) == ',') {
                //不断运算，直至栈顶不为'*'，添加'+'号
                while (!deque.isEmpty() && deque.peekLast() == '*') {
                    operation(deque, list);
                }
                deque.add('+');
            } else if (expression.charAt(i) == '}') {
                //不断运算，直至找到'{'，两者抵消
                while (!deque.isEmpty() && deque.peekLast() != '{') {
                    operation(deque, list);
                }
                deque.pollLast();
            } else {
                //先判断是否要添加'*'号，再添加新集合进入list
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    deque.add('*');
                }
                Set<String> set = new TreeSet<>();
                set.add("" + expression.charAt(i));
                list.add(set);
            }
        }
        //进行剩下的运算
        while (!deque.isEmpty())
            operation(deque, list);
        return new ArrayList<>(list.get(list.size() - 1));
    }

    private static void operation(Deque<Character> deque, List<Set<String>> list) {
        //每次运算，将list顶上两组Set合并
        int l = list.size() - 2, r = list.size() - 1;
        if (deque.peekLast() == '+')
            list.get(l).addAll(list.get(r));
        else {
            Set<String> set = new TreeSet<>();
            for (String left:list.get(l)) {
                for (String right:list.get(r)) {
                    set.add(left + right);
                }
            }
            list.set(l, set);
        }
        deque.pollLast();
        list.remove(r);
    }
}
