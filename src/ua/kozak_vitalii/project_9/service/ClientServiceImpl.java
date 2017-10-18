package ua.kozak_vitalii.project_9.service;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.dao.CategoryDao;
import ua.kozak_vitalii.project_9.dao.OrderDao;
import ua.kozak_vitalii.project_9.dao.ProductDao;
import ua.kozak_vitalii.project_9.dao.UserDao;
import ua.kozak_vitalii.project_9.domain.Order;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.exceptions.WrongOrderDataException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService{

    private UserDao userDao;
    private ProductDao productDao;
    private CategoryDao categoryDao;
    private OrderDao orderDao;

    public ClientServiceImpl(@NotNull UserDao userDao, @NotNull ProductDao productDao, @NotNull CategoryDao categoryDao, @NotNull OrderDao orderDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.orderDao = orderDao;
    }

    @Override
    public Product getProduct(Long productId) {
       return productDao.read(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    @Override
    public boolean addNewOrder(List productOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException {
        if (productOrder.isEmpty()) {
            throw new WrongOrderDataException("List is empty!");
        }
        Order order = new Order(productOrder, user, new Timestamp(new Date().getTime()), false , totalPrice);

        return orderDao.create(order) != null;

    }
}
