package com.company.db;
import com.company.models.Loan;
import com.company.models.Publication;
import com.company.models.User;

import java.sql.*;

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
        String sqlQuery = "UPDATE LOAN SET LATEDAYS  = '"+loan.lateDays+"', RETURNED = '"+loan.isReturned+"' WHERE ID = "+loan.loanId;
        statement.execute(sqlQuery);
    }

}
