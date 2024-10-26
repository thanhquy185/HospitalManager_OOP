package Patient;

class Relatives {
    // Properties
    private String fullname;
    private String idPatient;
    private String relationship;

    // Constructors
    public Relatives() {
        this.fullname = null;
        this.idPatient = null;
        this.relationship = null;
    }
    public Relatives(String fullname, String idPatient, String relationship) {
        this.fullname = fullname;
        this.idPatient = idPatient;
        this.relationship = relationship;
    }
    public Relatives(Relatives relatives) {
        this.fullname = relatives.fullname;
        this.idPatient = relatives.idPatient;
        this.relationship = relatives.relationship;
    }

    // Setter - Getter
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getFullname() {
        return this.fullname;
    }
    public String getIdPatient() {
        return this.idPatient;
    }
    public String getRelationship() {
        return this.relationship;
    }

    //Methods
}
