package HealthcareWorker;

import Common.Date;

class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String country, String phone,
                  String id, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, country, phone, id, department, position, yearsOfExperience, salary);
    }
    public Doctor(Doctor doctor) {
        super(doctor.fullname, doctor.birthday, doctor.gender, doctor.country, doctor.phone, doctor.id, doctor.department, doctor.position, doctor.yearsOfExperience, doctor.salary);
    }

    //Methods
    
}