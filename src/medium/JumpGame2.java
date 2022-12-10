package medium;

/**
 * @author vdsklnl
 * @create 2022-09-05 16:19
 * @description
 */
public class JumpGame2 {
    public static void main(String[] args) {

    }

    public int jump(int[] nums) {
        if (nums.length == 1)
            return 0;

        int result = 0;
        //当前跳跃范围
        int curDis = 0;
        //下一步跳跃范围
        int maxDis = 0;

        for (int i = 0; i <= curDis; i++) {
            maxDis = Math.max(maxDis, i + nums[i]);
            if (i == curDis) {
                curDis = maxDis;
                result++;
                if (curDis >= nums.length - 1)
                    break;
            }
        }

        return result;
    }
}
