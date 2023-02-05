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
                    if(operation > 0 && operation < 5){
                        Transaction transaction = new Transaction(operation, usr);
                        if(transaction.isTransactionValid(usr)){
                            transaction.transactionDetails();
                            transaction.executeTransaction(connection);
                            transaction.updateAccounts(connection);
                            usr = Auth.refreshSession(connection, usr);
                        } else System.out.println("Transaction not valid. Try again.");
                    }
                    else if(operation == 5) usr.accDetails();
                    else if(operation == 6) Transaction.listMyTransactions(connection, usr);
                    else if(operation == 7) Auth.changePassword(connection, usr);
                    else if(operation == 8) usr.accDelete(connection);
                    Bank.menu();
                    operation = Bank.chooseOperation();
                }
                System.out.println("Thank you for visiting the Bank of Java!");
                System.exit(0);
            } else Bank.onboard(connection);
        } catch(SQLException error){System.out.println("Error: " + error.getMessage());}
    }
}