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
}