package medium;

/**
 * @author vdsklnl
 * @create 2022-10-20 14:40
 * @description 136 || 137 || 260
 */
public class AppearOneTime {
    public static void main(String[] args) {
        System.out.println(10 & -10);
    }

    //136 其他元素出现两次
    public int singleNumber(int[] nums) {
        //位运算，异或两次恢复原值
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    //137 其他元素出现三次
    public int singleNumberII(int[] nums) {
        /*
            DFA，全称 Deterministic Finite Automaton 即确定有穷自动机：从一个状态通过一系列的事件转换到另一个状态，
            即 state -> event -> state。
            本题需要三个状态来记录00 -> 01 -> 10 -> 00
            状态转换公式可以记忆
        */
        //出现一次记录在one中，三次one = 0；
        int one = 0, two = 0;
        for (int num:nums) {
            one = one ^ num & ~two;
            two = two ^ num & ~one;
        }
        return one;
    }

    //260 其余元素两次，有两个一次元素
    /*
        x = x1 异或 x2
        位运算 x&-x 取出 x 的二进制表示中最低位那个1，设其为第l位，那么x1和x2中的某一个数的
        二进制表示的第l位为 0，另一个数的二进制表示的第l位为1。
        因此，对最低位l进行异或，分为一个为0， 一个为1， 两个整数即可找到
     */
    public int[] singleNumberIII(int[] nums) {
        //求出两个元素异或
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }
}
