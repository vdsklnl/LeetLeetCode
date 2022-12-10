package easy;

/**
 * @author vdsklnl
 * @create 2022-08-10 20:48
 * @description
 */
public class Square {
    public static void main(String[] args) {
//        int x = 2147483647;
        int x = 214739000;
        System.out.println(mySqrt(x));
    }

//    public static int mySqrt(int x) {
//        if (x == 0 || x == 1)
//            return x;
//
//        int level = 4633;
//        while (level > 0) {
//            if (x > 100 * level * level) {
//                break;
//            }
//            level--;
//        }
//
//        for (int i = 10 * level; i < 46341; i++) {
//            if ((i+1) * (i+1) > x)
//                return i;
//            else if ((i+1) * (i+1) == x)
//                return i + 1;
//        }
//        return 46340;
//    }

//    static int s;
//
//    //牛顿迭代法
//    public static int mySqrt(int x) {
//        if(x == 0 || x == 1) return x;
//        s = x;
//        return ((int)(sqrts(x)));
//    }
//
//    public static double sqrts(double x) {
//        double res = (x + s / x) / 2;
//        if (res == x) {
//            return x;
//        } else {
//            return sqrts(res);
//        }
//    }

    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        if (x < 4)
            return 1;
        if (x == 4)
            return 2;

        long left = 0, right = x, mid = (left + right) / 2;
        while (left <= right) {
            if (mid * mid == x) {
                break;
            } else if (mid * mid  < x) {
                if ((mid + 1) * (mid + 1) > x) {
                    break;
                }
                left = mid + 1;
                mid = (left + right) / 2;
                System.out.println("hhh");
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }

        return (int) mid;
    }
}
