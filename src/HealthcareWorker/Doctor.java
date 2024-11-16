package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String phone, String country,
            String type, int yearsOfExperience, int salary, String idDepartment, String isManagerDepartment) {
        super(fullname, birthday, gender, phone, country, type, yearsOfExperience, salary, idDepartment, isManagerDepartment);
    }
    public Doctor(Doctor doctor) {
        super(doctor.getFullname(), doctor.getBirthday(), doctor.getGender(), doctor.getPhone(),
            doctor.getCountry(), doctor.getId(), doctor.getType(), doctor.getYearsOfExperience(),
            doctor.getSalary(), doctor.getDepartmentID(), doctor.getIsManagerDepartment(),
            doctor.getMedicalRecordID());
    }

    //Methods
    @Override
    public void testPatient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPatient'");
    }
    @Override
    public void giveFoodToPatient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'giveFoodToPatient'");
    }
    @Override
    public void giveCurativeToPatient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'giveCurativeToPatient'");
    }
    @Override
    public void injectCurativePatient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'injectCurativePatient'");
    }
}