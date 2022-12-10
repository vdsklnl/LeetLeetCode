package hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author vdsklnl
 * @create 2022-11-05 15:18
 * @description 1106 单调栈
 */
public class BooleanExpression {
    public static void main(String[] args) {

    }

    public boolean parseBoolExpr(String expression) {
        Deque<Character> deque = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char ch = expression.charAt(i);
            if (ch == ',')
                continue;
            else if (ch != ')') {
                deque.push(ch);
            } else {
                  int t = 0, f = 0;
                  while (deque.peek() != '(') {
                      if (deque.pop() == 't')
                          t++;
                      else
                          f++;
                  }
                  deque.pop();
                  switch (deque.pop()) {
                      case '!':
                          deque.push(t > f ? 'f':'t');
                          break;
                      case '&':
                          deque.push(f > 0 ? 'f':'t');
                          break;
                      case '|':
                          deque.push(t > 0 ? 't':'f');
                          break;
                      default:
                  }
            }
        }
        return deque.pop() == 't';
    }
}
