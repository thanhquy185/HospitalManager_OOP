package Patient;

import Common.Person;
import Common.Date;

public class Patient extends Person {
    /* PROPERTIES */
    private String ID;
    private boolean isTest;
    private String type;
    private Relatives relatives;

    // Lab:
    private static int patientCounter = 0001;

    /* CONSTRUCTORS */ 
    public Patient(){
        super();
        setID();                
        this.isTest = false;
        this.type = "Normal";
        // Relative
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone){
        super(fullname, birthday,gender, country, phone);
        setID();
        this.isTest = false;
        this.type = "Normal";
        // Relative
    }
    public Patient(Person person){
        super(person);
        setID();
        this.isTest = false;
        this.type = "Normal";
        // Relative
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone, 
                   boolean test, String type){
        super(fullname, birthday,gender, country, phone);
        setID();
        this.isTest = test;
        this.type = type;
        // Relative
    }

    /* SETTER - GETTER */ 
    public void setID(){
        if(this.type.equals("Premium")){
            this.ID = "PPAT" + this.birthday.toString();
        } else {
            this.ID = "NPAT" + this.birthday.toString();
        }
    }
    // Lab:
    public void setIDTest(){
        // Vì patientCounter: static field -> truy cập thông qua Patient class.
        // Format patientCounter (4 số): 0001
        String counter = String.format("%04d", Patient.patientCounter);
        if(this.type.equals("Premium")){
            this.ID = "PPAT" + this.birthday.toString() + counter;
        } else {
            this.ID = "NPAT" + this.birthday.toString() + counter;
        }
        Patient.patientCounter++;
    }

    public void setType(String type){
        this.type = type;   
    }
    public void setTest(boolean test){
        this.isTest = test;
    }

    public String getID(){
        return this.ID;
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
}

