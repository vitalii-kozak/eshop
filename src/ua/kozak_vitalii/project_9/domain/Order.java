package ua.kozak_vitalii.project_9.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private List<ProductOrder> productOrders;
    private User user;
    private Timestamp purchaseDate;
    private boolean paid;
    private BigDecimal total_price = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_CEILING);


    public Order() {
        purchaseDate = new Timestamp(new Date().getTime());
    }

    public Order(List<ProductOrder> productOrders, User user, Timestamp purchaseDate, boolean paid, BigDecimal total_price) {
        this();
        this.productOrders = productOrders;
        this.user = user;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
        this.total_price = total_price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productOrders=" + productOrders +
                ", user=" + user +
                ", purchaseDate=" + purchaseDate +
                ", paid=" + paid +
                ", total_price=" + total_price +
                '}';
    }

    public String getPresentation() {

        return getId() + " | " + getPurchaseDate() + " | " + getUser().getId() + " | " + getUser().getFullName() + " | " + (isPaid() ? " PAID ":" NOT PAID ") + " | " + getTotal_price();
    }
}
