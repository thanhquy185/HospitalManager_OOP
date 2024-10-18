package MedicalRecord;

import Common.Date;

public class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDate;
	protected Date outputDate;

    // Constructor
	public MedicalRecord() {
		id = "";
		inputDate = new Date();
		outputDate = new Date();
	}
	public MedicalRecord(String id, Date inputDate, Date outputDate) {
		this.id = id;
		this.inputDate = inputDate;
		this.outputDate = outputDate;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		this.id = medicalRecord.id;
		this.inputDate = medicalRecord.inputDate;
		this.outputDate = medicalRecord.outputDate;
	}

	// Setter-Getter
	public void setId(String id) {
		this.id = id;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public void setOutputDate(Date outputDate) {
		this.outputDate = outputDate;
	}
	public String getId() {
		return this.id;
	}
	public Date getInputDate() {
		return this.inputDate;
	}
	public Date getOutputDate() {
		return this.outputDate;
	}

	// Methods
}
