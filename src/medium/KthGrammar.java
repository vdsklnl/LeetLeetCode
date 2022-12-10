package medium;

/**
 * @author vdsklnl
 * @create 2022-10-20 14:08
 * @description 779
 */
public class KthGrammar {
    public static void main(String[] args) {

    }

    /*
        我们构建了一个包含n行(索引从1开始)的表。首先在第一行我们写上一个0。
        接下来的每一行，将前一行中的0替换为01，1替换为10。 --相当于翻转复制
        例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
        给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
     */
    public int kthGrammar(int n, int k) {
        //不断翻转，递归实现，注意是减2的幂级数
        if (k == 1)
            return 0;

        if (k > (1 << (n - 2)))
            return 1 ^ kthGrammar(n - 1, k - (1 << (n - 2)));

        return kthGrammar(n - 1, k);
    }
}
