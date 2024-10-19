package MedicalRecord;

import Common.Date;

public class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDay;
	protected Date outputDay;

    // Constructor
	public MedicalRecord() {
		id = "";
		inputDay = new Date();
		outputDay = new Date();
	}
	public MedicalRecord(String id, Date inputDay, Date outputDay) {
		this.id = id;
		this.inputDay = inputDay;
		this.outputDay = outputDay;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		this.id = medicalRecord.id;
		this.inputDay = medicalRecord.inputDay;
		this.outputDay = medicalRecord.outputDay;
	}

	// Setter-Getter
	public void setId(String id) {
		this.id = id;
	}
	public void setInputDay(Date inputDay) {
		this.inputDay = inputDay;
	}
	public void setOutputDay(Date outputDay) {
		this.outputDay = outputDay;
	}
	public String getId() {
		return id;
	}
	public Date getInputDay() {
		return inputDay;
	}
	public Date getOutputDay() {
		return outputDay;
	}

	// Methods
}
