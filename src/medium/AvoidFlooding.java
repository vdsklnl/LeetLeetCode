package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author vdsklnl
 * @create 2022-10-25 14:42
 * @description 1488 贪心 + 二分
 */
public class AvoidFlooding {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("01234567");
        stringBuilder.delete(3, 7);
        System.out.println(stringBuilder);
    }

    public int[] avoidFlood(int[] rains) {
        //贪心 + 二分
        int n = rains.length;
        int[] res = new int[n];
        //存储区域和相应下雨天数
        Map<Integer, Integer> map = new HashMap<>();
        //使用TreeSet数据结构避免手动二分
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            if (rains[i] == 0) {
                res[i] = 1;
                set.add(i);
            } else {
                //已经下过雨，先泄洪
                if (map.containsKey(rains[i])) {
                    //ceiling -> 返回Set中大于等于e的最小元素
                    //higher -> 大于
                    Integer ceiling = set.ceiling(map.get(rains[i]));
                    if (ceiling == null)
                        return new int[0];
                    res[ceiling] = rains[i];
                    set.remove(ceiling);
                }
                map.put(rains[i], i);
            }
        }
        return res;
    }
}
