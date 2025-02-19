package com.vamsi;

public class Thread1 extends Thread{

    public Thread1(String threadName) {
        super(threadName);
    }

    @Override
    public void run(){
//        whatever code that is intended to execute must place in this run method
//        because whatever code in run method will executed by thread
        for(int i=0;i<5;i++)
        {
            System.out.println("inside  " + Thread.currentThread() + " " + i);
        }
    }
}
