package day8;
import java.util.concurrent.*;

class FileDownloader implements Runnable {
    private final String fileName;
    private final CountDownLatch latch;

    public FileDownloader(String fileName, CountDownLatch latch) {
        this.fileName = fileName;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Downloading: " + fileName);
            Thread.sleep((long) (Math.random() * 3000) + 1000); // Simulate download time
            System.out.println("Download complete: " + fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // Decrease latch count after task completion
        }
    }
}

class DataProcessor implements Runnable {
    private final String chunkName;
    private final CountDownLatch latch;

    public DataProcessor(String chunkName, CountDownLatch latch) {
        this.chunkName = chunkName;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Processing data chunk: " + chunkName);
            Thread.sleep((long) (Math.random() * 2000) + 500); // Simulate processing time
            System.out.println("Data processed: " + chunkName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // Decrease latch count
        }
    }
}

public class CountdownTimerApp {
    public static void main(String[] args) {
        int numFiles = 3;
        int numDataChunks = 3;
        int totalTasks = numFiles + numDataChunks;

        CountDownLatch latch = new CountDownLatch(totalTasks);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Start multiple file download tasks
        for (int i = 1; i <= numFiles; i++) {
            executor.execute(new FileDownloader("File" + i + ".zip", latch));
        }

        // Start multiple data processing tasks
        for (int i = 1; i <= numDataChunks; i++) {
            executor.execute(new DataProcessor("Chunk" + i, latch));
        }

        try {
            System.out.println("\n Waiting for all tasks to complete...");
            latch.await(); // Main thread waits for all tasks
            System.out.println("\n All tasks completed! Proceeding with merging results.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}