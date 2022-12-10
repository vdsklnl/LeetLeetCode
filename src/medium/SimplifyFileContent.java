package medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author vdsklnl
 * @create 2022-11-11 14:10
 * @description 71 栈应用
 */
public class SimplifyFileContent {
    public static void main(String[] args) {
        String[] split = "path//dfa/f/f".split("/");
        System.out.println(split.length);
    }

    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Deque<String> deque = new LinkedList<>();
        for (String name:split) {
            if ("..".equals(name)) {
                if (!deque.isEmpty())
                    deque.pollLast();
            } else if (name.length() > 0 && !".".equals(name))
                deque.offer(name);
        }
        StringBuilder sb = new StringBuilder();
        if (deque.isEmpty())
            sb.append("/");
        else {
            while (!deque.isEmpty()) {
                sb.append("/").append(deque.pollFirst());
            }
        }
        return sb.toString();
    }
}
