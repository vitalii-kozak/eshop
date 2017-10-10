package ua.kozak_vitalii.project_9.service;

import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.exceptions.WrongOrderDataException;

public interface ClientService {
    /**
     *  Returns a Product object by given id.
     *  If no record with such id found, returns null
     *  @param productId id of the Product to be found
     *  @return found Product object
     */
    Product getProduct(Long productId);

    /**
     *  Creates a new record of Course object with given parameters.
     *  @throws WrongOrderDataException in case some course data is wrong
     *  @param courseName name of the course
     *  @param startDate start date of the course
     *  @param endDate end date of the course
     *  @param professorId ID of Professor object
     *  @param departmentId ID of Department object
     *  @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     */
    boolean addNewOrder(String courseName, String startDate, String endDate, Long professorId, Long departmentId) throws WrongOrderDataException;
}
