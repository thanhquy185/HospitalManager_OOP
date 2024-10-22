package HealthcareWorker;

import Common.Date;

public class Nurse extends HealthcareWorker {
    //Properties

    //Constructors
    public Nurse() {
        super();
    }
    public Nurse(String fullname, Date birthday, String gender, String country, String phone,
                  String id, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, country, phone, id, department, "Y tá", yearsOfExperience, salary);
    }
    public Nurse(Nurse nurse) {
        super(nurse.getFullname(), nurse.getBirthday(), nurse.getGender(), nurse.getCountry(), nurse.getPhone(), nurse.getId(), nurse.getDepartment(), nurse.getPosition(), nurse.getYearsOfExperience(), nurse.getSalary());
    }

    //Methods
}
