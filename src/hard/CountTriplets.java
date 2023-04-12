package hard;

/**
 * @author vdsklnl
 * @create 2023-03-04 13:34
 * @description 982 位与0三元组
 */
public class CountTriplets {
    public static void main(String[] args) {

    }

    //使用数组记录一次位与结果及数量，再与一次增加结果
    public int countTriplets(int[] nums) {
        int[] count = new int[1 << 16];
        for (int i:nums) {
            for (int j:nums) {
                count[i & j]++;
            }
        }
        int res = 0;
        for (int num:nums) {
            for (int i = 0; i < (1 << 16); i++) {
                if ((num & i) == 0)
                    res += count[i];
            }
        }
        /*
            位与为0，则num对应0位置才能出现1，首先异或0^ffff，在对应位置才能为1，则为结果子集
            子集优化：二进制枚举子集，不断(sub - 1)^x = sub，均是子集直至0，只有位与为1的位置才能满足要求
         */
        for (int num:nums) {
            num = num ^ 0xffff;
            for (int sub = num; sub != 0; sub = (sub - 1) & num) {
                res += count[sub];
            }
            res += count[0];
        }
        return res;
    }
}
