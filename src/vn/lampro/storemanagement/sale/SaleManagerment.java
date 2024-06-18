package vn.lampro.storemanagement.sale;

import vn.lampro.storemanagement.Statistic.OrderManagerment;
import vn.lampro.storemanagement.update.customer.Customer;
import vn.lampro.storemanagement.update.customer.CustomerManagement;
import vn.lampro.storemanagement.update.product.Product;
import vn.lampro.storemanagement.update.product.ProductManagement;

import java.util.Scanner;


public class SaleManagerment {
    // Can mot gio hang
    // Cac thao tac cua khach hang gom
    // + them san pham vao gio
    // + xoa mot san pham khoi gio hang
    // + xoa san pham khoi gio hang
    // + sua so luong
    // + xem gio hang xem co gi
    // + thanh toan
    private int id;
    private static Cart cart = new Cart();

    static Scanner scr = new Scanner(System.in);

    public static void execute() {
        do {
            System.out.println("---------------QUAN LY GIO HANG--------------");
            System.out.println("Chon chuc nang quan ly");
            System.out.println("\t1. Hien thi gio hang");
            System.out.println("\t2. Them san pham gio hang");
            System.out.println("\t3. Thay doi so luong san pham trong gio");
            System.out.println("\t4. Xoa san pham trong gio hang");
            System.out.println("\t5. Huy gio hang");
            System.out.println("\t6. Thanh toan gio hang");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin  hang hoa
                    System.out.println("\n\tGIO HANG CUA BAN");
                    if (cart.getCartProducts().size() <= 0) {
                        System.out.println("\nKhong co san pham nao trong gio hang");
                    } else {
                        cart.display();
                    }
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    changeProductQuantity();
                    break;
                case 4:
                    deleteCartProduct();
                    break;
                case 5:
                    cart = new Cart();
                    System.out.println("\tThanh cong!");
                    break;
                case 6:
                    payment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon cua ban khong hop le");
            }
        } while (true);
    }

    private static void deleteCartProduct() {
        System.out.println("\n--------------------XOA SAN PHAM TRONG GIO HANG --------------------");
        System.out.println("\tNhap id sp can xoa:");
        int productId = Integer.parseInt(scr.nextLine());
        int index = cart.findCartProductById(productId);
        if (index == -1) {
            System.out.println("\tSan pham khong co trong gio hang");
            return;
        }
        cart.getCartProducts().remove(index);
        System.out.println("\tXoa san pham thanh cong!");
    }

    private static void addToCart() {
        System.out.println("\n-----------------THEM SAN PHAM VAO GIO HANG---------------------");

        System.out.println("\tNhap vao id san pham can mua: ");
        int productId = Integer.parseInt(scr.nextLine());
        // Kiem tra san pham nay co trong danh sach san pham khong
        int productIndex = ProductManagement.findById(productId);
        if (productIndex == -1) {
            System.out.println("\tSan pham khong co trong sd san pham");
            return;
        }
        // Co thi nhap so luong
        System.out.println("\tNhap so luong can mua");
        int quantity = Integer.parseInt(scr.nextLine());
        if (quantity <= 0) {
            System.out.println("\tSo luong khong hop le");
            return;
        }
        //Cap nhat san pham vao gio hang; Co 2 trong hop
        //+ TH1: San pham chua co trong gio hang => them moi
        //+ TH2: San pham cda co trong cua hang => tang so luong

        //Tim san pham xem co trong gio hang chua?
        int cartProductIndex = cart.findCartProductById(productId);
        //Tinh tong so luong hang du kien mua
        if (cartProductIndex != -1) { // San pham co trong gio
            // Tong cua so luong moi nhap va so luong co trong gio
            quantity += cart.getCartProducts().get(productIndex).getQuantity();
        }
        // Tong so luong mua khong duoc vuot qua so luong co ban trong ds san pham
        Product product = ProductManagement.getProductById(productId);
        if (quantity >= product.getQuantity()) {
            System.out.println("\tXin loi, so luong san pham khong du ban");
            return;
        }

        // Cap nhat gio hang
        if (cartProductIndex == -1) { //TH1
            cart.getCartProducts().add(new CartProduct(productId, quantity));
        } else {//TH2
            cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
        }
        System.out.println("\tThem san pham moi thanh cong!");

    }

    private static void changeProductQuantity() {
        System.out.println("\n------------------SUA SO LUONG SAN PHAM TRONG GIO HANG---------------------");

        System.out.println("\tNhap vao id san pham can sua: ");
        int productId = Integer.parseInt(scr.nextLine());
        // Kiem tra san pham nay co trong danh sach san pham khong
        if (cart.findCartProductById(productId) == -1) {
            System.out.println("\tSan pham khong co trong gio hang");
            return;
        }
        // Co thi nhap so luong
        System.out.println("\tNhap so luong can sua");
        int quantity = Integer.parseInt(scr.nextLine());
        if (quantity <= 0) {
            System.out.println("\tSo luong khong hop le");
            return;
        }
        Product product = ProductManagement.getProductById(productId);

        if (quantity >= product.getQuantity()) {
            System.out.println("\tXin loi, so luong san pham khong du ban");
            return;
        }

        // Cap nhat gio hang
        int cartProductIndex = cart.findCartProductById(productId);
        cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
        System.out.println("\tsua so luong san pham thanh cong!");

    }

    private static void payment() {
        // Cap nhat tong tin khach hang vao hoa don
        // Hien thi hoa don\
        // Luu hoa don vao ds hoa down
        // Xoa gio hang
        System.out.println("\n---------------------THANH TOAN GIO HANG---------------------------");
        // Cap nhat thong tin khach hang
        System.out.print("\t Nhap id khach hang: ");
        int customerId = Integer.parseInt(scr.nextLine());
        int customerIndex = CustomerManagement.findById(customerId);
        String customerName ;

        if (customerIndex == -1) { // khach moi
            do {
                System.out.print("\nNhap ten khach hang: ");
                customerName = scr.nextLine();
            } while (customerName.isEmpty());
            // Them khach hang vao danh sach khach hang
            customerId = CustomerManagement.autoId++;
            Customer customer = new Customer(customerId,customerName);
            CustomerManagement.getCustomers().add(customer);

        }else {
            // khach da co trong danh sach
            customerName = CustomerManagement.getCustomers().get(customerIndex).getName();
        }
        // Cap nhat hoa down
        int autoId = 1;
        cart.setId(autoId++);
        cart.setCustomerId(customerId);
        cart.setCode(cart.getId() + " " + customerId );
        // Luu hoa don vao danh sach hoa don (Order)
        OrderManagerment.getOrders().add(cart);
        //Hien thi hoa don
        System.out.println("\t\tHOA DON BAN HANG");
        cart.display();
        System.out.println("\tCam on ban da ung ho cua hang!");
        // Xoa gio hang
        cart = new Cart();
    }
}
