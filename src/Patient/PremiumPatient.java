package Patient;

import Common.Date;

public class PremiumPatient extends Patient {
    //Properties

    //Constructors
    public PremiumPatient() {
        super();
    }
    public PremiumPatient(String fullname, Date birthday,
            String gender, String phone, String country, String type) {
        super(fullname, birthday, gender, phone, country, type);
    }
    public PremiumPatient(String fullname, Date birthday, String gender,
            String phone, String country, String id, String type, String idMedicalRecord) {
        super(fullname, birthday, gender, phone, country, id, type, idMedicalRecord);
    }
    public PremiumPatient(PremiumPatient premiumPatient) {
        super(premiumPatient.getFullname(), premiumPatient.getBirthday(),
            premiumPatient.getGender(), premiumPatient.getPhone(), premiumPatient.getCountry(),
            premiumPatient.getId(),premiumPatient.getType(), premiumPatient.getMedicalRecordID());
    }

    //Methods
}
