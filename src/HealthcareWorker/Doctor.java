package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String country, String phone,
                  String type, int yearsOfExperience, double salary, String idDepartment) {
        super(fullname, birthday, gender, country, phone, type, yearsOfExperience, salary, idDepartment);
    }
    public Doctor(Doctor doctor) {
        super(doctor.getFullname(), doctor.getBirthday(), doctor.getGender(), doctor.getCountry(),
            doctor.getPhone(), doctor.getId(), doctor.getType(), doctor.getYearsOfExperience(),
            doctor.getSalary(), doctor.getIdDepartment(), doctor.getIsManagerDepartment());
    }

    //Methods

}