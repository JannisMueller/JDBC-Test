package com.company;

import java.util.List;

public interface UserDao {

    /**
     * Creates a new object User
     * @param id
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     */
    void create (String id, String userName,String password,String firstName,String lastName,String email,String phone);

    /**
     * gets all entries of a table
     * @return list
     */
    List<User> getALl();

    /**
     * Method that finds all entries with the specified name as parameter
     *
     * @param name Name that the user would like to find in the database
     * @return result of query as list
     *
     */
    List<User> getByName (String name);

    /**
     *
     * @param id
     * @return
     */

    /**
     * Method that deletes entries with the specified ID as parameter
     *
     * @param id id of the entry the user wants to permanently delete from the database
     * @return true id deletion was successful
     *
     */
    boolean delete (String id);





}
