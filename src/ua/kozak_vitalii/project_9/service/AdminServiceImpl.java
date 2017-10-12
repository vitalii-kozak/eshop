package ua.kozak_vitalii.project_9.service;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.dao.CategoryDao;
import ua.kozak_vitalii.project_9.dao.ProductDao;
import ua.kozak_vitalii.project_9.dao.UserDao;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Client;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.enums.UserType;
import ua.kozak_vitalii.project_9.exceptions.AuthenticationException;
import ua.kozak_vitalii.project_9.exceptions.RegistrationException;
import ua.kozak_vitalii.project_9.exceptions.WrongProductDataException;
import ua.kozak_vitalii.project_9.exceptions.WrongUserDataException;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminServiceImpl implements AdminService {

    private UserDao userDao;
    private ProductDao productDao;
    private CategoryDao categoryDao;

    public AdminServiceImpl(@NotNull UserDao userDao, @NotNull ProductDao productDao, @NotNull CategoryDao categoryDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Client> getClients() {
        return userDao.getClients();
    }

    @Override
    public User getUser(Long userId) {
        return userDao.read(userId);
    }

    /**
     * @throws AuthenticationException {@inheritDoc}
     */
    @Override
    public User login(String login, String password) throws AuthenticationException {
        if (login == null || login.isEmpty()) {
            throw new AuthenticationException("Login is a required field!");
        }
        if (password == null || password.isEmpty()) {
            throw new AuthenticationException("Password is a required field!");
        }
        return userDao.getUser(login, password);
    }

    /**
     * @throws RegistrationException {@inheritDoc}
     */
    @Override
    public boolean registerUser(String login, String password, String passwordConfirmation, String name, String surname, boolean isBlocked, UserType userType) throws RegistrationException {
        checkRegistrationData(login, name, surname, password, passwordConfirmation);
        User user = User.getUser(login, password, name, surname, isBlocked, userType);
        return userDao.create(user) != null;
    }

    /**
     * @throws WrongUserDataException {@inheritDoc}
     */
    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword, String passwordConfirmation)  throws WrongUserDataException {
        if (!user.getPassword().equals(oldPassword)) {
            throw new WrongUserDataException("Wrong password");
        }
        checkPassword(newPassword, passwordConfirmation);
        user.setPassword(newPassword);
        return userDao.update(user);
    }

    /**
     * @throws WrongUserDataException {@inheritDoc}
     */
    @Override
    public boolean changeUserData(User user, String password, String passwordConfirmation, String name, String surname, boolean isBlocked)  throws WrongUserDataException {

        checkPassword(password, passwordConfirmation);
        checkDataIsNotEmpty(name, "Name");
        checkDataIsNotEmpty(surname, "Surname");
        user.setName(name);
        user.setSurname(surname);
        user.setBlocked(isBlocked);

        return userDao.update(user);
    }

    /**
     *  Checks if user's registration data is OK
     *  @throws WrongUserDataException in case some data is invalid
     *  @param login user login
     *  @param password user password
     *  @param passwordConfirmation password confirmation (must match password)
     *  @param name user name
     *  @param surname user surname
     */
    private void checkRegistrationData(String login, String name, String surname, String password, String passwordConfirmation) throws RegistrationException {
        try {
            checkDataIsNotEmpty(login, "Login");
            if (!login.toLowerCase().matches("^[a-zA-Z0-9]+$")) {
                throw new RegistrationException("Login should be only letters and numbers.");
            }
            checkPassword(password, passwordConfirmation);
            checkDataIsNotEmpty(name, "Name");
            checkDataIsNotEmpty(surname, "Surname");
        } catch (WrongUserDataException e) {
            throw new RegistrationException(e.getMessage());
        }
        if (userDao.hasUser(login)) {
            throw new RegistrationException("User with login '" + login + "' already exists");
        }
    }

    /**
     *  Checks if password is OK and password confirmation matches password
     *  @throws WrongUserDataException
     *  @param password user password
     *  @param passwordConfirmation password confirmation (must match password)
     */
    private void checkPassword(String password, String passwordConfirmation) throws WrongUserDataException {
        if (password.isEmpty()) {
            throw new WrongUserDataException("Password is a required field!");
        }
        if (password.length() < 5) {
            throw new WrongUserDataException("Password must be 5 symbols minimum");
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]|[\\u0400-\\u044F]");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            throw new WrongUserDataException("Password must contain at least one letter");
        }
        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(password);
        if (!matcher.find()) {
            throw new WrongUserDataException("Password must contain at least one digit");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new WrongUserDataException("Password and password confirmation do not match");
        }
    }

    /**
     *  Checks if given String object is not empty
     *  @throws WrongUserDataException
     *  @param data data to check
     *  @param dataName data name representation
     */
    private void checkDataIsNotEmpty(String data, String dataName) throws WrongUserDataException {
        if (data == null || data.isEmpty()) {
            throw new WrongUserDataException(dataName + " can't be empty!");
        }
    }


    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productDao.read(productId);
    }


    @Override
    public boolean updateProduct(Product product) throws WrongProductDataException {
        if (product.getName().isEmpty()) {
            throw new WrongProductDataException("Product name can't be empty");
        }
        if (product.getId() == null) {
            return productDao.create(product) != null;
        } else {
            return productDao.update(product);
        }
    }

    @Override
    public boolean addNewProduct(String productName, Long categoryId, BigDecimal price) throws WrongProductDataException {
        if (productName.isEmpty()) {
            throw new WrongProductDataException("Product name is a required field!");
        }
        if (categoryId == null) {
            throw new WrongProductDataException("Category is a required field!");
        }
        Category category = (Category)categoryDao.read(categoryId);
        if (price == null) {
            throw new WrongProductDataException("Price is a required field!");
        }
        Product product = new Product(productName, category, price);
        return productDao.create(product) != null;
    }
}
