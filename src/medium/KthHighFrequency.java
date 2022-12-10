package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-09-21 16:43
 * @description 347 优先级队列，大顶堆、小顶堆，堆排序
 */
public class KthHighFrequency {
    public static void main(String[] args) {

    }

    /*
        要统计元素出现频率
        对频率排序
        找出前K个高频元素
     */

    //基于大顶堆实现:降序排列
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        //使用优先队列:出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o2[1] - o1[1]));
        for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] res = new int[k];
        //依次从队头弹出k个,就是出现频率前k高的元素
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }

        return res;
    }

    //基于小顶堆实现:升序排列
    public int[] topKFrequentII(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        //使用优先队列:出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
            //维持k个元素有序
            if (pq.size() < k)
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            else {
                if (entry.getValue() > pq.peek()[1]) {
                    //弹出队头，添加新元素
                    pq.poll();
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }

        int[] res = new int[k];
        //依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll()[0];
        }

        return res;
    }
}
