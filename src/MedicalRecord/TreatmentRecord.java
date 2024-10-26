package MedicalRecord;

import Common.Date;

public class TreatmentRecord extends MedicalRecord {
    //	Properties

    // Constructor
	public TreatmentRecord() {
		super();
	}
	public TreatmentRecord(Date inputDay, Date outputDay, String idSick, String levelSick,
			boolean isTest, String idPatient, String idDoctor, String idNurse) {
		super(inputDay, outputDay, idPatient, idDoctor, idNurse, idSick, levelSick, isTest);
	}
	public TreatmentRecord(TreatmentRecord treatmentRecord) {
		super(treatmentRecord.getId(), treatmentRecord.getInputDay(), treatmentRecord.getOutputDay(),
			treatmentRecord.getIdPatient(),treatmentRecord.getIdDoctor(), treatmentRecord.getIdNurse(),
			treatmentRecord.getIdSick(), treatmentRecord.getLevelSick(), treatmentRecord.getIsTest(),
			treatmentRecord.getPrice());
	}

    // Setter - Getter

	//Methods
}