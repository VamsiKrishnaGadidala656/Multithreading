package com.vamsi.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,4,10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2),new CustomThreadFactory(),new CustomRejectionHandler());
        poolExecutor.allowCoreThreadTimeOut(true);

        for (int i=0; i<7; i++) {

            poolExecutor.submit(() -> {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task processed by " + Thread.currentThread().getName());
            } );
        }

        poolExecutor.shutdown();
    }

    /*
        By default corePoolSize(number of threads) are created in pool, if free threads will automatically pick the task
        from pool,
        but -- if threads are not free,the submitted task will go into queue(of particular size)
            -- if threads are not free and queue was also full, then new threads will be created upto maximumPoolSize
                and new threads will perform the new tasks.
            -- even after creating all threads(maximumPoolSize), still new task is coming but no thread is free,then it will be rejected
            according rejection handler.
     */
// https://notebook.zohopublic.in/public/notes/74tdo0e297bb7d6dd4d45a837d13f60fedc3f
}

class CustomThreadFactory implements ThreadFactory {


    @Override
    public Thread newThread(Runnable runnable) {

        Thread thread = new Thread(runnable);
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}

class CustomRejectionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

        System.out.println("task rejected: " + runnable.toString());
    }
}
