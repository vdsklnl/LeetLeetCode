package medium;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-09-16 16:21
 * @description 739
 */
public class DailyTemperature {
    public static void main(String[] args) {

    }

    public static int[] dailyTemperatures(int[] temperatures) {
        //单调栈
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int pre = stack.pop();
                res[pre] = i - pre;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            res[stack.pop()] = 0;
        }
        return res;
    }
}
