package medium;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author vdsklnl
 * @create 2022-09-05 21:12
 * @description
 */
public class RebuildQueue {
    public static void main(String[] args) {

    }

    public int[][] reconstructQueue(int[][] people) {
        //先按身高降序排列，身高相同k小在前
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return b[0] - a[0];
        });

        LinkedList<int[]> result = new LinkedList<>();
        for (int[] p:people) {
            //p[1]为坐标，先由高到低排列后，按顺序插入则完成
            result.add(p[1], p);
        }
        return result.toArray(new int[people.length][]);
    }
}
