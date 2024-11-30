package HealthcareWorker;

import Common.Date;

public class Nurse extends HealthcareWorker {
    //Properties

    //Constructors
    public Nurse() {
        super();
    }
    public Nurse(String fullname, Date birthday, String gender, String phone, String country,
            String type, int yearsOfExperience, String departmentID, boolean isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country, type, yearsOfExperience, departmentID, isManagerDepartment, medicalRecordID);
    }
    public Nurse(String fullname, Date birthday, String gender, String phone, String country, String id,
            String type, int yearsOfExperience, double salary, String departmentID, boolean isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID);
    }
    public Nurse(Nurse nurse) {
        super(nurse.getFullname(), nurse.getBirthday(), nurse.getGender(), nurse.getPhone(),
            nurse.getCountry(), nurse.getId(), nurse.getType(), nurse.getYearsOfExperience(),
            nurse.getSalary(), nurse.getDepartmentID(), nurse.getIsManagerDepartment(),
            nurse.getMedicalRecordID());
    }

    //Methods
    @Override
	public double calSalary() {
		double basicSalary = 1000, salaryCoefficient = 0.1;
		return basicSalary + basicSalary * this.yearsOfExperience * salaryCoefficient;
	}
    @Override
	public void giveFoodToPatient() {
        System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
    }
	@Override
	public void giveCurativeToPatient() {
        System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
    }
	@Override
	public void injectCurativePatient() {
        System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
    }
}
