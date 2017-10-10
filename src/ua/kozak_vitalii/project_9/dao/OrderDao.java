package ua.kozak_vitalii.project_9.dao;

import ua.kozak_vitalii.project_9.domain.Order;
import java.util.List;

public interface OrderDao {
    Long create(Order order);
    Order read(Long id);
    boolean update(Order order);
    boolean delete(Order order);
    List<Order> findAll();
}
