package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author vdsklnl
 * @create 2022-11-01 19:18
 * @description 659 Hash表 + 最小堆 || 贪心
 */
public class SeparateContinuousSubArr {
    public static void main(String[] args) {

    }

    public boolean isPossible(int[] nums) {
        //堆中存放子序列长度
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num:nums) {
            if (!map.containsKey(num))
                map.put(num, new PriorityQueue<>());
            if (map.containsKey(num - 1)) {
                int pre = map.get(num - 1).poll();
                //表明之前的序列只有一，删除
                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
                map.get(num).offer(pre + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        //判断map中每一条序列长度
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry:entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3)
                return false;
        }
        return true;
    }

    //贪心
    public boolean isPossibleII(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> arrMap = new HashMap<>();
        for (int num:nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        //根据数量生成序列，arrMap中记录以key为结尾序列有几条
        for (int num:nums) {
            int count = countMap.get(num);
            if (count > 0) {
                int pre = arrMap.getOrDefault(num - 1, 0);
                //存在以num - 1为结尾的序列，直接将num加入，序列以num结尾
                if (pre > 0) {
                    countMap.put(num, count - 1);
                    arrMap.put(num, arrMap.getOrDefault(num, 0) + 1);
                    arrMap.put(num - 1, pre - 1);
                } else {
                    //不存在，以num为首找是否满足条件
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    //存在，直接建立以num + 2结尾的序列
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        arrMap.put(num + 2, arrMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        //不存在，返回false
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
