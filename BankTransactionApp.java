package day8;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// Bank Account Class
class BankAccount {
    private final int accountId;
    private double balance;
    private final Lock lock = new ReentrantLock(); // ReentrantLock for thread-safe transactions

    public BankAccount(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }
}

// Bank Transaction System
class BankTransactionSystem {
    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountId(), account);
    }

    public void transferMoney(int fromId, int toId, double amount) {
        BankAccount fromAccount = accounts.get(fromId);
        BankAccount toAccount = accounts.get(toId);

        if (fromAccount == null || toAccount == null) {
            System.out.println("❌ Invalid account(s) for transfer.");
            return;
        }

        // Lock ordering: Always lock lower account ID first to prevent deadlock
        BankAccount firstLock = fromId < toId ? fromAccount : toAccount;
        BankAccount secondLock = fromId < toId ? toAccount : fromAccount;

        firstLock.getLock().lock();
        secondLock.getLock().lock();
        try {
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                System.out.println("Transfer of $" + amount + " from Account " + fromId + " to Account " + toId + " successful.");
            } else {
                System.out.println("Transfer failed due to insufficient funds in Account " + fromId);
            }
        } finally {
            firstLock.getLock().unlock();
            secondLock.getLock().unlock();
        }
    }

    public void printBalances() {
        System.out.println("\n🏦 Account Balances:");
        for (BankAccount account : accounts.values()) {
            System.out.println("Account " + account.getAccountId() + " → $" + account.getBalance());
        }
    }
}

// Main Class to Simulate Transactions
public class BankTransactionApp {
    public static void main(String[] args) {
        BankTransactionSystem bank = new BankTransactionSystem();

        // Creating bank accounts
        bank.addAccount(new BankAccount(101, 1000));
        bank.addAccount(new BankAccount(102, 2000));
        bank.addAccount(new BankAccount(103, 1500));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Simulate multiple concurrent transfers
        executor.execute(() -> bank.transferMoney(101, 102, 300));
        executor.execute(() -> bank.transferMoney(102, 103, 500));
        executor.execute(() -> bank.transferMoney(103, 101, 200));

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final balances
        bank.printBalances();
    }
}