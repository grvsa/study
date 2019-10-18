package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named
public class ShopRepository {

    /*
        CREATE SCHEMA `javaee_shop_db` ;

        CREATE TABLE IF NOT EXISTS `javaee_shop_db`.`products` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `product_name` VARCHAR(45) NULL,
          `category` VARCHAR(45) NULL,
          `description` VARCHAR(45) NULL,
          `price` DOUBLE NULL,
          PRIMARY KEY (`id`));

          CREATE TABLE `javaee_shop_db`.`categories` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `category_name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
          PRIMARY KEY (`id`));

          CREATE TABLE `javaee_shop_db`.`orders` (
            `id` INT NOT NULL,
              `product_name` INT NULL,
              `qty` INT NULL,
              `active` TINYINT NULL);

    */

    private static final Logger logger = LoggerFactory.getLogger(ShopRepository.class);

    @Inject
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
            this.connection = dataSource.getConnection();
            createTableProductsIfNotExists(connection);
            createTableCategoriesIfNotExists(connection);
            createTableOrderIfNotExists(connection);
            if (this.findAllCategories().isEmpty()){
                this.insert(new Category(-1L,"Мужская одежда"));
                this.insert(new Category(-1L,"Аксессуары"));
                this.insert(new Category(-1L,"Обувь"));
            }
            if (this.findAllProducts().isEmpty()){
                this.insert(new Product(-1L,"Пальто",1L,"Замечательно для зимы",7000.65));
                this.insert(new Product(-1L,"Шапка",1L,"Вязаная",700.12));
                this.insert(new Product(-1L,"Сумка",2L,"Кожезаменитель",1500.37));
                this.insert(new Product(-1L,"Ботинки",3L,"На толстой подошве",3000.00));
            }
            if (this.findAllOrders().isEmpty()){
                this.fillOrderTable();
            }
    }

    private void fillOrderTable() throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "INSERT INTO orders(id, product_name, qty, active) VALUES(?,?,?,?);")){
            statement.setLong(1,1L);
            statement.setLong(2,1L);
            statement.setLong(3,1L);
            statement.setBoolean(4,true);

            statement.execute();
            statement.setLong(1,1L);
            statement.setLong(2,2L);
            statement.setLong(3,2L);
            statement.setBoolean(4,true);
            statement.execute();

            statement.setLong(1,2L);
            statement.setLong(2,3L);
            statement.setLong(3,1L);
            statement.setBoolean(4,true);
            statement.execute();

            statement.setLong(1,2L);
            statement.setLong(2,4L);
            statement.setLong(3,1L);
            statement.setBoolean(4,true);
            statement.execute();
        }
    }


    public void insert(Product product) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO products(product_name,category,description,price) values(?,?,?,?)"
        )){
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());

            statement.execute();
        }
    }

    public void insert(Category category) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO categories(category_name) values(?)"
        )){
            statement.setString(1, category.getName());

            statement.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE products set " +
                        "product_name = ?, " +
                        "category = ?, " +
                        "description = ?, " +
                        "price = ? " +
                        "WHERE id = ?; "
        )){
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setLong(5,product.getId());

            statement.execute();
        }
    }

    public void update(Category category) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE categories set " +
                        "category_name = ? " +
                        "WHERE id = ?; "
        )){
            statement.setString(1, category.getName());
            statement.setLong(2,category.getId());

            statement.execute();
        }
    }

    public void delete(Product product) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM products WHERE id = ?;"
        )){
            statement.setLong(1,product.getId());

            statement.execute();
        }
    }

    public void delete(Category category) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM categories WHERE id = ?;"
        )){
            statement.setLong(1,category.getId());

            statement.execute();
        }
    }

    public void delete(Order order) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?;"
        )){
            statement.setLong(1,order.getId());

            statement.execute();
        }
    }

    public void changeStatus(Order order) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE orders set " +
                        "active = ? " +
                        "WHERE id = ?; "
        )){
            statement.setBoolean(1, !order.getActive());
            statement.setLong(2, order.getId());

            statement.execute();
        }
    }

    public Product findProductById(long id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM products WHERE id = ?;"
        )){
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Product(resultSet.getLong(1),
                                    resultSet.getString(2),
                                    resultSet.getLong(3),
                                    resultSet.getString(4),
                                    resultSet.getDouble(5));
            }
        }
        return new Product(-1L,"",-1L,"",0.0);
    }

    public Category findCategoryById(long id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM categories WHERE id = ?;"
        )){
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Category(resultSet.getLong(1),
                        resultSet.getString(2));
            }
        }
        return new Category(-1L,"");
    }

    public List<Product> findAllProducts() throws SQLException {
        List<Product> result = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()){
                result.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)
                ));
            }
        }
        return result;
    }

    public List<Category> findAllCategories() throws SQLException {
        List<Category> result = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()){
                result.add(new Category(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                ));
            }
        }
        return result;
    }

    public List<Order> findAllOrders() throws SQLException {
        List<Order> result = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                boolean active = resultSet.getBoolean(4);
                Order order = null;
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getId() == id){
                        order = result.get(i);
                        break;
                    }
                }
                if (order == null){
                    order = new Order();
                    order.setId(id);
                    order.setActive(active);
                    result.add(order);
                }
                order.getOrder().put(resultSet.getLong(2), resultSet.getLong(3));
            }
        }
        return result;
    }

    public List<Product> findAllProductsByCategory(Category category) throws SQLException {
        List<Product> result = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM products WHERE category = ?;"
        )){
            statement.setLong(1,category.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)
                ));
            }
        }
        return result;
    }

    private void createTableProductsIfNotExists(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS products (\n" +
                            "id INT NOT NULL AUTO_INCREMENT,\n" +
                            "product_name VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,\n" +
                            "category INT NOT NULL ,\n" +
                            "description VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ,\n" +
                            "price DOUBLE NULL,\n" +
                            " PRIMARY KEY (id));"
            );
        }
    }

    private void createTableCategoriesIfNotExists(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS categories (\n" +
                            "id INT NOT NULL AUTO_INCREMENT,\n" +
                            "category_name VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,\n" +
                            "PRIMARY KEY (id));"
            );
        }
    }

    private void createTableOrderIfNotExists(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS orders (\n" +
                            "id INT NOT NULL,\n" +
                            "product_name INT NULL,\n" +
                            "qty INT NULL,\n" +
                            "active TINYINT NULL);"
            );
        }
    }
}
