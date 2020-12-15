package com.company;
import java.util.List;

public class Main {


    public static void main(String[] args){

        //creates a new UserDao Interface
        UserDao userDao = new UserDaoJDBCImpl();

        //creates new User and puts the user in database
        userDao.create("841314-3334","jamu","1234","Jannis","Mueller","test@test.se","ß733068922");

        //prints out all entries from the database
        List<User> list = userDao.getALl();
        System.out.println(list);

        //prints out all entries with first name Johanna
        List<User> list1 = userDao.getByName("Johanna");
        System.out.println(list1);

        //deletes user from database
        userDao.delete("841314-3334");









       /* try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=everyloop","sa","neuseeland1");
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM Users");

            while (rs.next()){
                System.out.println(rs.getString("FirstName"));
            }

        } catch (SQLException e) {
           throw new RuntimeException("couldnt connect" + e);
        }
*/



    }
}
