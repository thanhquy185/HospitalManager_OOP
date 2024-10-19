package Patient;

class Relatives {
    // Properties
    private String fullname;
    private Patient patient;
    private String relationship;

    // Constructors
    public Relatives() {
        fullname = "anonymous";
        patient = new Patient();
        relationship = "???";
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
        return fullname;
    }
    public Patient getPatient() {
        return patient;
    }
    public String getRelationship() {
        return relationship;
    }

    //Methods
}
