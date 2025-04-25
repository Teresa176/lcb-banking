/**
 * Abstract account class
 * Encapsulates holder name, immutable 4-digit account number, and balance.
 */

import java.util.Random;

public abstract class Account {

    private String holderName;
    private final int accountNumber;       // 1000-9999, generated once
    private double balance;

    private static final Random RNG = new Random();

    protected Account(String holderName, double openingBalance) {
        this.holderName   = holderName;
        this.balance      = openingBalance;
        this.accountNumber = 1000 + RNG.nextInt(9000);
    }

    /* ---------- basic accessors ---------- */

    public int    getAccountNumber()        { return accountNumber; }
    public String getHolderName()           { return holderName;    }
    public double getBalance()              { return balance;       }

    public void   setHolderName(String name){ this.holderName = name; }

    /** Protected helper – lets subclasses adjust balance safely */
    protected void setBalance(double newBal){ this.balance = newBal; }

    /* ---------- common behaviour ---------- */

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Deposit must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("✓ $%.2f deposited.%n", amount);
    }

    /** Subclasses provide their own overdraft / interest logic */
    public abstract void withdraw(double amount);

    @Override
    public String toString() {
        return """
               ----------------------------------------
               Holder : %s
               Account: %04d
               Balance: $%.2f
               """.formatted(holderName, accountNumber, balance);
    }
}
