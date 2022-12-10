package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-30 16:45
 * @description
 */
public class FindMost {
    int count;
    int maxCount;
    List<Integer> list;
    TreeNode pre; //记录上一次统计节点

    public int[] findMode(TreeNode root) {
        count = 0;
        maxCount = 0;
        list = new ArrayList<>();
        find(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    //中序遍历
    public void find(TreeNode root) {
        if (root == null) {
            return;
        }
        //左
        find(root.left);

        //中
        if (pre == null || root.val != pre.val) {
            count = 1;
        } else {
            count++;
        }

        //更新结果
        if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(root.val);
        } else if (count == maxCount) {
            list.add(root.val);
        }

        pre = root;

        //右
        find(root.right);
    }
}
