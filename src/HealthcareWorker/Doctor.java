package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String phone, String country,
                  String type, int yearsOfExperience, double salary, String idDepartment) {
        super(fullname, birthday, gender, phone, country, type, yearsOfExperience, salary, idDepartment);
    }
    public Doctor(Doctor doctor) {
        super(doctor.getFullname(), doctor.getBirthday(), doctor.getGender(), doctor.getPhone(),
            doctor.getCountry(), doctor.getId(), doctor.getType(), doctor.getYearsOfExperience(),
            doctor.getSalary(), doctor.getDepartmentID(), doctor.getIsManagerDepartment(),
            doctor.getMedicalRecordID());
    }

    //Methods

}