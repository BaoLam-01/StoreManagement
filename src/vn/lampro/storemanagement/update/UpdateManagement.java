package vn.lampro.storemanagement.update;

import vn.lampro.storemanagement.update.category.CategoryManagement;
import vn.lampro.storemanagement.update.customer.CustomerManagement;
import vn.lampro.storemanagement.update.product.ProductManagement;

import java.util.Scanner;

public class UpdateManagement {
    public static void execute() {
        Scanner scr = new Scanner(System.in);
        do {
            System.out.println("---------------CAP NHAT THONG TIN HE THONG--------------");
            System.out.println("Chon chuc nang cap nhat");
            System.out.println("\t1. Cap nhat thong tin loai hang");
            System.out.println("\t2. Cap nhat thong tin hang hoa");
            System.out.println("\t3. Cap nhat thong tin khach hang");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin loai hang
                    CategoryManagement.execute();
                    break;
                case 2:  //Cap nhat thong tin hang hoa
                    ProductManagement.execute();
                    break;
                case 3:  //Cap nhat thong tin khach hang
                    CustomerManagement.execute();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon cua ban khong hop le");
            }
        } while (true);
    }
}
