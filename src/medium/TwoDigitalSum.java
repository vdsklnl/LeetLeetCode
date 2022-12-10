package medium;

/**
 * @author vdsklnl
 * @create 2022-10-30 21:34
 * @description
 */
public class TwoDigitalSum {
    public static void main(String[] args) {
        System.out.println((3 & 3) << 1);
    }

    public int getSum(int a, int b) {
        //使用位运算，位加法用异或，保存进位
        while (b != 0) {
            //所有二进制进位保存
            int count = (a & b) << 1;
            a = a ^ b;
            b = count;
        }
        return a;
    }
}
