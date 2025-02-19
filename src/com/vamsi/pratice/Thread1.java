package com.vamsi.pratice;

public class Thread1 extends Thread {

    public Thread1(String threadName) {
        super(threadName);
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("inside  " + Thread.currentThread().getName() + " " + i);
        }
    }
}
