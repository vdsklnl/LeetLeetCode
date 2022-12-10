package medium;

/**
 * @author vdsklnl
 * @create 2022-10-17 11:23
 * @description
 */
public class SeparateList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(2);
        partition(head, 3);
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        //temp1存储大 || temp2存储小
        ListNode temp1 = new ListNode(200);
        ListNode temp2 = new ListNode(200);
        temp1.next = head;
        ListNode cur1 = temp1;
        ListNode cur2 = temp2;
        while (cur1 != null && cur1.next != null) {
            if (cur1.next.val < x) {
                cur2.next = cur1.next;
                cur1.next = cur1.next.next;
                cur2.next.next = null;
                cur2 = cur2.next;
            } else
                cur1 = cur1.next;
        }
        cur2.next = temp1.next;
        return temp2.next;
    }
}
