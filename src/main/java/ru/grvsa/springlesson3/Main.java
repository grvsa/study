package ru.grvsa.springlesson3;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static BufferedReader reader;
    public static Session session = null;
    public static SessionFactory factory = null;
    public static String command = "";

    public static void main(String[] args) {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        if (factory == null){
            System.out.println("Что то пошло не так ...");
            System.exit(1);
        }

        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (!command.equalsIgnoreCase("exit")){
                menu();
                command = reader.readLine();
                if (command.equals("1")){
                    showAllProducts();
                }else if (command.equals("2")){
                    showAllUsers();
                }else if (command.equals("3")){
                    showAllOrders();
                }else if (command.equals("4")){
                    showOrdersByUser();
                }else if (command.equals("5")){
                    showUsersByProduct();
                }else if (command.equals("6")){
                    deleteUser();
                }else if (command.equals("7")){
                    deleteOrder();
                }else if (command.equals("8")){
                    deleteProduct();
                }else if (command.equalsIgnoreCase("exit")){
                    System.out.println("Bye !!!");
                }else{
                    System.out.println("Неподдерживаемая команда");
                }
            }
        }catch(IOException e){
            System.out.println("Что то пошло не так");
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void menu(){
        System.out.println("1. Вывести список продуктов");
        System.out.println("2. Вывести список пользователей");
        System.out.println("3. Вывести список заказов");
        System.out.println("4. Вывести список заказов конкретного пользователя");
        System.out.println("5. Вывести список пользователей определенного продукта");
        System.out.println("6. Удалить пользователя");
        System.out.println("7. Удалить заказ");
        System.out.println("8. Удалить продукт");
        System.out.println("Наберите [exit] для выхода");
        System.out.println("Выберите команду[1-8]:");
    }

    public static void deleteUser() throws IOException{
        System.out.println("Выберите пользователя для удаления из списка: ");
        List<User> users = showAllUsers();
        User user = null;
        try {
            int num = Integer.parseInt(reader.readLine());
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == num){
                    user = users.get(i);
                    break;
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Ошибка ввода данных !");
        }

        if (user != null){
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }else {
            System.out.println("Выбраный пользователь отсутствует");
        }
    }

    public static void deleteProduct() throws IOException{
        System.out.println("Выберите продукт из списка: ");
        List<Product> products = showAllProducts();

        Product product = getProduct(products);

        if (product != null){
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.remove(product);
            session.getTransaction().commit();
        }else {
            System.out.println("Выбраный продукт отсутсвует");
        }

    }

    public static Product getProduct(List<Product> products) throws IOException{
        Product product = null;
        try{
            int num = Integer.parseInt(reader.readLine());

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == num){
                    product = products.get(i);
                    break;
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Ошибка ввода данных !!!");
        }
        return product;
    }

    public static User getUser(List<User> users) throws IOException{
        User user = null;
        try {
            int number = Integer.parseInt(reader.readLine());

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == number) {
                    user = users.get(i);
                    break;
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Ошибка ввода данных !!!");
        }
        return user;
    }

    public static void deleteOrder() throws IOException{
        System.out.println("Выберите заказ из списка: ");
        List<Order> orders = showAllOrders();
        Order order = getOrder(orders);

        if (order != null){
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.remove(order);
            session.getTransaction().commit();
        }else {
            System.out.println("Выбраный заказ отсутствует");
        }
    }

    public static Order getOrder(List<Order> orders) throws IOException{
        Order order = null;
        try {
            int number = Integer.parseInt(reader.readLine());

            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getId() == number) {
                    order = orders.get(i);
                    break;
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Ошибка ввода данных !!!");
        }
        return order;
    }

    public static List<User> showAllUsers(){
        System.out.println("Вывод списка пользователей");
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        for (User user :
                users) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        return users;
    }

    public static List<Product> showAllProducts(){
        System.out.println("Вывод списка продуктов");
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product").getResultList();
        for (Product product :
                products) {
            System.out.println(product);
        }
        session.getTransaction().commit();
        return products;
    }

    public static List<Order> showAllOrders(){
        System.out.println("Вывод списка заказов");
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Order> orders = session.createQuery("from Order").getResultList();
        for (Order order :
                orders) {
            System.out.println(order);
        }
        session.getTransaction().commit();
        return orders;
    }

    public static void showOrdersByUser() throws IOException{
        System.out.println("Выберите пользователя из списка:");
        List<User> users = showAllUsers();
        User user = getUser(users);

        if (user != null){
            session = factory.getCurrentSession();
            session.beginTransaction();
            user = (User) session.load(User.class, user.getId());
            List<Order> orders = user.getOrders();
            System.out.println("Список заказов пользователя " + user);
            for (Order order :
                    orders) {
                System.out.println(order);
            }
            session.getTransaction().commit();
        }else {
            System.out.println("Выбраный пользователь отсутствует");
        }
    }

    public static void showUsersByProduct() throws IOException {
        System.out.println("Выберите продук из списка");
        List<Product> products = showAllProducts();
        Product product = getProduct(products);

        if (product != null) {
            Set<User> users = new HashSet<User>();
            session = factory.getCurrentSession();
            session.beginTransaction();

            List<Order> orders = ((Product) session.load(Product.class, product.getId())).getOrders();
            for (Order order :
                    orders) {
                users.add(order.getUser());
            }

            for (User user :
                    users) {
                System.out.println(user);
            }
            session.getTransaction().commit();
        }else{
            System.out.println("Выбраный продукт отсутствует");
        }
    }

}
