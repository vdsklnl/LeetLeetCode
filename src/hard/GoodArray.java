package hard;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2023-02-15 14:27
 * @description 1250 数论
 */
public class GoodArray {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
    }

    //裴蜀定理：对数a b，记最大公约数g = gcd(a, b)，一定存在整数x y，使得a * x + b * y = g
    //除非数组中全是成倍数（不含1），否则一定存在子集满足条件，即两个数最大公约数为1
    public boolean isGoodArray(int[] nums) {
        int g = nums[0];
        for (int num:nums) {
            g = gcd(g, num);
            if (g == 1)
                break;
        }
        return g == 1;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a:gcd(b, a % b);
    }
}
