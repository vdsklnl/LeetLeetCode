package hard;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-09-17 15:33
 * @description 42
 */
public class Rainwater {
    public static void main(String[] args) {

    }

    /**
     * 列计算：
     * 每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。
     * rainwater = Math.min(leftHigh, rightHigh) - height
     */

    //双指针:时间复杂度高
    public static int trapDoublePointer(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            //首尾不接雨水
            if (i == 0 || i == height.length - 1)
                continue;

            int left = 0;
            for (int j = i - 1; j >= 0; j--) {
                left = Math.max(left, height[j]);
            }

            int right = 0;
            for (int j = i + 1; j < height.length; j++) {
                right = Math.max(right, height[j]);
            }

            int water = Math.min(left, right) - height[i];
            if (water > 0)
                res += water;
        }
        return res;
    }

    //动态规划：提前将左右边最高柱子记录
    public static int trapDynamicProgramming(int[] height) {
        int res = 0, n = height.length;
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];

        dpLeft[0] = height[0];
        for (int i = 1; i < n; i++) {
            dpLeft[i] = Math.max(dpLeft[i - 1], height[i]);
        }

        dpRight[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dpRight[i] = Math.max(dpRight[i + 1], height[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int water = Math.min(dpLeft[i], dpRight[i]) - height[i];
            if (water > 0)
                res += water;
        }
        return res;
    }

    /**
     * 行计算：
     * 要求宽度的时候 如果遇到相同高度的柱子，需要使用最右边的柱子来计算宽度
     * 栈存放从顶到底递增
     */

    //单调栈：按照行方向计算
    public static int trapStack(int[] height) {
        int res = 0, n = height.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n; i++) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            } else if (height[i] == height[stack.peek()]) {
                //相同时，将最新的压入，相等的相邻墙，左边一个是不可能存放雨水的
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int low = stack.pop();
                    if (!stack.isEmpty()) {
                        int high = Math.min(height[i], height[stack.peek()]) - height[low];
                        if (high > 0)
                            res += high * (i - stack.peek() - 1);
                    }
                }
                stack.push(i);
            }
        }
        return res;
    }
}
