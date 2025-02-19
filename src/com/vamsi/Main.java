package com.vamsi;

public class Main {

    public static void main(String[] args) {
        System.out.println("main is starting");

//        Thread thread1 = new Thread1("thread1");
//        thread1.setDaemon(true);
//        we need to method whether thread is user or daemon before starting
//        thread1.start();

//        Thread thread2 = new Thread(new Thread2(),"thread2");
//        thread2.start();

//        Lamba funciton
//        Thread thread2 = new Thread(() -> {
//            for (int i=0;i<5;i++)
//                System.out.println("inside " + Thread.currentThread() + " " + i);
//
//        },"thread2");
/*
        Stack stack = new Stack(5);
        new Thread(()->{
            int counter = 0;
            while (++counter < 10)
                System.out.println("Pushed: " + stack.push(100));
        },"Pusher").start();

        new Thread(()->{
            int counter = 0;
            while (++counter < 10)
                System.out.println("Popped: " + stack.pop());
        },"Popper").start();
        System.out.println("main is existing");
    }
*/
/*
        Thread thread3 = new Thread(() -> {

            try {
                Thread.sleep(1);
                for (int i = 10000; i > 0; i--) ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "States");
        thread3.start();

        while (true) {
            Thread.State state = thread3.getState();
            System.out.println(state);
            if (state == Thread.State.TERMINATED) break;
        }
*/
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread());
        },"Our Thread");
        thread.start();

        try{
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main is existing");
//  join makes thread completes the current created thread intended tasks...then continues flow...

    /**
       1. main thread is also user thread.
       2.even main is existing,the program doesn't terminate.....because the program waits until all user threads complete
            2.1 but if there is no users threads executing but there are daemon threads,then program might terminate.
            2.2 but if there is users threads executing, the programs never terminate untils those threads completes their execution.
       3.we can make thread as daemon thread by passing true to setDaemon(boolean) method of thread class
    */


/**
    Since there are two ways to create the thread
        i.implementing the Runnable interface
        ii.Extending the Thread class

    Which way is better??
        since multiple inheritance is not allowed in java...implementing the runnable interface is good choice
        because we can implement multiple interfaces.

        if we created the thread by extending thread class... now we don't have to any chance to extend any another useful
        class that we need...it creates problem.

        but if we created the thread by implementing runnable...now we can extend any another useful class also and
        even can implement other interfaces also.
 */
    }
}
