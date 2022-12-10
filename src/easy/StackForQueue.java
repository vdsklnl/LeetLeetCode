package easy;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-08-16 21:22
 * @description
 */
public class StackForQueue {
}

class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        dumpIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public void dumpIn() {
        if (!stackOut.isEmpty())
            return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}
