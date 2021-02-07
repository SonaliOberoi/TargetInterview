package com.practice.linklist;

//https://leetcode.com/problems/odd-even-linked-list/
public class OddEven {

    public ListNode oddEvenList(ListNode head) {
        if(head == null) {
            return head;
        }

        ListNode current = head;
        ListNode previous = null;
        ListNode firstEven = current.val % 2 == 0 ? current : null;
        ListNode prevEven = null;
        while (current != null) {
            if(current.val % 2 != 0 && previous != null && previous.val % 2 == 0) {
                ListNode next = current.next;
                if(prevEven != null) {
                    prevEven.next = current;
                    current.next = firstEven;
                } else if(prevEven == null && firstEven != null) {
                    current.next = firstEven;
                }
                if(firstEven == null && current.val % 2 == 0) {
                    firstEven = current;
                    prevEven = previous;
                }
                current = next;
            } else {
                if(firstEven == null && current.val % 2 == 0) {
                    firstEven = current;
                    prevEven = previous;
                }
                previous = current;
                current = current.next;
            }
        }
        return head;

    }

    public static void main(String args[]) {
        OddEven oddEven = new OddEven();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        oddEven.oddEvenList(head);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
