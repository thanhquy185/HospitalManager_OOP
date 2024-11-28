package MedicalRecord;

import Common.Date;
import HealthcareWorker.HealthcareWorkerManager;
import Patient.PatientManager;

public class TreatmentRecord extends MedicalRecord {
    // Properties

    // Constructor
	public TreatmentRecord() {
		super();
	}
	public TreatmentRecord(Date inputDay, Date outputDay, String type, String patientID, 
			String sickID, String sickLevel, String healthcareWorkerID) {
		super(inputDay, outputDay, type, patientID, sickID, sickLevel, healthcareWorkerID);
	}
	public TreatmentRecord(String id, Date inputDay, Date outputDay, String type, 
			Double fee, String patientID, String sickID, String sickLevel, String healthcareWorkerID) {
		super(id, inputDay, outputDay, type, fee, patientID, sickID, sickLevel, healthcareWorkerID);
	}
	public TreatmentRecord(TreatmentRecord treatmentRecord) {
		super(treatmentRecord.getId(), treatmentRecord.getInputDay(), treatmentRecord.getOutputDay(),
			treatmentRecord.getType(), treatmentRecord.getFee(), treatmentRecord.getPatientID(),
			treatmentRecord.getSickID(), treatmentRecord.getSickLevel(), treatmentRecord.getHealthcareWorkerID());
	}

    // Setter - Getter

	// Methods
	@Override
	public double calFee() {
		double basicFee = 100, feeCoefficient = 0;
		// Là chữa bệnh (có thể chữa nhiều ngày)
		feeCoefficient += 0.8 * Date.getInstance().calNumbersOfDay(inputDay, outputDay);
		// Tuỳ theo việc loại chăm sóc Bệnh nhân là Bình thường hay Cao cấp thì hệ số phí sẽ khác
		if(PatientManager.getInstance().findObjectById(this.patientID).getType().equals("Bình thường")) feeCoefficient += 0.5;
		else if(PatientManager.getInstance().findObjectById(this.patientID).getType().equals("Cao cấp")) feeCoefficient += 2;
		// Mặc định các loại Bệnh khác nhau đều có chung một hệ số phí (phức tạp quá nên Quy không dám làm sợ sai ngữ nghĩa)
		feeCoefficient += 1;
		// Tuỳ theo mức độ của Bệnh mà hệ số phí sẽ khác
		if(this.sickLevel.equals("Nhẹ")) {
			feeCoefficient += 0.2;
		} else if(this.sickLevel.equals("Vừa")) {
			feeCoefficient += 0.5;
		} else if(this.sickLevel.equals("Nặng")) {
			feeCoefficient += 0.8;
		}
		return basicFee + basicFee * feeCoefficient;
	}
	@Override
	public void testPatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		System.out.println(" - Việc kiểm tra hoàn tất. Tiến hành công việc khám cho Bệnh nhân "
			+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
	}
	@Override
	public void giveFoodToPatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		System.out.println(" - Việc kiểm tra hoàn tất. Tiến hành công việc đưa khẩu phần ăn cho Bệnh nhân "
			+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
	}
	@Override
	public void giveCurativeToPatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		if(this.sickLevel.equals("Nhẹ") || this.sickLevel.equals("Vừa"))
			System.out.println(" - Việc kiểm tra hoàn tất. Tiến hành công việc đưa thuốc uống cho Bệnh nhân "
				+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
		else System.out.println(" - Việc kiểm tra hoàn tất. Không thể tiến hành công việc đưa thuốc uống cho Bệnh nhân "
				+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
	}
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Hồ sơ Bệnh án " + "(" + this.id + ", " + this.type + ", " + this.sickID + ", " + this.sickLevel + ")" + " đang được kiểm tra bởi Nhân viên Y tế "
			+ this.healthcareWorkerID + " - " + HealthcareWorkerManager.getInstance().findObjectById(this.healthcareWorkerID).getFullname());
		if(this.sickLevel.equals("Nặng"))
			System.out.println(" - Việc kiểm tra hoàn tất. Tiến hành công việc tiêm thuốc cho Bệnh nhân "
				+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
		else System.out.println(" - Việc kiểm tra hoàn tất. Không thể tiến hành công việc tiêm thuốc cho Bệnh nhân "
				+ this.patientID + " - " + PatientManager.getInstance().findObjectById(this.patientID).getFullname());
	}
}