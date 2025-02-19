package com.vamsi.pratice;

import java.util.LinkedList;
import java.util.Queue;

public class ProConQueue {

    int[] q;
    int size;
    int index;
    public ProConQueue(int capacity) {
        q = new int[capacity];
        index = -1;
        size = capacity;
    }


    boolean enqueue(int ele) {

        synchronized (this) {
            if (isFull()) {
                System.out.println("queue is full");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                return false;
            }

            index++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            q[index] = ele;
            this.notifyAll();
            return true;
        }
    }

    int dequeue() {

        synchronized (this) {
            if (isEmpty()) {
                System.out.println("queue is empty");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                return -1;
            }
            int ele = q[index];
            index--;
            this.notifyAll();
            return ele;
        }
    }

    private boolean isFull() {

        return index == size-1;
    }

    private boolean isEmpty() {

        return index == -1;
    }
}
