package hard;

import java.io.PrintWriter;
import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-04-16 20:59
 * @description 1157 随机化 + 二分查找
 */
public class MajorityChecker {
    static final int K = 20;
    int[] arr;
    Map<Integer, List<Integer>> map;
    Random random;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            //记录数据出现位置集合
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        random = new Random();
    }

    public int query(int left, int right, int threshold) {
        int len = right - left + 1;
        for (int i = 0; i < K; i++) {
            int x = arr[left + random.nextInt(len)];
            List<Integer> list = map.get(x);
            //区间是[left, right]，二分查找时，“=”情况包含在right中，但不包含在left中
            int cnt = queryEnd(list, right) - queryStart(list, left);
            if (cnt >= threshold)
                return x;
            else if (cnt * 2 >= len)
                return -1;
        }
        return -1;
    }

    public int queryEnd(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (list.get(mid) > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int queryStart(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (list.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        MajorityChecker mc = new MajorityChecker(new int[]{1, 2, 3});
        mc.query(0, 2, 2);
    }
}
