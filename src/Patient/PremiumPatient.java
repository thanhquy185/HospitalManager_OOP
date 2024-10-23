package Patient;

import Common.Date;

public class PremiumPatient extends Patient {
    //Properties

    //Constructors
    public PremiumPatient() {
        super();
    }
    public PremiumPatient(String fullname, Date birthday, String gender,
        String country, String phone, boolean isTest, String type) {
        super(fullname, birthday, gender, country, phone, isTest, type);
    }
    public PremiumPatient(String fullname, Date birthday, String gender, String country,
        String phone, String id, boolean isTest, String type, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, id, isTest, type, relatives);
    }
    public PremiumPatient(PremiumPatient premiumPatient) {
        super(premiumPatient.getFullname(), premiumPatient.getBirthday(), premiumPatient.getGender(),
            premiumPatient.getCountry(), premiumPatient.getPhone(), premiumPatient.getId(),
            premiumPatient.getIsTest(), premiumPatient.getType(), premiumPatient.getRelatives());
    }

    //Methods
}
