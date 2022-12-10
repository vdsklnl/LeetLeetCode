package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-16 14:40
 * @description 850 Scan Line 经典题目
 */
public class RectangleAreaII {
    public static void main(String[] args) {

    }

    /**
     * 将所有给定的矩形的左右边对应的 x 端点提取出来并排序，每个端点可看作是一条竖直的线段，
     * 问题转换为求解「由多条竖直线段分割开」的多个矩形的面积总和：
     * 相邻线段之间的宽度为单个矩形的「宽度」（通过 x 差值直接算得），
     * 问题转换为求该区间内高度的并集（即矩形的高度）
     */

    public static int rectangleArea(int[][] rectangles) {
        List<Integer> list = new ArrayList<>();

        //将所有矩形x坐标加入并排序
        for (int[] rec:rectangles) {
            list.add(rec[0]);
            list.add(rec[2]);
        }
        Collections.sort(list);

        //从首遍历到尾
        long res = 0, mod = (int) (1e9 + 7);
        for (int i = 1; i < list.size(); i++) {
            int left = list.get(i - 1);
            int right = list.get(i);
            int len = right - left;
            if (len == 0) continue;
            List<int[]> lines = new ArrayList<>();
            for (int[] rec:rectangles) {
                //说明此rec在区间内有贡献，统计y坐标值
                if (rec[0] <= left && rec[2] >= right) {
                    lines.add(new int[]{rec[1], rec[3]});
                }
            }
            //对y坐标组进行排序
            Collections.sort(lines, (l1, l2) -> l1[0] != l2[0] ? l1[0] - l2[0]:l1[1] - l2[1]);
            long tall = 0, low = -1, high = -1;
            for (int[] line:lines) {
                if (line[0] > high) {
                    tall += high - low;
                    low = line[0];
                    high = line[1];
                } else if (line[1] > high) {
                    high = line[1];
                }
            }
            //加上最后一次
            tall += high - low;
            res += tall * len;
            res %= mod;
        }

        return (int) res;
    }
}
