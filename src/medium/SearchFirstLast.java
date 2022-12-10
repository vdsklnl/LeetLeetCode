package medium;

/**
 * @author vdsklnl
 * @create 2022-08-11 20:50
 * @description
 */
public class SearchFirstLast {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target)
            return new int []{-1, -1};
        int index = binarySearch(nums, target);
        if (index == -1)
            return new int []{-1, -1};
        int left = index - 1;
        int right = index + 1;
        while (left >= 0 && nums[left] == target)
            left--;
        while (right < nums.length && nums[right] == target)
            right++;
        return new int []{left + 1, right - 1};
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
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
}
