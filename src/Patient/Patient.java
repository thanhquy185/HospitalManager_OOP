package Patient;

import Common.Person;

public class Patient extends Person {
    // Properties
    private String ID;
    private boolean isTest;
    private String type;
    private Relatives relatives;

    // Constructors:
    public Patient(){
        super();
    }
    // Setter - Getter
    public void setID(){

    }
    public void setType(String type){
        this.type = type;   //Normal | Premium
    }

    public String getID(){
        return this.ID;
    }
    public String getType(){
        return this.type;
    }
    public Relatives getRelatives(){
        return this.relatives;
    }



}

