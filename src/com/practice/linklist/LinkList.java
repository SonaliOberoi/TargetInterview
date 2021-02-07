package com.practice.linklist;


public class LinkList {
    Node head;

    void insert(int element) {
        Node node = new Node(element);
        if(head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    Node insertRecur(Node currentNode, int element) {
        if (currentNode == null) {
            return new Node(element);
        } else {
            currentNode.next = insertRecur(currentNode.next, element);
        }
        return currentNode;
    }

    void printElements(){
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.element);
            temp =temp.next;
        }
    }

    void traverseRecur(Node head) {
        if(head == null) {
            return;
        }
        System.out.println(head.element);
        traverseRecur(head.next);
    }

    Node reverseRecur(Node currentNode) {
        if (currentNode == null) {
            return currentNode;
        }
        if (currentNode.next == null) {
            return currentNode;
        }
        Node newHeadNode = reverseRecur(currentNode.next);

        // change references for middle chain
        currentNode.next.next = currentNode;
        currentNode.next = null;

        // send back new head node in every recursion
        return newHeadNode;
    }

    public Node swapPairs(Node head) {
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        Node firstNode = head;
        Node secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }

    public static void main(String args[]) {
        LinkList ll = new LinkList();
        ll.insert(10);
        ll.insert(20);
        ll.insert(30);
        ll.insert(40);
        ll.insert(50);
        ll.insert(60);
        ll.printElements();
        LinkList ll2 = new LinkList();
        Node head = null;
        head = ll2.insertRecur(head, 6);
        head = ll2.insertRecur(head, 8);
        head = ll2.insertRecur(head, 10);
        head = ll2.insertRecur(head, 12);
        head = ll2.insertRecur(head, 14);
       // ll2.traverseRecur(head);
       // ll2.traverseRecur(ll2.reverseRecur(head));

        ll2.swapPairs(head);
    }
}
