package HealthcareWorker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Account.AccountManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.*;
import Department.*;
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
    public void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public void setNumbers(int numbers) {
        HealthcareWorkerManager.numbers = numbers;
    }
    public static HealthcareWorkerManager getInstance() {
        if(HealthcareWorkerManager.instance == null) {
            HealthcareWorkerManager.instance = new HealthcareWorkerManager();
        }
        return HealthcareWorkerManager.instance;
    }
    public ArrayList<HealthcareWorker> getList() {
        return HealthcareWorkerManager.list;
    }
    public int getNumbers() {
        return HealthcareWorkerManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public HealthcareWorker findObjectByIndex(int index){
        if(index < 0 || index > HealthcareWorkerManager.numbers - 1) return null;
        return HealthcareWorkerManager.list.get(index);
    }
    @Override
    public HealthcareWorker findObjectById(String id){
        if(HealthcareWorkerManager.numbers == 0) return null;
        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list) {
            if(healthcareWorker.getId().equals(id)) return healthcareWorker;
        }
        return null;
    }
    @Override
    public String getInfoByIndex(int index) {
        if(index < 0 || index > HealthcareWorkerManager.numbers - 1) return null;
        return HealthcareWorkerManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        HealthcareWorker healthcareWorker = findObjectById(id);
        if(healthcareWorker == null) return null;
        return healthcareWorker.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*");
	    System.out.println("|     HỌ TÊN NHÂN VIÊN     |  NGÀY SINH | PHÁI | ĐIỆN THOẠI |    QUỐC TỊCH    | MÃ NHÂN VIÊN | LOẠI NHÂN VIÊN | KN | TIỀN LƯƠNG |  MÃ KHOA  | TRKHOA | MÃ BỆNH ÁN |");
	    System.out.println("*--------------------------+------------+------+------------+-----------------+--------------+----------------+----+------------+-----------+--------+------------*");
        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list) {
            String fullname = healthcareWorker.getFullname();
            String birthday = healthcareWorker.getBirthday().getDateFormatByCondition("has cross");
            String gender = healthcareWorker.getGender();
            String phone = healthcareWorker.getPhone();
            String country = healthcareWorker.getCountry();
            String id = healthcareWorker.getId();
            String type = healthcareWorker.getType();
            int yearsOfExperience = healthcareWorker.getYearsOfExperience();
            double salary = healthcareWorker.getSalary();
            String departmentID = healthcareWorker.getDepartmentID();
            String isManagerDepartment = healthcareWorker.getIsManagerDepartment();
            String medicalRecordID = healthcareWorker.getMedicalRecordID();
            System.out.println(String.format("| %-24s | %-10s | %-4s | %-10s | %-15s | %-12s | %-14s | %-2s | %-10s | %-9s | %-6s | %-10s |",
                fullname, birthday, gender, phone, country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID));
        }
        if(HealthcareWorkerManager.numbers >= 1)
            System.out.println("*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*");
    }
    @Override
    public void add(HealthcareWorker healthcareWorker){
        HealthcareWorkerManager.list.add(healthcareWorker);
        HealthcareWorkerManager.numbers++;
    }
    @Override
    public void update(HealthcareWorker healthcareWorkerUpdate, int choice) {
        Scanner sc = new Scanner(System.in);
        if(choice == 1 || choice == 9) {
            System.out.print(" - Nhập họ và tên mới: "); 
            String newFullname = sc.nextLine();
            healthcareWorkerUpdate.setFullname(newFullname);
        }
        if(choice == 2 || choice == 9) {
            System.out.print(" - Nhập ngày sinh mới (dd-mm-yyyy hoặc ddmmyyyy): "); 
            String newBirthdayStr = sc.nextLine();
            while(!Date.getInstance().isDateFormat(newBirthdayStr)
                    || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()) {
                System.out.println("----- -----");
                System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                newBirthdayStr = sc.nextLine();
                System.out.println("----- -----");
            }
            Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
            healthcareWorkerUpdate.setBirthday(newBirthdayObj);
            AccountManager.getInstance().findObjectById(healthcareWorkerUpdate.getId()).setPassword(newBirthdayObj.getDateFormatByCondition("has not cross"));
        }
        if(choice == 3 || choice == 9) {
            System.out.print(" - Nhập giới tính mới (Nam hoặc Nữ): "); 
            String newGender = sc.nextLine();
            while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                System.out.println("----- -----");
                System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                newGender = sc.nextLine();
                System.out.println("----- -----");
            }
            healthcareWorkerUpdate.setGender(newGender);
        }
        if(choice == 4 || choice == 9) {
            System.out.print(" - Nhập số điện thoại mới (10 số): ");
            String newPhone = sc.nextLine();
            while(newPhone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newPhone)) {
                System.out.println("----- -----");
                System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (10 số): ");
                newPhone = sc.nextLine();
                System.out.println("----- -----");
            }
            healthcareWorkerUpdate.setPhone(newPhone);
        }
        if(choice == 5 || choice == 9) {
            System.out.print(" - Nhập quốc tịch mới: ");
            String newCountry = sc.nextLine();
            healthcareWorkerUpdate.setCountry(newCountry);
        }
        if(choice == 6 || choice == 9) {
            System.out.print(" - Nhập số năm kinh nghiệm (từ 0 trở lên): ");
            String yearsOfExperienceStr = sc.nextLine();
            while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                    || Integer.parseInt(yearsOfExperienceStr) < 0) {
                System.out.println("----- -----");
                System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (từ 0 trở lên): ");
                yearsOfExperienceStr = sc.nextLine();
                System.out.println("----- -----");
            }
            int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);
            healthcareWorkerUpdate.setYearsOfExperience(yearsOfExperienceInt);
            healthcareWorkerUpdate.setSalary(healthcareWorkerUpdate.calSalary());
        }
        if(choice == 7 || choice == 9) {
            // Xử lý những vấn đề chưa hợp logic ở Khoa cũ
            Department oldDepartment = DepartmentManager.getInstance().findObjectById(healthcareWorkerUpdate.getDepartmentID());
            if(oldDepartment != null) {
                if(oldDepartment.getManagerID() != null && oldDepartment.getManagerID().equals(healthcareWorkerUpdate.getId())) {
                    oldDepartment.setManagerID(null);
                    healthcareWorkerUpdate.setIsManagerDepartment("Không");
                }
            }

            // Chọn Khoa quản lý mới
            System.out.println(" - Chọn Khoa thuộc về");
            // 1 - DEP00001 | Tai-Mũi-Họng
            // 2 - DEP00002 | Thận
            // ...
            int numberList = 0;
            for(Department department : DepartmentManager.getInstance().getList()) {
                System.out.println(++numberList + " - " + department.getId() + " | " + department.getName());
            }
            // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
            System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
            String info = sc.nextLine();
            while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                        && DepartmentManager.getInstance().findObjectById(info) == null)
                    || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                        && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                System.out.println("----- -----");
                System.out.println("! - KHOA KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                info = sc.nextLine();
            }
            // Lấy mã Khoa đã được chọn
            String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                ? DepartmentManager.getInstance().findObjectById(info).getId()
                : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();
            healthcareWorkerUpdate.setDepartmentID(newDepartmentID);
        }
        if(choice == 8 || choice == 9) {
            // Lấy ra mã Khoa quản lí Nhân viên Y tế hiện tại
            String departmentID = healthcareWorkerUpdate.getDepartmentID();
            // Nếu Khoa quản lí hiện tại chưa có trưởng Khoa thì có thể thiết lập trưởng Khoa
            Department currentDepartment = DepartmentManager.getInstance().findObjectById(departmentID);
            if(currentDepartment.getManagerID() != null) {
                System.out.println("Khoa " + departmentID + " hiện tại đã có trưởng Khoa");
                if(currentDepartment.getManagerID().equals(healthcareWorkerUpdate.getId())) {
                    System.out.println("Trưởng Khoa chính là Nhân viên Y tế đang được sửa");
                }
                else {
                    System.out.println("Trưởng Khoa không phải là Nhân viên Y tế đang được sửa");
                }
            } else {
                System.out.println("Vì Khoa " + departmentID + " hiện tại chưa có trưởng Khoa");
                System.out.println("Bạn có muốn bổ nhiệm Nhân viên này làm trưởng Khoa ?");
                System.out.print(" - Nhập 'YES' để xác nhận: ");
                String conform = sc.nextLine();
                if(conform.equals("YES")) {
                    if(healthcareWorkerUpdate.getType().equals("Bác sĩ")) {
                        DepartmentManager.getInstance().findObjectById(departmentID).setManagerID(healthcareWorkerUpdate.getId());
                        healthcareWorkerUpdate.setIsManagerDepartment("Có");
                        System.out.println(" - Đã bổ nhiểm Nhân viên Y tế làm trưởng Khoa");
                    } else {
                        System.out.println(" - Vì Nhân viên Y tế hiện tại là Y tá nên không thể bổ nhiệm làm trưởng Khoa");
                    }
                } else {
                    System.out.println("Bạn đã không nhập 'YES' nên không bổ nhiệm");
                }
            }
        }
    }
    @Override
    public void remove(String id){
        for(int i = 0; i < HealthcareWorkerManager.numbers; i++) {
            if(HealthcareWorkerManager.list.get(i).getId().equals(id)) {
                HealthcareWorkerManager.list.remove(i);
                HealthcareWorkerManager.numbers--;
                return;
            }
        }
    }
    @Override
    public void sort(String condition){
        // HealthcareWorkerManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch(condition) {
            case "id asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getId()
                    ));
                break;
            }
            case "id desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getId()
                    ).reversed());
                break;
            }
            case "name asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getFullname()
                    ));
                break;
            }
            case "name desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getFullname()
                    ).reversed());
                break;
            }
            case "birthday asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getBirthday().getDateFormatByCondition("has not cross")
                    ));
                break;
            }
            case "birthday desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getBirthday().getDateFormatByCondition("has not cross")
                    ).reversed());
                break;
            }
            case "years asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getYearsOfExperience()
                    ));
                break;
            }
            case "years desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getYearsOfExperience()
                    ).reversed());
                break;
            }
            case "salary asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getSalary()
                    ));
                break;
            }
            case "salary desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getSalary()
                    ).reversed());
            }
        }
    }
    @Override
    public void loadFromFile() {
        File file = new File("src/Database/HealthcareWorkerDatabase.txt");
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Nhân viên Y tế không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String fullName = info[0];
                Date birthday = Date.getInstance().getDateFromDateFormat(info[1]);
                String gender = info[2];
                String phone = info[3];
                String country = info[4];
                String id = info[5];
                String type = info[6];
                int yearsOfExperience = Integer.parseInt(info[7]);
                double salary = Double.parseDouble(info[8]);
                String departmentID = null;
                if(!info[9].equals("null")) {
                    departmentID = info[9];
                }
                String isManagerDepartment = null;
                if(!info[10].equals("null")) {
                    isManagerDepartment = info[10];
                }
                String medicalRecordID = null;
                if(!info[11].equals("null"))
                    medicalRecordID = info[11];
                HealthcareWorker newHealthcareWorker = null;
                if(type.equals("Y tá"))
                    newHealthcareWorker = new Nurse(fullName, birthday, gender, phone,
                        country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID);
                else if(type.equals("Bác sĩ"))
                    newHealthcareWorker = new Doctor(fullName, birthday, gender, phone,
                        country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID);
                add(newHealthcareWorker);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void saveToFile() {
        File file = new File("src/Database/HealthcareWorkerDatabase.txt");
        // Kiểm tra file có tồn tại với path ở trên.
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Nhân viên Y tế không tồn tại");
            return;
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list){
                String fullname = healthcareWorker.getFullname();
                String birthday = healthcareWorker.getBirthday().getDateFormatByCondition("has cross");
                String gender = healthcareWorker.getGender();
                String phone = healthcareWorker.getPhone();
                String country = healthcareWorker.getCountry();
                String id = healthcareWorker.getId();
                String type = healthcareWorker.getType();
                int yearsOfExperience = healthcareWorker.getYearsOfExperience();
                double salary = healthcareWorker.getSalary();
                String departmentID = healthcareWorker.getDepartmentID();
                String isManagerDepartment = healthcareWorker.getIsManagerDepartment();
                String medicalRecordID = healthcareWorker.getMedicalRecordID();
                bufferedWriter.write(fullname + "|" + birthday + "|" + gender + "|" + phone + "|" + country 
                    + "|" + id + "|" + type + "|" + yearsOfExperience + "|" + salary + "|" + departmentID
                    + "|" + isManagerDepartment + "|" + medicalRecordID + "\n");
            }
            // Đóng bufferedWrite và fileWrite sau khi write xong.
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
