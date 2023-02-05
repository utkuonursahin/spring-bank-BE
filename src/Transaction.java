import java.util.*;
import java.sql.*;

public class Transaction {
    private String transactionID;
    private String type;
    private int senderID;
    private int receiverID;
    private int amount;

    //CONSTRUCTOR
    public Transaction(int operation, User user) {
        this.setTransactionID(generateTransactionID());
        this.setType(decideType(operation));
        this.setSenderID(user.getUserID());
        this.setReceiverID(decideReceiver());
        this.setAmount(decideAmount());
    }

    //GETTERS
    public String getTransactionID() {return transactionID;}
    public String getType() {return type;}
    public int getSenderID() {return senderID;}
    public int getReceiverID() {return receiverID;}
    public int getAmount() {return amount;}

    //SETTERS
    public void setTransactionID(String transactionID) {this.transactionID = transactionID;}
    public void setType(String type) {this.type = type;}
    public void setSenderID(int senderID) {this.senderID = senderID;}
    public void setReceiverID(int receiverID) {this.receiverID = receiverID;}
    public void setAmount(int amount) {this.amount = amount;}

    //METHODS
    public String generateTransactionID(){
        Random random = new Random();
        int f4d = random.nextInt(10000);
        char[] chars = {'A','B','C','D','E','F','X','Y','Z','W','a','b','c','d','e','f','x','y','z','w'};
        StringBuilder sb = new StringBuilder();
        Random random1 = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random1.nextInt(chars.length)];
            sb.append(c);
        }
        String str = sb.toString();
        int s4d = random.nextInt(10000);
        return f4d + "-" + str + "-" +s4d;
    }

    public String decideType(int choice){
        String type = "";
        switch(choice){
            case 1: type = "Deposit"; break;
            case 2: type = "Withdraw"; break;
            case 3: type = "Transfer"; break;
            case 4: type = "Debt"; break;
        }
        return type;
    }

    public int decideReceiver(){
        if(!(this.type.equals("Transfer"))) return this.senderID;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the receiver: ");
        return scanner.nextInt();
    }

    public int decideAmount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount: ");
        return scanner.nextInt();
    }

    public boolean isTransactionValid(User user){
        if(this.amount <= 0) return false;
        else if(this.type.equals("Deposit") || this.type.equals("Withdraw")) return true;
        else if(this.type.equals("Transfer") && this.senderID != this.receiverID) return true;
        else if(this.type.equals("Transfer") && user.getDebt() > 0) return true;
        else return false;
    }

    public void transactionDetails(){
        System.out.println("You are viewing your transaction details:");
        System.out.println("Transaction ID: " + this.transactionID);
        System.out.println("Type: " + this.type);
        System.out.println("Sender ID: " + this.senderID);
        System.out.println("Receiver ID: " + this.receiverID);
        System.out.println("Amount: " + this.amount);
    }

    public void executeTransaction(Connection connection) throws SQLException{
        try{
            String sql = "INSERT INTO transactions (idtransaction, type, senderID, receiverID, amount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.transactionID);
            statement.setString(2, this.type);
            statement.setInt(3, this.senderID);
            statement.setInt(4, this.receiverID);
            statement.setInt(5, this.amount);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) System.out.println("Transaction created successfully!");
        } catch (SQLException error){throw new SQLException(error.getMessage());}
    }

    public void updateAccounts(Connection connection) throws SQLException {
        try{
            String sql = "UPDATE users SET balance = balance + ? , debt = debt + ? WHERE iduser = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            if(this.type.equals("Transfer")) {
                statement.setInt(1, this.amount);
                statement.setInt(2, 0);
                statement.setInt(3, this.receiverID);
                statement.executeUpdate();
                statement.setInt(1, -this.amount);
                statement.setInt(2, 0);
                statement.setInt(3, this.senderID);
                statement.executeUpdate();
            } else if(this.type.equals("Deposit")){
                statement.setInt(1, this.amount);
                statement.setInt(2, 0);
                statement.setInt(3, this.senderID);
                statement.executeUpdate();
            } else if(this.type.equals("Withdraw")){
                statement.setInt(1, -this.amount);
                statement.setInt(2, 0);
                statement.setInt(3, this.senderID);
                statement.executeUpdate();
            } else if(this.type.equals("Debt")){
                statement.setInt(1, -this.amount);
                statement.setInt(2, -this.amount);
                statement.setInt(3, this.senderID);
                statement.executeUpdate();
            }
            System.out.println("Transactions made to accounts!");
        } catch (SQLException error){throw new SQLException(error.getMessage());}
    }

    public static void listMyTransactions(Connection connection, User usr) throws SQLException{
        try{
            String sql = "SELECT * FROM transactions WHERE senderID = ? or receiverID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usr.getUserID());
            statement.setInt(2, usr.getUserID());
            ResultSet result = statement.executeQuery();
            while(result.next()){
                System.out.println("Transaction ID: " + result.getString("idtransaction"));
                System.out.println("Type: " + result.getString("type"));
                System.out.println("Sender ID: " + result.getInt("senderID"));
                System.out.println("Receiver ID: " + result.getInt("receiverID"));
                System.out.println("Amount: " + result.getInt("amount"));
                System.out.println("--------------------------------------------------");
            }
        } catch (SQLException error){throw new SQLException(error.getMessage());}
    }
}
