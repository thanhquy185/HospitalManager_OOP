package Patient;

import Common.Person;
import Common.Date;

// public abstract class Patient extends Person {
public class Patient extends Person {
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
        if(this.type.equals(("Premium"))){
            return "PPAT" + postfix;
        }
        return "NPAT" + postfix;
    }
    // Done
    // - Hàm lấy ra thông tin của Bệnh nhân
    public String getInfo() {
        return  this.fullname + " | " + this.birthday + " | " + this.gender + " | " + this.phone + this.country + " | " +
                this.id + " | " + this.type + " | " + this.medicalRecordID;    
    }
}