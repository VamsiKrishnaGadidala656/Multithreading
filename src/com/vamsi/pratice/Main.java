package com.vamsi.pratice;

public class Main {
    public static void main(String[] args) {

        /*
        Thread1 thread1 = new Thread1("myThread1");

//        thread1.start();

        Stack s = new Stack(5);
        Deadlock deadlock = new Deadlock();

        new Thread(() -> {
//                int counter = 0;
//                while(++counter < 10) {
//                    System.out.println("pushed:: " + s.push(100));
//                }
            deadlock.add();
         },"pusher").start();

        new Thread(() -> {

            int counter = 0;

//            while(++counter < 10) {
//                System.out.println("popped:: " + s.pop());
//            }
            deadlock.remove();
        },"popped").start();
        */
        ProConQueue proConQueue = new ProConQueue(5);
        Thread producer = new Thread(() -> {

            int counter = 0;
            while(++counter < 5) {
                System.out.println("enqueued:: " + proConQueue.enqueue(100));
            }
        },"enqueuer");

        Thread consumer = new Thread(() -> {

            int counter = 0;
            while(++counter < 5) {
                System.out.println("dequeued:: " + proConQueue.dequeue());
            }
        },"dequeue");

        producer.start();
        consumer.start();
    }
}
