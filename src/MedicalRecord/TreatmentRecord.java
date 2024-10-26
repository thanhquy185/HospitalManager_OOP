package MedicalRecord;

import Common.Date;

public class TreatmentRecord extends MedicalRecord {
    //	Properties
	private String idPatient;
	private String idDoctor;
	private String idNurse;

    // Constructor
	public TreatmentRecord() {
		super();
		this.idPatient = null;
		this.idDoctor = null;
		this.idNurse = null;
	}
	public TreatmentRecord(Date inputDay, Date outputDay, String idSick, String levelSick,
			boolean isTest, String idPatient, String idDoctor, String idNurse) {
		super(inputDay, outputDay, idSick, levelSick, isTest);
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
		this.idNurse = idNurse;
	}
	public TreatmentRecord(TreatmentRecord treatmentRecord) {
		super(treatmentRecord.getId(), treatmentRecord.getInputDay(),
			treatmentRecord.getOutputDay(), treatmentRecord.getIdSick(),
			treatmentRecord.getLevelSick(), treatmentRecord.getIsTest(),
			treatmentRecord.getPrice());
		this.idPatient = treatmentRecord.getIdPatient();
		this.idDoctor = treatmentRecord.getIdDoctor();
		this.idNurse = treatmentRecord.getIdNurse();
	}

    // Setter - Getter
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}
	public void setIdNurse(String idNurse) {
		this.idNurse = idNurse;
	}
	public String getIdPatient() {
		return this.idPatient;
	}
	public String getIdDoctor() {
		return this.idDoctor;
	}
	public String getIdNurse() {
		return this.idNurse;
	}

	//Methods
}