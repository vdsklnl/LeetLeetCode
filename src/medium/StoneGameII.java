package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2023-02-22 14:38
 * @description 1140 记忆化搜索
 */
public class StoneGameII {
    public static void main(String[] args) {

    }

    /*
        记忆化搜索，寻找每一步可以比对手多获得的石子，结果为（总多获得的石子 + 总石子数）/ 2
        dfs(i, m):表示已取走i堆，可以取[1, 2 * m]堆石子情况下多获得石子数
     */
    Map<Integer, Integer> map;
    int[] preSum;

    public int stoneGame(int[] piles) {
        int n = piles.length;
        preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + piles[i];
        }
        map = new HashMap<>();
        return (preSum[n] + backtracking(piles,0, 1)) / 2;
    }

    public int backtracking(int[] piles,int i, int m) {
        if (i == piles.length)
            return 0;
        //保证每一种情况不会重复存储
        int key = i * 201 + m;
        if (!map.containsKey(key)) {
            int value = Integer.MIN_VALUE;
            for (int x = 1; x <= 2 * m; x++) {
                if (i + x > piles.length)
                    break;
                value = Math.max(value, preSum[i + x] - preSum[i] - backtracking(piles, i + x, Math.max(m, x)));
            }
            map.put(key, value);
        }
        return map.get(key);
    }
}
