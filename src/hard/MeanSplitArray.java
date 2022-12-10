package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vdsklnl
 * @create 2022-11-14 13:25
 * @description 805 折半搜索 + 二进制枚举 + 哈希表 + 数学
 */
public class MeanSplitArray {
    public static void main(String[] args) {

    }

    /*
        二进制枚举：通过二进制每一位值判断是否选入当前组合
        n位数，总共2^n可能，对i种组合，i & (1 << j) != 0 -> 第j个数被选入
        例：n = 5，22 -> 10110 则2、3、5被选入
     */
    public boolean splitArraySameAverage(int[] nums) {
        //分成两个数组，均值相同，则每个数组均值与总均值相同，找到一个组合即可
        if (nums.length == 1)
            return false;
        //n/2个元素有2^(n/2)种可能，
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }
        //确保不会出现分数，将每个元素*n，则均值为sum，再减去sum，均值为0
        for (int i = 0; i < n; i++) {
            nums[i] = n * nums[i] - sum;
        }

        Set<Integer> set = new HashSet<>();
        //1起始，表示不存在空数组
        for (int i = 1; i < (1 << m); i++) {
            int total = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    total += nums[j];
                }
            }
            //通过数学变换将是ave(nums) = 0，若选择过程中为0，则直接表示成功（剩余均值为0）
            if (total == 0)
                return true;
            set.add(total);
        }

        //可以进行折半查找，在A中选出A1，B中若存在B1 = -A1，则A1B1合成新数组，满足要求
        //B数组不能全选（A+B为全数组）
        for (int i = 1; i < (1 << (n - m)) - 1; i++) {
            int total = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    total += nums[j];
                }
            }
            //通过数学变换将是ave(nums) = 0，若选择过程中为0，则直接表示成功（剩余均值为0）
            if (total == 0 || set.contains(-total))
                return true;
        }
        return false;
    }

    //动态规划
    /*
        问题可以等价于在数组中取k个数，使得其和为 k*avg
        对应的「0-1背包问题」则为是否可以取k件物品使得背包的重量为 k*avg
        dp[i][x]表示当前已从数组nums取出i个元素构成的和为x的可能性：true表示可以，false表示不行

        假设前j−1个元素中存在长度为i的子集且子集的和为x，则此时 dp[i][x]=true，我们当前遍历nums[j]时，
        则可以推出一定存在长度为i+1的子集且满足子集的和为x+nums[j]，可以得到状态转移方程为：
            dp[i+1][x+nums[j]]=dp[i][x]

        减枝技巧如下:
            根据题意可以推出 sum(A)=sum(nums)*k/n，则此时如果满足题目要求，则一定存在整数 k∈[1,n]
            [sum(nums)×k]mod n = 0，因此我们可以提前是否存在k满足上述要求，如果不存在则可以提前终止。
            根据题目要求可以划分为两个子数组 A,B则可以知道两个子数组中一定存在一个数组的长度小于等于 n/2
            因此我们只需检测子数组中是否存在其和满足 subsum×n=nums×k即可。
     */
    public boolean splitArraySameAverageII(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean isPossible = false;
        for (int i = 1; i <= m; i++) {
            if (sum * i % n == 0) {
                isPossible = true;
                break;
            }
        }
        if (!isPossible) {
            return false;
        }
        Set<Integer>[] dp = new Set[m + 1];
        for (int i = 0; i <= m; i++) {
            dp[i] = new HashSet<Integer>();
        }
        dp[0].add(0);
        for (int num : nums) {
            for (int i = m; i >= 1; i--) {
                for (int x : dp[i - 1]) {
                    int curr = x + num;
                    if (curr * n == sum * i) {
                        return true;
                    }
                    dp[i].add(curr);
                }
            }
        }
        return false;
    }
}
