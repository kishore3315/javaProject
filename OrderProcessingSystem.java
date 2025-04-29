package day8;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// Order class representing a customer order
class Order {
    private final int orderId;
    private final String customerName;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }
}

// OrderProcessor thread to process orders
class OrderProcessor implements Runnable {
    private final BlockingQueue<Order> orderQueue;
    private final AtomicBoolean running;

    public OrderProcessor(BlockingQueue<Order> orderQueue, AtomicBoolean running) {
        this.orderQueue = orderQueue;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get() || !orderQueue.isEmpty()) {
            try {
                // Wait for up to 2 seconds for an order, else return null
                Order order = orderQueue.poll(2, TimeUnit.SECONDS);
                if (order != null) {
                    System.out.println("Processing Order: " + order.getOrderId() + " for " + order.getCustomerName());
                    Thread.sleep(1000); // Simulate order processing time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("OrderProcessor interrupted.");
            }
        }
        System.out.println("OrderProcessor shutting down.");
    }
}

// Main class for the Order Processing System
public class OrderProcessingSystem {
    public static void main(String[] args) {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        AtomicBoolean running = new AtomicBoolean(true);

        // Start the OrderProcessor thread
        Thread processorThread = new Thread(new OrderProcessor(orderQueue, running));
        processorThread.start();

        // Simulate multiple customers placing orders
        Thread customer1 = new Thread(() -> placeOrders(orderQueue, "Alice"));
        Thread customer2 = new Thread(() -> placeOrders(orderQueue, "Bob"));
        Thread customer3 = new Thread(() -> placeOrders(orderQueue, "Charlie"));

        customer1.start();
        customer2.start();
        customer3.start();

        try {
            customer1.join();
            customer2.join();
            customer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Signal OrderProcessor to stop
        running.set(false);
        try {
            processorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order Processing System shut down.");
    }

    private static void placeOrders(BlockingQueue<Order> orderQueue, String customerName) {
        for (int i = 1; i <= 3; i++) {
            Order order = new Order(i, customerName);
            try {
                orderQueue.put(order); // Thread-safe order insertion
                System.out.println(customerName + " placed Order " + order.getOrderId());
                Thread.sleep(500); // Simulate time between orders
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}