import java.util.*;
import java.sql.*;
public class User {
    private int userID;
    private String username;
    private String password;
    private int balance;
    private int debt;

    //CONSTRUCTOR
    public User(int userID, String username, String password, int balance, int debt) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.debt = debt;
    }

    //GETTERS
    public int getUserID() {return userID;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public int getBalance() {return balance;}
    public int getDebt() {return debt;}

    //SETTERS
    public void setUserID(int userID) {this.userID = userID;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setBalance(int balance) {this.balance = balance;}
    public void setDebt(int debt) {this.debt = debt;}

    //METHODS
    public void accDetails(){
        System.out.println("You are viewing your account details:");
        System.out.println("Username: " + this.username);
        System.out.print("Balance: " + this.balance);
        System.out.print(" | "+"Debt: " + this.debt);
    }

    public void accDelete(Connection connection) throws SQLException {
        System.out.println("Are you sure you want to delete your account? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(!answer.toUpperCase().equals("Y")) return;
        try {
            String sql = "DELETE FROM users WHERE iduser = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.userID);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) System.out.println("Your account was deleted successfully!");
            System.exit(0);
        } catch (SQLException error) {throw new SQLException(error.getMessage());}
    }
}