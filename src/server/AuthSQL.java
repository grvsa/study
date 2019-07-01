package server;

import message.Message;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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

    public static boolean checkNickValid(String nick){
        String sql = "select id from users where nick=\"%s\"";
        boolean result = true;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,nick));
            while (resultSet.next()){
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkLoginValid(String login){
        String sql = "select id from users where login=\"%s\"";
        boolean result = true;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,login));
            while (resultSet.next()){
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean register(String login,String pass, String nick){
        String sql = "insert into users (login,pass,nick) values(\"%s\",\"%s\",\"%s\")";
        boolean result = true;
        try {
            statement.execute(String.format(sql,login,pass,nick));
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;

    }

    public static void addToBanList(String nick1, String nick2){
        String sql = "insert into banlist (login,ban) values((select login from users where nick=\"%s\"), (select login from users where nick=\"%s\"))";
        try {
            statement.execute(String.format(sql,nick1,nick2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromBan(String nick1, String nick2){
        String sql = "delete from banlist where login=(select login from users where nick=\"%s\") and ban=(select login from users where nick=\"%s\")";
        try {
            statement.execute(String.format(sql,nick1,nick2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getBanListForUser(String login){
        String sql = "select users.nick from banlist\n" +
                "inner join users on banlist.login = users.login\n" +
                "where banlist.ban=\"%s\"";
        Set<String> banlist = new HashSet<>();

        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,login));
            while (resultSet.next()){
                banlist.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banlist;
    }

    public static String getLoginByNick(String nick){
        String result ="";
        String sql = "select login from users where nick=\"%s\"";
        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,nick));
            if (resultSet.next()) result = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Set<String> getBanListByLogin(String login){
        Set<String> set = new HashSet<>();
        String sql ="select users.nick from banlist\n" +
                "inner join users on banlist.ban = users.login\n" +
                "where banlist.login = \"%s\"";

        try {
            ResultSet resultSet = statement.executeQuery(String.format(sql,login));
            while (resultSet.next()){
                set.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

}
