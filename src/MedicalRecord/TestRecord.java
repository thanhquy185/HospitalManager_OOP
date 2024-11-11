package MedicalRecord;

import Common.Date;

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
}
