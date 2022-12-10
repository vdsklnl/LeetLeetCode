package easy;

/**
 * @author vdsklnl
 * @create 2022-08-10 21:43
 * @description
 */
public class DeleteDuplicates {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode listNode = head;
        while (listNode != null && listNode.next != null) {
            if (listNode.next.val == listNode.val) {
                listNode.next = listNode.next.next;
            } else {
                listNode = listNode.next;
            }
        }
        return head;
    }
}

