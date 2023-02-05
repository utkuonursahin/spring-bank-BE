import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Bank.welcome();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bank","root","test1234")){
            if(Auth.isSignedUp()){
                User usr = Auth.login(connection);
                Bank.menu();
                int operation = Bank.chooseOperation();
                while(operation != 0){
                    if(operation == 1) usr.accDetails();
                    else if(operation == 2) Auth.changePassword(connection, usr);
                    else if(operation == 3) Transaction.listMyTransactions(connection, usr);
                    else {
                        Transaction transaction = new Transaction(operation, usr);
                        if(transaction.isTransactionValid(usr)){
                            transaction.transactionDetails();
                            transaction.executeTransaction(connection);
                            transaction.updateAccounts(connection);
                            usr = Auth.refreshSession(connection, usr);
                        } else System.out.println("Transaction not valid. Try again.");
                    }
                    Bank.menu();
                    operation = Bank.chooseOperation();
                }
            } else Bank.onboard(connection);
        } catch(SQLException error){System.out.println("Error: " + error.getMessage());}
    }
}