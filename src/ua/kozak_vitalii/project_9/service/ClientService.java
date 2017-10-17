package ua.kozak_vitalii.project_9.service;

import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.exceptions.WrongOrderDataException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    /**
     *  Returns a Product object by given id.
     *  If no record with such id found, returns null
     *  @param productId id of the Product to be found
     *  @return found Product object
     */
    Product getProduct(Long productId);

    /**
     * Returns list of all products
     *
     * @return list of Product objects
     */
    List<Product> getProducts();

    /**
     *  Creates a new record of Order object with given parameters.
     *  @throws WrongOrderDataException in case some order data is wrong
     *  @param productOrder list of producs
     *  @param user user
     *  @param totalPrice total price of order
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    public boolean addNewOrder(List productOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException;
}
