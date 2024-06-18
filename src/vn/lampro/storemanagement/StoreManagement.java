package vn.lampro.storemanagement;

import vn.lampro.storemanagement.sale.SaleManagerment;
import vn.lampro.storemanagement.update.UpdateManagement;
import vn.lampro.storemanagement.update.category.CategoryManagement;
import vn.lampro.storemanagement.update.customer.CustomerManagement;
import vn.lampro.storemanagement.update.product.ProductManagement;

import java.util.Scanner;

public class StoreManagement {
    public static void main(String[] args) {


        CategoryManagement.init();
        CustomerManagement.init();
        ProductManagement.init();


        Scanner scr = new Scanner(System.in);
        do {
            System.out.println("\n======================CHUONG TRINH QUAN LY BAN HANG====================");
            System.out.println("Chon chuc nang quan ly");
            System.out.println("\t1. Cap nhat thong tin cua hang");
            System.out.println("\t2. Quan ly ban hang");
            System.out.println("\t3. Thong ke so lieu");
            System.out.println("\t0. Thoat");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin he thong
                    UpdateManagement.execute();
                    break;
                case 2:  //Quan ly ban hang
                    SaleManagerment.execute();
                    break;
                case 3:  //Thong ke
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lua chon cua ban khong hop le");
            }
        } while (true);

    }
}