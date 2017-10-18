package ua.kozak_vitalii.project_9.dao;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.*;
import ua.kozak_vitalii.project_9.enums.UserType;

import javax.sql.DataSource;;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.*;


import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private DataSource datasource;
    private final String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT orders.*, users.*, products.*, product_orders.*, categories.* " +
            "FROM orders " +
            "LEFT JOIN users AS users ON users.id = orders.user_id " +
            "LEFT JOIN product_orders AS product_orders ON product_orders.orders_id = orders.id " +
            "LEFT JOIN products AS products ON products.id = product_orders.products_id " +
            "LEFT JOIN categories as categories ON products.category_id = categories.id";


    public OrderDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Order order) {
        String query_text = "INSERT INTO orders (total_price, purchase_date, user_id, paid) VALUES (?, ?, ?, ?)";
        logger.info(query_text);
        Long id = null;
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            connection.setAutoCommit(false);
            statement.setBigDecimal(1, order.getTotal_price());
            statement.setTimestamp(2, new Timestamp(order.getPurchaseDate().getTime()));
            statement.setLong(3, order.getUser().getId());
            statement.setBoolean(4, order.isPaid());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                id = result.getLong(1);
                order.setId(id);

                boolean resultCreateProductOrderList = createProductOrderList(connection, order, order.getProductOrders());
                if (!resultCreateProductOrderList) {
                    connection.rollback();
                    logger.error("Error occurred while creating productOrders list");
                }
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            logger.error("Failed to insert into Orders! " + e.getMessage());
            return null;
        }
    }

    private boolean createProductOrderList(Connection connection, Order order, List<ProductOrder> productOrders){
        int result = 0;
        for (ProductOrder productOrder : productOrders) {
            result += createProductOrder(connection, order, productOrder.getProduct(), productOrder.getProductQuantity());
        }
        return result == productOrders.size();
    }

    private int createProductOrder(Connection connection, Order order, Product product, int productQuantity){
        String query_text = "INSERT INTO Product_orders (products_id, orders_id, product_quantity) VALUES (?, ?, ?)";
        logger.info(query_text);
        try (PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            statement.setLong(1, product.getId());
            statement.setLong(2, order.getId());
            statement.setInt(3, productQuantity);

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to insert into Product_orders! " + e.getMessage());
            return -1;
        }
    }

    @Override
    public Order read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE orders.id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Order> orders = getOrderFromResultSet(result);
            if (orders.size() > 0) {
                Order order = orders.get(0);
                order.setProductOrders(getProductOrdersFromResultSet(result));
                return order;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from Orders! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Order order) {
        String query_text = "UPDATE orders SET total_price = ?, purchase_date = ?, user_id = ?, paid = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {

            statement.setBigDecimal(1, order.getTotal_price());
            statement.setTimestamp(2, new Timestamp(order.getPurchaseDate().getTime()));
            statement.setLong(3, order.getUser().getId());
            statement.setBoolean(4, order.isPaid());
            System.out.println("****************************************");
            System.out.println(order.isPaid());
            statement.setLong(5, order.getId());

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated Orders! " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean delete(Order order) {
        String query_text = "DELETE orders, product_orders FROM orders LEFT JOIN product_orders as product_orders ON orders_id = id WHERE orders.id= ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setLong(1, order.getId());
            int resultDeleteOrders = statement.executeUpdate();

            if (!(resultDeleteOrders > 0)) {
                connection.rollback();
                logger.error("Error occurred while deleting Orders, productOrders lists");
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from Orders! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> findAll() {

        String query_text = "SELECT orders.*, users.* FROM orders LEFT JOIN users AS users ON users.id = orders.user_id";
        logger.info(query_text);
        List<Order> orders = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            orders = getOrderFromResultSetSimple(result);
        } catch (SQLException e) {
            logger.error("Failed to read from Orders! " + e.getMessage());
        }
        return orders;
    }

    private List<Order> getOrderFromResultSetSimple(ResultSet result) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (result.next()) {

            long orderId = result.getLong("orders.id");
            Timestamp purchaseDate = result.getTimestamp("orders.purchase_date");
            boolean paid = result.getBoolean("orders.paid");
            BigDecimal totalPrice = result.getBigDecimal("orders.total_price");
            long userId = result.getLong("users.id");

            String login = result.getString("users.login");
            String password = result.getString("users.password");
            String name = result.getString("users.name");
            String surname = result.getString("users.surname");
            boolean isBlocked = result.getBoolean("users.isBlocked");
            UserType userType = UserType.valueOf(result.getString("users.user_type").toUpperCase());


            User user = User.getUser(login, password, name, surname, isBlocked, userType);
            user.setId(userId);

            //Order order = new Order(getProductOrdersFromResultSet(result), user, purchaseDate, paid, totalPrice);
            Order order = new Order(null, user, purchaseDate, paid, totalPrice);
            order.setId(orderId);
            orders.add(order);
        }
        return orders;
    }

    private List<Order> getOrderFromResultSet(ResultSet result) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (result.next()) {

            long orderId = result.getLong("orders.id");
            Timestamp purchaseDate = result.getTimestamp("orders.purchase_date");
            boolean paid = result.getBoolean("orders.paid");
            BigDecimal totalPrice = result.getBigDecimal("orders.total_price");

            String login = result.getString("users.login");
            String password = result.getString("users.password");
            String name = result.getString("users.name");
            String surname = result.getString("users.surname");
            boolean isBlocked = result.getBoolean("users.isBlocked");
            UserType userType = UserType.valueOf(result.getString("users.user_type").toUpperCase());
            long userId = result.getLong("users.id");

            User user = User.getUser(login, password, name, surname, isBlocked, userType);
            user.setId(userId);

            Order order = new Order(getProductOrdersFromResultSet(result), user, purchaseDate, paid, totalPrice);
            order.setId(orderId);
            orders.add(order);
        }
        return orders;
    }

    private List<ProductOrder> getProductOrdersFromResultSet(ResultSet result) throws SQLException {
        List<ProductOrder> productOrders = new ArrayList<>();
        while (result.next()) {

            Long categoryId = result.getLong("products.category_id");
            String categoryName = result.getString("categories.name");
            Category category = new Category(categoryName);

            category.setId(categoryId);

            int productQuantity = result.getInt("product_orders.product_quantity");
            long productId = result.getLong("products.id");
            String name = result.getString("products.name");
            BigDecimal price = result.getBigDecimal("products.price");

            Product product = new Product(name, category, price);
            product.setId(productId);

            ProductOrder productOrder = new ProductOrder(product, productQuantity);
            productOrders.add(productOrder);
        }
        return productOrders;
    }

}
