package MedicalRecord;

import Common.Date;
import HealthcareWorker.Nurse;
public class TestRecord extends MedicalRecord {
    // Properties
	private Nurse nurse;
	private String reason;
	private String diagnose;

    // Constructor
	public TestRecord() {
		super();
		this.nurse = new Nurse();
		this.reason = "";
		this.diagnose = "";
	}
	public TestRecord(Date inputDay, Date outputDay, Nurse nurse, String reason, String diagnose) {
		super(inputDay, outputDay);
		this.nurse = nurse;
		this.reason = reason;
		this.diagnose = diagnose;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getInputDay(), testRecord.getOutputDay());
		this.nurse = new Nurse(testRecord.getNurse());
		this.reason = testRecord.getReason();
		this.diagnose = testRecord.getDiagnose();
	}

    // Setter - Getter
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public Nurse getNurse() {
		return this.nurse;
	}
	public String getReason() {
		return this.reason;
	}
	public String getDiagnose() {
		return this.diagnose;
	}

	// Methods
}
