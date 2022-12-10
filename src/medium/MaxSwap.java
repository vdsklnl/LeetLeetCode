package medium;

/**
 * @author vdsklnl
 * @create 2022-09-13 10:55
 * @description
 */
public class MaxSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(98368));
    }

    public static int maximumSwap(int num) {
        //贪心算法，每一个后面元素应小于前面，但发生交换位置应尽可能前
        char[] chs = (num + "").toCharArray();
        int maxIndex = chs.length - 1;
        int pre = -1, cur = -1;
        for (int i = chs.length - 1; i >= 0; i--) {
            if (chs[i] > chs[maxIndex]) {
                maxIndex = i;
            } else if (chs[i] < chs[maxIndex]) {
                pre = maxIndex;
                cur = i;
            }
        }

        if (cur == -1) {
            return num;
        } else {
            char temp = chs[pre];
            chs[pre] = chs[cur];
            chs[cur] = temp;
            return Integer.parseInt(new String(chs));
        }
    }
}
