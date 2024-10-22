package Patient;

import Common.Date;

public class PremiumPatient extends Patient {
    //Properties

    //Constructors
    public PremiumPatient() {
        super();
    }
    public PremiumPatient(String fullname, Date birthday, String gender, String country, String phone,
                            boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, isTest, "Premium", relatives);
    }
    public PremiumPatient(PremiumPatient premiumPatient) {
        super(premiumPatient.getFullname(), premiumPatient.getBirthday(), premiumPatient.getGender(), premiumPatient.getCountry(), premiumPatient.getPhone(), premiumPatient.getId(), premiumPatient.getTest(), premiumPatient.getType(), premiumPatient.getRelatives());
    }

    //Methods
}
