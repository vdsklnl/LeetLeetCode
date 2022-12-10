package medium;

/**
 * @author vdsklnl
 * @create 2022-10-24 16:38
 * @description 81 搜索旋转排序数组2  33 搜索旋转排序数组1（元素均不相同）
 */
public class SearchRotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 1, 1};
        search(nums, 0);
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums[left] == target)
            return left;
        if (nums[right] == target)
            return right;

        //确定边界
        if (target < nums[left] && target > nums[right])
            return -1;
        else if (target < nums[left]) {
            while (nums[left] > nums[right]) {
                mid = (left + right) / 2;
                if (left == mid)
                    break;
                left = mid;
            }
            while (left - 1 >= 0 && nums[left] > nums[left - 1])
                left--;
        } else if (target > nums[right]) {
            while (nums[left] > nums[right]) {
                right /= 2;
            }
            while (right + 1 < nums.length && nums[right] < nums[right + 1])
                right++;
        }

        while (left <= right && nums[left] <= nums[right]) {
            mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static boolean searchII(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums[left] == target || nums[right] == target)
            return true;

        //确定边界
        if (target < nums[left] && target > nums[right])
            return false;
        else if (target < nums[left]) {
            while (nums[left] >= nums[right]) {
                mid = (left + right) / 2;
                if (left == mid)
                    break;
                left = mid;
            }
            while (left - 1 >= 0 && nums[left] >= nums[left - 1])
                left--;
        } else if (target >= nums[right]) {
            while (nums[left] > nums[right]) {
                right /= 2;
            }
            while (right + 1 < nums.length && nums[right] <= nums[right + 1])
                right++;
        }

        while (left <= right && nums[left] <= nums[right]) {
            mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }
}
