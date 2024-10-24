package Patient;

class Relatives {
    // Properties
    private String fullname;
    private Patient patient;
    private String relationship;

    // Constructors
    public Relatives() {
        this.fullname = null;
        this.patient = null;
        this.relationship = null;
    }
    public Relatives(String fullname, String relationship) {
        this.fullname = fullname;
        this.patient = null;
        this.relationship = relationship;
    }
    public Relatives(String fullname, Patient patient, String relationship) {
        this.fullname = fullname;
        this.patient = patient;
        this.relationship = relationship;
    }
    public Relatives(Relatives relatives) {
        this.fullname = relatives.fullname;
        this.patient = relatives.patient;
        this.relationship = relatives.relationship;
    }

    // Setter - Getter
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getFullname() {
        return this.fullname;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public String getRelationship() {
        return this.relationship;
    }

    //Methods
}
