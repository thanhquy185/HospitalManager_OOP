package HealthcareWorker;

import java.util.Scanner;

import Common.*;
import Department.Department;
import Department.DepartmentManager;

// HealthcareWorker class
public class HealthcareWorker extends Person implements ActionsInHospital {
    //Properties
    protected String id;
    protected String type;
    protected Integer yearsOfExperience;
    protected Integer salary;
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
            String type, int yearsOfExperience, int salary, String departmentID, String isManagerDepartment) {
        super(fullname, birthday, gender, phone, country);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.id = getFormatId();
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.departmentID = departmentID;
        this.isManagerDepartment = isManagerDepartment;
        this.medicalRecordID = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String phone,
            String country, String id, String type, int yearsOfExperience, int salary,
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
    public void setSalary(int salary) {
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
    public int getSalary() {
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
    public void setInfo(String condition) {
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
        // - Nhập loại nhân viên 
        String type = null;
        if(condition.equals("is manager")) {
            type = "Bác sĩ";
        } else if(condition.equals("is not manager")) {
            System.out.print(" - Nhập loại nhân viên (Bác sĩ hoặc Y tá): ");;
            type = sc.nextLine();
            while(!type.equals("Bác sĩ") && !type.equals("Y tá")) {
                System.out.println("----- -----");
                System.out.println("! - LOẠI NHÂN VIÊN KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (Bác sĩ hoặc Y tá): ");
                type = sc.nextLine();
                System.out.println("----- -----");
            }
        }
        // - Nhập số năm kinh nghiệm 
        System.out.print(" - Nhập số năm kinh nghiệm (từ 2 năm trở lên): ");
        String yearsOfExperienceStr = sc.nextLine();
        while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                || Integer.parseInt(yearsOfExperienceStr) < 2) {
            System.out.println("----- -----");
            System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (từ 2 năm trở lên): ");
            yearsOfExperienceStr = sc.nextLine();
            System.out.println("----- -----");
        }
        int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);
        // - Nhập tiền lương 
        System.out.print(" - Nhập tiền lương (tối thiểu là 1000): ");
        String salaryStr = sc.nextLine();
        while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(salaryStr)
                || Integer.parseInt(salaryStr) < 1000) {
            System.out.println("----- -----");
            System.out.println("! - TIỀN LƯƠNG KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (tối thiểu là 1000): ");
            salaryStr = sc.nextLine();
            System.out.println("----- -----");
        }
        int salaryInt = Integer.parseInt(salaryStr);
        // - Chọn Khoa quản lý
        String departmentID = null;
        if(condition.equals("is not manager")) {
            System.out.println(" - Chọn Khoa thuộc về");
            // 1 - DEP00001 | Tai-Mũi-Họng
            // 2 - DEP00002 | Thận
            // ...
            int numberList = 1;
            for(Department department : DepartmentManager.getInstance().getList()) {
                System.out.println(numberList++ + " - " + department.getId() + " | " + department.getName());
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
        // - Xét Nhân viên có là trưởng Khoa
        String isManagerDepartment = null;
        if(condition.equals("is manager")) {
            isManagerDepartment = "Có";
        } else if(condition.equals("is not manager")) {
            isManagerDepartment = "Không";
        }

        // Gán dữ liệu đã nhập cho đối tượng
        countHealthcareWorkerCreated++;
        this.id = getFormatId();
        this.fullname = fullname;
        this.birthday = birthdayObj;
        this.gender = gender;
        this.phone = phone;
        this.country = country;
        this.type = type;
        this.yearsOfExperience = yearsOfExperienceInt;
        this.salary = salaryInt;
        this.departmentID = departmentID;
        this.isManagerDepartment = isManagerDepartment;
    }
    // - Hàm lấy ra thông tin của Nhân viên Y tế
    public String getInfo() {
        return this.fullname + " | " + this.birthday.getDateFormatByCondition("has cross")
            + " | " + this.gender + " | " + this.phone + " | " + this.country + " | " + this.id
            + " | " + this.type + " | " + this.yearsOfExperience + " | " + this.salary
            + " | " + this.departmentID + " | " + this.isManagerDepartment + " | " + this.medicalRecordID;
    }
    // - Hàm khám cho Bệnh nhân
	@Override
	public void testPatient() {
		System.out.println(" - Nhân viên Y tế chuẩn bị các thiết bị. Tiến hành công việc khám");
	}
	// - Hàm đưa khẩu phần ăn cho Bệnh nhân
	@Override
	public void giveFoodToPatient() {
		System.out.println(" - Nhân viên Y tế chuẩn bị khẩu phần ăn. Đưa cho Bệnh nhân");
	}
	// - Hàm đưa thuốc uống cho Bệnh nhân (mức độ Bệnh: Nhẹ hoặc Vừa)
	@Override
	public void giveCurativeToPatient() {
		System.out.println(" - Nhân viên Y tế chuẩn bị thuốc uống và một số thứ khác. Đưa cho Bệnh nhân");
	}
	// - Hàm tiêm thuốc cho Bệnh nhân (mức độ Bệnh: Nặng)
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Nhân viên Y tế chuẩn bị ông tiếm. Tiến hành công việc tiêm thuốc");
	}
}
