package Patient;

import java.util.Scanner;

<<<<<<< HEAD
// public abstract class Patient extends Person {
public class Patient extends Person {
=======
import Common.*;
public class Patient extends Person implements ActionsInHospital {
>>>>>>> origin/main
    // Properties
    protected String id;
    protected String type;                      // Normal | Premium
    protected String medicalRecordID;           // ID Bệnh Án
    protected static int countPatientCreated;

    // Static
    static {
        Patient.countPatientCreated = 0;
    }

    // Constructors
    // Không có tham số => Patient này không có ID -> Không thể đưa vào list
    public Patient() {
        super();                        // All: null
        this.id = null;
        this.type = null;
        this.medicalRecordID = null;
    }
    // Cơ bản, không có [type] -> Mặc định [type]: "Normal"
    public Patient( String fullName, Date birthday, String gender, String phone, String country){
        super(fullName, birthday, gender, phone, country);
        Patient.countPatientCreated++;
        this.id = getFormatId();
        this.type = "Normal";
        this.medicalRecordID = null;

    }
    // Cơ bản, có [type]
    public Patient( String fullname, Date birthday, String gender, String phone, String country,
                    String type){
        super(fullname, birthday, gender, phone, country);
        Patient.countPatientCreated++;
        this.type = type;
        this.id = getFormatId();
        this.medicalRecordID = null;
    }
    // Cơ bản, có [ID], [type], [medicalRecordID]
    public Patient( String fullname, Date birthday, String gender, String phone, String country, 
                    String id, String type, String medicalRecordID){
        super(fullname, birthday, gender, phone, country);
        Patient.countPatientCreated++;
        this.id = id;
        this.type = type;
        this.medicalRecordID = medicalRecordID;
    }
    public Patient(Patient patient) {
        super(patient.fullname, patient.birthday,
            patient.gender, patient.phone, patient.country);
        Patient.countPatientCreated++;
        this.id = patient.id;
        this.type = patient.type;
        this.medicalRecordID = patient.medicalRecordID;
    }

    // Setter - Getter
    public void setId(String id){
        // Cần một hàm kiểm tra id có hợp lệ hay không (này để Quy làm)
        if(!Patient.isFormatId(id))
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
    // Để public static: có thể sử dụng để kiểm tra formatID (được nhập) khi update() bệnh nhân
    public static boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 9 ký tự
        if(id.length() != 9)
            return false;
        // -- Kiểm tra tiền tố
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
<<<<<<< HEAD
        if(this.type.equals(("Premium"))){
            return "PPAT" + postfix;
=======
        return "PAT" + postfix;
    }
    // - Hàm gán thông tin cho Bệnh nhân
    public void setInfoWithNoMedicalRecord() {
        Scanner sc = new Scanner(System.in);

        // Nhập tên Bệnh nhân
        System.out.print(" -+ Nhập họ tên: ");
        String fullname = sc.nextLine();
        // Nhập ngày sinh Bệnh nhân
        System.out.print(" -+ Nhập ngày sinh (dd-mm-yyyy hoặc ddmmyyyy): ");
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
        System.out.print(" -+ Nhập giới tính (Nam / Nữ): ");
        String gender = sc.nextLine();
        while(!gender.equals("Nam") && !gender.equals("Nữ")) {
            System.out.println("----- -----");
            System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (Nam / Nữ): ");
            gender = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập số điện thoại Bệnh nhân
        System.out.print(" -+ Nhập số điện thoại (10 số): ");
        String phone = sc.nextLine();
        while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
            System.out.println("----- -----");
            System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (10 số): ");
            phone = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập quốc tịch Bệnh nhân
        System.out.print(" -+ Nhập quốc tịch: ");
        String country = sc.nextLine();

        // Nhập loại chăm sóc Bệnh nhân
        System.out.print(" -+ Nhập loại chăm sóc (Bình thường hoặc Cao cấp): ");
        String type = sc.nextLine();
        while(!type.equals("Bình thường") && !type.equals("Cao cấp")) {
            System.out.println("----- -----");
            System.out.println("! - LOẠI CHĂM SÓC KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (Bình thường hoặc Cao cấp): ");
            type = sc.nextLine();
            System.out.println("----- -----");
>>>>>>> origin/main
        }

        // Gán dữ liệu đã nhập cho đối tượng
        Patient.countPatientCreated++;
        this.id = getFormatId();
        this.fullname = fullname;
        this.birthday = birthdayObj;
        this.gender = gender;
        this.phone = phone;
        this.country = country;
        this.type = type;
        this.medicalRecordID = null;
    }
    // Done
    // - Hàm lấy ra thông tin của Bệnh nhân
    public String getInfo() {
<<<<<<< HEAD
        return  this.fullname + " | " + this.birthday + " | " + this.gender + " | " + this.phone + this.country + " | " +
                this.id + " | " + this.type + " | " + this.medicalRecordID;    
    }
=======
        return this.fullname + " | " + this.birthday.getDateFormatByCondition("has cross") + " | " + this.gender + " | " + this.phone 
            + " | " + this.country + " | " + this.id + " | " + this.type + " | " + this.medicalRecordID;
    }
    // - Hàm khám cho Bệnh nhân
	@Override
	public void testPatient() {
		System.out.println(" - Bệnh nhân đã được khám xong. Sức khoẻ có lẽ có tiến triển hơn chút");
	}
	// - Hàm đưa khẩu phần ăn cho Bệnh nhân
	@Override
	public void giveFoodToPatient() {
        if(this.type.equals("Bình thường"))
		    System.out.println(" - Bệnh nhân đã nhận được khẩu phần ăn loại Bình thường");
        else if(this.type.equals("Cao cấp"))
            System.out.println(" - Bệnh nhân đã nhận được khẩu phần ăn loại Cao cấp");
	}
	// - Hàm đưa thuốc uống cho Bệnh nhân (mức độ Bệnh: Nhẹ hoặc Vừa)
	@Override
	public void giveCurativeToPatient() {
        if(this.type.equals("Bình thường"))
		    System.out.println(" - Bệnh nhân đã nhận được thuốc uống, nước lọc và một chút bánh ngọt");
        else if(this.type.equals("Cao cấp"))
            System.out.println(" - Bệnh nhân đã nhận được thuốc uống, trái cây, nước lọc, nước trái cây và một chút bánh");

	}
	// - Hàm tiêm thuốc cho Bệnh nhân (mức độ Bệnh: Nặng)
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Bệnh nhân đã được tiêm thuốc xong. Bệnh nhân đang nghỉ ngơi");
	}
>>>>>>> origin/main
}