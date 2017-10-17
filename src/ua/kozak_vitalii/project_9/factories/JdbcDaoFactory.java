package ua.kozak_vitalii.project_9.factories;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.dao.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger logger = Logger.getLogger(JdbcDaoFactory.class);
    private static JdbcDaoFactory instance;
    private UserDao userDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    private CategoryDao categoryDao;

    private JdbcDaoFactory() {
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            DataSource datasource = (DataSource) context.lookup("jdbc/MySQLDataSource");
            userDao = new UserDaoImpl(datasource);
            productDao = new ProductDaoImpl(datasource);
            categoryDao = new CategoryDaoImpl(datasource);
            orderDao = new OrderDaoImpl(datasource);

        } catch (NamingException e) {
            logger.error("Failed to initialize context: " + e.getMessage());
        }
    }

    public static JdbcDaoFactory getInstance() {
        synchronized (JdbcDaoFactory.class) {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }
        }
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public ProductDao getProductDao() {
        return productDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
