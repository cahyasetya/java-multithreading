package com.learning.multithreading.basics;

/**
 * Level 3: Synchronization - Avoiding Race Conditions
 *
 * Race Condition: When multiple threads access shared data and try to change it
 * at the same time, leading to unpredictable results.
 *
 * This example demonstrates:
 * 1. A race condition problem
 * 2. How to fix it using synchronization
 */
public class SynchronizationExample {

    /**
     * Counter without synchronization - NOT THREAD-SAFE
     */
    static class UnsafeCounter {
        private int count = 0;

        public void increment() {
            count++; // This is actually 3 operations: read, modify, write
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * Counter with synchronization - THREAD-SAFE
     */
    static class SafeCounter {
        private int count = 0;

        // Synchronized method - only one thread can execute this at a time
        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    /**
     * Alternative: Using synchronized block for finer control
     */
    static class SafeCounterWithBlock {
        private int count = 0;
        private final Object lock = new Object();

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Demonstrating Race Condition ===\n");

        // Test 1: Without synchronization
        testUnsafeCounter();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test 2: With synchronization
        testSafeCounter();
    }

    private static void testUnsafeCounter() throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        int numThreads = 10;
        int incrementsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];

        // Create threads that increment the counter
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCounter.increment();
                }
            });
        }

        // Start all threads
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();

        int expectedCount = numThreads * incrementsPerThread;
        int actualCount = unsafeCounter.getCount();

        System.out.println("UNSAFE Counter Results:");
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count:   " + actualCount);
        System.out.println("Lost updates:   " + (expectedCount - actualCount));
        System.out.println("Time taken:     " + (endTime - startTime) + "ms");

        if (actualCount != expectedCount) {
            System.out.println("❌ RACE CONDITION DETECTED!");
        }
    }

    private static void testSafeCounter() throws InterruptedException {
        SafeCounter safeCounter = new SafeCounter();
        int numThreads = 10;
        int incrementsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];

        // Create threads that increment the counter
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    safeCounter.increment();
                }
            });
        }

        // Start all threads
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();

        int expectedCount = numThreads * incrementsPerThread;
        int actualCount = safeCounter.getCount();

        System.out.println("SAFE Counter Results:");
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count:   " + actualCount);
        System.out.println("Lost updates:   " + (expectedCount - actualCount));
        System.out.println("Time taken:     " + (endTime - startTime) + "ms");

        if (actualCount == expectedCount) {
            System.out.println("✓ SUCCESS! All increments were counted correctly.");
        }
    }
}
