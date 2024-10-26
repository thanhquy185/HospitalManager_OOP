package Patient;

import Common.Person;
import Common.Date;

public abstract class Patient extends Person {
    // Properties
    protected String id;
    protected String type;
    protected Boolean isTest;
    protected String idMedicalRecord;
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
        this.isTest = null;
        this.idMedicalRecord = null;
    }
    // Này Quy dùng để khi Đăng ký người dùng mới
    public Patient(String fullname, Date birthday, String gender, String country, String phone){
        super(fullname, birthday,gender, country, phone);
        this.id = null;
        this.type = null;
        this.isTest = null;
    }
    public Patient(String fullname, Date birthday, String gender,
            String country, String phone, boolean isTest, String type){
        super(fullname, birthday,gender, country, phone);
        Patient.countPatientCreated++;
        this.type = type;
        this.id = getFormatId();
        this.isTest = isTest;
    }
    public Patient(String fullname, Date birthday, String gender, String country,
            String phone, String id, boolean isTest, String type, String idMedicalRecord){
        super(fullname, birthday, gender, country, phone);
        this.id = id;
        this.type = type;
        this.isTest = isTest;
        this.idMedicalRecord = idMedicalRecord;
    }
    public Patient(Patient patient) {
        super(patient.fullname, patient.birthday,
            patient.gender, patient.country, patient.phone);
        this.id = patient.id;
        this.type = patient.type;
        this.isTest = patient.isTest;
        this.idMedicalRecord = patient.idMedicalRecord;
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
    public void setIsTest(boolean test){
        this.isTest = test;
    }
    public void setIdMedicalRecord(String idMedicalRecord) {
        this.idMedicalRecord = idMedicalRecord;
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
    public boolean getIsTest(){
        return this.isTest;
    }
    public String getIdMedicalRecord() {
        return this.idMedicalRecord;
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
        if(this.type.equals(("Cao cấp"))){
            return "PPAT" + postfix;
        }
        return "NPAT" + postfix;
    }
}