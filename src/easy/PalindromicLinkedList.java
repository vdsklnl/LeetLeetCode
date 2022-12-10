package easy;

/**
 * @author vdsklnl
 * @create 2022-09-18 19:50
 * @description
 */
public class PalindromicLinkedList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        System.out.println(isPalindrome(root));
    }

    //先分割成前后子串，再反转后子串并与前串对比
    public static boolean isPalindrome(ListNode head) {
        if (head == null && head.next == null) {
            return true;
        }
        //从slow开始分割，pre记录slow前一节点，此种方法前串长度<=后串
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        ListNode cur1 = head;
        ListNode cur2 = reverse(slow);

        while (cur1 != null) {
            if (cur1.val != cur2.val)
                return false;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode temp = null;
        ListNode pre = null;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}
