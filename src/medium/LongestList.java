package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-03 14:57
 * @description
 */
public class LongestList {
    public static void main(String[] args) {

    }

    public int findLongestChain(int[][] pairs) {
        //对后一数进行排序，再比较前一数记录结果 (升序排列)
        //lambda表达式，重写比较方法
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int res = 1, tmp = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (tmp < pairs[i][0]) {
                res++;
                tmp = pairs[i][1];
            }
        }
        return res;
    }
}
