package Patient;

import Common.Person;
import Common.Date;

public class Patient extends Person {
    /* PROPERTIES */
    private String ID;
    private boolean isTest;
    private String type;
    private Relatives relatives;
    private static int patientCounter = 00001;

    /* CONSTRUCTORS */ 
    public Patient(){
        super();
        this.isTest = false;
        this.type = "Normal";
        setID();                
        // Relative
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone){
        super(fullname, birthday,gender, country, phone);
        this.isTest = false;
        this.type = "Normal";
        setID();
        // Relative
    }
    public Patient(String fullname, Date birthday, String gender, String country, String phone, 
                   boolean test, String type){
        super(fullname, birthday,gender, country, phone);
        this.isTest = test;
        this.type = type;
        setID();
        // Relative
    }
    public Patient(Person person){
        super(person);
        this.isTest = false;
        this.type = "Normal";
        setID();    
        // Relative
    }
    public Patient(Person person, boolean test, String type){
        super(person);
        this.isTest = test;
        this.type = type;
        setID();
    }

    /* SETTER - GETTER */ 
    public void setID(){
        // Vì patientCounter: static field -> truy cập thông qua Patient class.
        // Format patientCounter (5 số): 00001
        String counter = String.format("%05d", Patient.patientCounter);
        if(this.type.equals("Premium")){
            this.ID = "PPAT" + counter;
        } else {
            this.ID = "NPAT" + counter;
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

