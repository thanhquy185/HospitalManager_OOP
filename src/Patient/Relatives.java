package Patient;

class Relatives {
    // Properties
    private String fullname;
    private Patient patient;
    private String relationship;

    // Constructors
    Relatives() {
        this.fullname = "anonymous";
        this.patient = new Patient();
        this.relationship = "???";
    }
    Relatives(String fullname, Patient patient, String relationship) {
        this.fullname = fullname;
        this.patient = patient;
        this.relationship = relationship;
    }
    Relatives(Relatives relatives) {
        this.fullname = relatives.fullname;
        this.patient = relatives.patient;
        this.relationship = relatives.relationship;
    }

    // Setter - Getter
    void setFullname(String fullname) {
        this.fullname = fullname;
    }
    void setPatient(Patient patient) {
        this.patient = patient;
    }
    void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    String getFullname() {
        return this.fullname;
    }
    Patient getPatient() {
        return this.patient;
    }
    String getRelationship() {
        return this.relationship;
    }

    //Methods
}
