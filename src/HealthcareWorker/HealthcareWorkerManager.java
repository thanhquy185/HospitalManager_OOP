package HealthcareWorker;

import java.util.ArrayList;
import java.util.Scanner;

import Common.Date;
import HealthcareWorker.HealthcareWorker;
import HealthcareWorker.HealthcareWorkerManager;
import Common.CRUD;

// import Common.Date; 
public class HealthcareWorkerManager implements CRUD<HealthcareWorker> {
    // Properties
    private static HealthcareWorkerManager instance;
    private static ArrayList<HealthcareWorker> list;
    private static int numbers;

    // Constructors
    public HealthcareWorkerManager() {
        HealthcareWorkerManager.list = new ArrayList<>();
        HealthcareWorkerManager.numbers = 0;
    }
    public HealthcareWorkerManager(ArrayList<HealthcareWorker> list, int numbers) {
        HealthcareWorkerManager.list = list;
        HealthcareWorkerManager.numbers = numbers;
    }

    // Setter - Getter
    public static void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public static void setNumbers(int numbers) {
        HealthcareWorkerManager.numbers = numbers;
    }
    public static HealthcareWorkerManager getInstance() {
        if(HealthcareWorkerManager.instance == null) {
            HealthcareWorkerManager.instance = new HealthcareWorkerManager();
        }
        return HealthcareWorkerManager.instance;
    }
    public static ArrayList<HealthcareWorker> getList() {
        return HealthcareWorkerManager.list;
    }
    public static int getNumbers() {
        return HealthcareWorkerManager.numbers;
    }

    
    // Methods
    // public static void add(){
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Nhap ho va ten: "); String fullname = sc.nextLine();
    //     System.out.println("Nhap ngay sinh: "); int day = sc.nextInt();
    //     System.out.println("Nhap thang sinh: "); int month = sc.nextInt();
    //     System.out.println("Nhap nam sinh: "); int year = sc.nextInt();
    //     Date birthday = new Date(day, month, year);
    //     System.out.println("Nhap gioi tinh: "); String gender = sc.nextLine();
    //     System.out.println("Nhap quoc tich: "); String country = sc.nextLine();
    //     System.out.println("Nhap so dien thoai: "); String phone = sc.nextLine();
    //     System.out.println("Nhap bo phan lam viec: "); String department = sc.nextLine();
    //     System.out.println("Nhap vi tri lam viec: "); String position = sc.nextLine();
    //     System.out.println("Nhap so nam kinh nghiem: "); int yearsOfExperience = sc.nextInt();
    //     System.out.println("Nhap luong: "); double salary = sc.nextDouble();
    //     HealthcareWorker worker = new HealthcareWorker(fullname, birthday, gender, country, phone, "DOC00001",department, position, yearsOfExperience, salary);
    //     sc.close();
    //     HealthcareWorkerManager.list.add(worker);
    // }

    // public static void update(){
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Nhap vi tri nhan vien ban muon chinh sua: "); int pos = sc.nextInt();
    //     System.out.println("Nhap ho va ten: "); String fullname = sc.nextLine();
    //     System.out.println("Nhap ngay sinh: "); int day = sc.nextInt();
    //     System.out.println("Nhap thang sinh: "); int month = sc.nextInt();
    //     System.out.println("Nhap nam sinh: "); int year = sc.nextInt();
    //     Date birthday = new Date(day, month, year);
    //     System.out.println("Nhap gioi tinh: "); String gender = sc.nextLine();
    //     System.out.println("Nhap quoc tich: "); String country = sc.nextLine();
    //     System.out.println("Nhap so dien thoai: "); String phone = sc.nextLine();
    //     System.out.println("Nhap ID: "); String ID = sc.nextLine();
    //     System.out.println("Nhap bo phan lam viec: "); String department = sc.nextLine();
    //     System.out.println("Nhap vi tri lam viec: "); String position = sc.nextLine();
    //     System.out.println("Nhap so nam kinh nghiem: "); int yearsOfExperience = sc.nextInt();
    //     System.out.println("Nhap luong: "); double salary = sc.nextDouble();
    //     HealthcareWorker worker = new HealthcareWorker(fullname, birthday, gender, country, phone, ID,department, position, yearsOfExperience, salary);
    //     HealthcareWorkerManager.list.set(pos, worker);
    //     sc.close();
    // }

    // public static void remove(){
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Nhap ID: "); String ID = sc.nextLine();
    //     HealthcareWorker worker = new HealthcareWorker(ID);
    //     HealthcareWorkerManager.list.remove(worker);
    //     sc.close();
    // }

    // public static void removeAll(){
    //     HealthcareWorkerManager.list.clear();
    // }

    // public static void find(){
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Nhap vi tri ban muon tim kiem: "); int pos = sc.nextInt();
    //     HealthcareWorkerManager.list.get(pos - 1);
    //     sc.close();
    // }

    // public static void findByID(){
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Nhap id ban muon tim kiem: "); String id = sc.nextLine();
    //     for (HealthcareWorker worker : list){
    //         if(worker.getId().indexOf(id)>=0){
    //             System.out.println(worker.getId());
    //         }
    //     }
    //     sc.close();
    // }

    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public void add(HealthcareWorker healthcareWorker){
        HealthcareWorkerManager.list.add(healthcareWorker);
        HealthcareWorkerManager.numbers++;
    }
    // -- Cập nhật thông tin của đối tượng thông qua id của đối tượng đó
    @Override
    public void update(HealthcareWorker healthcareWorker){
        HealthcareWorkerManager.list.set(findIndexById(healthcareWorker.getId()), healthcareWorker);
    }
    @Override
    public void removeOne(String id){
        HealthcareWorkerManager.list.remove(findIndexById(id));
        HealthcareWorkerManager.numbers--;
    }
    @Override
    public void removeAll(){
        HealthcareWorkerManager.list.clear();
        HealthcareWorkerManager.numbers = 0;
    }
    @Override
    // -- Tìm vị trí của đối tượng trong lớp quản lý
    public int findIndexById(String idSearch){
        for(int i = 0; i < HealthcareWorkerManager.numbers; i++){
            if(HealthcareWorkerManager.list.get(i).getId().equals(idSearch)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public HealthcareWorker find(String id){
        return HealthcareWorkerManager.list.get(findIndexById(id));
    }
    @Override
    public HealthcareWorker findOneByCondition(String condition){
        return new HealthcareWorker();
    }
    @Override
    public ArrayList<HealthcareWorker> findAllByCondition(String condition){
        return new ArrayList<HealthcareWorker>();
    }
    @Override
    public void sort(){
        HealthcareWorkerManager.list.sort(null);
    }
}
