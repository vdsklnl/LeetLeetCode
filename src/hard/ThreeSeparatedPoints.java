package hard;

/**
 * @author vdsklnl
 * @create 2022-10-07 13:11
 * @description
 */
public class ThreeSeparatedPoints {
    public static void main(String[] args) {

    }

    public int[] threeEqualParts(int[] arr) {
        //对三等分二进制数相等，则各部分1数量一定相同
        int sum = 0;
        for (int num:arr) sum += num;
        if (sum == 0) return new int[]{0, 2};
        if (sum % 3 != 0) return new int[]{-1, -1};

        //将三个指针位置定位于每组1的头部，排除前导0
        int part = sum / 3, n = arr.length;
        int first = 0, second = 0, third = 0, count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                if (count == 0) {
                    first = i;
                } else if (count == part) {
                    second = i;
                } else if (count == 2 * part) {
                    third = i;
                }
                count++;
            }
        }

        //此时，从third到末尾的长度为相等二进制数的长度，只有当相隔长度足够才有可能
        int len = n - third;
        if (first + len <= second && second + len <= third) {
            int i = 0;
            while (third + i < n) {
                if (arr[first + i] != arr[second + i] || arr[first + i] != arr[third + i])
                    return new int[]{-1, -1};
                i++;
            }
            return new int[]{first + len - 1, second + len};
        }
        return new int[]{-1, -1};
    }
}
