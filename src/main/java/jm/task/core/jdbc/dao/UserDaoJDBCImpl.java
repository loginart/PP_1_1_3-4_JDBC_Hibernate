package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE UsersTable (id INTEGER, name VARCHAR(255) NOT NULL , lastName VARCHAR(255) NOT NULL, age INTEGER NOT NULL)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

     String sql = "DROP TABLE UsersTable";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }



    public void saveUser(String name, String lastName, byte age) {

    String sql = "INSERT INTO UsersTable (name, lastName, age) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }


    public void removeUserById(long id) {

    String sql = "DELETE FROM UsersTable WHERE id = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        String sql = "SELECT * FROM UsersTable";
        try(Statement statement= connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {

    String sql = "DELETE FROM UsersTable";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

        preparedStatement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
    }
    }
}
