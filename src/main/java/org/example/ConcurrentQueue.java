package org.example;


class Node{
    int value;
    Node next;

    Node(int _value ){
        this.value = _value;

    }
}
public class ConcurrentQueue {

    private Node head ;
    private Node tail ;

   private  final  Object headLock = new Object();
    private  final  Object tailLock = new Object();

    ConcurrentQueue(){
        Node dummy = new Node(0);
        head = tail = dummy;
    }

    void  enqueue(int val){
        Node new_node = new Node(val);

        synchronized (tailLock){
            tail.next = new_node;
            tail = tail.next;
        }
    }

    int dequeue(){
        synchronized (headLock){
          Node oldHead = head;
          Node newHead = head.next;

          if(newHead == null) return -1;
          int value = newHead.value;
          head = newHead;
          return value;
        }
    }
}
