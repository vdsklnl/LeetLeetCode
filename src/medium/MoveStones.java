package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-04-07 16:35
 * @description
 */
public class MoveStones {
    public static void main(String[] args) {
        numMovesStonesII(new int[] {7,9,4});
    }

    public static int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        if (stones[n - 1] - stones[0] == n - 1)
            return new int[]{0, 0};
        int max = Math.max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - n + 1;
        //最小操作数是将石子排到空间大小为n的操作数
        int min = n;
        for (int i = 0, j = 0; i < n && j + 1 < n; i++) {
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n)
                j++;
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1)
                min = Math.min(min, 2);
            else
                min = Math.min(min, n - (j - i + 1));
        }
        return new int[] {min, max};
    }
}
