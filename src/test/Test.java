package test;

/**
 * @author vdsklnl
 * @create 2023-04-04 20:32
 * @description
 */
public class Test {
    public static long countKPowerSums(long num, long[] powers) {
        long bin = 0;
        for (int i = powers.length - 1; i >= 0; i--) {
            if (powers[i] > 0 && powers[i] <= num) {
                num -= powers[i];
                bin |= 1L << i;
            }
        }
        return bin;
    }

    public static long kPowerSumCountInRange(long l, long r, int k) {
        long[] powers = new long[64];
        powers[0] = 1;
        for (int i = 1; i < powers.length; i++) {
            powers[i] = powers[i - 1] * k;
            if (powers[i] > r) {
                powers[i] = 0;
            }
        }
        long leftCount = countKPowerSums(l - 1, powers);
        long rightCount = countKPowerSums(r, powers);
        return rightCount - leftCount;
    }

//    public static long maxKPowerSum(long num, int k) {
//        long left = 1, right = num;
//        long ans = 0;
//        while (left <= right) {
//            long mid = left + (right - left) / 2;
//            long count = kPowerSumCountInRange(0, mid, k);
//            if (count > 0) {
//                ans = mid;
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return ans;
//    }

    public static void main(String[] args) {
        long l = 1L, r = 210000L;
        int k = 4;
        System.out.println(kPowerSumCountInRange(l, r, k));
    }
}
