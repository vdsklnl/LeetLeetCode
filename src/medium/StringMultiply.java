package medium;

/**
 * @author vdsklnl
 * @create 2022-10-05 17:57
 * @description
 */
public class StringMultiply {
    public static void main(String[] args) {

    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";

        //使用数组存储，最多位数 = l1 + l2，高位存储位置在数组头，低位在数组尾
        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];

        //从低位开始乘，倒序，并存储在相应位数 + 1上，最后处理位数也是倒序往前增加
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                result[i + j + 1] += x * y;
            }
        }

        //处理位数也是倒序往前增加
        for (int i = m + n - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] %= 10;
        }

        //将每个位数相加形成字符串，先判断首位是否为0
        StringBuilder sb = new StringBuilder();
        int index = (result[0] == 0) ? 1:0;
        for (; index < m + n; index++) {
            sb.append(result[index]);
        }

        return sb.toString();
    }
}
