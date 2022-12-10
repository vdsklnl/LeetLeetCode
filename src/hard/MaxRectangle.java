package hard;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-09-17 21:32
 * @description 82 largestRectangleArea
 */
public class MaxRectangle {
    public static void main(String[] args) {

    }

    /**
     * 对当前元素，最大矩形为：
     * 宽度：左右两边第一个小于本身之间
     * 高度：元素本身
     */

    //动态规划
    public static int DynamicProgramming(int[] heights) {
        int n = heights.length;
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];

        dpLeft[0] = -1;
        for (int i = 1; i < n; i++) {
            int temp = i - 1;
            // 这里不是用if，而是不断向右寻找的过程
            while (temp >= 0 && heights[temp] >= heights[i]) {
                temp = dpLeft[temp];
            }
            dpLeft[i] = temp;
        }

        dpRight[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int temp = i + 1;
            while (temp < n && heights[temp] >= heights[i]) {
                temp = dpRight[temp];
            }
            dpRight[i] = temp;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (dpRight[i] - dpLeft[i] - 1));
        }
        return res;
    }

    //单调栈
    public static int singleStack(int[] heights) {
        // 数组扩容，在头和尾各加入一个元素，计算时采用3个元素：本身，左右第一个小值
        int [] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int index = 0; index < heights.length; index++){
            newHeights[index + 1] = heights[index];
        }
        heights = newHeights;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int res = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else if (heights[i] == heights[stack.peek()]) {
                //相同时，将最新的压入，相等的相邻墙，左边一个是不可能存放雨水的
                stack.pop();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int low = stack.pop();
                    if (!stack.isEmpty()) {
                        res = Math.max(res, heights[low] * (i - stack.peek() - 1));
                    }
                }
                stack.push(i);
            }
        }
        return res;
    }
}
