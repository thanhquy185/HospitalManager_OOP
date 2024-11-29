package MedicalRecord;

import Common.Date;
import HealthcareWorker.HealthcareWorkerManager;
import Patient.PatientManager;

public class TestRecord extends MedicalRecord {
    // Properties

    // Constructor
	public TestRecord() {
		super();
	}
	public TestRecord(Date inputDay, Date outputDay, String type, String patientID, 
			String sickID, String sickLevel, String healthcareWorkerID) {
		super(inputDay, outputDay, type, patientID, sickID, sickLevel, healthcareWorkerID);
	}
	public TestRecord(String id, Date inputDay, Date outputDay, String type, 
			Double fee, String patientID, String sickID, String sickLevel, String healthcareWorkerID) {
		super(id, inputDay, outputDay, type, fee, patientID, sickID, sickLevel, healthcareWorkerID);
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getId(), testRecord.getInputDay(), testRecord.getOutputDay(),
			testRecord.getType(), testRecord.getFee(), testRecord.getPatientID(),
			testRecord.getSickID(), testRecord.getSickLevel(), testRecord.getHealthcareWorkerID());
	}

    // Setter - Getter

	// Methods
	@Override
	public double calFee() {
		double basicFee = 100, feeCoefficient = 0;
		// Là khám bệnh (Vì khám trong 1 ngày)
		feeCoefficient += 0.2;
		// Tuỳ theo việc loại chăm sóc Bệnh nhân là Bình thường hay Cao cấp thì hệ số phí sẽ khác
		if(PatientManager.getInstance().findObjectById(this.patientID).getType().equals("Bình thường")) feeCoefficient += 0.5;
		else if(PatientManager.getInstance().findObjectById(this.patientID).getType().equals("Cao cấp")) feeCoefficient += 2;
		// Mặc định các loại Bệnh khác nhau đều có chung một hệ số phí (phức tạp quá nên Quy không dám làm sợ sai ngữ nghĩa)
		feeCoefficient += 1;
		// Tuỳ theo mức độ của Bệnh mà hệ số phí sẽ khác
		if(this.type.equals("Nhẹ")) {
			feeCoefficient += 0.2;
		} else if(this.type.equals("Vừa")) {
			feeCoefficient += 0.5;
		} else if(this.type.equals("Nặng")) {
			feeCoefficient += 0.8;
		}
		return basicFee + basicFee*feeCoefficient;
	}
	@Override
	public void giveFoodToPatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		System.out.println(" - Việc kiểm tra hoàn tất. Vì là Bệnh án Khám bệnh nên không thể tiến hành công việc đưa khẩu phần ăn cho Bệnh nhân");
	}
	@Override
	public void giveCurativeToPatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		System.out.println(" - Việc kiểm tra hoàn tất. Vì là Bệnh án Khám bệnh nên không thể tiến hành công việc đưa thuốc uống cho Bệnh nhân");
	}
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		System.out.println(" - Việc kiểm tra hoàn tất. Vì là Bệnh án Khám bệnh nên không thể tiến hành công việc tiêm thuốc cho Bệnh nhân");
	}
}
