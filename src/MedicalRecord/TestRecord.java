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
		nurse = new Nurse();
		reason = "";
		diagnose = "";
	}
	public TestRecord(String id, Date inputDay, Date outputDay, Nurse nurse, String reason, String diagnose) {
		super(id, inputDay, outputDay);
		this.nurse = nurse;
		this.reason = reason;
		this.diagnose = diagnose;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.id, testRecord.inputDay, testRecord.outputDay);
		this.nurse = new Nurse(testRecord.nurse);
		this.reason = testRecord.reason;
		this.diagnose = testRecord.diagnose;
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
		return nurse;
	}
	public String getReason() {
		return reason;
	}
	public String getDiagnose() {
		return diagnose;
	}

	// Methods
}
