package com.k2senterprise;

class Node{
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}

public class RotateLinkedList {

    Node addToLinkList(int a[]) {
        Node head = null;
        Node tail = null;
        for (int i = 0; i < a.length; i++) {
            Node newNode = new Node(a[i]);
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }
        return head;
    }

    Node removeElement(Node node, int val) {
        while(node != null) {
            if(node.data == val) {
                Node dummy = node.next;
                node.next = null;
                node = dummy;
                continue;
            }
            node = node.next;

        }
        return node;
    }

    public void printAllNodes(Node node){
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {

        RotateLinkedList r1 = new RotateLinkedList();
        Node nodes = r1.addToLinkList(new int[]{7,59,63,25,1});
        r1.printAllNodes(nodes);
        nodes = r1.removeElement(nodes, 25);
        r1.printAllNodes(nodes);
    }
}
