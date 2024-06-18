package vn.lampro.storemanagement.sale;

import vn.lampro.storemanagement.update.product.Product;
import vn.lampro.storemanagement.update.product.ProductManagement;

public class CartProduct {
    private int productId;
    private int quantity;

    public void display() {
        Product product = new ProductManagement().getProductById(productId);
        System.out.printf("%-35s %,8d %,15.2f %,15.2f%n", product.getName(),
                this.quantity, product.getPrice(),this.total());
    }

    public double total() {
        Product product = ProductManagement.getProductById(this.productId);
        return this.quantity * product.getPrice();
    }

    public CartProduct(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void CartProduct() {

    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
