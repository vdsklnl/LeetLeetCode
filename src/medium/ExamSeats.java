package medium;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author vdsklnl
 * @create 2023-02-06 16:51
 * @description 855 考场就座 树集合 + 优先队列
 */
public class ExamSeats {
    int n;
    //使用树集合来保存每个学生的位置
    TreeSet<Integer> seats;
    //优先队列，使用数对记录位置并进行排序
    PriorityQueue<int[]> pq;

    public ExamSeats(int n) {
        this.n = n;
        this.seats = new TreeSet<Integer>();
        this.pq = new PriorityQueue<int[]>((a, b) -> {
            int d1 = a[1] - a[0], d2 = b[1] - b[0];
            return d1 / 2 < d2 / 2 || (d1 / 2 == d2 / 2 && a[0] > b[0]) ? 1 : -1;
        });
    }

    public int seat() {
        if (seats.isEmpty()) {
            seats.add(0);
            return 0;
        }
        int left = seats.first(), right = n - 1 - seats.last();
        while (seats.size() >= 2) {
            int[] p = pq.peek();
            //不包含元素全部删除，直至区间变成有效区间
            if (seats.contains(p[0]) && seats.contains(p[1]) && seats.higher(p[0]) == p[1]) { // 不属于延迟删除的区间
                int d = p[1] - p[0];
                if (d / 2 < right || d / 2 <= left) { // 最左或最右的座位更优
                    break;
                }
                pq.poll();
                pq.offer(new int[]{p[0], p[0] + d / 2});
                pq.offer(new int[]{p[0] + d / 2, p[1]});
                seats.add(p[0] + d / 2);
                return p[0] + d / 2;
            }
            pq.poll(); // leave 函数中延迟删除的区间在此时删除
        }
        if (right > left) { // 最右的位置更优
            pq.offer(new int[]{seats.last(), n - 1});
            seats.add(n - 1);
            return n - 1;
        } else {
            pq.offer(new int[]{0, seats.first()});
            seats.add(0);
            return 0;
        }
    }

    public void leave(int p) {
        //延迟删除，在添加时删除旧区间，根据集合元素
        if (p != seats.first() && p != seats.last()) {
            int prev = seats.lower(p), next = seats.higher(p);
            pq.offer(new int[]{prev, next});
        }
        seats.remove(p);
    }
}
