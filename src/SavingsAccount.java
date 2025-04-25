/** Interest-bearing savings account (interest applied on every deposit) */
public class SavingsAccount extends Account {

    private final double annualRate;   // e.g. 0.019 represents 1.9 %

    public SavingsAccount(String holder, double openingBalance, double rate) {
        super(holder, openingBalance);
        this.annualRate = rate;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);      // add cash first
        applyMonthlyInterest();     // then apply one month of interest
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Withdrawal must be positive.");
            return;
        }
        if (amount > getBalance()) {
            System.out.println("✗ Insufficient funds. Leave brokie. Get outta here.");
            return;
        }
        setBalance(getBalance() - amount);
        System.out.printf("✓ $%.2f withdrawn.%n", amount);
    }

    private void applyMonthlyInterest() {
        double monthlyRate = annualRate / 12.0;
        setBalance(getBalance() * (1 + monthlyRate));
    }

    @Override
    public String toString() {
        return super.toString()
                + "Type   : Savings" + "\n"
                + "Rate   : %.2f%%%n"
                .formatted(annualRate * 100);
    }
}
