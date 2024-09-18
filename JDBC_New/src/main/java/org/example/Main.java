    package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

//    C - Create
//    R - Read
//    U - Update
//    D - DELETE

//    1. statement
//    2. prepared statement
//    3. callable statement

    public static void main(String[] args) {
//        updateData("Mang", "Kepweng", "kepweng@yahoo.com", 7);
//        deleteData(5);
//        fetchData();
    }

    public static void create(User user) {
//                                                  1         2        3
        String insertQuery = "INSERT INTO users(firstname, lastname, email) " +
//                      1  2  3
                "VALUES(?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                System.out.println("Inserted Successfully!");
            }
            else {
                System.out.println("ERROR!");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(User user) {
//                                                         1             2          3            4
        String insertQuery = "UPDATE users SET firstname = ?, lastname = ?, email = ? WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                System.out.println("Updated Successfully!");
            }
            else {
                System.out.println("ERROR!");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(int id) {
//
        String insertQuery = "DELETE FROM users WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, id);

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                System.out.println("Deleted Successfully!");
            }
            else {
                System.out.println("ERROR!");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fetchData() {
        String fetchDataQuery = "SELECT * FROM users";
        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(fetchDataQuery);
        ) {

            while(resultSet.next()) {
                System.out.println("----------");
                System.out.println("id: " + resultSet.getString("id"));
                System.out.println("first name: " + resultSet.getString("firstname"));
                System.out.println("last name: " + resultSet.getString("lastname"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("----------");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}