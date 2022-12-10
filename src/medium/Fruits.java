package medium;

/**
 * @author vdsklnl
 * @create 2022-08-12 12:55
 * @description
 */
public class Fruits {
    public static void main(String[] args) {
        int[] fruit = {0,1,6,6,4,4,6};
        System.out.println(totalFruit(fruit));
    }

    public static int totalFruit(int[] fruits) {
        if (fruits.length == 0)
            return 0;

        int first = fruits[0], second = -1;
        int f = 0, s = 0;

        for (int i = 1; i < fruits.length; i++) {
            if (fruits[i] != first) {
                second = fruits[i];
                s = i;
                break;
            }
        }

        if (second == -1) {
            return fruits.length;
        }

        int len = s + 1, max = s + 1;

        for (int i = s + 1; i < fruits.length; i++) {
            if (fruits[i] == first) {
                if (s > f)
                    f = i;
                len++;
            } else if (fruits[i] == second) {
                if (f > s)
                    s = i;
                len++;
            } else {
                if (len > max) {
                    max = len;
                }
                if (s > f) {
                    first = second;
                    second = fruits[i];
                    f = s;
                    s = i;
                    len = s - f + 1;
                } else {
                    second = fruits[i];
                    s = i;
                    len = s - f + 1;
                }
            }
        }

        if (len > max)
            max = len;

        return max;
    }
}
