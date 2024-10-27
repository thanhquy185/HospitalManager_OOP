package MedicalRecord;

import Common.Date;

public class TestRecord extends MedicalRecord {
    // Properties
	private String reason;
	private Boolean diagnose;

    // Constructor
	public TestRecord() {
		super();
		this.reason = null;
		this.diagnose = null;
	}
	public TestRecord(Date inputDay, Date outputDay, String idPatient,
			String idDoctor, String idNurse, boolean isTest, String reason) {
		super(inputDay, outputDay, idPatient, idDoctor, idNurse, isTest);
		this.reason = reason;
		this.diagnose = null;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getId(), testRecord.getInputDay(), testRecord.getOutputDay(),
			testRecord.getIdPatient(),testRecord.getIdDoctor(), testRecord.getIdNurse(),
			testRecord.getIdSick(), testRecord.getLevelSick(), testRecord.getIsTest(),
			testRecord.getPrice());
		this.reason = testRecord.getReason();
		this.diagnose = testRecord.getDiagnose();
	}

    // Setter - Getter
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setDiagnose(boolean diagnose) {
		this.diagnose = diagnose;
	}
	public String getReason() {
		return this.reason;
	}
	public boolean getDiagnose() {
		return this.diagnose;
	}

	// Methods
}
