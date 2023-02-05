import java.util.*;
import java.sql.*;

public class Bank {
    public static void welcome(){
        System.out.println("***** Welcome to the Bank of Java! *****");
        System.out.print("Do you have an account with us? (Y/N)");
    }
    public static void menu(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. View account details");
        System.out.println("2. Change password");
        System.out.println("3. List my transactions");
        System.out.println("4. Deposit money");
        System.out.println("5. Withdraw money");
        System.out.println("6. Transfer money");
        System.out.println("7. Pay debt");
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
        switch(choice){
            case 1:
                System.out.println("View account details");
                break;
            case 2:
                System.out.println("Change password");
                break;
            case 3:
                System.out.println("List my transactions");
                break;
            case 4:
                System.out.println("Deposit money");
                break;
            case 5:
                System.out.println("Withdraw money");
                break;
            case 6:
                System.out.println("Transfer money");
                break;
            case 7:
                System.out.println("Pay debt");
                break;
            case 0:
                System.out.println("Exit");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                chooseOperation();
        }
        return choice;
    }
}
