package com.company.db;
import com.company.models.Loan;
import com.company.models.Publication;
import com.company.models.User;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:DatabaseLibrary.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Statement statement;
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(User user) throws SQLException {
        String sqlQuery = "INSERT INTO USER(REGISTRATION, NAME) VALUES ('"+user.registration+"','"+user.name+"')";
        statement.execute(sqlQuery);
    }
    public static void insertPublication(Publication publication) throws SQLException {
        String sqlQuery = "INSERT INTO PUBLICATION(TITLE, AUTHOR, QUANTITY) VALUES ('"+publication.title+"','"+publication.author+"','"+publication.quantity+"')";
        statement.execute(sqlQuery);
    }
    public static void insertLoan(Loan loan) throws SQLException {
        String sqlQuery = "INSERT INTO LOAN(USERID, PUBLICATIONID, LATEDAYS, ISRETURNED) VALUES ('"+loan.userId+"','"+loan.publicationId+"','"+loan.lateDays+"','"+loan.isReturned+"')";
        statement.execute(sqlQuery);
    }
    public static void deleteUser(int id) throws SQLException {
        String sqlQuery = "DELETE FROM USER WHERE ID = "+id;
        statement.execute(sqlQuery);
    }

    public static void deletePublication(int id) throws SQLException {
        String sqlQuery = "DELETE FROM PUBLICATION WHERE ID = "+id;
        statement.execute(sqlQuery);
    }
    public static void updateUser(User user) throws SQLException {
        String sqlQuery = "UPDATE USER SET REGISTRATION = '"+user.registration+"', NAME = '"+user.name+"' WHERE ID = "+user.id;
        statement.execute(sqlQuery);
    }

    public static void updatePublication(Publication publication) throws SQLException {
        String sqlQuery = "UPDATE PUBLICATION SET TITLE = '"+publication.title+"', AUTHOR = '"+publication.author+"', QUANTITY = '"+publication.quantity+"' WHERE ID = "+publication.id;
        statement.execute(sqlQuery);
    }

    public static void updateLoan(Loan loan) throws SQLException {
        String sqlQuery = "UPDATE LOAN SET LATEDAYS  = '"+loan.lateDays+"', ISRETURNED = '"+loan.isReturned+"' WHERE ID = "+loan.loanId;
        statement.execute(sqlQuery);
    }

    public static ArrayList<User> getAllUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            String registration = resultSet.getString("REGISTRATION");
            String name = resultSet.getString("NAME");
            User user = new User(id, registration, name);
            users.add(user);
        }

        return users;
    }

    public static ArrayList<Publication> getAllPublication() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PUBLICATION");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Publication> publications = new ArrayList<>();

        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            String title = resultSet.getString("TITLE");
            String author = resultSet.getString("AUTHOR");
            Integer quantity = resultSet.getInt("QUANTITY");

            Publication publication = new Publication(id,title,author,quantity);
            publications.add(publication);
        }

        return publications;
    }
    public static Loan getLoan(int client_id, int publication_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOAN WHERE USERID = "+client_id+" AND PUBLICATIONID = "+publication_id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            Integer userid = resultSet.getInt("USERID");
            Integer publicationid = resultSet.getInt("PUBLICATIONID");
            Integer lateDays = resultSet.getInt("LATEDAYS");
            String isreturned = resultSet.getString("ISRETURNED");
            return new Loan(id,userid,publicationid,lateDays,isreturned);
        }
        return null;
    }

    public static ArrayList<Publication> getPublicationsFromUserId(int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOAN WHERE USERID = "+userId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Publication> publications = new ArrayList<>();

        while (resultSet.next()){
            Integer publicationId = resultSet.getInt("PUBLICATIONID");
            String returned = resultSet.getString("ISRETURNED");
            if(returned.equals("n")){
                Publication publication = getPublication(publicationId);
                publications.add(publication);
            }
        }
        return publications;
    }
    public static Publication getPublication(int publicationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PUBLICATION WHERE ID = "+publicationId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            String title = resultSet.getString("TITLE");
            String author = resultSet.getString("AUTHOR");
            Integer quantity = resultSet.getInt("QUANTITY");

            return new Publication(id,title,author,quantity);
        }
        return null;
    }

    public static Loan getActualLoan(int userId, int publicationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOAN WHERE USERID = '"+userId+"' AND PUBLICATIONID = "+publicationId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            Integer userid = resultSet.getInt("USERID");
            Integer publicationid = resultSet.getInt("PUBLICATIONID");
            Integer lateDays = resultSet.getInt("LATEDAYS");
            String isreturned = resultSet.getString("ISRETURNED");

            if(isreturned.equals("n")){
                return new Loan(id,userid,publicationid,lateDays,isreturned);
            }
        }
        return null;
    }



}
