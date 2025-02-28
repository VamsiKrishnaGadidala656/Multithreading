package com.vamsi.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureVsCallable {

    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        //UseCase1
        Future<?> futureObj = executor.submit(() -> {
            System.out.println("Task1 with Runnable");
        });

        try {
            Object object = futureObj.get();
            System.out.println("status:: " + (object==null));
        } catch (ExecutionException | InterruptedException e) {

        }

        //UseCase2
        List<Integer> output = new ArrayList<>();

        //with lamba expression
        Future<List<Integer>> futureObject2 = executor.submit(() -> {
            output.add(100);
            System.out.println("Task2 Runnable with return object");

        },output);

        try {
            List<Integer> outputFromFutureObject2 = futureObject2.get();
            System.out.println("outputFromFutureObject2:: " + outputFromFutureObject2);
        } catch (ExecutionException | InterruptedException e) {

        }

        //without lamba expression
        List<Integer> output2 = new ArrayList<>();
        Future<List<Integer>> futureObject3 = executor.submit(new FutureReturnClass(output2),output2);
        try {
            List<Integer> outputFromFutureObject3 = futureObject3.get();
            System.out.println("outputFromFutureObject3:: " + outputFromFutureObject3);
        } catch (ExecutionException | InterruptedException e) {

        }


        //UseCase3
        Future<List<Integer>> futureObject4 = executor.submit(() -> {
            System.out.println("Task3 with callable");
            List<Integer> list = new ArrayList<>();
            list.add(300);

            return list;

        });

        try {
            List<Integer> outputFromFutureObject4 = futureObject4.get();
            System.out.println("outputFromFutureObject4:: " + outputFromFutureObject4);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }



        /*
            Runnable VS Callable
            1.Both Runnable and Callable are functional interfaces
            2.Callable also represent task which need to be executed just like Runnable
            3.Main difference is:
                i.Runnable do not have return type.
                ii.Callable has the capability to return the value


            4.

            @FunctionalInterface
            public interface Runnable {
                public abstract void run()
            }

            @FunctionalInterface
            public interface Callable<V> {
                V call() throws Exception;
            }

         */
    }
}
