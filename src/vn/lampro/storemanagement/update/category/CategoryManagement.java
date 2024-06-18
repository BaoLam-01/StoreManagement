package vn.lampro.storemanagement.update.category;

import java.util.*;

public class CategoryManagement {
    public static int autoId = 1;
    public static ArrayList<Category> categories = new ArrayList<>();

    static Scanner scr = new Scanner(System.in);

    public static void init() {
        categories.add(new Category(autoId++, "Dien tu"));
        categories.add(new Category(autoId++, "Thoi trang"));
        categories.add(new Category(autoId++, "Gia dung"));
    }

    public static void execute() {
        do {
            System.out.println("---------------CAP NHAT THONG TIN LOAI HANG--------------");
            System.out.println("Chon chuc nang cap nhat");
            System.out.println("\t1. Hien thi danh sach loai hang");
            System.out.println("\t2. Them moi loai hang hoa");
            System.out.println("\t3. Sua thong tin loai hang");
            System.out.println("\t4. Xoa thong tin loai hang");
            System.out.println("\t5. Sap xep danh sach");
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
        System.out.println("\n----------------DANH SACH LOAI HANG-----------------");
        System.out.printf("%5s %-30s%n", "Ma LH", "Ten loai hang");
        for (Category category : categories) {
            category.display();
        }
    }

    private static void add() { // them loai hang moi vao danh sach
        System.out.println("\n------------------Them loai hang moi vao danh sach---------------------");
        System.out.printf("Nhap ten loai hang moi: ");
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
        Category category = new Category(autoId++, name);
        //+add doi tuong vao danh sach
        categories.add(category);
        System.out.println("\tThem moi loai hang thanh cong!");
    }

    private static void edit() { // sua thong tin cua loai hang
        System.out.println("\n------------------Sua thong tin loai hang---------------------");
        System.out.println("\tNhap ma LH can sua:");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa loai hang khong co trong danh sach");
            return;
        }
        System.out.printf("Nhap ten loai hang moi: ");
        String name = scr.nextLine();
        //kiem tra tinh hop le
        if (name.isEmpty()) {
            System.out.println("\nTen khong duoc de trong");
            return;
        }

        //kiem tra ten da ton tai trong danh sach chua
        if (findByName(name)!= -1 && findByName(name) != index) { // ten da ton tai
            System.out.println("\tTen " + name + " da co trong danh sach");
            return;
        }
        //sua thong tin
        categories.get(index).setName(name);
        System.out.println("\tSua thong tin loai hang thanh cong!");
    }

    private static void remove() {
        System.out.println("\n------------------Xoa thong tin loai hang---------------------");
        System.out.println("\tNhap ma LH can xoa: ");
        int id = Integer.parseInt(scr.nextLine());
        //tim xem co trong danh sach khong
        int index = findById(id);
        if (index == -1) {
            System.out.println("\tMa loai hang khong co trong danh sach");
            return;
        }
        categories.remove(index);
        System.out.println("\tXoa loai hang thanh cong!");
    }

    private static void sort() {
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    //ham kiem tra id ton tai
    public static int findById(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    // ham tim kiem ten loai hang da ton tai trong danh sach chua
    private static int findByName(String name) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    //ham tim theo id va tra ve doi tuong
    public static Category getCategoryById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return new Category();
    }
}
