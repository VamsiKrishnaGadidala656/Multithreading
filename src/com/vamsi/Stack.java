package com.vamsi;

public class Stack {

    private int[] array;
    private int stackTop;
    private Object lock;

    public Stack(int capacity)
    {
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }
    protected synchronized boolean push(int element)
    {
//        synchronized (lock){
            if(isFull())
                return true;
            ++stackTop;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            array[stackTop] = element;
            return true;
//        }

    }

    protected synchronized int pop()
    {
//        synchronized (lock){
            if(isEmpty())
                return Integer.MIN_VALUE;
            int obj = array[stackTop];

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stackTop--;
            return obj;
//        }

    }
    private boolean isFull()
    {
        return stackTop >= array.length-1;
    }

    private boolean isEmpty()
    {
        return stackTop < 0;
    }


    /**
        1.without synchronization when thread1 calls push method and increments stackTop value to 0 but before the element inserted
          into stack... the thread2 calls pop method and decrements value to -1.....and now thread1 tries put element at stackTop=-1 which gives the error.
        2.only one thread which have lock can allow into synchronized methods
        3.In Non-static Synchronized method, this(current object)  is used as lock;
        4.In Static Synchronized method, classname.class is used as lock because static keyword related to class not object.

     */
}
