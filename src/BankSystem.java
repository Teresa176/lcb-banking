import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** main banking console */
public class BankSystem {

    private final List<Account> accounts = new ArrayList<>();
    private final Scanner in = new Scanner(System.in);

    /* preset accounts */
    public BankSystem() {
        accounts.add(new CheckingAccount("Diego Martin", 668.57, 100.00));
        accounts.add(new SavingsAccount ("Janice Watt" , 120.00, 0.019));
        accounts.add(new CheckingAccount("Michael Rose",  37.65,   0.0));
    }

    /* menu loop */
    public void run() {
        System.out.println("London Central Bank – Teller Console");

        while (true) {
            printMenu();
            int choice = readInt("Select option: ");

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> displayAccount();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> { System.out.println("Session ended. Goodbye!"); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* helper methods */

    private void createAccount() {
        System.out.print("Create (C)hecking or (S)avings? ");
        char kind = in.next().trim().toUpperCase().charAt(0); in.nextLine();

        String name   = readLine("Account holder name: ");
        double open   = readDouble("Opening balance: $");

        Account newAcc;
        if (kind == 'C') {
            double lim = readDouble("Overdraft limit: $");
            newAcc = new CheckingAccount(name, open, lim);
        } else if (kind == 'S') {
            double rate = readDouble("Annual interest rate (e.g. 0.0185 = 1.85%) : ");
            newAcc = new SavingsAccount(name, open, rate);
        } else {
            System.out.println("Invalid, try again");
            return;
        }
        accounts.add(newAcc);
        System.out.printf("Account created successfully! Your new account number is %04d.%n",
                newAcc.getAccountNumber());
    }

    private void displayAccount() {
        Account a = locateAccount();
        if (a != null) System.out.println(a);
    }

    private void deposit() {
        Account a = locateAccount();
        if (a == null) return;
        double amt = readDouble("Amount to deposit : $");
        a.deposit(amt);
    }

    private void withdraw() {
        Account a = locateAccount();
        if (a == null) return;
        double amt = readDouble("Amount to withdraw: $");
        a.withdraw(amt);
    }

    private Account locateAccount() {
        int id = readInt("Enter 4-digit account number: ");
        return accounts.stream()
                .filter(ac -> ac.getAccountNumber() == id)
                .findFirst()
                .orElseGet(() -> { System.out.println("Account not found."); return null; });
    }

    private void printMenu() {
        System.out.println("""
                           
                           1  Create new account
                           2  Display account details
                           3  Deposit money
                           4  Withdraw money
                           5  Quit
                           """);
    }

    /* readers */
    /* converts values */

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) return in.nextInt();
            in.next();          
            System.out.println("Invalid, enter a whole number.");
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextDouble()) return in.nextDouble();
            in.next();
            System.out.println("Invalid, enter a valid amount.");
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }

    /* ---------- entry point ---------- */
    public static void main(String[] args) {
        new BankSystem().run();
    }
}