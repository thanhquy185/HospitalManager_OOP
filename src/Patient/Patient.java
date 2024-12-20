package Patient;

import java.util.Scanner;

import Common.*;
import MedicalRecord.MedicalRecord;
import MedicalRecord.MedicalRecordManager;
public abstract class Patient extends Person implements ActionsInHospital {
    // Properties
    protected String id;
    protected String type;
    protected String medicalRecordID;
    private static int countPatientCreated;

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
    // - Kiểm tra có đúng định dạng (PAT)xxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 9)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("PAT")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(3);
        if(!myClass.getInstance().hasAllCharacterIsNumber(postfix)) return false;
        return true;
    }
    // - Lấy id đúng định dạng (PAT)xxxxx
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
        while(!myClass.getInstance().isValidName(fullname)) {
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
        while(phone.length() != 10 || !myClass.getInstance().hasAllCharacterIsNumber(phone)) {
            System.out.println("----- -----");
            System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (10 số): ");
            phone = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập quốc tịch Bệnh nhân
        System.out.print(" - Nhập quốc tịch: ");
        String country = sc.nextLine();
        while(!myClass.getInstance().isValidName(country)) {
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
        return this.fullname + " | " + this.birthday.getDateFormatByCondition("has cross") + " | " + this.gender
            + " | " + this.phone + " | " + this.country + " | " + this.id + " | " + this.type + " | " + this.medicalRecordID;
    }
    @Override
    public void testPatient() {
        System.out.println(" - Bệnh nhân " + this.fullname + " đã được khám xong (chế độ chăm sóc: "+ this.type + "). Sức khoẻ có lẽ có tiến triển hơn chút");
    }
    @Override
	public void giveFoodToPatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh")) {
            System.out.println(" - Bệnh nhân " + this.fullname + " đã nhận được khẩu phần ăn loại " + this.type);
        } else {
            System.out.println(" - Bệnh nhân " + this.fullname + " không làm gì và tiếp tục nghỉ ngơi");
        }
	}
	@Override
	public void giveCurativeToPatient() {}
	@Override
	public void injectCurativePatient() {
		MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh") && currentMedicalRecord.getSickLevel().equals("Nặng")) {
		    System.out.println(" - Bệnh nhân " + this.fullname + " đã được tiêm thuốc loại " + this.type + " xong. Bệnh nhân đang nghỉ ngơi");
        } else {
            System.out.println(" - Bệnh nhân " + this.fullname + " không làm gì và tiếp tục nghỉ ngơi");
        }
	}
}