package medium;

/**
 * @author vdsklnl
 * @create 2023-02-06 19:29
 * @description 1664
 */
public class BalanceArray {
    public static void main(String[] args) {

    }

    public int waysToMakeFair(int[] nums) {
        //使用sum表示数组奇偶位数差值，判断后，当前位置及之前值与最初相同，后续判断时要相反操作
        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += i % 2 == 0 ? nums[i]:-nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            sum -= i % 2 == 0 ? nums[i]:-nums[i];
            res += sum == 0 ? 1:0;
            sum -= i % 2 == 0 ? nums[i]:-nums[i];
        }
        return res;
    }
}
