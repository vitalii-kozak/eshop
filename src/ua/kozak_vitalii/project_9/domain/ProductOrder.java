package ua.kozak_vitalii.project_9.domain;

import com.sun.istack.internal.NotNull;

public class ProductOrder {
    private Product product;
    private int productQuantity;

    public ProductOrder(@NotNull Product product, @NotNull int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOrder that = (ProductOrder) o;

        if (productQuantity != that.productQuantity) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + productQuantity;
        return result;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                ", product=" + product +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
