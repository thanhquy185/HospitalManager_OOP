package MedicalRecord;

import Common.Date;

public class TestRecord extends MedicalRecord {
    // Properties
	private String idPatient;
	private String idNurse;
	private String reason;
	private String diagnose;

    // Constructor
	public TestRecord() {
		super();
		this.idPatient = null;
		this.idNurse = null;
		this.reason = null;
		this.diagnose = null;
	}
	public TestRecord(Date inputDay, Date outputDay, String levelSick, String idSick,
			boolean isTest, String idPatient, String idNurse, String reason, String diagnose) {
		super(inputDay, outputDay, idSick, levelSick, isTest);
		this.idPatient = idPatient;
		this.idNurse = idNurse;
		this.reason = reason;
		this.diagnose = diagnose;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getId(), testRecord.getInputDay(), testRecord.getOutputDay(),
			testRecord.getIdSick(), testRecord.getLevelSick(), testRecord.getIsTest(),
			testRecord.getPrice());
		this.idPatient = testRecord.getIdPatient();
		this.idNurse = testRecord.getIdNurse();
		this.reason = testRecord.getReason();
		this.diagnose = testRecord.getDiagnose();
	}

    // Setter - Getter
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public void setIdNurse(String idNurse) {
		this.idNurse = idNurse;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public String getIdPatient() {
		return this.idPatient;
	}
	public String getIdNurse() {
		return this.idNurse;
	}
	public String getReason() {
		return this.reason;
	}
	public String getDiagnose() {
		return this.diagnose;
	}

	// Methods
}
