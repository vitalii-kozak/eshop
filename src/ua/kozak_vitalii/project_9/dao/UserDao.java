package ua.kozak_vitalii.project_9.dao;

import ua.kozak_vitalii.project_9.domain.Client;
import ua.kozak_vitalii.project_9.domain.User;

import java.util.List;

public interface UserDao {
    Long create(User user);
    User read(Long id);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAll();
    User getUser(String login, String password);
    boolean hasUser(String login);
    List<Client> getClients();
}