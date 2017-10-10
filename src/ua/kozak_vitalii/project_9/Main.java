package ua.kozak_vitalii.project_9;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import ua.kozak_vitalii.project_9.dao.*;
import ua.kozak_vitalii.project_9.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataSource datasource;

        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/eshop1?useUnicode=yes&characterEncoding=utf8&useSSL=false");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("7878");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        datasource = new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setPoolProperties(p);

        Category category = new Category("HDD");
        CategoryDao categoryDao = new CategoryDaoImpl(datasource);
        System.out.println(category);
        System.out.println(categoryDao.create(category));
        System.out.println(categoryDao.create(category));
        System.out.println(categoryDao.delete(category));
        System.out.println(categoryDao.read(1L));
        category = categoryDao.read(1L);
        System.out.println(categoryDao.findAll());
        category.setName("Hard Disk Drives");
        System.out.println(categoryDao.update(category));
        System.out.println(categoryDao.findAll());


        UserDao userDao = new UserDaoImpl(datasource);
        System.out.println(userDao.findAll());
        User guest = new Client("guest", "1111", "Guest", "Guest",false);
        User guest2 = new Client("guest2", "2222", "Guest2", "Guest2",false);
        System.out.println(userDao.create(guest));
        System.out.println(userDao.create(guest2));
        System.out.println(userDao.hasUser("guest2"));
        guest2.setIsBlocked(true);
        System.out.println(userDao.update(guest2));
        System.out.println(userDao.findAll());
        System.out.println(userDao.delete(guest2));
        System.out.println(userDao.findAll());

        Product product = new Product("Western digital", category, new BigDecimal(99.98));
        Product product2 = new Product("Toshiba", category, new BigDecimal(88.98));
        ProductDao productDao = new ProductDaoImpl(datasource);
        System.out.println(productDao.create(product));
        System.out.println(productDao.create(product2));

        System.out.println(productDao.read(1L));
        product = productDao.read(1L);
        product.setName("Seagate");

        System.out.println(productDao.update(product));
        System.out.println(productDao.findAll());
//        System.out.println(productDao.delete(product));
        System.out.println(productDao.findAll());

        Order order = new Order();
        ProductOrder productOrder = new ProductOrder(product, 1);
        ProductOrder productOrder2 = new ProductOrder(product2, 2);
        List<ProductOrder> productOrders = new ArrayList<>();
        productOrders.add(productOrder);
        productOrders.add(productOrder2);
        order.setProductOrders(productOrders);
        order.setUser(guest);
        order.setPaid(false);
        BigDecimal sum = new BigDecimal(300.95);

        order.setTotal_price(sum);

        OrderDao orderDao = new OrderDaoImpl(datasource);
        orderDao.create(order);
        System.out.println("*****************");
        //System.out.println(orderDao.delete(order));
        Order order2 = orderDao.read(1L);
        System.out.println(order2);


        datasource.close();
    }
}
