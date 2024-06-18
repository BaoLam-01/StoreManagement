package vn.lampro.storemanagement.update.customer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerManagement {
    public static int autoId = 1;
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    static Scanner scr = new Scanner(System.in);

    public static void init() {
        customers.add(new Customer(autoId++, "Anh Cong"));
        customers.add(new Customer(autoId++, "Chi Minh"));
        customers.add(new Customer(autoId++, "Chu Tu"));
    }

    public static void execute() {
        do {
            System.out.println("---------------CAP NHAT THONG TIN KHACH HANG--------------");
            System.out.println("Chon chuc nang cap nhat");
            System.out.println("\t1. Hien thi danh sach khach hang");
            System.out.println("\t2. Them moi khach hang");
            System.out.println("\t3. Sua thong tin khach hang");
            System.out.println("\t4. Xoa thong tin khach hang");
            System.out.println("\t5. Sap xep danh sach khach hang");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int chose = Integer.parseInt(scr.nextLine());
            switch (chose) {
                case 1:  //Cap nhat thong tin loai hang
                    display();
                    break;
                case 2:  //Cap nhat thong tin hang hoa
                    add();
                    break;
                case 3:  //Cap nhat thong tin khach hang
                    edit();
                    break;
                case 4:  //Cap nhat thong tin khach hang
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
        System.out.println("\n----------------DANH SACH KHACH HANG-----------------");
        System.out.printf("%5s %-30s%n", "Ma KH", "Ten khach hang");
        for (Customer Customer : customers) {
            Customer.display();
        }
    }

    private static void add() { // them loai hang moi vao danh sach
        System.out.println("\n------------------Them khach hang moi vao danh sach---------------------");
        System.out.printf("Nhap ten khach hang moi: ");
        String name = scr.nextLine();
        //kiem tra tinh hop le
        if (name.isEmpty()) {
            System.out.println("\nTen khong duoc de trong");
            return;
        }
        //kiem tra ten da ton tai trong danh sach chua
        if (findByName(name)!= -1) { // ten da ton tai
            System.out.println("\tTen " + name + " da co trong danh sach");
            return;
        }
        //them loai hang vao danh sach
        //+tao doi tuong loai hang
        Customer Customer = new Customer(autoId++, name);
        //+add doi tuong vao danh sach
        customers.add(Customer);
        System.out.println("\tThem moi khach hang thanh cong!");
    }

    private static void edit() { // sua thong tin cua loai hang
        System.out.println("\n------------------Sua thong tin khach hang---------------------");
        System.out.println("\tNhap ma KH can sua:");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa khach hang khong co trong danh sach");
            return;
        }
        System.out.printf("Nhap ten khach hang moi: ");
        String name = scr.nextLine();
        //kiem tra tinh hop le
        if (name.isEmpty()) {
            System.out.println("\nTen kh khong duoc de trong");
            return;
        }

        //kiem tra ten da ton tai trong danh sach chua
        if (findByName(name)!= -1 && findByName(name) != index) { // ten da ton tai
            System.out.println("\tTen " + name + " da co trong danh sach");
            return;
        }
        //sua thong tin
        customers.get(index).setName(name);
        System.out.println("\tSua thong tin khach hang thanh cong!");
    }

    private static void remove() {
        System.out.println("\n------------------Xoa thong tin khach hang---------------------");
        System.out.println("\tNhap ma KH can xoa: ");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa khach hang khong co trong danh sach");
            return;
        }
        customers.remove(index);
        System.out.println("\tXoa khach hang thanh cong!");
    }

    private static void sort() {
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    //ham kiem tra id ton tai
    public static int findById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    // ham tim kiem ten khach hang da ton tai trong danh sach chua
    private static int findByName(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // lay 1 khach hang theo id
    public static Customer getCustomerById(int id) {
        for (Customer i : customers) {
            if (i.getId() == id) {
                return i;
            }
        }
        return new Customer();
    }
}
