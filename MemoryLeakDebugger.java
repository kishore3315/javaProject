package day9;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDebugger {
    // Strong Reference List (Causes Memory Leak)
    private static final List<byte[]> strongList = new ArrayList<>();
    
    // Weak Reference List (Fixes Memory Leak)
    private static final List<WeakReference<byte[]>> weakList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🔍 Starting Memory Leak Simulation...");

        while (true) {
            byte[] memoryChunk = new byte[1024 * 1024]; // Allocate 1MB
            
            // Uncomment the below line to cause a memory leak
            // strongList.add(memoryChunk); 
            
            // ✅ Fix: Use WeakReference instead
            weakList.add(new WeakReference<>(memoryChunk));

            System.out.println("Allocated 1MB, Total Size: " + weakList.size() + "MB");

            System.gc(); // Force Garbage Collection (for testing)

            try {
                Thread.sleep(100); // Simulate a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
