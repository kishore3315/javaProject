package day8;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDetector {

    private static List<byte[]> memoryLeakList = new ArrayList<>();
    private static ThreadLocal<byte[]> threadLocalLeak = new ThreadLocal<>();
    private static final String FILE_PATH = "test.txt";

    public static void main(String[] args) {
        System.out.println("Initial Memory Usage:");
        printMemoryUsage();

        simulateMemoryLeak();
        System.out.println("\nAfter Memory Leak Simulation:");
        printMemoryUsage();

        fixMemoryLeak();
        System.out.println("\nAfter Fixing Memory Leak:");
        printMemoryUsage();
    }

    private static void simulateMemoryLeak() {
        for (int i = 0; i < 10; i++) {
            memoryLeakList.add(new byte[10 * 1024 * 1024]); // 10MB allocations
        }
        threadLocalLeak.set(new byte[10 * 1024 * 1024]); 

        readFile(); // Read file safely
    }

    private static void fixMemoryLeak() {
        memoryLeakList.clear();
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[10 * 1024 * 1024]);
        threadLocalLeak.remove();
        readFile();
        System.gc();
    }

    private static void readFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Warning: File 'test.txt' not found. Skipping file reading.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.println("Reading file content: " + br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used Memory: " + (usedMemory / (1024 * 1024)) + " MB");
        System.out.println("Total Memory: " + (runtime.totalMemory() / (1024 * 1024)) + " MB");
        System.out.println("Max Memory: " + (runtime.maxMemory() / (1024 * 1024)) + " MB");
    }
}