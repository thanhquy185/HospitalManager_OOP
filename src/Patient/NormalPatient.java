package Patient;

import Common.Date;

public class NormalPatient extends Patient {
    //Properties

    //Constructors
    public NormalPatient() {
        super();
    }
    public NormalPatient(String fullname, Date birthday, String gender,
        String country, String phone, boolean isTest, String type) {
        super(fullname, birthday, gender, country, phone, isTest, type);
    }
    public NormalPatient(String fullname, Date birthday, String gender, String country,
        String phone, String id, boolean isTest, String type) {
        super(fullname, birthday, gender, country, phone, id, isTest, type);
    }
    public NormalPatient(NormalPatient normalPatient) {
        super(normalPatient.getFullname(), normalPatient.getBirthday(),
            normalPatient.getGender(), normalPatient.getCountry(), normalPatient.getPhone(),
            normalPatient.getId(), normalPatient.getIsTest(), normalPatient.getType());
    }

    //Methods
}
