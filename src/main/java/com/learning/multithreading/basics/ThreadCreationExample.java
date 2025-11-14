package com.learning.multithreading.basics;

/**
 * Level 1: Creating Threads - The Very First Step
 *
 * This example demonstrates two ways to create threads in Java:
 * 1. Extending the Thread class
 * 2. Implementing the Runnable interface (preferred approach)
 *
 * Key Concepts:
 * - Thread creation
 * - start() vs run()
 * - Basic thread execution
 */
public class ThreadCreationExample {

    /**
     * Method 1: Extending Thread class
     * Simple but limited (Java doesn't support multiple inheritance)
     */
    static class MyThread extends Thread {
        private String threadName;

        public MyThread(String name) {
            this.threadName = name;
        }

        @Override
        public void run() {
            System.out.println(threadName + " is running on thread: " +
                             Thread.currentThread().getName());

            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + " - Count: " + i);
                try {
                    // Sleep for 500 milliseconds
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " interrupted");
                }
            }

            System.out.println(threadName + " has finished");
        }
    }

    /**
     * Method 2: Implementing Runnable interface (RECOMMENDED)
     * More flexible - your class can extend another class if needed
     */
    static class MyRunnable implements Runnable {
        private String taskName;

        public MyRunnable(String name) {
            this.taskName = name;
        }

        @Override
        public void run() {
            System.out.println(taskName + " is running on thread: " +
                             Thread.currentThread().getName());

            for (int i = 1; i <= 5; i++) {
                System.out.println(taskName + " - Count: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(taskName + " interrupted");
                }
            }

            System.out.println(taskName + " has finished");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        System.out.println("=".repeat(50));

        // Method 1: Using Thread class
        System.out.println("\n1. Creating thread by extending Thread class:");
        MyThread thread1 = new MyThread("Worker-1");
        thread1.start();  // IMPORTANT: Use start(), NOT run()

        // Method 2: Using Runnable interface
        System.out.println("\n2. Creating thread using Runnable interface:");
        Thread thread2 = new Thread(new MyRunnable("Task-1"));
        thread2.start();

        // Method 3: Using lambda expression (Java 8+)
        System.out.println("\n3. Creating thread using lambda:");
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Thread - Count: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread3.start();

        System.out.println("\nMain thread continues to run...");
        System.out.println("Notice: All threads run concurrently!");

        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("All threads have completed. Main thread exits.");
    }
}
