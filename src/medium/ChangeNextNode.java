package medium;

/**
 * @author vdsklnl
 * @create 2022-08-12 21:36
 * @description
 */
public class ChangeNextNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        swapPairs(head);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        //也可以先设头节点，然后head进行迭代，此时不会发生循环问题
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode pre = node;
        ListNode temp;

        while (pre.next != null && pre.next.next != null) {
            temp = head.next.next;
            pre.next = head.next;          // 将 prev 的 next 改为 head 的 next
            head.next.next = head;          // 将 head.next(prev.next) 的next，指向 head
            head.next = temp;               // 将head 的 next 接上缓存的temp
            pre = head;                    // 步进1位
            head = head.next;               // 步进1位
        }

        return node.next;

//        //先确定头节点，head = head.next，然后进行第一次交换
//        ListNode pre = head; //记录交换第一个元素
//        ListNode cur = head.next; //记录待交换元素
//        head = head.next;
//        pre.next = cur.next; //先修改pre，否则进入循环
//        head.next = pre;
//        ListNode temp = pre; //保存pre的状态
//        pre = pre.next; //pre进入下一次交换
//        if (pre == null || pre.next == null)
//            return head;
//        cur = pre.next;
//
//        //temp 是每次交换的头节点
//        while (cur != null) {
//            temp.next = cur;
//            pre.next = cur.next; //改变pre
//            cur.next = pre;
//            temp = pre; //交换完成，temp更新作为头节点
//            pre = pre.next;
//            if (pre == null)
//                break;
//            cur = pre.next;
//        }
//
//        return head;
    }
}
