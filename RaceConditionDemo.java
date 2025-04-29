package day9;
class SharedCounter {
    private int counter = 0;  // Shared counter variable

    public void increment() {
        counter++;  // Not thread-safe (race condition)
    }

    public int getCounter() {
        return counter;
    }
}

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        // Create multiple threads that update the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expected: 2000, but may produce incorrect results due to race condition
        System.out.println("Final Counter Value: " + sharedCounter.getCounter());
    }
}