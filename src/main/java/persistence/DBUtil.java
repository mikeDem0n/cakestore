package main.java.persistence;

import java.sql.*;

public class DBUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/cakestore";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed. Check output console.");
            e.printStackTrace();
        }
        if (connection == null) {
            System.err.println("Failed to establish connection. Connection object is null.");
        } else {
            System.out.println("Connection established successfully.");
        }
        return connection;
    }


    public static void closeConnection(Connection connection) {
        if(connection != null){
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement statement) {
        if(statement != null){
            try{
                statement.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /*public static void main(String[] args) {
        System.out.println(getConnection());
    }*/
}
