package medium;

/**
 * @author vdsklnl
 * @create 2022-10-30 19:40
 * @description 390
 */
public class RemoveGame {
    public static void main(String[] args) {

    }

    /*
        每次删除构成等差数列，n次删除公差为2^n，结构移动2^(n-1)
        奇数会删成偶数，两端均删去 || 偶数前往后会删头，后往前会删尾，
        最后只剩队列首元素，尾元素可表示为 a1 + stepk * (cnt - 1) cnt为元素数目
     */
    public int lastRemaining(int n) {
        int cnt = n;
        int k = 0, step = 1, a1 = 1;
        while (cnt > 1) {
            if (k % 2 == 0)
                a1 += step; //一定向后移动一位
            else
                a1 += (cnt % 2 == 0) ? 0:step; //偶数时，从后往前首元素不变化，否则移动一位
            k++;
            step <<= 1;
            cnt >>= 1;
        }
        return a1;
    }
}
