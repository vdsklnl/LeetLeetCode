package hard;

/**
 * @author vdsklnl
 * @create 2022-09-05 20:31
 * @description
 */
public class HandOutCandy {
    public static void main(String[] args) {

    }

    public int candy(int[] ratings) {
        //分为两趟，先考虑右边比左边大的情况，
        //再考虑左边比右边大的情况
        int[] candys = new int[ratings.length];
        candys[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            } else
                candys[i] = 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candys[i] = Math.max(candys[i], candys[i + 1] + 1);
            }
        }

        int result = 0;
        for (int res:candys)
            result += res;

        return result;
    }
}
