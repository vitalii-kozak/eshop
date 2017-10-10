package ua.kozak_vitalii.project_9.service;

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

public interface AdminService {
    /**
     *  Looks for a record by given parameters; if founds, returns appropriate User object, otherwise - returns null.
     *  @throws AuthenticationException in case login and/or password are empty
     *  @param login user login
     *  @param password user passwords
     *  @return found User object
     */
    User login(String login, String password) throws AuthenticationException;

    /**
     *  Create a record of a new user with given parameters;
     *  @throws RegistrationException in case some parameters are invalid
     *  @param login user login
     *  @param password user password
     *  @param passwordConfirmation password confirmation (must match password)
     *  @param name user name
     *  @param surname user family name
     *  @param isBlocked is user in black list
     *  @param userType user type; available values: UserType.ADMIN, UserType.PROFESSOR or UserType.STUDENT
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean registerUser(String login, String password, String passwordConfirmation, String name, String surname, boolean isBlocked, UserType userType) throws RegistrationException;

    /**
     *  Changes user's password to a new one
     *  @throws WrongUserDataException in case some parameters are invalid
     *  @param user current user
     *  @param oldPassword current user's password
     *  @param newPassword new user's password
     *  @param passwordConfirmation new password confirmation (must match new password)
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean changePassword(User user, String oldPassword, String newPassword, String passwordConfirmation) throws WrongUserDataException;

    /**
     *  Changes user's personal data e.g. name, family name and patronymic
     *  @throws WrongUserDataException in case some parameters are invalid
     *  @param user current user
     *  @param password user's current password
     *  @param name user's new name
     *  @param surname user's new surname
     *  @param isBlocked is user in black list
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean changeUserData(User user, String password, String name, String surname, boolean isBlocked)  throws WrongUserDataException;

    /**
     *  Returns list of all users who have user type == Client
     *  @return list of Client objects
     */
    List<Client> getClients();

    /**
     *  Returns list of all users
     *  @return list of User objects
     */
    List<User> getUsers();

    /**
     *  Returns list of all products
     *  @return list of Product objects
     */
    List<Product> getProducts();

    /**
     *  Returns a Product object by given id.
     *  If no record with such id found, returns null
     *  @param productId id of the Product to be found
     *  @return found Product object
     */
    Product getProduct(Long productId);

    /**
     *  Updates given product or creates a new one in case product's id is null
     *  @throws WrongProductDataException in case department name is empty
     *  @param product product to be updated/created
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean updateProduct(Product product) throws WrongProductDataException;

    /**
     *  Creates a new record of Product object with given parameters.
     *  @throws WrongProductDataException in case some product data is wrong
     *  @param productName name of the product
     *  @param categoryId category id of the product
     *  @param price price of the product
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean addNewProduct(String productName, Long categoryId, BigDecimal price) throws WrongProductDataException;
}
