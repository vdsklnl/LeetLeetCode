package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-09-05 11:07
 * @description
 */
public class RepeatSubtrees {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        DFS(root);
        return result;
    }

    public String DFS(TreeNode root) {
        //" "记录null值
        if (root == null) {
            return " ";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append("_");
        sb.append(DFS(root.left)).append(DFS(root.right));

        String res = sb.toString();
        map.put(res, map.getOrDefault(res, 0) + 1);
        if (map.get(res) == 2)
            result.add(root);

        return res;
    }

    public static void main(String[] args) {

    }
}
