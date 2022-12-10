package easy;

/**
 * @author vdsklnl
 * @create 2022-08-12 21:05
 * @description
 */
public class ListReverse {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        /*
        双指针

        if (head == null || head.next == null)
            return head;

        ListNode cur = head;
        ListNode pre = null;

        while(cur != null) {
            head = cur;
            cur = cur.next;
            head.next = pre;
            pre = head;
        }

        return pre;

         */

        //递归
        if (head == null || head.next == null)
            return head;
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }
}
