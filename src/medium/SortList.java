package medium;

import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-17 15:37
 * @description 148 归并排序
 */
public class SortList {
    public static void main(String[] args) {
        System.out.println(1 << 4);
    }

    //顶而下
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    //分：切割链表
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        //使用快慢指针分割链表
        ListNode fast = head, slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(0);
        ListNode temp = head, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null)
            temp.next = temp1;
        if (temp2 != null)
            temp.next = temp2;

        return head.next;
    }

    //底而上
    /*
        1.用subLength表示每次需要排序的子链表的长度，初始时subLength=1。
        2.每次将链表拆分成若干个长度为subLength的子链表（最后一个子链表的长度可以小于subLength），
          按照每两个子链表一组进行合并，合并后即可得到若干个长度为subLength×2的有序子链表（最后一个
          子链表的长度可以小于subLength×2）。
        3.将subLength的值加倍，重复第2步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或
          等于length，整个链表排序完毕。
     */
    public ListNode sortListII(ListNode head) {
        if (head == null)
            return head;

        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }

        ListNode newHead = new ListNode(0, head);
        for (int sublen = 1; sublen <= len; sublen <<= 1) {
            ListNode pre = newHead, cur = newHead.next;
            while (cur != null) {
                //第一条链
                ListNode head1 = cur;
                for (int i = 1; i < sublen && cur.next != null; i++) {
                    cur = cur.next;
                }
                //第二条链
                ListNode head2 = cur.next;
                cur.next = null;
                cur = head2;
                for (int i = 1; i < sublen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                //记录下一次开始位置
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }

                //排序完，移动至下一起始处
                pre.next = merge(head1, head2);
                while (pre.next != null)
                    pre = pre.next;

                cur = next;
            }
        }
        return newHead.next;
    }
}
