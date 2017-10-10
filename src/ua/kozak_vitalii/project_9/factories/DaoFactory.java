package ua.kozak_vitalii.project_9.factories;


import ua.kozak_vitalii.project_9.dao.*;
import ua.kozak_vitalii.project_9.enums.DaoType;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();
    public abstract ProductDao getProductDao();
    public abstract OrderDao getOrderDao();
    public abstract CategoryDao getCategoryDao();


    public static DaoFactory getFactory(DaoType type) {
        if (type == DaoType.JDBC) {
            return JdbcDaoFactory.getInstance();
        } else {
            return null;
        }
    }
}
