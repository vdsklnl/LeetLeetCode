package hard;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-10-28 20:36
 * @description 295 数据流中位数
 */
public class MedianFinder {
    public static void main(String[] args) {

    }

    //维护两个优先队列获得中位数，两个队列长度差距小于等于1
    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public MedianFinder() {
        //min 大值在头部，max 小值在头部
        queMin = new PriorityQueue<>((a, b) -> b - a);
        queMax = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMin.size() > queMax.size() + 1)
                queMax.offer(queMin.poll());
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size())
                queMin.offer(queMax.poll());
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size())
            return queMin.peek();
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}
