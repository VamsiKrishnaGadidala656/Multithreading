package com.vamsi.pratice;

import java.util.LinkedList;
import java.util.Queue;

public class Deadlock {

    Queue<Integer> queue = new LinkedList<Integer>();
    Queue<Integer> q = new LinkedList<>();

    void add() {

        synchronized (queue) {

            System.out.println("queue");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("sleep was done");
            synchronized (q) {
                System.out.println("queue2");
            }
        }

    }

    void remove() {
        synchronized (q) {

            System.out.println("q");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            synchronized (queue) {
                System.out.println("q2");
            }

        }
    }

}
