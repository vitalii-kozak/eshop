package ua.kozak_vitalii.project_9.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final Logger logger = Logger.getLogger(CategoryDaoImpl.class);
    private DataSource datasource;
    private final String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT * FROM categories";

    public CategoryDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Category category) {
        String query_text = "INSERT INTO categories (name) VALUES (?)";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, category.getName());

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                long id = result.getLong(1);
                category.setId(id);
                return id;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to insert into categories! " + e.getMessage());
            return null;
        }
    }

    @Override
    public Category read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Category> categories = getCategoryFromResultSet(result);
            if (categories.size() > 0) {
                Category category = categories.get(0);
                return category;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from categories! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Category category) {
        String query_text = "UPDATE categories SET name = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated categories! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Category category) {
        String query_text = "DELETE FROM categories WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, category.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from categories! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> findAll() {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT;
        logger.info(query_text);
        List<Category> products = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            products = getCategoryFromResultSet(result);
        } catch (SQLException e) {
            logger.error("Failed to read from categories! " + e.getMessage());
        }
        return products;
    }

    private List<Category> getCategoryFromResultSet(ResultSet result) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (result.next()) {

            long id = result.getLong("id");
            String name = result.getString("name");

            Category category = new Category(name);
            category.setId(id);
            categories.add(category);
        }
        return categories;
    }
}
