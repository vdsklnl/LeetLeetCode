package medium;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2023-02-06 17:01
 * @description 1801 优先队列
 */
public class BackLogOrders {
    public static void main(String[] args) {

    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>();
        int mod = (int) 1e9 + 7;
        long res = 0;
        for (int[] order:orders) {
            int price = order[0], amount = order[1], type = order[2];
            if (type == 0) {
                while (amount > 0 && !sell.isEmpty() && sell.peek()[0] <= price) {
                    int[] s = sell.poll();
                    int count = Math.min(amount, s[1]);
                    amount -= count;
                    s[1] -= count;
                    if (s[1] > 0)
                        sell.offer(new int[] {s[0], s[1]});
                }
                if (amount > 0)
                    buy.offer(new int[] {price, amount});
            } else {
                while (amount > 0 && !buy.isEmpty() && buy.peek()[0] >= price) {
                    int[] b = buy.poll();
                    int count = Math.min(amount, b[1]);
                    amount -= count;
                    b[1] -= count;
                    if (b[1] > 0)
                        buy.offer(new int[] {b[0], b[1]});
                }
                if (amount > 0)
                    sell.offer(new int[] {price, amount});
            }
        }
        while (!sell.isEmpty()) {
            res += sell.poll()[1];
            res %= mod;
        }
        while (!buy.isEmpty()) {
            res += buy.poll()[1];
            res %= mod;
        }
        return (int) res;
    }
}
