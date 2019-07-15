package server;

import message.Message;
import message.Type;

import java.sql.*;
import java.util.*;

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

    public synchronized static String getNickByLoginPass(String login,String pass){
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

    public synchronized static boolean checkNickValid(String nick){
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

    public synchronized static boolean checkLoginValid(String login){
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

    public synchronized static boolean register(String login,String pass, String nick){
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

    public synchronized static void addToBanList(String nick1, String nick2){
        String sql = "insert into banlist (login,ban) values((select login from users where nick=\"%s\"), (select login from users where nick=\"%s\"))";
        try {
            statement.execute(String.format(sql,nick1,nick2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void removeFromBan(String nick1, String nick2){
        String sql = "delete from banlist where login=(select login from users where nick=\"%s\") and ban=(select login from users where nick=\"%s\")";
        try {
            statement.execute(String.format(sql,nick1,nick2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Set<String> getBanListForUser(String login){
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

    public synchronized static String getLoginByNick(String nick){
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

    public synchronized static Set<String> getBanListByLogin(String login){
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

    public synchronized static void saveMessage(Message message){
        String destination = message.getDestination().toString().substring(1,message.getDestination().toString().length() - 1);
        String sql = String.format(
                "insert into history (type,fromNick,message,parameters,destination) values(%s,\"%s\",\"%s\",\"%s\",\"%s\")",
                message.getType().getValue(),
                message.getFrom(),
                message.getMessage(),
                message.getParameters(),
                destination);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static List<Message> getHistoryForNick(String nick, int count){
        List<Message> result = new ArrayList<>();
        String sql = "select * from history order by id desc";

        try {
            ResultSet set = statement.executeQuery(sql);
            while (set.next() && count > 0){
                int type = set.getInt(2);
                String from = set.getString(3);
                String message = set.getString(4);
                String parameters = set.getString(5);
                String destination = set.getString(6);
                if (type == 4){
                    result.add(new Message(Type.MESSAGE,from,message,parameters));
                    count--;
                }else{
                    if (from.equals(nick) || destination.contains(nick)){
                        Message temp = new Message(Type.PRIVATE,from,message,parameters);
                        temp.getDestination().addAll(Arrays.asList(destination.split(", ")));
                        result.add(temp);
                        count--;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
