package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-06 17:00
 * @description
 */
public class NoOverlappingArea {
    /*
     * 与射箭452异曲同工
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int result = 1, rightBound = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= rightBound) {
                result++;
                rightBound = intervals[i][1];
            } else {
                rightBound = Math.min(rightBound, intervals[i][1]);
            }
        }
        return intervals.length - result;
    }

    public static void main(String[] args) {

    }
}
