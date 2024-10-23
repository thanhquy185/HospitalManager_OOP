package MedicalRecord;

import Common.Date;

public class MedicalRecord {
    //	Properties
	protected String id;
	protected Date inputDay;
	protected Date outputDay;
	private static int countMedicalRecordCreated;

	// Static
	static {
		MedicalRecord.countMedicalRecordCreated = 0;
	}

    // Constructor
	public MedicalRecord() {
		this.id = "?";
		this.inputDay = new Date();
		this.outputDay = new Date();
	}
	public MedicalRecord(Date inputDay, Date outputDay) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = getFormatId();
		this.inputDay = inputDay;
		this.outputDay = outputDay;
	}
	public MedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord.countMedicalRecordCreated++;
		this.id = medicalRecord.getId();
		this.inputDay = medicalRecord.getInputDay();
		this.outputDay = medicalRecord.getOutputDay();
	}

	// Setter-Getter
	public void setId(String id) {
		if(isFormatId(id))
			this.id = id;
		this.id = "?";
	}
	public void setInputDay(Date inputDay) {
		this.inputDay = inputDay;
	}
	public void setOutputDay(Date outputDay) {
		this.outputDay = outputDay;
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
