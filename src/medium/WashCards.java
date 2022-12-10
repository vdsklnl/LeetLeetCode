package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-10-08 14:55
 * @description
 */
public class WashCards {
    public static void main(String[] args) {

    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }
        //将索引值根据原数组从小到大排序，Integer类型数组才能作为原下标加入排序
        Arrays.sort(idx1, (o1, o2) -> nums1[o1] - nums1[o2]);
        Arrays.sort(idx2, (o1, o2) -> nums2[o1] - nums2[o2]);

        //按顺序遍历nums1将元素放入，此时若大于，一定是差值最小的情况
        //否则，将元素放到与nums2最大元素对应位置
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                res[idx2[left]] = nums1[idx1[i]];
                left++;
            } else {
                res[idx2[right]] = nums1[idx1[i]];
                right--;
            }
        }
        return res;
    }

    //空间换时间
    /*
        为了让每个决策回合具有独立性，我们需要对两数组进行排序，同时为了在构造答案时，能够对应回 nums2 的原下标，
        排序前我们需要使用「哈希表」记录每个nums2[i]的下标为何值。
     */
    public int[] advantageCountII(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        //list存储同一值多个位置
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums2[i], new ArrayList<>());
            list.add(i);
            map.put(nums2[i], list);
        }
        Arrays.sort(nums1); Arrays.sort(nums2);
        int[] ans = new int[n];
        for (int l1 = 0, l2 = 0, r2 = n - 1; l1 < n; l1++) {
            int t = nums1[l1] > nums2[l2] ? l2 : r2;
            List<Integer> list = map.get(nums2[t]);
            int idx = list.remove(list.size() - 1);
            ans[idx] = nums1[l1];
            if (t == l2) l2++;
            else r2--;
        }
        return ans;
    }
}
