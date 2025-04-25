/**
 * Abstract account class
 * holder name, 4-digit account number, and balance
 */

import java.util.Random;

public abstract class Account {

    private String holderName;
    private final int accountNumber; // generates between 1000-9999
    private double balance;

    private static final Random RNG = new Random();

    protected Account(String holderName, double openingBalance) {
        this.holderName   = holderName;
        this.balance      = openingBalance;
        this.accountNumber = 1000 + RNG.nextInt(9000);
    }

    /* accessors */

    public int    getAccountNumber()        { return accountNumber; }
    public String getHolderName()           { return holderName;    }
    public double getBalance()              { return balance;       }

    public void   setHolderName(String name){ this.holderName = name; }
    protected void setBalance(double newBal){ this.balance = newBal; }

    /**
     * deposits the amount into the account
     * @parameters amount of money deposited
     */

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Deposit must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("✓ $%.2f deposited.%n", amount);
    }

    /**
     * withdraws amount from the account
     * @parameters amount of money withdrawing
     */
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
