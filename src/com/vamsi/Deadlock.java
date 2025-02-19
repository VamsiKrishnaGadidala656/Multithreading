package com.vamsi;

public class Deadlock {

    public static void main(String[] args) {

        System.out.println("main is starting");
        String lock1 = "Vamsi";
        String lock2 = "Krishna";

        Thread thread1 = new Thread(()->{

            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("lock acquired");
                }
            }
        },"Thread1");

        Thread thread2 = new Thread(() -> {

            synchronized (lock2){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (lock1){
                    System.out.println("lock acquired");
                }
            }
        },"Thread2");

        thread1.start();
        thread2.start();

        System.out.println("main is existing");
    }
}
