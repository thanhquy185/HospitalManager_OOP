package Patient;

import Common.Person;
import Common.Date;

public class Patient extends Person {
    // Properties
    protected String id;
    protected boolean isTest;
    protected String type;
    protected Relatives relatives;
    protected static int countPersonCreated;

    // Static
    static {
        Patient.countPersonCreated = 0;
    }

    // Constructors
    public Patient() {
        super();
        this.id = "?";
        this.isTest = false;
        this.type = "Normal";
        this.relatives = new Relatives();
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone){
        super(fullname, birthday,gender, country, phone);
        this.id = "?";
        this.isTest = false;
        this.type = "Normal";
        this.relatives = new Relatives();
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone, 
                   boolean test, String type, Relatives relatives){
        super(fullname, birthday,gender, country, phone);
        Patient.countPersonCreated++;
        this.isTest = test;
        this.type = type;
        this.relatives = new Relatives(relatives);
        this.id = getFormatId();
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone, 
                   String id, boolean test, String type, Relatives relatives){
        super(fullname, birthday, gender, country, phone);
        this.id = id;
        this.isTest = test;
        this.type = type;
        this.relatives = new Relatives(relatives);
    }
    public Patient(Patient patient) {
        super(patient.getFullname(), patient.getBirthday(), patient.getGender(), patient.getCountry(), patient.getPhone());
        this.id = patient.getId();
        this.isTest = patient.getTest();
        this.type = patient.getType();
        this.relatives = new Relatives(patient.getRelatives());
    }
    // public Patient(Person person){
    //     super(person);
    //     this.id = "?";
    //     this.isTest = false;
    //     this.type = "Normal";
    //     this.relatives = new Relatives();
    // }
    // public Patient(Person person, boolean test, String type, Relatives relatives){
    //     super(person);
    //     this.id = getFormatId();
    //     this.isTest = test;
    //     this.type = type;
    //     this.relatives = relatives;
    // }
    // public Patient(Person person, String id, boolean test, String type, Relatives relatives){
    //     super(person);
    //     this.id = id;
    //     this.isTest = test;
    //     this.type = type;
    //     this.relatives = relatives;
    // }

    // Setter - Getter
    public void setId(String id){
        // Cần một hàm kiểm tra id có hợp lệ hay không (này để Quy làm)
        if(isFormatId(id))
            this.id = id;
        this.id = "?";
    }
    public void setType(String type){
        this.type = type;
    }
    public void setTest(boolean test){
        this.isTest = test;
    }
    public void setRelatives(Relatives relatives) {
        this.relatives = relatives;
    }
    public String getId(){
        return this.id;
    }
    public boolean getTest(){
        return this.isTest;
    }
    public String getType(){
        return this.type;
    }
    public Relatives getRelatives(){
        return this.relatives;
    }

    // Methods
    private boolean isFormatId(String id) {
        // Nếu không phải là chuỗi 9 ký tự
        if(id.length() != 9)
            return false;
        // Kiểm tra tiền tối
        String prefix = id.substring(0, 4);
        if(!prefix.equals("NPAT") && !prefix.equals("PPAT"))
            return false;
        // Kiểm tra hậu tố
        String postfix = id.substring(4);
        for(int i = 0; i < postfix.length(); i++) {
            // Chuyển ký tự về mã số Unicode
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 58)
                return false;
        }
        return true;
    }
    private String getFormatId() {
        // Vì patientCounter: static field -> truy cập thông qua Patient class.
        // Format patientCounter (5 số): 00001
        String postfix = String.format("%05d", Patient.countPersonCreated);
        if(this.type.equals(("Cao cấp"))){
            return "PPAT" + postfix;
        }
        return "NPAT" + postfix;
    }
}