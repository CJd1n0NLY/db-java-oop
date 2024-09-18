package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplementation implements UserDao {
    private final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "";


    @Override
    public List<User> getAllUsers() {
        String fetchDataQuery = "SELECT * FROM users";

        List<User> userList = new ArrayList<>();

        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(fetchDataQuery);
        ) {

            while(resultSet.next()) {
               User userInfo = new User();
               userInfo.setId(resultSet.getInt("id"));
               userInfo.setFirstname(resultSet.getString("firstname"));
               userInfo.setLastname(resultSet.getString("lastname"));
               userInfo.setEmail(resultSet.getString("email"));

               userList.add(userInfo);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int id) {
        String fetchDataQuery = "SELECT * FROM users WHERE id = " + id;

        User user = new User();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchDataQuery)){

            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getByEmail(String email) {
        String fetchDataQuery = "SELECT * FROM users WHERE email = '" + email + "'";

        User user = new User();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchDataQuery)){

            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

//        USING PREPARED STATEMENT
//        String fetchDataQuery = "SELECT * FROM users WHERE email = ?";
//
//        User user = new User();
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(fetchDataQuery)) {
//
//            preparedStatement.setString(1, email);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    user.setId(resultSet.getInt("id"));
//                    user.setFirstname(resultSet.getString("firstname"));
//                    user.setLastname(resultSet.getString("lastname"));
//                    user.setEmail(resultSet.getString("email"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean create(User user) {
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
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  false;
    }

    @Override
    public boolean delete(int id) {
//                                                  1         2        3
        String deleteQuery = "DELETE FROM users WHERE id = ?";
//                      1  2  3
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  false;
    }
    @Override
    public boolean update(User user) {
//                                                  1         2        3
        String updateQuery = "UPDATE users SET firstname = ?, lastname = ?, email = ? WHERE id = ?";


        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());

            int affectedRow = preparedStatement.executeUpdate();

            if(affectedRow > 0) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  false;
    }

}