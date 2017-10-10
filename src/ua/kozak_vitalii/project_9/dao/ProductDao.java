package ua.kozak_vitalii.project_9.dao;

import ua.kozak_vitalii.project_9.domain.Product;

import java.util.List;

public interface ProductDao {
    Long create(Product product);
    Product read(Long id);
    boolean update(Product product);
    boolean delete(Product product);
    List<Product> findAll();
}
