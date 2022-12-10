package hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-11-30 14:00
 * @description 895 Hash表 + 栈
 */
public class MaxFrequenceStack {
    public static void main(String[] args) {

    }

    //对每个频率建立栈（队列），记录最大频率，并弹出后面的元素
    Map<Integer, Integer> fre;
    Map<Integer, Deque<Integer>> map;
    int maxFre;

    public MaxFrequenceStack() {
        fre = new HashMap<>();
        map = new HashMap<>();
        maxFre = 0;
    }

    public void push(int val) {
        fre.put(val, fre.getOrDefault(val, 0) + 1);
        map.putIfAbsent(fre.get(val), new ArrayDeque<>());
        map.get(fre.get(val)).push(val);
        maxFre = Math.max(maxFre, fre.get(val));
    }

    public int pop() {
        int val = map.get(maxFre).pop();
        fre.put(val, fre.get(val) - 1);
        if (map.get(maxFre).isEmpty())
            maxFre--;
        return val;
    }
}
