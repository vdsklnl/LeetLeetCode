package hard;

/**
 * @author vdsklnl
 * @create 2022-10-23 21:50
 * @description
 */
public class FindMedianNum {
    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //不断遍历，找到中位数（奇数len + 1 / 2， 偶数len/2 + (len/2 + 1) / 2，记录最后两次结果）
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int pre = 0, cur = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i <= len / 2; i++) {
            pre = cur;
            if (s1 < m && (s2 >= n || nums1[s1] <= nums2[s2]))
                cur = nums1[s1++];
            else
                cur = nums2[s2++];
        }
        if ((len & 1) == 1) //len % 2 == 1 相当于len & 1 == 1
            return cur;
        return (pre + cur) / 2.0;
    }
}
