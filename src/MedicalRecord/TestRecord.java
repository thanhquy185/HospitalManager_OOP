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
