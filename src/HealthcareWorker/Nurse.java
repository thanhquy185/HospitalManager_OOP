package HealthcareWorker;

import Common.Date;

public class Nurse extends HealthcareWorker {
    //Properties

    //Constructors
    public Nurse() {
        super();
    }
    public Nurse(String fullname, Date birthday, String gender, String country,
            String phone, String type, int yearsOfExperience, double salary, String idDepartment) {
        super(fullname, birthday, gender, country, phone, type, yearsOfExperience, salary, idDepartment);
    }
    public Nurse(Nurse nurse) {
        super(nurse.getFullname(), nurse.getBirthday(), nurse.getGender(), nurse.getCountry(),
            nurse.getPhone(), nurse.getId(), nurse.getType(), nurse.getYearsOfExperience(),
            nurse.getSalary(), nurse.getIdDepartment(), nurse.getIsManagerDepartment());
    }

    //Methods
}
