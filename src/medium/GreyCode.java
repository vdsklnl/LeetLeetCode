package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2023-02-23 19:53
 * @description 89 格雷编码 公式法  1238 循环码编辑
 */
public class GreyCode {
    public static void main(String[] args) {

    }

    /*
        格雷码也可以使用公式直接求出。第i(i≥0)个格雷码即为：
        gi=i⊕i/2
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add((i >> 1) ^ i);
        }
        return res;
    }

    //在格雷编码基础上 位与初始值
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add((i >> 1) ^ i ^ start);
        }
        return res;
    }
}
