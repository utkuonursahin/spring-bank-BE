import java.util.*;
import java.sql.*;

public class Bank {
    public static void welcome(){
        System.out.println("***** Welcome to the Bank of Java! *****");
        System.out.print("Do you have an account with us? (Y/N)");
    }
    public static void menu(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. Transfer money");
        System.out.println("4. Pay debt");
        System.out.println("5. View account details");
        System.out.println("6. List my transactions");
        System.out.println("7. Change password");
        System.out.println("8. Delete account");
        System.out.println("0. Exit");
    }

    public static void onboard(Connection connection) throws SQLException {
        try{
            System.out.print("Do you want to sign up? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.toUpperCase().equals("Y")){
                Auth.signup(connection);
                System.out.println("Thank you for signing up with the Bank of Java!... Now you can login to your account.");
                System.exit(0);
            } else{
                System.out.println("Thank you for visiting the Bank of Java!");
                System.exit(0);
            }
        }catch(SQLException error){throw new SQLException(error.getMessage());}
    }

    public static int chooseOperation(){
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while(choice < 0 || choice > 8){
            System.out.println("Invalid choice. Try again.");
            choice = scanner.nextInt();
        }
        return choice;
    }
}
