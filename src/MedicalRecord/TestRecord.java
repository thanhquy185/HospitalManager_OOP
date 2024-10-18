package MedicalRecord;

    // Chứa thông tin về y tá, lý do và chẩn đoán
public class TestRecord extends MedicalRecord {
    //	Properties
	private Nurse nurse;
	private String reason;
	private String diagnose;

    // Constructor
	public TestRecord() {
		nurse = new Nurse();
		reason = "";
		diagnose = "";
	}

	public TestRecord(Nurse nurse, String reason, String diagnose) {
		this.nurse = nurse;
		this.reason = reason;
		this.diagnose = diagnose;
	}
	public TestRecord(TestRecord testRecord)
	{
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
		return this.nurse;
	}

	public String getReason() {
		return this.reason;
	}

	public String getDiagnose() {
		return this.diagnose;
	}
}
