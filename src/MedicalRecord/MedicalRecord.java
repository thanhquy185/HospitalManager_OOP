package MedicalRecord;

    // Chứa thông tin về ngày nhập viện và ngày xuất viện
public class MedicalRecord {
    //	Properties
	protected String id; // Unique: duy nhất
	protected Date inputDate; // Ngày nhập viện
	protected Date outputDate; // Ngày xuất viện
    // Constructor
	public MedicalRecord() {
		id = "";
		inputDate = new Date();
		outputDate = new Date();
	}

	public MedicalRecord(Date inputDate, Date outputDate) {
		this.id = randomID();
		this.inputDate = inputDate;
		this.outputDate = outputDate;
	}
	public MedicalRecord(MedicalRecord medicalrecord)
	{
		this.id = medicalrecord.id;
		this.inputDate = medicalrecord.inputDate;
		this.outputDate = medicalrecord.outputDate;
	}
    // Setter-Getter
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public void setId(String id) {
		this.id = id;
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

    //	tiento-5songaunhien( 5 số ngẫu nhiên bắt đầu: 00000, nếu 2 phần tử đầu tiên trùng lặp => 00000++)
	public String randomID() {
		String id = "";
		return id;
	}
}
