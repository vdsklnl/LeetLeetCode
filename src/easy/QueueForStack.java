package easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-08-16 21:18
 * @description
 */
public class QueueForStack {
    public static void main(String[] args) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.poll();
//        System.out.println(queue.peek());
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        stack.pop();
    }
}

/*
 * 队列是一种特殊的线性表，它只允许在表的前端进行删除操作，而在表的后端进行插入操作。
 * 前出后进
 */

class MyStack {
    int size;
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        //除最后一位数，重新装载
        int size = queue.size() - 1;
        while (size > 0) {
            queue.add(queue.peek());
            queue.poll();
            size--;
        }

        int res = queue.peek();
        queue.poll();

        return res;
    }

    public int top() {
        int size = queue.size() - 1;
        while (size > 0) {
            queue.add(queue.peek());
            queue.poll();
            size--;
        }

        int res = queue.peek();
        queue.add(queue.poll());

        return res;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}


