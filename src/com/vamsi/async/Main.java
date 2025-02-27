package com.vamsi.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        /*
        for(int i=0;i<8;i++) {

            executor.submit(() -> {

                System.out.println("Thread name :: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        */

        //how to know the status of submitted task??

        Future<?> futureObj = executor.submit(() -> {
            System.out.println("this is the task, which thread will execute");
        });

        //caller is checking the status
        System.out.println("is Done:: " + futureObj.isDone());
        try {
            futureObj.get(2,TimeUnit.SECONDS);

        }  catch (TimeoutException e) {
            System.out.println("TimeoutException happened");
        }   catch (ExecutionException | InterruptedException e) {
            System.out.println("exception occurred:: " + e.getMessage());
        }

//        future.get(long l,TimeUnit) waits if required until given timeout period,throws TimeOutException if
//        the task is not completed within given time.

        try {
            futureObj.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("exception occurred");
        }
//        future.get() waits infinitely until the task is completed

        System.out.println("isDone :: " + futureObj.isDone());
        System.out.println("is Cancelled:: " + futureObj.isCancelled());

//        Returns true if this task completed. Completion may be due to normal termination, an exception,
//        or cancellation --in all of these cases, this method will return true.

    }
}
