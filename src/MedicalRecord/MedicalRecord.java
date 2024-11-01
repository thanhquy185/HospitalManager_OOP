package MedicalRecord;

import Common.Date;

public abstract class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDay;
	protected Date outputDay;
	protected String type;
	protected String patientID;
	protected String doctorID;
	protected String nurseID;
	protected String sickID;
	protected String sickLevel;
	protected Double price;
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
		this.patientID = null;
		this.doctorID = null;
		this.nurseID = null;
		this.sickID = null;
		this.sickLevel = null;
		this.price = null;
	}
	// Tạo Hồ sơ Khám bệnh
	public MedicalRecord(Date inputDay, Date outputDay, String type,
			String patientID, String doctorID, String nurseID) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = getFormatId();
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.type = type;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.nurseID = nurseID;
		this.sickID = null;
		this.sickLevel = null;
	}
	// Tạo Hồ sơ Chữa bệnh
	public MedicalRecord(Date inputDay, Date outputDay, String type, String patientID,
			String doctorID, String nurseID, String sickID, String sickLevel) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = getFormatId();
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.type = type;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.nurseID = nurseID;
		this.sickID = sickID;
		this.sickLevel = sickLevel;
	}
	public MedicalRecord(String id, Date inputDay, Date outputDay,
			String type, String patientID, String doctorID, String nurseID,
			String sickID, String sickLevel, double price) {
		this.id = id;
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.type = type;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.nurseID = nurseID;
		this.sickID = sickID;
		this.sickID = sickID;
		this.sickLevel = sickLevel;
		this.price = price;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		this.id = medicalRecord.id;
		this.inputDay = medicalRecord.inputDay;
		this.outputDay = medicalRecord.outputDay;
		this.type = type;
		this.patientID = medicalRecord.patientID;
		this.doctorID = medicalRecord.doctorID;
		this.nurseID = medicalRecord.nurseID;
		this.sickID = medicalRecord.sickID;
		this.sickLevel = medicalRecord.sickLevel;
		this.price = medicalRecord.price;
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
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public void setNurseID(String nurseID) {
		this.nurseID = nurseID;
	}
	public void setSickID(String sickID) {
		this.sickID = sickID;
	}
	public void setSickLevel(String sickLevel) {
		this.sickLevel = sickLevel;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getPatientID() {
		return this.patientID;
	}
	public String getDoctorID() {
		return this.doctorID;
	}
	public String getNurseID() {
		return this.nurseID;
	}
	public String getSickID() {
		return this.sickID;
	}
	public String getSickLevel() {
		return this.sickLevel;
	}
	public Double getPrice() {
		return this.price;
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
	// - Hàm lấy ra thông tinnn của Bệnh án
    public String getInfo() {
        return null;
    }
}
