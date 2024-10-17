package Patient;

import Common.Date;

public class PatientPremium extends Patient {
    //Properties

    //Constructors
    public PatientPremium() {
        super();
    }
    public PatientPremium(String fullname, Date birthday, String gender, String country, String phone,
                            String id, boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, id, isTest, "Premium", relatives);                
    }
    public PatientPremium(PatientPremium patientPremium) {
        super(patientPremium.fullname, patientPremium.birthday, patientPremium.gender, patientPremium.country, patientPremium.phone, patientPremium.id, patientPremium.isTest, "Premium", patientPremium.relatives);
    }

    //Methods
}
