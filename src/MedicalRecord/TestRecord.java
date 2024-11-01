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
	public TestRecord(Date inputDay, Date outputDay, String type,
			String idPatient, String idDoctor, String idNurse, String reason) {
		super(inputDay, outputDay, type, idPatient, idDoctor, idNurse);
		this.reason = reason;
		this.diagnose = null;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getId(), testRecord.getInputDay(), testRecord.getOutputDay(),
			testRecord.getType(), testRecord.getPatientID(),testRecord.getDoctorID(),
			testRecord.getNurseID(), testRecord.getSickID(), testRecord.getSickLevel(),
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
