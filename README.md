# Java Multithreading: From 0 to 100

A comprehensive guide to learning Java multithreading from absolute basics to advanced concepts.

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Basic understanding of Java programming

## Project Structure

```
src/main/java/com/learning/multithreading/
├── basics/           # Level 0-30: Fundamentals
├── intermediate/     # Level 30-60: Core Concepts
├── advanced/         # Level 60-80: Advanced Topics
├── patterns/         # Level 80-90: Common Patterns
└── performance/      # Level 90-100: Optimization
```

## Learning Path

### Level 0-30: Basics (basics/)

1. **Thread Creation**
   - Creating threads using Thread class
   - Creating threads using Runnable interface
   - Thread lifecycle and states

2. **Thread Operations**
   - start(), run(), sleep(), join()
   - Thread priorities and daemon threads
   - Thread naming and identification

3. **Synchronization Basics**
   - The `synchronized` keyword
   - Synchronized methods vs synchronized blocks
   - Understanding race conditions

### Level 30-60: Intermediate (intermediate/)

4. **Inter-thread Communication**
   - wait(), notify(), notifyAll()
   - Producer-Consumer problem
   - Thread coordination

5. **Locks and Conditions**
   - ReentrantLock
   - ReadWriteLock
   - Condition variables
   - Lock fairness

6. **Thread Safety**
   - Immutable objects
   - Thread-safe collections
   - Atomic variables (AtomicInteger, AtomicReference, etc.)
   - volatile keyword

7. **Executor Framework**
   - ExecutorService
   - ThreadPoolExecutor
   - ScheduledExecutorService
   - Callable and Future

### Level 60-80: Advanced (advanced/)

8. **Concurrent Collections**
   - ConcurrentHashMap
   - CopyOnWriteArrayList
   - BlockingQueue implementations
   - ConcurrentLinkedQueue

9. **Advanced Synchronization**
   - CountDownLatch
   - CyclicBarrier
   - Semaphore
   - Phaser
   - Exchanger

10. **Fork/Join Framework**
    - RecursiveTask and RecursiveAction
    - Work-stealing algorithm
    - Parallel streams

11. **CompletableFuture**
    - Asynchronous programming
    - Chaining operations
    - Exception handling
    - Combining futures

### Level 80-90: Patterns (patterns/)

12. **Concurrency Patterns**
    - Thread Pool pattern
    - Producer-Consumer pattern
    - Reader-Writer pattern
    - Thread-Local Storage
    - Double-Checked Locking
    - Balking pattern

13. **Reactive Patterns**
    - Event-driven architecture
    - Non-blocking algorithms
    - Lock-free data structures

### Level 90-100: Performance (performance/)

14. **Performance Optimization**
    - Thread contention analysis
    - False sharing and cache lines
    - Lock-free programming
    - Memory barriers and happens-before

15. **Best Practices**
    - Avoiding deadlocks
    - Testing concurrent code
    - Debugging multithreaded applications
    - JVM thread tuning

## Getting Started

### Build the Project

```bash
mvn clean compile
```

### Run Examples

Each example can be run as a standalone Java application:

```bash
mvn exec:java -Dexec.mainClass="com.learning.multithreading.basics.ThreadCreationExample"
```

### Run Tests

```bash
mvn test
```

## Learning Tips

1. Start from Level 0 and progress sequentially
2. Run each example and observe the output
3. Modify examples to experiment with different scenarios
4. Read the comments in the code carefully
5. Try to predict the output before running the code
6. Practice writing your own multithreaded programs

## Resources

- Java Concurrency in Practice (Book)
- Java Documentation: java.util.concurrent package
- Oracle Java Tutorials: Concurrency

## Progress Tracking

- [ ] Basics (0-30)
- [ ] Intermediate (30-60)
- [ ] Advanced (60-80)
- [ ] Patterns (80-90)
- [ ] Performance (90-100)

## Contributing

Feel free to add more examples or improve existing ones!
