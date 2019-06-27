package server;

import message.Message;

import java.sql.*;

public class AuthSQL {
    private static Connection connection;
    private static Statement statement;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:usersDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {;
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginPass(String login,String pass){
        String result = null;
        String sql = "select nick from users where login=\"%s\" and pass=\"%s\"";
        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,login,pass));
            if (resultSet.next()){
                result = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
