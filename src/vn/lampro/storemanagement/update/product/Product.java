package vn.lampro.storemanagement.update.product;

import vn.lampro.storemanagement.update.category.Category;
import vn.lampro.storemanagement.update.category.CategoryManagement;

import java.util.Scanner;

public class Product {
    private int id;
    private int categoryId;
    private String name;
    private int quantity;
    private double price;


    public void display() {
        Category category = CategoryManagement.getCategoryById(this.categoryId);
        System.out.printf("%5d %-20s %-30s %8d %,15.2f%n", this.id, category.getName(), this.name, this.quantity, this.price);
    }

    Scanner scr = new Scanner(System.in);

    public void edit() {
        do {
            System.out.println("---------------SUA THONG TIN HANG HOA--------------");
            System.out.println("Chon thong tin can sua");
            System.out.println("\t1. Sua loai hang");
            System.out.println("\t2. Sua ten hang hoa");
            System.out.println("\t3. Sua so luong hang hoa");
            System.out.println("\t4. Sua don gia hang hoa");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin  hang hoa
                    editCategoryId();
                    break;
                case 2:  //Cap nhat thong tin hang hoa
                    editName();
                    break;
                case 3:  //Cap nhat thong tin  hang hoa
                    editQuantity();
                    break;
                case 4:  //Cap nhat thong tin hang hoa
                    editPrice();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon cua ban khong hop le");
            }
        } while (true);

    }

    //Tao phuong thuc sua thogn tin hang hoa
    public void editCategoryId() {
        System.out.print("Nhap ma loai hang: ");
        int id = Integer.parseInt(scr.nextLine());
        //kiem tra tinh hop le
        if (CategoryManagement.findById(id) == -1) {
            System.out.println("\tMa loai hang khong ton tai");
            return;
        }
        this.setCategoryId(id);
        System.out.println("Sua ma loai hang thanh cong!");
    }

    //Tao phuowg thuc sua ten
    public void editName() {
        System.out.printf("\nNhap ten hang hoa moi: ");
        String name = scr.nextLine();
        //kiem tra tinh hop le
        if (name.isEmpty()) {
            System.out.println("\nTen khong duoc de trong");
            return;
        }
        if (ProductManagement.findByName(name) != -1) { // ten da ton tai
            System.out.println("\tTen " + name + " da co trong danh sach");
            return;
        }
        this.setName(name);
        System.out.println("Sua ten hang hoa thanh cong!");
    }

    //Tao phuong thuc sua so luong
    public void editQuantity() {
        System.out.print("\nNhap so luong: ");
        int sl = Integer.parseInt(scr.nextLine());
        if (sl < 0) {
            System.out.println("\tSo luong khong doc la so am");
            return;
        }
        this.setQuantity(sl);
        System.out.println("Sua so luong hang hoa thanh cong!");
    }

    //tao phuong thuc sua gia
    public void editPrice() {
        System.out.print("\nNhap don gia: ");
        double gia = Double.parseDouble(scr.nextLine());
        if (gia < 0) {
            System.out.println("\tDon gia khong duoc la so am");
            return;
        }
        this.setPrice(gia);
        System.out.println("Sua don gia thanh cong!");
    }

    public Product() {

    }

    public Product(int id, int categoryId, String name, int quantity, double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
