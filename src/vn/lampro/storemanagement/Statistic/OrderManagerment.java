package vn.lampro.storemanagement.Statistic;

import vn.lampro.storemanagement.sale.Cart;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerment {
    private static List<Cart> orders = new ArrayList<>();
    public static List<Cart> getOrders() {
        return orders;
    }

    public static void setOrders(List<Cart> orders) {
        OrderManagerment.orders = orders;
    }

    public void display() {
        System.out.println("---------------------------THONG KE DANH SACH GIO HANG----------------");
        for (Cart cart : orders) {
            cart.display();
            System.out.println("---------------------------------------------------------------");
        }
    }
}

