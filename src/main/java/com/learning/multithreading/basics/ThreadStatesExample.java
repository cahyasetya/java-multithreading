package com.learning.multithreading.basics;

/**
 * Level 2: Understanding Thread States
 *
 * A thread in Java can be in one of these states:
 * - NEW: Thread created but not yet started
 * - RUNNABLE: Thread executing or ready to execute
 * - BLOCKED: Thread blocked waiting for a monitor lock
 * - WAITING: Thread waiting indefinitely for another thread
 * - TIMED_WAITING: Thread waiting for a specified time
 * - TERMINATED: Thread has completed execution
 *
 * This example demonstrates these states in action.
 */
public class ThreadStatesExample {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread States Demonstration ===\n");

        // NEW state
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread acquired lock and will wait...");
                    lock.wait(2000); // WAITING/TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            try {
                System.out.println("Thread is sleeping...");
                Thread.sleep(1000); // TIMED_WAITING
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Thread is about to finish");
        });

        // 1. NEW
        System.out.println("1. After creation: " + thread.getState());

        // 2. RUNNABLE
        thread.start();
        Thread.sleep(100); // Give thread time to start
        System.out.println("2. After start(): " + thread.getState());

        // 3. TIMED_WAITING
        Thread.sleep(500);
        System.out.println("3. During wait(): " + thread.getState());

        // 4. Wait for thread to finish
        thread.join();

        // 5. TERMINATED
        System.out.println("4. After completion: " + thread.getState());

        // Demonstrate BLOCKED state
        demonstrateBlockedState();
    }

    private static void demonstrateBlockedState() throws InterruptedException {
        System.out.println("\n=== Demonstrating BLOCKED State ===\n");

        final Object sharedLock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (sharedLock) {
                System.out.println("Thread 1: Holding lock");
                try {
                    Thread.sleep(3000); // Hold lock for 3 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread 1: Releasing lock");
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Waiting for lock...");
            synchronized (sharedLock) {
                System.out.println("Thread 2: Acquired lock");
            }
        });

        thread1.start();
        Thread.sleep(100); // Ensure thread1 gets the lock first

        thread2.start();
        Thread.sleep(500); // Give thread2 time to reach BLOCKED state

        System.out.println("Thread 2 state (should be BLOCKED): " + thread2.getState());

        thread1.join();
        thread2.join();

        System.out.println("\nAll threads completed");
    }
}
