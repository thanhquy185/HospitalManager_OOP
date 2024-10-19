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
        super(fullname, birthday, gender, country, phone, id, department, position, yearsOfExperience, salary);
    }
    public Nurse(Nurse nurse) {
        super(nurse.fullname, nurse.birthday, nurse.gender, nurse.country, nurse.phone, nurse.id, nurse.department, nurse.position, nurse.yearsOfExperience, nurse.salary);
    }

    //Methods
}
