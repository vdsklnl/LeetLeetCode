package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-11-18 14:43
 * @description 891
 */
public class SubSequenceWidthSum {
    public static void main(String[] args) {

    }

    /*
        对序列，每个元素对结果贡献与位置无关
        {4, 1, 3, 6, 2, 5} -> {1, 2, 3, 4, 5, 6}
        其中，4作为序列最小值和最大值对结果的贡献为(2^i - 2^(n-1-i)) * 4，i为排序后下标
        相当于左边选元素有2^i种可能，右边选元素有2^(n-1-i)种可能
        排序后，元素值相同不影响结果，只考虑左右，不会重复
     */
    public int sumSubseqWidths(int[] nums) {
        //上述式子可以转化为计算2^i贡献，2^i * (nums[i] - nums[n - 1 - i])
        final int mod = (int) 1e9 + 7;
        Arrays.sort(nums);
        long res = 0l, pow = 1l;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            res += (nums[i] - nums[n - 1 - i]) * pow;
            pow = pow * 2 % mod;
        }
        return (int) (res % mod + mod) % mod;
    }
}
