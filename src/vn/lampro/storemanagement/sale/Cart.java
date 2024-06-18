package vn.lampro.storemanagement.sale;

import vn.lampro.storemanagement.update.customer.CustomerManagement;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private  int customerId =1;
    private String code;
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Cart(int id, int customerId, String code, List<CartProduct> cartProducts) {
        this.id = id;
        this.customerId = customerId;
        this.code = code;
        this.cartProducts = cartProducts;
    }

    public Cart() {
    }


    public void display() {
        System.out.println("\tMa gio hang: " + this.code);
        System.out.println("\tTen khach hang: "+ CustomerManagement.getCustomerById(customerId).getName());
        System.out.println("\tDanh sach san pham: ");
        System.out.printf("%-35s %8s %15s %15s%n", "Ten san pham", "So luong", "Don gia", "Thanh tien");
        for (CartProduct cartProduct : cartProducts) {
            cartProduct.display();
        }
        System.out.printf("\tCong thanh tien: %,.2f%n", this.totalCartMoney());

    }

    private double totalCartMoney() {
        double total = 0;
        for (CartProduct cartProduct : cartProducts) {
            total += cartProduct.total();
        }
        return total;
    }

    //Tim san pham trong gio hang
    public int findCartProductById(int productId) {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }
}
