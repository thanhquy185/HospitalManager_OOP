package MedicalRecord;

import Common.Date;

public abstract class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDay;
	protected Date outputDay;
	protected String type;
	protected Double fee;
	protected String patientID;
	protected String sickID;
	protected String sickLevel;
	protected String healthcareWorkerID;
	private static int countMedicalRecordCreated;

	// Static
	static {
		MedicalRecord.countMedicalRecordCreated = 0;
	}

    // Constructor
	public MedicalRecord() {
		this.id = null;
		this.inputDay = new Date();
		this.outputDay = new Date();
		this.type = null;
		this.fee = null;
		this.patientID = null;
		this.sickID = null;
		this.sickLevel = null;
		this.healthcareWorkerID = null;
	}

	// public MedicalRecord(Date inputDay, Date outputDay, String type,
	// 		String patientID, String doctorID, String nurseID) {
	// 	MedicalRecord.countMedicalRecordCreated++;
	// 	this.id = getFormatId();
	// 	this.inputDay = inputDay;
	// 	this.outputDay = outputDay;
	// 	this.type = type;
	// 	this.patientID = patientID;
	// 	this.doctorID = doctorID;
	// 	this.nurseID = nurseID;
	// 	this.sickID = null;
	// 	this.sickLevel = null;
	// }
	
	public MedicalRecord(Date inputDay, Date outputDay, String type, String patientID,
			String sickID, String sickLevel, String healthcareWorkerID) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = getFormatId();
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.type = type;
		this.patientID = patientID;
		this.sickID = sickID;
		this.sickLevel = sickLevel;
		this.healthcareWorkerID = healthcareWorkerID;
	}
	public MedicalRecord(String id, Date inputDay, Date outputDay,
			String type, Double fee, String patientID, String sickID,
			String sickLevel, String healthcareWorkerID) {
		this.id = id;
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.type = type;
		this.fee = fee;
		this.patientID = patientID;
		this.sickID = sickID;
		this.sickLevel = sickLevel;
		this.healthcareWorkerID = healthcareWorkerID;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		this.id = medicalRecord.id;
		this.inputDay = medicalRecord.inputDay;
		this.outputDay = medicalRecord.outputDay;
		this.type = medicalRecord.type;
		this.fee = medicalRecord.fee;
		this.patientID = medicalRecord.patientID;
		this.sickID = medicalRecord.sickID;
		this.sickLevel = medicalRecord.sickLevel;
		this.healthcareWorkerID = medicalRecord.healthcareWorkerID;
	}

	// Setter-Getter
	public void setId(String id) {
		if(!isFormatId(id))
			this.id = null;
		this.id = id;
	}
	public void setInputDay(Date inputDay) {
		this.inputDay = inputDay;
	}
	public void setOutputDay(Date outputDay) {
		this.outputDay = outputDay;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public void setSickID(String sickID) {
		this.sickID = sickID;
	}
	public void setSickLevel(String sickLevel) {
		this.sickLevel = sickLevel;
	}
	public void setHealthcareWorkerID(String healthcareWorkerID) {
		this.healthcareWorkerID = healthcareWorkerID;
	}
	public static void setCountMedicalRecordCreated(int countMedicalRecordCreated) {
		MedicalRecord.countMedicalRecordCreated = countMedicalRecordCreated;
	}
	public String getId() {
		return this.id;
	}
	public Date getInputDay() {
		return this.inputDay;
	}
	public Date getOutputDay() {
		return this.outputDay;
	}
	public String getType() {
		return this.type;
	}
	public Double getFee() {
		return this.fee;
	}
	public String getPatientID() {
		return this.patientID;
	}
	public String getSickID() {
		return this.sickID;
	}
	public String getSickLevel() {
		return this.sickLevel;
	}
	public String getHealthcareWorkerID() {
		return this.healthcareWorkerID;
	}
	public static int getCountMedicalRecordCreated() {
		return MedicalRecord.countMedicalRecordCreated;
	}

	// Methods
	 // - Kiểm tra có đúng định dạng MERxxxxx
	private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 8)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("MER")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(3);
        for(int i = 0; i < postfix.length(); i++) {
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
	 // - Lấy id có đúng định dạng MERxxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", MedicalRecord.countMedicalRecordCreated);
        return "MER" + postfix;
    }
	// - Hàm lấy ra thông tinn của Bệnh án
    public String getInfo() {
        return this.id + " | " + this.inputDay.getDateFormatByCondition("has cross")
			+ " | " + this.outputDay.getDateFormatByCondition("has cross") + " | " + this.type
			+ " | " + this.fee + " | " + this.patientID + " | " + this.healthcareWorkerID 
			+ " | " + this.sickID + " | " + this.sickLevel;
    }
}
