package hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author vdsklnl
 * @create 2022-09-22 14:57
 * @description 239 单调队列
 */

public class SlidingWindowMax {
    public static void main(String[] args) {
        int[] nums = {1,3,1,2,0,5};
        maxSlidingWindowII(nums, 3);
    }

    //解法二:利用双端队列手动实现单调队列
    /**
     * 用一个单调队列来存储对应的下标，每当窗口滑动的时候，直接取队列的头部指针对应的值放入结果集即可
     * 单调队列类似 （tail -->） 3 --> 2 --> 1 --> 0 (--> head) (右边为头结点，元素存的是下标)
     */
    public static int[] maxSlidingWindowII(int[] nums, int k) {
        //ArrayDeque:Java中，双端队列的数组实现
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            //当队列头元素到现元素范围大于k，直接弹出
            if (!deque.isEmpty() && deque.peek() < i - k + 1)
                deque.poll();

            //当现元素大于队尾元素，队尾元素需弹出
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()])
                deque.removeLast();

            deque.add(i);

            //此时已添加k个元素，可以逐步填入结果集
            if (i >= k - 1) {
                res[index++] = nums[deque.peek()];
            }
        }

        return res;
    }


    //研究滑动窗口最大值，存放单调队列，始终保持递减
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1)
            return nums;

        int index = 0;
        int[] res = new int[nums.length - k + 1];
        MyQueue myQueue = new MyQueue();

        //先将前k个元素放入队列
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        res[index++] = myQueue.peek();

        //判断后续元素
        for (int i = k; i < nums.length; i++) {
            //移动窗口，先判断是否将队首元素弹出
            myQueue.poll(nums[i - k]);
            //将新元素加入队列，更新队列
            myQueue.add(nums[i]);
            //队列头元素加入结果集
            res[index++] = myQueue.peek();
        }

        return res;
    }
}

//使用Deque作为基本容器实现，单调队列 -> Deque
class MyQueue {
    Deque<Integer> deque = new LinkedList<>();

    //添加元素，保持递减，若加入元素大于队首，则将所有全部弹出
    void add(int num) {
        while (!deque.isEmpty() && num > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(num);
    }

    //弹出元素，当遍历到的元素与队列头元素相同，则将头元素弹出
    void poll(int num) {
        if (!deque.isEmpty() && num == deque.peek()) {
            deque.poll();
        }
    }

    //查看队列首元素，始终为添加元素最大值
    int peek() {
        return deque.peek();
    }
}
