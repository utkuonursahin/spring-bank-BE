import java.util.*;
import java.sql.*;

public class Auth {
    public static boolean isSignedUp() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer.toUpperCase().equals("Y");
    }

    public static User login(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username:");
        String username = scanner.nextLine();
        System.out.print("Enter your password:");
        String password = scanner.nextLine();
        try{
            String query = "SELECT password FROM bank.users WHERE username = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, username);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(!(resultSet.next())) throw new SQLException("No such user exists.");
            else if(!(BCrypt.checkpw(password, resultSet.getString("password")))) throw new SQLException("Username or password is incorrect.");

            query = "SELECT * FROM bank.users WHERE username = ?";
            prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, username);
            resultSet = prepareStatement.executeQuery();
            System.out.println("Login successful...");
            resultSet.next();
            return new User(resultSet.getInt("iduser"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("balance"),
                    resultSet.getInt("debt"));
        }
        catch(SQLException error){throw new SQLException(error.getMessage());}
    }

    public static void signup(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username:");
        String username = scanner.nextLine();
        System.out.print("Enter your password:");
        String password = scanner.nextLine();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        try{
            String query = "INSERT INTO bank.users (username, password)"+"VALUES (?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, hashedPassword);
            prepareStatement.executeUpdate();
        }catch (SQLException error){throw new SQLException(error.getMessage());}
    }

    public static User refreshSession(Connection connection, User user) throws SQLException {
        try{
            String query = "SELECT * FROM bank.users WHERE iduser = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, user.getUserID());
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()){
                return new User(resultSet.getInt("iduser"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("balance"),
                        resultSet.getInt("debt"));
            } else return null;

        }catch (SQLException error){throw new SQLException(error.getMessage());}
    }

    public static void changePassword(Connection connection, User user) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your old password:");
        String oldPassword = scanner.nextLine();
        System.out.print("Enter your new password:");
        String newPassword = scanner.nextLine();
        try{
            String query = "SELECT password FROM bank.users WHERE iduser = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, user.getUserID());
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.next() && !(BCrypt.checkpw(oldPassword, resultSet.getString("password")))) throw new SQLException("Old password is incorrect.");
            else if(BCrypt.checkpw(newPassword, resultSet.getString("password"))) throw new SQLException("New password cannot be the same as the old password.");
            newPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            String updateQuery = "UPDATE bank.users SET password = ? WHERE iduser = ?";

            prepareStatement = connection.prepareStatement(updateQuery);
            prepareStatement.setString(1, newPassword);
            prepareStatement.setInt(2, user.getUserID());
            prepareStatement.executeUpdate();
        }catch (SQLException error){throw new SQLException(error.getMessage());}
    }
}
