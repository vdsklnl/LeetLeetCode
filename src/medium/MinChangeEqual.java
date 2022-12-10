package medium;

/**
 * @author vdsklnl
 * @create 2022-12-07 17:10
 * @description 1775 Hash + 贪心
 */
public class MinChangeEqual {
    public static void main(String[] args) {
        int[] n1 = {1,2,3,4,5,6};
        int[] n2 = {1,1,2,2,2,2};
        minOperations(n1, n2);
    }

    public static int minOperations(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 * 6 < n2 || n1 > 6 * n2)
            return -1;
        int[] c1 = new int[6], c2 = new int[6];
        int diff = 0;
        for (int num:nums1) {
            diff += num;
            c1[num - 1]++;
        }
        for (int num:nums2) {
            diff -= num;
            c2[num - 1]++;
        }
        if (diff == 0)
            return 0;
        if (diff > 0)
            return change(c1, c2, diff);
        return change(c2, c1, -diff);
    }

    public static int change(int[] c1, int[] c2, int diff) {
        //同时操作的数呈现镜像关系
        int[] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] += c1[i];
            c[6 - i] += c2[i];
        }
        int res = 0;
        for (int i = 5; i >= 1 && diff > 0; i--) {
            //相当于ceil函数，12 / 5 -> 只需变三次
            int t = Math.min((diff + i - 1) / i, c[i]);
            res += t;
            diff -= t * i;
        }
        return res;
    }
}
