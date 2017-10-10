package ua.kozak_vitalii.project_9.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.dao.CategoryDaoImpl.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private DataSource datasource;
    private final String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT products.*, categories.* FROM Products as products LEFT JOIN Categories as categories ON products.category_id = categories.id";

    public ProductDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Product product) {
        String query_text = "INSERT INTO products (name, category_id, price) VALUES (?, ?, ?)";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory().getId());
            statement.setBigDecimal(3, product.getPrice());

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                long id = result.getLong(1);
                product.setId(id);
                return id;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to insert into Products! " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE products.id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Product> products = getProductFromResultSet(result);
            if (products.size() > 0) {
                Product product = products.get(0);
                return product;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from Product! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Product product) {
        String query_text = "UPDATE products SET name = ?, category_id = ?, price = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setString(1, product.getName());
            statement.setLong(2, product.getCategory().getId());
            statement.setBigDecimal(3, product.getPrice());
            statement.setLong(4, product.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated Products! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        String query_text = "DELETE FROM products WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from Products! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT;
        logger.info(query_text);
        List<Product> products = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            products = getProductFromResultSet(result);
        } catch (SQLException e) {
            logger.error("Failed to read from Products! " + e.getMessage());
        }
        return products;
    }

    private List<Product> getProductFromResultSet(ResultSet result) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (result.next()) {

            long id = result.getLong("products.id");
            String name = result.getString("products.name");
            Long category_id = result.getLong("products.category_id");
            String categoryName = result.getString("categories.name");

            Category category = new Category(categoryName);
            category.setId(category_id);
            BigDecimal price = result.getBigDecimal("products.price");

            Product product = new Product(name, category, price);
            product.setId(id);
            products.add(product);
        }
        return products;
    }
}
