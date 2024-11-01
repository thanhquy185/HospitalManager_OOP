package MedicalRecord;

import Common.Date;

public class TreatmentRecord extends MedicalRecord {
    //	Properties

    // Constructor
	public TreatmentRecord() {
		super();
	}
	public TreatmentRecord(Date inputDay, Date outputDay, String type,
			String idPatient, String idDoctor, String idNurse, String idSick, String levelSick) {
		super(inputDay, outputDay, type, idPatient, idDoctor, idNurse, idSick, levelSick);
	}
	public TreatmentRecord(TreatmentRecord treatmentRecord) {
		super(treatmentRecord.getId(), treatmentRecord.getInputDay(), treatmentRecord.getOutputDay(),
			treatmentRecord.getType(), treatmentRecord.getPatientID(),treatmentRecord.getDoctorID(),
			treatmentRecord.getNurseID(), treatmentRecord.getSickID(), treatmentRecord.getSickLevel(),
			treatmentRecord.getPrice());
	}
    // Setter - Getter

	//Methods
}