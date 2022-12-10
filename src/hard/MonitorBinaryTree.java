package hard;

/**
 * @author vdsklnl
 * @create 2022-09-07 13:24
 * @description
 */
public class MonitorBinaryTree {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        //防止根节点未被覆盖
        if (backtracking(root) == 0)
            res++;
        return res;
    }

    /**
     *  摄像头监视子节点与父节点
     * 节点的状态值：
     *  0 表示无覆盖
     *  1 表示有摄像头
     *  2 表示有覆盖
     *  后序遍历，根据左右节点的情况,来判断自己的状态
     */
    public int backtracking(TreeNode root) {
        //空节点默认被覆盖，叶子节点则处于未被覆盖状态，在其父节点放置摄像头
        if (root == null) {
            return 2;
        }

        int left = backtracking(root.left);
        int right = backtracking(root.right);

        if (left == 2 && right == 2) {
            // 如果左右节点都覆盖了的话，那么本节点的状态就应该是无覆盖，没有摄像头
            // (2,2)
            return 0;
        } else if (left == 0 || right == 0) {
            // 左右节点存在无覆盖状态,那根节点此时应该放一个摄像头
            // (0,0) (0,1) (0,2) (1,0) (2,0)
            // 状态值为 1 摄像头数 ++;
            res++;
            return 1;
        } else {
            // 左右节点的 状态为 (1,1) (1,2) (2,1) 也就是左右节点至少存在 1个摄像头，
            // 那么本节点就是处于被覆盖状态
            return 2;
        }
    }

    public static void main(String[] args) {

    }
}
