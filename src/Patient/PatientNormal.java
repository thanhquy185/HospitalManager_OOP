package Patient;

import Common.Date;

public class PatientNormal extends Patient {
    //Properties

    //Constructors
    public PatientNormal() {
        super();
    }
    public PatientNormal(String fullname, Date birthday, String gender, String country, String phone,
                            String id, boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, id, isTest, "Normal", relatives);                
    }
    public PatientNormal(PatientNormal patientNormal) {
        super(patientNormal.fullname, patientNormal.birthday, patientNormal.gender, patientNormal.country, patientNormal.phone, patientNormal.id, patientNormal.isTest, "Normal", patientNormal.relatives);
    }

    //Methods
}
