package Patient;

import java.util.Scanner;

import Common.*;
public abstract class Patient extends Person implements ActionsInHospital {
    // Properties
    protected String id;
    protected String type;
    protected String medicalRecordID;
    protected static int countPatientCreated;

    // Static
    static {
        Patient.countPatientCreated = 0;
    }

    // Constructors
    public Patient() {
        super();
        this.id = null;
        this.type = null;
        this.medicalRecordID = null;
    }
    public Patient(String fullname, Date birthday, String gender,
            String phone, String country, String type){
        super(fullname, birthday,gender, phone, country);
        Patient.countPatientCreated++;
        this.type = type;
        this.id = getFormatId();
        this.medicalRecordID = null;
    }
    public Patient(String fullname, Date birthday, String gender, String phone,
            String country, String id, String type, String medicalRecordID){
        super(fullname, birthday, gender, phone, country);
        this.id = id;
        this.type = type;
        this.medicalRecordID = medicalRecordID;
    }
    public Patient(Patient patient) {
        super(patient.fullname, patient.birthday,
            patient.gender, patient.phone, patient.country);
        this.id = patient.id;
        this.type = patient.type;
        this.medicalRecordID = patient.medicalRecordID;
    }

    // Setter - Getter
    public void setId(String id){
        // Cần một hàm kiểm tra id có hợp lệ hay không (này để Quy làm)
        if(!isFormatId(id))
            this.id = null;
        this.id = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setMedicalRecordID(String medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }
    public static void setCountPatientCreated(int countPatientCreated) {
        Patient.countPatientCreated = countPatientCreated;
    }
    public String getId(){
        return this.id;
    }
    public String getType(){
        return this.type;
    }
    public String getMedicalRecordID() {
        return this.medicalRecordID;
    }
    public static int getCountPatientCreated() {
        return Patient.countPatientCreated;
    }

    // Methods
    // - Kiểm tra có đúng định dạng (NPAT/PPAT)xxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 9 ký tự
        if(id.length() != 9)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 4);
        if(!prefix.equals("NPAT")
            && !prefix.equals("PPAT")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(4);
        for(int i = 0; i < postfix.length(); i++) {
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57)
                return false;
        }
        return true;
    }
    // - Lấy id đúng định dạng (NPAT/PPAT)xxxxx
    private String getFormatId() {
        // --
        String postfix = String.format("%05d", Patient.countPatientCreated);
        return "PAT" + postfix;
    }
    // - Hàm gán thông tin cho Bệnh nhân
    public void setInfoWithNoMedicalRecord(String condition) {
        Scanner sc = new Scanner(System.in);
        // Nhập tên Bệnh nhân
        System.out.print(" - Nhập họ tên: ");
        String fullname = sc.nextLine();
        while(!myCharacterClass.getInstance().isValidName(fullname)) {
            System.out.println("----- -----");
            System.out.println("! - HỌ TÊN KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại: ");
            fullname = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập ngày sinh Bệnh nhân
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
        // Nhập giới tính Bệnh nhân
        System.out.print(" - Nhập giới tính (Nam / Nữ): ");
        String gender = sc.nextLine();
        while(!gender.equals("Nam") && !gender.equals("Nữ")) {
            System.out.println("----- -----");
            System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (Nam / Nữ): ");
            gender = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập số điện thoại Bệnh nhân
        System.out.print(" - Nhập số điện thoại (10 số): ");
        String phone = sc.nextLine();
        while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
            System.out.println("----- -----");
            System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (10 số): ");
            phone = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập quốc tịch Bệnh nhân
        System.out.print(" - Nhập quốc tịch: ");
        String country = sc.nextLine();
        while(!myCharacterClass.getInstance().isValidName(country)) {
            System.out.println("----- -----");
            System.out.println("! - QUỐC TỊCH KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại: ");
            country = sc.nextLine();
            System.out.println("----- -----");
        }

        // Gán dữ liệu đã nhập cho đối tượng
        Patient.countPatientCreated++;
        this.id = getFormatId();
        this.fullname = fullname;
        this.birthday = birthdayObj;
        this.gender = gender;
        this.phone = phone;
        this.country = country;
        this.type = condition.equals("is normal patient") ? "Bình thường" : "Cao cấp";
        this.medicalRecordID = null;
    }
    // - Hàm lấy ra thông tin của Bệnh nhân
    public String getInfo() {
        return this.fullname + " | " + this.birthday.getDateFormatByCondition("has cross") + " | " + this.gender + " | " + this.phone 
            + " | " + this.country + " | " + this.id + " | " + this.type + " | " + this.medicalRecordID;
    }
}