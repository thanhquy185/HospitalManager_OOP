package MedicalRecord;

import Common.Date;

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
}