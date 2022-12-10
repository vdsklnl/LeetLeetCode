package easy;

/**
 * @author vdsklnl
 * @create 2022-08-11 21:27
 * @description 双指针
 */
public class RemoveElement {
    public static void main(String[] args) {

    }

    public int removeElement(int[] nums, int val) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[len++] = nums[i];
            } else {
                i++;
                if (i == nums.length)
                    break;
                if (nums[i] != val) {
                    nums[len++] = nums[i];
                }
            }
        }
        return len;
    }
}
