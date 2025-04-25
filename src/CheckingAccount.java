/** Current account with an overdraft */
public class CheckingAccount extends Account {

    private final double overdraftLimit;

    public CheckingAccount(String holder, double openingBalance, double limit) {
        super(holder, openingBalance);
        this.overdraftLimit = limit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Withdrawal must be positive.");
            return;
        }
        if (getBalance() - amount < -overdraftLimit) {
            System.out.printf("✗ Transaction would exceed overdraft limit ($%.2f).%n", overdraftLimit);
            return;
        }
        setBalance(getBalance() - amount);
        System.out.printf("✓ $%.2f withdrawn.%n", amount);
    }

    @Override
    public String toString() {
        return super.toString()
                + "Type   : Checking" + "\n"
                + "O/D Lim: $%.2f%n"
                .formatted(overdraftLimit);
    }
}
