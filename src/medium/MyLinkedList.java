package medium;

/**
 * @author vdsklnl
 * @create 2022-08-12 20:23
 * @description
 */
public class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);
        System.out.println(linkedList.get(1));
        System.out.println("========");
    }

    int size = 0;
    ListNode head = null;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (size == 0 || index > size || index < 0)
            return -1;
        ListNode temp = head;
        int num = 0;
        while (temp != null) {
            if (num == index) {
                return temp.val;
            }
            num++;
            temp = temp.next;
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new ListNode(val);
            size++;
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(val);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        if (index > size) {
            return;
        }

        int num = 0;
        ListNode node = head;

        while (node != null) {
            num++;
            if (num == index) {
                ListNode listNode= new ListNode(val);
                listNode.next = node.next;
                node.next = listNode;
                size++;
                break;
            }
            node = node.next;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        int num = 0;
        ListNode node = head;
        while (node != null) {
            num++;
            if (num == index) {
                node.next = node.next.next;
                size--;
                return;
            }
            node = node.next;
        }
    }
}
