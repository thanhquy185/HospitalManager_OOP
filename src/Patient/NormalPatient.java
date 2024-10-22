package Patient;

import Common.Date;

public class NormalPatient extends Patient {
    //Properties

    //Constructors
    public NormalPatient() {
        super();
    }
    public NormalPatient(String fullname, Date birthday, String gender, String country, String phone,
                             boolean isTest, Relatives relatives) {
        super(fullname, birthday, gender, country, phone, isTest, "Normal", relatives);
    }
    public NormalPatient(NormalPatient normalPatient) {
        super(normalPatient.getFullname(), normalPatient.getBirthday(), normalPatient.getGender(), normalPatient.getCountry(), normalPatient.getPhone(), normalPatient.getId(), normalPatient.getTest(), normalPatient.getType(), normalPatient.getRelatives());
    }

    //Methods
}
