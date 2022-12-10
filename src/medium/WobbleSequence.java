package medium;

/**
 * @author vdsklnl
 * @create 2022-09-05 13:46
 * @description
 */
public class WobbleSequence {
    public static void main(String[] args) {

    }

    public int wiggleMaxLength(int[] nums) {
        //贪心算法，去除山谷与山峰之间的值
        if (nums.length <= 1) {
            return nums.length;
        }

        int preDiff = 0, curDiff = 0;
        int result = 1; //此时最少一个
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            //curDiff = 0, 不记录跳过
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                preDiff = curDiff;
                result++;
            }
        }

        return result;
    }
}
