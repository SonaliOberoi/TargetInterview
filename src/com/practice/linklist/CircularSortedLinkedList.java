package com.practice.linklist;

//https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
public class CircularSortedLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal);
        //no element
        if(head == null) {
            insert.next = insert;
            return insert;
        }
        //one element
        if(head.next == head) {
            head.next = insert;
            insert.next = head;
            return head;
        }

        Node previous = head;
        Node current = head.next;
        Node next = current.next;
        if(next == previous) {
            //two elements
            if(insertVal < previous.val) {
                current.next = insert;
                insert.next = previous;
            } else {
                previous.next = insert;
                insert.next = current;
            }
            return head;
        }

        Node pivotNode = null;
        while(true) {
            //when all elements are equal
            if(next == head) {
                pivotNode = current;
                System.out.println("pivot is: " + pivotNode.val);
                break;
            }
            if(current.val > previous.val && current.val > next.val) {
                pivotNode = current;
                System.out.println("pivot is: " + pivotNode.val);
                break;
            }
            previous = current;
            current = next;
            next = current.next;
        }
        while(true) {
            System.out.println("prev: " + previous.val + " current:" + current.val + " next:" + next.val);
            if(insertVal == current.val) {
                current.next = insert;
                insert.next = next;
                return head;
            }
            if(insertVal < current.val && insertVal > previous.val) {
                previous.next = insert;
                insert.next = current;
                return head;
            } else if(insertVal > current.val && insertVal > previous.val) {
                if(current == pivotNode) {
                    current.next = insert;
                    insert.next = next;
                    return head;
                }
            } else {
                if(current == pivotNode && insertVal < next.val) {
                    current.next = insert;
                    insert.next = next;
                    return head;
                }
            }
            previous = current;
            current = next;
            next = current.next;
        }
    }
    Node head;
    CircularSortedLinkedList() {
        Node root = new Node(3);
        root.next = new Node(3);
        root.next.next = new Node(3);
        root.next.next.next = root;
        head = root;
    }

    public static void main(String args[]) {
        CircularSortedLinkedList circularSortedLinkedList = new CircularSortedLinkedList();
        circularSortedLinkedList.insert(circularSortedLinkedList.head, 0);

    }

}
