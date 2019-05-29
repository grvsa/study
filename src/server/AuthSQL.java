package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthSQL {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userTable.db");
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

    public static String getNickByLoginPass(String login, int pass){
        String sql = "select nick from userTable where login=? and pass=?";
        String nick = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setInt(2,pass);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                nick = resultSet.getString(1);
            }

            return nick;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "user2";
    }

    public static void blackListAddItem(String loginUser, String nick){
        try {
            String sql = "insert into blackList(loginUser,loginBan) values(?,(select login from userTable where nick=?))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginUser);
            preparedStatement.setString(2,nick);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void blackListRemoveItem(String loginUser, String nick){
        try {
            String sql = "delete from blackList where loginUser=? and loginBan = (select login from userTable where nick=?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginUser);
            preparedStatement.setString(2,nick);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> blackListAsList(String login){
        ArrayList<String> result = new ArrayList<>();
        try {
            String sql = "select loginUser from blackList where loginBan=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String banList(String login){
        StringBuilder sb = new StringBuilder("blackList ");
        String sql ="select nick from userTable inner join blackList on userTable.login = blackList.loginBan where loginUser=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sb.append(resultSet.getString(1) + " ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean checkAvailableNick(String nick){
        String sql = String.format("select nick from userTable where nick=\"%s\"",nick);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void setNick(String login, String nick){
        String sql = "update userTable set nick=? where login=?";
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nick);
            preparedStatement.setString(2,login);
            result = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
