package HealthcareWorker;

import java.util.ArrayList;
import java.util.Scanner;

import Common.Date;

// import Common.Date; 
public class HealthcareWorkerManager {
    // Properties
    private static HealthcareWorkerManager instance;
    private static ArrayList<HealthcareWorker> list;
    private static int numbersOfList;

    // Constructors
    public HealthcareWorkerManager() {
        list = new ArrayList<>();
        numbersOfList = 0;
    }
    public HealthcareWorkerManager(ArrayList<HealthcareWorker> list, int numbersOfList) {
        HealthcareWorkerManager.list = list;
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public static void setNumbersOfList(int numbersOfList) {
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }
    public static HealthcareWorkerManager getInstance() {
        if(instance == null) {
            instance = new HealthcareWorkerManager();
        }
        return instance;
    }
    public static ArrayList<HealthcareWorker> getList() {
        return list;
    }
    public static int getNumbersOfList() {
        return numbersOfList;
    }

    
    // Methods

    public static void addWorker(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ho va ten: "); String fullname = sc.nextLine();
        System.out.println("Nhap ngay sinh: "); int day = sc.nextInt();
        System.out.println("Nhap thang sinh: "); int month = sc.nextInt();
        System.out.println("Nhap nam sinh: "); int year = sc.nextInt();
        Date birthday = new Date(day, month, year);
        System.out.println("Nhap gioi tinh: "); String gender = sc.nextLine();
        System.out.println("Nhap quoc tich: "); String country = sc.nextLine();
        System.out.println("Nhap so dien thoai: "); String phone = sc.nextLine();
        System.out.println("Nhap bo phan lam viec: "); String department = sc.nextLine();
        System.out.println("Nhap vi tri lam viec: "); String position = sc.nextLine();
        System.out.println("Nhap so nam kinh nghiem: "); int yearsOfExperience = sc.nextInt();
        System.out.println("Nhap luong: "); double salary = sc.nextDouble();
        HealthcareWorker worker = new HealthcareWorker(fullname, birthday, gender, country, phone, "DOC00001",department, position, yearsOfExperience, salary);
        sc.close();
        HealthcareWorkerManager.list.add(worker);
    }

    

    public static void update(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vi tri nhan vien ban muon chinh sua: "); int pos = sc.nextInt();
        System.out.println("Nhap ho va ten: "); String fullname = sc.nextLine();
        System.out.println("Nhap ngay sinh: "); int day = sc.nextInt();
        System.out.println("Nhap thang sinh: "); int month = sc.nextInt();
        System.out.println("Nhap nam sinh: "); int year = sc.nextInt();
        Date birthday = new Date(day, month, year);
        System.out.println("Nhap gioi tinh: "); String gender = sc.nextLine();
        System.out.println("Nhap quoc tich: "); String country = sc.nextLine();
        System.out.println("Nhap so dien thoai: "); String phone = sc.nextLine();
        System.out.println("Nhap ID: "); String ID = sc.nextLine();
        System.out.println("Nhap bo phan lam viec: "); String department = sc.nextLine();
        System.out.println("Nhap vi tri lam viec: "); String position = sc.nextLine();
        System.out.println("Nhap so nam kinh nghiem: "); int yearsOfExperience = sc.nextInt();
        System.out.println("Nhap luong: "); double salary = sc.nextDouble();
        HealthcareWorker worker = new HealthcareWorker(fullname, birthday, gender, country, phone, ID,department, position, yearsOfExperience, salary);
        HealthcareWorkerManager.list.set(pos, worker);
        sc.close();
    }

    public static void remove(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ID: "); String ID = sc.nextLine();
        HealthcareWorker worker = new HealthcareWorker(ID);
        HealthcareWorkerManager.list.remove(worker);
        sc.close();
    }


    public static void removeAll(){
        HealthcareWorkerManager.list.clear();
    }

    public static void find(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vi tri ban muon tim kiem: "); int pos = sc.nextInt();
        HealthcareWorkerManager.list.get(pos - 1);
        sc.close();
    }

    public static void findByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id ban muon tim kiem: "); String id = sc.nextLine();
        for (HealthcareWorker worker : list){
            if(worker.getId().indexOf(id)>=0){
                System.out.println(worker.getId());
            }
        }
        sc.close();
    }

    



}
