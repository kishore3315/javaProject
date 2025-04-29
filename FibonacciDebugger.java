package day9;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FibonacciDebugger {
    static Map<Integer, Integer> memo = new HashMap<>(); // Memoization cache

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Fibonacci number to calculate: ");
        int n = scanner.nextInt();
        scanner.close();

        System.out.println("\n Debugging Recursive Fibonacci...");
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));

        System.out.println("\n Optimized Iterative Fibonacci...");
        System.out.println("Fibonacci(" + n + ") = " + fibonacciIterative(n));
    }

    // 🛑 Recursive Fibonacci (With Debugging & Memoization)
    static int fibonacci(int n) {
        System.out.println("Calculating Fibonacci(" + n + ")"); // Debug Log

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n); // ✅ Use Cached Value

        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result); //  Store in Memoization Cache
        return result;
    }

    //  Optimized Iterative Fibonacci (No Recursion, No Stack Overflow)
    static int fibonacciIterative(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
