package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-02 13:17
 * @description
 */
public class RecoveryIP {
    public static void main(String[] args) {

    }

    //回溯
    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12)
            return result;
        backtracking(s, 0, 0);
        return result;
    }

    public void backtracking(String s, int index, int num) {
        //num 记录ip地址位数，必须为4
        if (index == s.length() && num == 4) {
            result.add(sb.toString());
            return;
        }

        //如果start等于s的长度但是ip段的数量不为4，或者ip段的数量为4但是start小于s的长度，则直接返回
        if (index == s.length() || num == 4) {
            return;
        }

        for (int i = index; i < s.length() && i - index < 3 && Integer.parseInt(s.substring(index, i + 1)) >= 0
                && Integer.parseInt(s.substring(index, i + 1)) <= 255; i++) {
            if (i - index > 0 && s.charAt(index) - '0' == 0)
                continue;

            sb.append(s.substring(index, i + 1));
            // 当stringBuilder里的网段数量小于3时，才会加点；如果等于3，说明已经有3段了，最后一段不需要再加点
            if (num < 3) {
                sb.append(".");
            }
            num++;
            backtracking(s, i + 1, num);
            num--;
            // 删除当前stringBuilder最后一个网段，注意考虑点的数量的问题(包含加1，左闭右开加1)
            sb.delete(index + num, i + num + 2);
        }
    }
}
