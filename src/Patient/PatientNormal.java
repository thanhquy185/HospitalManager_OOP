package Patient;

import Common.Date;

public class PatientNormal extends Patient {
    //Properties

    //Constructors
    public PatientNormal() {
        super();
    }
    public PatientNormal(String fullname, Date birthday, String gender, String country, String phone,
                             boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, isTest, "Normal", relatives);
    }
    public PatientNormal(PatientNormal patientNormal) {
        super(patientNormal.getFullname(), patientNormal.getBirthday(), patientNormal.getGender(), patientNormal.getCountry(), patientNormal.getPhone(), patientNormal.getId(), patientNormal.getTest(), patientNormal.getType(), patientNormal.getRelatives());
    }

    //Methods
}
