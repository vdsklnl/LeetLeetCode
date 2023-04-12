package medium;

/**
 * @author vdsklnl
 * @create 2023-02-06 19:17
 * @description 1824 动态规划
 */
public class MinSideJumps {
    public static void main(String[] args) {

    }

    public int minSideJumps(int[] obstacles) {
        //动态规划，每前进一步计算三个位置最小值
        int n = obstacles.length - 1;
        int[] res = new int[3];
        res[0] = 1; res[2] = 1;
        for (int i = 1; i <= n; i++) {
            //计算之前一步最小值
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1)
                    res[j] = Integer.MAX_VALUE;
                else
                    min = Math.min(min, res[j]);
            }
            //更新最新状态
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1)
                    continue;
                res[j] = Math.min(res[j], min + 1);
            }
        }
        return Math.min(res[0], Math.min(res[1], res[2]));
    }
}
