package Patient;

import Common.Person;
import Common.Date;

public class Patient extends Person {
    // Properties
    protected String id;
    protected Boolean isTest;
    protected String type;
    protected Relatives relatives;
    protected static Integer countPersonCreated;

    // Static
    static {
        Patient.countPersonCreated = 0;
    }

    // Constructors
    public Patient() {
        super();
        this.id = null;
        this.isTest = false;
        this.type = "Normal";
        this.relatives = null;
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone){
        super(fullname, birthday,gender, country, phone);
        this.id = null;
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
    // - Kiểm tra có đúng định dạng (NPAT/PPAT)xxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 9 ký tự
        if(id.length() != 9)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 4);
        if(!prefix.equals("NPAT") && !prefix.equals("PPAT"))
            return false;
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
        String postfix = String.format("%05d", Patient.countPersonCreated);
        if(this.type.equals(("Cao cấp"))){
            return "PPAT" + postfix;
        }
        return "NPAT" + postfix;
    }
}