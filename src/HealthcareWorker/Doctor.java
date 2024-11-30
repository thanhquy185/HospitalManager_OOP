package HealthcareWorker;

import Common.Date;
import MedicalRecord.MedicalRecord;
import MedicalRecord.MedicalRecordManager;

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
	public void giveFoodToPatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh")) {
            System.out.println(" - " + this.type + " " + this.fullname + " chuẩn bị khẩu phần ăn (theo chế độ chăm sóc). Đưa cho Bệnh nhân");
        } else {
            System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
        }
	}
	@Override
	public void giveCurativeToPatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh")
                && (currentMedicalRecord.getSickLevel().equals("Nhẹ") || currentMedicalRecord.getSickLevel().equals("Vừa"))) {
            System.out.println(" - " + this.type + " " + this.fullname + " chuẩn bị thuốc uống và một số thứ khác (theo chế độ chăm sóc). Đưa cho Bệnh nhân");
        } else {
            System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
        }
	}
	@Override
	public void injectCurativePatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh") && currentMedicalRecord.getSickLevel().equals("Nặng")) {
            System.out.println(" - " + this.type + " " + this.fullname + " chuẩn bị ông tiếm (theo chế độ chăm sóc). Tiến hành công việc tiêm thuốc");
        } else {
            System.out.println(" - " + this.type + " " + this.fullname + " không làm gì");
        }
	}
}