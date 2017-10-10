package ua.kozak_vitalii.project_9.dao;

import ua.kozak_vitalii.project_9.domain.Category;

import java.util.List;

public interface CategoryDao {
    Long create(Category category);
    Category read(Long id);
    boolean update(Category category);
    boolean delete(Category category);
    List<Category> findAll();
}
