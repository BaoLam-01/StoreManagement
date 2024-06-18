package vn.lampro.storemanagement.update.product;


import vn.lampro.storemanagement.update.category.Category;
import vn.lampro.storemanagement.update.category.CategoryManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManagement {
    static Scanner scr = new Scanner(System.in);

    public static int autoId = 1;
    public static ArrayList<Product> products = new ArrayList<>();

    public static void init() {
        products.add(new Product(autoId++, 1, "Tu lanh Samsung", 10, 12000000));
        products.add(new Product(autoId++, 2, "Ao khac nu mong", 100, 120000));
        products.add(new Product(autoId++, 2, "Bo the thao nam", 50, 200000));
        products.add(new Product(autoId++, 1, "Lo vi song LG", 30, 2700000));
        products.add(new Product(autoId++, 3, "Chao chong dinh Einghh", 100, 500000));
    }

    public static void execute() {
        do {
            System.out.println("---------------CAP NHAT THONG TIN HANG HOA--------------");
            System.out.println("Chon chuc nang cap nhat");
            System.out.println("\t1. Hien thi danh sach hang hoa");
            System.out.println("\t2. Them moi hang hoa");
            System.out.println("\t3. Sua thong tin hang hoa");
            System.out.println("\t4. Xoa thong tin hang hoa");
            System.out.println("\t5. Sap xep danh sach");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin  hang hoa
                    display();
                    break;
                case 2:  //Cap nhat thong tin hang hoa
                    add();
                    break;
                case 3:  //Cap nhat thong tin  hang hoa
                    edit();
                    break;
                case 4:  //Cap nhat thong tin hang hoa
                    remove();
                    break;
                case 5:  //Cap nhat thong tin khach hang
                    sort();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon cua ban khong hop le");
            }
        } while (true);
    }

    private static void display() {
        System.out.println("\n----------------DANH SACH HANG HOA-----------------");
        System.out.printf("%5s %-20s %-30s %8s %15s%n", "Ma HH", "Ten loai hang", "Ten hang hoa", "So luong", "Don gia");
        for (Product product : products) {
            product.display();
        }
    }

    private static void add() { // them loai hang moi vao danh sach
        System.out.println("\n------------------Them hang hoa moi vao danh sach---------------------");
        System.out.print("Nhap ma loai hang: ");
        int id = Integer.parseInt(scr.nextLine());
        //kiem tra tinh hop le
        if (CategoryManagement.findById(id) == -1) {
            System.out.println("\tMa loai hang khong ton tai");
            return;
        }
        System.out.printf("\nNhap ten hang hoa moi: ");
        String name = scr.nextLine();
        //kiem tra tinh hop le
        if (name.isEmpty()) {
            System.out.println("\nTen khong duoc de trong");
            return;
        }
        if (findByName(name) != -1) { // ten da ton tai
            System.out.println("\tTen " + name + " da co trong danh sach");
            return;
        }
        System.out.print("\nNhap so luong: ");
        int sl = Integer.parseInt(scr.nextLine());
        if (sl < 0) {
            System.out.println("\tSo luong khong doc la so am");
            return;
        }
        System.out.print("\nNhap don gia: ");
        double gia = Double.parseDouble(scr.nextLine());
        if (gia < 0) {
            System.out.println("\tDon gia khong duoc la so am");
            return;
        }
        Product product = new Product(autoId++, id, name,sl,gia);
        //+add doi tuong vao danh sach
        products.add(product);
        System.out.println("\tThem moi hang hoa thanh cong!");
    }

    private static void edit() { // sua thong tin cua hang hoa
        System.out.println("\n------------------Sua thong tin hang hoa---------------------");
        System.out.println("\tNhap ma HH can sua:");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa hang hoa khong co trong danh sach");
            return;
        }
        products.get(index).edit();
    }

    private static void remove() {
        System.out.println("\n------------------Xoa thong tin hang hoa---------------------");
        System.out.println("\tNhap ma HH can xoa: ");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa hang hoa khong co trong danh sach");
            return;
        }
        products.remove(index);
        System.out.println("\tXoa hang hoa thanh cong!");
    }


    private static void sort() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    //ham kiem tra id ton tai
    public static int findById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public static int findByName(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    //ham tim theo id va tra ve doi tuong
    public static Product getProductById(int id) {

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return new Product();
    }

}
