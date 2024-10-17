package Patient;

import Common.Person;
import Common.Date;
public class Patient extends Person {
    // Properties
    protected String id;
    protected boolean isTest;
    protected String type;
    protected Relatives relatives;

    // Constructors
    public Patient(){
        super();
        this.id = "000000000";
        this.isTest = false;
        this.type = "Normal";
        this.relatives = new Relatives();
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone,
                            String id, boolean isTest, String type, Relatives relatives) {
        super(fullname, birthday, gender, country, phone);
        this.id = id;
        this.isTest = isTest;
        this.type = type;
        this.relatives = relatives;
    }
    public Patient(Patient patient) {
        super(patient.fullname, patient.birthday, patient.gender, patient.country, patient.phone);
        this.id = patient.id;
        this.isTest = patient.isTest;
        this.type = patient.type;
        this.relatives = patient.relatives;
    }

    // Setter - Getter
    public void setId(String id){
        this.id = id;
    }
    public void setTest(boolean isTest) {
        this.isTest = isTest;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setRelatives(Relatives relatives){
        this.relatives = relatives;
    }
    public String getID(){
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
    public void function() {

        // Vì patientCounter: static field -> truy cập thông qua Patient class.
        //     Format patientCounter (4 số): 0001
        //     String counter = String.format("%05d", Patient.patientCounter);
        //     if(this.type.equals("Premium")){
        //         this.ID = "PPAT" + counter;
        //     } else {
        //         this.ID = "NPAT" + counter;
        //     }
        //     Patient.patientCounter++;
    }
}
