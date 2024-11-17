package HealthcareWorker;

import java.util.Scanner;

import Common.*;
import Department.Department;
import Department.DepartmentManager;

// HealthcareWorker class
public abstract class HealthcareWorker extends Person implements ActionsInHospital {
    //Properties
    protected String id;
    protected String type;
    protected Integer yearsOfExperience;
    protected Double salary;
    protected String departmentID;
    protected String isManagerDepartment;
    protected String medicalRecordID;
    private static int countHealthcareWorkerCreated;

    // Static
    static {
        HealthcareWorker.countHealthcareWorkerCreated = 0;
    }

    // Constructor
    public HealthcareWorker() {
        super();
        this.id = null;
        this.type = null;
        this.yearsOfExperience = null;
        this.salary = null;
        this.departmentID = null;
        this.isManagerDepartment = null;
        this.medicalRecordID = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String phone, String country,
            String type, int yearsOfExperience, String departmentID, String isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.id = getFormatId();
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = calSalary();
        this.departmentID = departmentID;
        this.isManagerDepartment = isManagerDepartment;
        this.medicalRecordID = medicalRecordID;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String phone,
            String country, String id, String type, int yearsOfExperience, double salary,
            String departmentID, String isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country);
        this.id = id;
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.departmentID = departmentID;
        this.isManagerDepartment = isManagerDepartment;
        this.medicalRecordID = medicalRecordID;
    }
    public HealthcareWorker(HealthcareWorker healthcareWorker) {
        super(healthcareWorker.fullname, healthcareWorker.birthday,
            healthcareWorker.gender, healthcareWorker.phone, healthcareWorker.country);
        this.id = healthcareWorker.id;
        this.type = healthcareWorker.type;
        this.yearsOfExperience = healthcareWorker.yearsOfExperience;
        this.salary = healthcareWorker.salary;
        this.departmentID = healthcareWorker.departmentID;
        this.isManagerDepartment = healthcareWorker.isManagerDepartment;
        this.medicalRecordID = healthcareWorker.medicalRecordID;
    }

    // Setter - Getter
    public void setId(String id) {
        if(!isFormatId(id))
            this.id = null;
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
    public void setIsManagerDepartment(String isManagerDepartment) {
        this.isManagerDepartment = isManagerDepartment;
    }
    public void setMedicalRecordID(String medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }
    public static void setCountHealthcareWorkerCreated(int countHealthcareWorkerCreated) {
        HealthcareWorker.countHealthcareWorkerCreated = countHealthcareWorkerCreated;
    }
    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.type;
    }
    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }
    public double getSalary() {
        return this.salary;
    }
    public String getDepartmentID() {
        return this.departmentID;
    }
    public String getIsManagerDepartment() {
        return this.isManagerDepartment;
    }
    public String getMedicalRecordID() {
        return this.medicalRecordID;
    }
    public static int getCountHealthcareWorkerCreated() {
        return HealthcareWorker.countHealthcareWorkerCreated;
    }

    // Methods
    // - Hàm tính tiền lương cho một Nhân viên Y tế
    protected abstract double calSalary();
    // - Kiểm tra có đúng định dạng (DOC/NUR)xxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 8)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("DOC")
            && !prefix.equals("NUR")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(3);
        for(int i = 0; i < postfix.length(); i++) {
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
    // - Lấy id có đúng định dạng (DOC/NUR)xxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", HealthcareWorker.countHealthcareWorkerCreated);
        return "HEW" + postfix;
    }
    // - Hàm gán thông tin Nhân viên Y tế
    // -- condition1 là Y tá hay Bác sĩ
    // -- condition2 là trưởng Khoa hay không
    public void setInfo(String condition1, String condition2) {
        Scanner sc = new Scanner(System.in);
        
        // - Nhập tên 
        System.out.print(" - Nhập họ tên: ");
        String fullname = sc.nextLine();
        // - Nhập ngày sinh 
        System.out.print(" - Nhập ngày sinh (dd-mm-yyyy hoặc ddmmyyyy): ");
        String birthdayStr = sc.nextLine();
        while(!Date.getInstance().isDateFormat(birthdayStr)
                || !Date.getInstance().getDateFromDateFormat(birthdayStr).isDate()) {
            System.out.println("----- -----");
            System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
            birthdayStr = sc.nextLine();
           System.out.println("----- -----");
        }
        Date birthdayObj = Date.getInstance().getDateFromDateFormat(birthdayStr);
        // - Nhập giới tính 
        System.out.print(" - Nhập giới tính (Nam / Nữ): ");
        String gender = sc.nextLine();
        while(!gender.equals("Nam") && !gender.equals("Nữ")) {
            System.out.println("----- -----");
            System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (Nam / Nữ): ");
            gender = sc.nextLine();
            System.out.println("----- -----");
        }
        // - Nhập số điện thoại 
        System.out.print(" - Nhập số điện thoại (10 số): ");
        String phone = sc.nextLine();
        while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
            System.out.println("----- -----");
            System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (10 số): ");
            phone = sc.nextLine();
            System.out.println("----- -----");
        }
        // - Nhập quốc tịch 
        System.out.print(" - Nhập quốc tịch: ");
        String country = sc.nextLine();
        // - Nhập số năm kinh nghiệm 
        String yearsOfExperienceStr = null;
        if(condition2.equals("is manager")) {
            System.out.print(" - Nhập số năm kinh nghiệm (từ 4 năm trở lên): ");
            yearsOfExperienceStr = sc.nextLine();
            while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                    || Integer.parseInt(yearsOfExperienceStr) < 4) {
                System.out.println("----- -----");
                System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (từ 4 năm trở lên): ");
                yearsOfExperienceStr = sc.nextLine();
                System.out.println("----- -----");
            }
        } else if(condition2.equals("is not manager")) {
            System.out.print(" - Nhập số năm kinh nghiệm (từ 0 năm trở lên): ");
            yearsOfExperienceStr = sc.nextLine();
            while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                    || Integer.parseInt(yearsOfExperienceStr) < 0) {
                System.out.println("----- -----");
                System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (từ 0 năm trở lên): ");
                yearsOfExperienceStr = sc.nextLine();
                System.out.println("----- -----");
            }
        }
        int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);
        // - Chọn Khoa quản lý
        String departmentID = null;
        if(condition2.equals("is not manager")) {
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
            departmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                ? DepartmentManager.getInstance().findObjectById(info).getId()
                : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();
        }

        // Gán dữ liệu đã nhập cho đối tượng
        countHealthcareWorkerCreated++;
        this.id = getFormatId();
        this.fullname = fullname;
        this.birthday = birthdayObj;
        this.gender = gender;
        this.phone = phone;
        this.country = country;
        this.type = condition1.equals("is nurse") ? "Y tá" : "Bác sĩ";
        this.yearsOfExperience = yearsOfExperienceInt;
        this.salary = calSalary();
        this.departmentID = departmentID;
        this.isManagerDepartment = condition2.equals("is manager") ? "Có" : "Không";
    }
    // - Hàm lấy ra thông tin của Nhân viên Y tế
    public String getInfo() {
        return this.fullname + " | " + this.birthday.getDateFormatByCondition("has cross")
            + " | " + this.gender + " | " + this.phone + " | " + this.country + " | " + this.id
            + " | " + this.type + " | " + this.yearsOfExperience + " | " + this.salary
            + " | " + this.departmentID + " | " + this.isManagerDepartment + " | " + this.medicalRecordID;
    }
}
