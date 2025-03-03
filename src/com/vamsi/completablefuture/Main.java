package com.vamsi.completablefuture;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        /*

            CompletableFuture
                - Introduced in Java8
                - To help in Async programming
                - we can considered it as an advanced version of Future which provides additional capability like
                chaining.

            How to use this:
                - supplyAsync method initiates an Async operation.
                - 'supplier is executed asynchronously in a separate thread.
                - if we want more control on Threads,we can pass Executor in the method.
                - By Default its uses, shared Fork-Join Pool executor. It dynamically adjust its pool size based on processors.
         */

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,10,10,
                TimeUnit.MINUTES,new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync( () ->
        {
            return "task completed";
        },poolExecutor);

        try {
            System.out.println(asyncTask1.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
            thenApply & thenApplyAsync
            - apply a function to the result of previous async function
            - return new completableFuture Object

         */

        CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            return "Vamsi";
        },poolExecutor).thenApply((String val) -> {
            return val + "krishna";
        });

        try {
            System.out.println(asyncTask2.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
            thenApply Method:
            -----------------
                - Its a Synchronous execution.
                - Means, it uses same thread which completed the previous Async task.

            thenApplyAsync Method:
            ---------------------
                - Its a Asynchronous execution
                - Means,it uses different thread(from 'fork-join' pool, if we do not provide the executor
                in the method), to complete this function.
                - if Multiple 'thenApplyAsync' is used, ordering can not be guarantee, they will run concurrently

         */

        CompletableFuture<String> asyncTask3 = CompletableFuture.supplyAsync( () -> {
            System.out.println("Thread Name which runs 'supplyAsync' : " + Thread.currentThread().getName());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Vamsi ";
        },poolExecutor).thenApplyAsync( (String val) -> {
            System.out.println("Thread Name which runs 'thenApplyAsync' : " + Thread.currentThread().getName());
            System.out.println("first thenApplyAsync");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return val + "Krishna";
        },poolExecutor).thenApplyAsync( (String val) -> {
            System.out.println("Thread Name which runs 'thenApplyAsync' : " + Thread.currentThread().getName());
            System.out.println("second thenApplyAsync");
            return val + "Gadidala";
        },poolExecutor);

        System.out.println("Thread Name for after completeable future: " + Thread.currentThread().getName());
        try {
            System.out.println(asyncTask3.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*

            3.thenCompose and thenComposeAsync
            ---------------------------------
                - Chain together dependent Async operations.
                - Means when next Async operation depends on the result of the  previous Async one.we can tied them together.
                - For async tasks, we can bring some ordering using this.

         */

        CompletableFuture<String> asyncTask4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread Name which runs supplyAsync : " + Thread.currentThread().getName());
            return "Vamsi and";
        },poolExecutor).thenComposeAsync( (String val) -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("Thread Name which runs supplyAsync : " + Thread.currentThread().getName());
                return val + "Coding";
            });
        },poolExecutor);

        /*
            thenAccept and thenAcceptAsync:
            -------------------------------
                - Generally end stage, in the chain of Async operations
                - It does not return anything
         */

        CompletableFuture<Void> asyncTask5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread Name which runs supplyAsync : " + Thread.currentThread().getName());
            return "v";
        },poolExecutor).thenAccept((String val) -> {
            System.out.println("All stages completed");
        });

        /*
            thenCombine and thenCombineAsync:
            --------------------------------
            - Used to combine the result of 2 comparable Future
         */

        CompletableFuture<Integer> asyncTask6 = CompletableFuture.supplyAsync( () -> {
            return 10;
        },poolExecutor);

        CompletableFuture<String> asyncTask7 = CompletableFuture.supplyAsync( () -> {
            return "v";
        },poolExecutor);

        CompletableFuture<String> combinedFutureObj = asyncTask6.thenCombine(asyncTask7,(Integer val1,String val2) -> val1+val2);

    }
}
