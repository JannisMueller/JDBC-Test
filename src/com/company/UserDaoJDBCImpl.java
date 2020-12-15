package com.company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection conn;
    private PreparedStatement createStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getAllByAge;
    private PreparedStatement deleteById;

    /**
     * constructor with Connection to database and prepared statements with SQL queries
     *
     * @throws Exception
     */
    public UserDaoJDBCImpl() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=everyloop","sa","neuseeland1");
            createStatement = conn.prepareStatement("INSERT INTO Users (id,userName,password,firstName,lastName, email, phone) VALUES (?,?,?,?,?,?,?)");
            getAllStatement = conn.prepareStatement("SELECT * FROM Users");
            getAllByAge = conn.prepareStatement("SELECT * FROM Users WHERE firstName = ?");
            deleteById = conn.prepareStatement("DELETE FROM Users WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Problem in DAP constructor " + e);
        }
    }

    /**
     * Method to create a new entry in database
     *
     * @param id (PK of the database)
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     *
     * @throws Exception
     */
    @Override
    public void create(String id, String userName, String password, String firstName, String lastName, String email, String phone) {
        try {
            createStatement.setString(1, id);
            createStatement.setString(2, userName);
            createStatement.setString(3, password);
            createStatement.setString(4, firstName);
            createStatement.setString(5, lastName);
            createStatement.setString(6, email);
            createStatement.setString(7, phone);

            createStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Problem in DAO create " + e);
        }
    }

    /**
     * Method that gets all entries of the database
     *
     * @return result of Query as list
     * @throws Exception
     */
    @Override
    public List<User> getALl() {
        ArrayList<User> result = new ArrayList<>();
        try {
            ResultSet rs = getAllStatement.executeQuery();
            while (rs.next()) {
                result.add(new User(rs.getString("ID"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone")

                ));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Problem in DAO getAll " + e);
        }
    }

    /**
     * Method that finds all entries with the specified name as parameter
     *
     * @param name Name that the user would like to find in the database
     * @return result of query as list
     * @throws Exception
     */

    @Override
    public List<User> getByName (String name) {
        ArrayList<User> resultByAge = new ArrayList<>();
        try {
            getAllByAge.setString(1,name);
            ResultSet rs = getAllByAge.executeQuery();
            while (rs.next()) {
                resultByAge.add(new User(rs.getString("ID"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone")

                ));
            }
            return resultByAge;

        } catch (SQLException e) {
            throw new RuntimeException("Problem in DAO getAll " + e);
        }

    }

    /**
     * Method that deletes entries with the specified ID as parameter
     *
     * @param id id of the entry the user wants to permanently delete from the database
     * @return true id deletion was successful
     * @throws Exception
     */
    @Override
    public boolean delete(String id) {
        try {
            deleteById.setString(1,id);
            deleteById.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete entry " + e);
        }


    }
}