package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String country, String phone,
                  String id, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, country, phone, id, department, "Bác sĩ", yearsOfExperience, salary);
    }
    public Doctor(Doctor doctor) {
        super(doctor.getFullname(), doctor.getBirthday(), doctor.getGender(), doctor.getCountry(), doctor.getPhone(), doctor.getId(), doctor.getDepartment(), doctor.getPosition(), doctor.getYearsOfExperience(), doctor.getSalary());
    }

    //Methods

}