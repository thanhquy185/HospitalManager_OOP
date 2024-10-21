package Patient;

import Common.Date;

public class PatientPremium extends Patient {
    //Properties

    //Constructors
    public PatientPremium() {
        super();
    }
    public PatientPremium(String fullname, Date birthday, String gender, String country, String phone,
                            boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, isTest, "Premium", relatives);
    }
    public PatientPremium(PatientPremium patientPremium) {
        super(patientPremium.getFullname(), patientPremium.getBirthday(), patientPremium.getGender(), patientPremium.getCountry(), patientPremium.getPhone(), patientPremium.getId(), patientPremium.getTest(), patientPremium.getType(), patientPremium.getRelatives());
    }

    //Methods
}
