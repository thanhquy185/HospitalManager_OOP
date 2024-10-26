package MedicalRecord;

import Common.Date;

public abstract class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDay;
	protected Date outputDay;
	protected String idPatient;
	protected String idDoctor;
	protected String idNurse;
	protected String idSick;
	protected String levelSick;
	protected Boolean isTest;
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
		this.isTest = null;
		this.idPatient = null;
		this.idDoctor = null;
		this.idNurse = null;
		this.idSick = null;
		this.levelSick = null;
		this.price = null;
	}
	public MedicalRecord(Date inputDay, Date outputDay, String idPatient,
			String idDoctor, String idNurse, String idSick, String levelSick, boolean isTest) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = getFormatId();
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
		this.idNurse = idNurse;
		this.idSick = idSick;
		this.levelSick = levelSick;
		this.isTest = isTest;
	}
	public MedicalRecord(String id, Date inputDay, Date outputDay,
			String idPatient, String idDoctor, String idNurse, String idSick, 
			String levelSick, boolean isTest, double price) {
		this.id = id;
		this.inputDay = inputDay;
		this.outputDay = outputDay;
		this.idDoctor = idDoctor;
		this.idNurse = idNurse;
		this.idSick = idSick;
		this.idSick = idSick;
		this.levelSick = levelSick;
		this.isTest = isTest;
		this.price = price;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		this.id = medicalRecord.id;
		this.inputDay = medicalRecord.inputDay;
		this.outputDay = medicalRecord.outputDay;
		this.idPatient = medicalRecord.idPatient;
		this.idDoctor = medicalRecord.idDoctor;
		this.idNurse = medicalRecord.idNurse;
		this.idSick = medicalRecord.idSick;
		this.levelSick = medicalRecord.levelSick;
		this.isTest = medicalRecord.isTest;
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
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}
	public void setIdNurse(String idNurse) {
		this.idNurse = idNurse;
	}
	public void setIdSick(String idSick) {
		this.idSick = idSick;
	}
	public void setLevelSick(String levelSick) {
		this.levelSick = levelSick;
	}
	public void setIsTest(boolean isTest) {
		this.isTest = isTest;
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
	public String getIdPatient() {
		return this.idPatient;
	}
	public String getIdDoctor() {
		return this.idDoctor;
	}
	public String getIdNurse() {
		return this.idNurse;
	}
	public String getIdSick() {
		return this.idSick;
	}
	public String getLevelSick() {
		return this.levelSick;
	}
	public boolean getIsTest() {
		return this.isTest;
	}
	public double getPrice() {
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
}
