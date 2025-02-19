package com.vamsi.pratice;

public class Stack {

    int capacity;
    int top;
    int array[];
    Object lock;
    public Stack(int capacity) {
        this.capacity = capacity;
        this.top = -1;
        this.array = new int[capacity];
        this.lock = new Object();
    }

    public boolean push(int element) {
        synchronized (Stack.class) {
            if (isFull()) {
                System.out.println("Stack is full");
                return false;
            }

            ++top;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            array[top] = element;
            return true;
        }
    }

    public int pop() {

        synchronized (Stack.class) {
            if(isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            int obj = array[top];

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            top--;
            return obj;
        }
    }

    private boolean isFull() {

        return top == capacity - 1;
    }

    private boolean isEmpty() {
        return top == -1;
    }
}
