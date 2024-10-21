package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker implements Comparable<Doctor>{
    //Properties

    private static final String Objects = null;
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

    @Override
    public int compareTo(Doctor o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    @Override
    public int hashCode(){
        return Objects.hash(fullname, birthday, gender,country,phone, id, department,position, yearsOfExperience, salary);
    }

    @Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
    Doctor other = (Doctor) obj;
    return Objects.equals(id, other.id);
    //Methods
    
}
    
}