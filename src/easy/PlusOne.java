package easy;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-08-10 19:47
 * @description
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {8,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1] += 1;
            return digits;
        }

        digits[digits.length - 1] = 0;
        int count = 1;

        for (int i = digits.length - 2; i >= 0; i--) {
            if ((digits[i] + count) >= 10) {
                digits[i] = digits[i] + count - 10;
                count = 1;
            } else {
                digits[i] += 1;
                count = 0;
            }
            if (count == 0)
                break;
        }

        if (count == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }

        return digits;
    }
}
