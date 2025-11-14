package com.learning.multithreading.intermediate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Level 4: Producer-Consumer Pattern with wait() and notify()
 *
 * This is a classic concurrency problem where:
 * - Producer threads generate data and put it in a shared buffer
 * - Consumer threads take data from the buffer and process it
 * - Buffer has a maximum capacity
 *
 * Key Concepts:
 * - wait(): Releases lock and waits for notification
 * - notify()/notifyAll(): Wakes up waiting threads
 * - Inter-thread communication
 */
public class ProducerConsumerExample {

    /**
     * Shared buffer with bounded capacity
     */
    static class SharedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        /**
         * Producer calls this to add items
         */
        public synchronized void produce(int item) throws InterruptedException {
            // Wait if buffer is full
            while (buffer.size() == capacity) {
                System.out.println("Buffer is full. Producer waiting...");
                wait(); // Release lock and wait
            }

            buffer.add(item);
            System.out.println("Produced: " + item + " | Buffer size: " + buffer.size());

            // Notify consumer that item is available
            notifyAll();
        }

        /**
         * Consumer calls this to remove items
         */
        public synchronized int consume() throws InterruptedException {
            // Wait if buffer is empty
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty. Consumer waiting...");
                wait(); // Release lock and wait
            }

            int item = buffer.poll();
            System.out.println("Consumed: " + item + " | Buffer size: " + buffer.size());

            // Notify producer that space is available
            notifyAll();

            return item;
        }
    }

    /**
     * Producer thread
     */
    static class Producer implements Runnable {
        private final SharedBuffer buffer;
        private final int itemsToProduce;

        public Producer(SharedBuffer buffer, int itemsToProduce) {
            this.buffer = buffer;
            this.itemsToProduce = itemsToProduce;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= itemsToProduce; i++) {
                    buffer.produce(i);
                    Thread.sleep(100); // Simulate production time
                }
                System.out.println("Producer finished producing " + itemsToProduce + " items");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Consumer thread
     */
    static class Consumer implements Runnable {
        private final SharedBuffer buffer;
        private final int itemsToConsume;

        public Consumer(SharedBuffer buffer, int itemsToConsume) {
            this.buffer = buffer;
            this.itemsToConsume = itemsToConsume;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < itemsToConsume; i++) {
                    buffer.consume();
                    Thread.sleep(150); // Simulate consumption time
                }
                System.out.println("Consumer finished consuming " + itemsToConsume + " items");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Producer-Consumer Pattern Demo ===\n");

        SharedBuffer buffer = new SharedBuffer(5); // Buffer capacity of 5

        // Create producer and consumer
        Thread producer = new Thread(new Producer(buffer, 10), "Producer");
        Thread consumer = new Thread(new Consumer(buffer, 10), "Consumer");

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for completion
        producer.join();
        consumer.join();

        System.out.println("\n=== Demo Completed ===");
        System.out.println("\nKey Takeaways:");
        System.out.println("1. wait() releases the lock and pauses the thread");
        System.out.println("2. notify()/notifyAll() wakes up waiting threads");
        System.out.println("3. Always use wait() in a while loop (not if)");
        System.out.println("4. Both wait() and notify() must be called within synchronized block");
    }
}
