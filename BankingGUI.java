import javax.swing.*;
import java.util.HashMap;
class Account {
    int accNo;
    String name;
    double balance;

    public Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }
}
public class BankingGUI {
    static HashMap<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        showLoginScreen();
    }
    static void showLoginScreen() {
        JFrame frame = new JFrame("Bank Login");
        frame.setSize(300, 250);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Account No:");
        l1.setBounds(20, 30, 100, 25);
        frame.add(l1);

        JTextField accField = new JTextField();
        accField.setBounds(120, 30, 120, 25);
        frame.add(accField);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(20, 70, 100, 25);
        frame.add(l2);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 70, 120, 25);
        frame.add(nameField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(90, 120, 100, 30);
        frame.add(loginBtn);
        JButton createBtn = new JButton("Create Account");
        createBtn.setBounds(70, 160, 150, 30);
        frame.add(createBtn);
        loginBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accField.getText());
                String name = nameField.getText();

                if (accounts.containsKey(accNo) &&
                        accounts.get(accNo).name.equalsIgnoreCase(name)) {
                    frame.dispose();
                    showDashboard(accounts.get(accNo));
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Details!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid input!");
            }
        });
        createBtn.addActionListener(e -> showCreateAccount());

        frame.setVisible(true);
    }
    static void showCreateAccount() {
        JFrame frame = new JFrame("Create Account");
        frame.setSize(300, 250);
        frame.setLayout(null);

        JTextField accField = new JTextField();
        accField.setBounds(120, 30, 120, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 70, 120, 25);

        JTextField balField = new JTextField();
        balField.setBounds(120, 110, 120, 25);

        frame.add(new JLabel("Acc No:")).setBounds(20, 30, 100, 25);
        frame.add(new JLabel("Name:")).setBounds(20, 70, 100, 25);
        frame.add(new JLabel("Balance:")).setBounds(20, 110, 100, 25);

        frame.add(accField);
        frame.add(nameField);
        frame.add(balField);

        JButton createBtn = new JButton("Create");
        createBtn.setBounds(90, 160, 100, 30);
        frame.add(createBtn);

        createBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accField.getText());
                String name = nameField.getText();
                double bal = Double.parseDouble(balField.getText());

                accounts.put(accNo, new Account(accNo, name, bal));

                JOptionPane.showMessageDialog(frame, "Account Created!");
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        frame.setVisible(true);
    }
    static void showDashboard(Account acc) {
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(350, 300);
        frame.setLayout(null);
        JLabel welcome = new JLabel("Welcome " + acc.name);
        welcome.setBounds(100, 20, 200, 25);
        frame.add(welcome);
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(100, 60, 120, 30);
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(100, 100, 120, 30);
        JButton balanceBtn = new JButton("Check Balance");
        balanceBtn.setBounds(100, 140, 120, 30);
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(100, 180, 120, 30);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(balanceBtn);
        frame.add(logoutBtn);
        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount:");
            try {
                double amt = Double.parseDouble(input);
                acc.balance += amt;
                JOptionPane.showMessageDialog(frame, "Deposited!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid!");
            }
        });
        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount:");
            try {
                double amt = Double.parseDouble(input);
                if (amt > acc.balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                } else {
                    acc.balance -= amt;
                    JOptionPane.showMessageDialog(frame, "Withdrawn!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid!");
            }
        });
        balanceBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Balance: ₹" + acc.balance));
        logoutBtn.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });

        frame.setVisible(true);
    }
}