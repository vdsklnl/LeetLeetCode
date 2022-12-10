package hard;

/**
 * @author vdsklnl
 * @create 2022-08-28 18:56
 * @description
 */
public class FactorialZero {
    public static void main(String[] args) {

    }

    public int preimageSizeFZF(int k) {
        long left = 4l * k, right = 5l * k, mid = (left + right) / 2;
        while (left < right) {
            if (f(mid) == k)
                break;
            else if (f(mid) > k) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        return f(mid) == k ? 5:0;
    }

    public int f(long k) {
        int res = 0;
        while (k > 4) {
            res += k / 5;
            k /= 5;
        }
        return res;
    }
}
