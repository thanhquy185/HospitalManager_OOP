package HealthcareWorker;

import Common.Date;

public class Nurse extends HealthcareWorker {
    //Properties

    //Constructors
    public Nurse() {
        super();
    }
    public Nurse(String fullname, Date birthday, String gender, String phone, String country,
            String type, int yearsOfExperience, int salary, String idDepartment, String isManagerDepartment) {
        super(fullname, birthday, gender, phone, country, type, yearsOfExperience, salary, idDepartment, isManagerDepartment);
    }
    public Nurse(Nurse nurse) {
        super(nurse.getFullname(), nurse.getBirthday(), nurse.getGender(), nurse.getPhone(),
            nurse.getCountry(), nurse.getId(), nurse.getType(), nurse.getYearsOfExperience(),
            nurse.getSalary(), nurse.getDepartmentID(), nurse.getIsManagerDepartment(),
            nurse.getMedicalRecordID());
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
