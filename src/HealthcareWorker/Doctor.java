package HealthcareWorker;

import Common.Date;

public class Doctor extends HealthcareWorker {
    //Properties

    //Constructors
    public Doctor() {
        super();
    }
    public Doctor(String fullname, Date birthday, String gender, String phone, String country,
            String type, int yearsOfExperience, String departmentID, boolean isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country, type, yearsOfExperience, departmentID, isManagerDepartment, medicalRecordID);
    }
    public Doctor(String fullname, Date birthday, String gender, String phone, String country, String id,
            String type, int yearsOfExperience, double salary, String departmentID, boolean isManagerDepartment, String medicalRecordID) {
        super(fullname, birthday, gender, phone, country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID);
    }
    public Doctor(Doctor doctor) {
        super(doctor.getFullname(), doctor.getBirthday(), doctor.getGender(), doctor.getPhone(),
            doctor.getCountry(), doctor.getId(), doctor.getType(), doctor.getYearsOfExperience(),
            doctor.getSalary(), doctor.getDepartmentID(), doctor.getIsManagerDepartment(),
            doctor.getMedicalRecordID());
    }

    //Methods
    @Override
	public double calSalary() {
		double basicSalary = 1300;
        double salaryCoefficient = this.isManagerDepartment == true ? 0.5 : 0.2;
		return basicSalary + basicSalary * this.yearsOfExperience * salaryCoefficient;
	}
   @Override
	public void testPatient() {
		System.out.println(" - Bác sĩ chuẩn bị các thiết bị. Tiến hành công việc khám");
	}
	@Override
	public void giveFoodToPatient() {
		System.out.println(" - Bác sĩ chuẩn bị khẩu phần ăn. Đưa cho Bệnh nhân");
	}
	@Override
	public void giveCurativeToPatient() {
		System.out.println(" - Bác sĩ chuẩn bị thuốc uống và một số thứ khác. Đưa cho Bệnh nhân");
	}
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Bác sĩ chuẩn bị ông tiếm. Tiến hành công việc tiêm thuốc");
	}
}