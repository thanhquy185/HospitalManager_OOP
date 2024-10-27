package Patient;

import Common.Date;

public class NormalPatient extends Patient {
    //Properties

    //Constructors
    public NormalPatient() {
        super();
    }
    public NormalPatient(String fullname, Date birthday,
            String gender, String phone, String country, String type) {
        super(fullname, birthday, gender, phone, country, type);
    }
    public NormalPatient(String fullname, Date birthday, String gender,
            String phone, String country, String id, String type, String idMedicalRecord) {
        super(fullname, birthday, gender, phone, country, id, type, idMedicalRecord);
    }
    public NormalPatient(NormalPatient normalPatient) {
        super(normalPatient.getFullname(), normalPatient.getBirthday(),
            normalPatient.getGender(), normalPatient.getPhone(), normalPatient.getCountry(),
            normalPatient.getId(),normalPatient.getType(), normalPatient.getIdMedicalRecord());
    }

    //Methods
}
