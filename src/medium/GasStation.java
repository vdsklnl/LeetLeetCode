package medium;

/**
 * @author vdsklnl
 * @create 2022-09-05 17:30
 * @description
 */
public class GasStation {
    public static void main(String[] args) {

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, start = 0, cur = 0;
        for (int i = 0; i < gas.length; i++) {
            cur += (gas[i] - cost[i]);
            sum += (gas[i] - cost[i]);
            if (cur < 0) {
                start = (i + 1) % gas.length;
                cur = 0;
            }
        }

        //跑不完全程
        if (sum < 0) {
            return -1;
        }
        return start;
    }
}
