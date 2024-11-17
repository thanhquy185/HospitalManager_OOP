package MedicalRecord;

import java.util.Scanner;

import Common.*;
import Sick.*;
import HealthcareWorker.HealthcareWorker;
import HealthcareWorker.HealthcareWorkerManager;

public abstract class MedicalRecord implements ActionsInHospital {
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
		this.fee = calFee();
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
	// - Hàm tính tiền phí của một Bệnh án
	public abstract double calFee();
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
	// - Hàm gán thông tin cho Bệnh án
	public void setInfoWithNoPatient(String condition, Date patientBirthday) {
		Scanner sc = new Scanner(System.in);
		// Nhập ngày lập Hồ sơ Bệnh án
        System.out.print(" - Nhập ngày lập Hồ sơ Bệnh án (dd-mm-yyyy hoặc ddmmyyyy): ");
        String inputDayStr = sc.nextLine();
        while(!Date.getInstance().isDateFormat(inputDayStr)
                || !Date.getInstance().getDateFromDateFormat(inputDayStr).isDate()
				|| !Date.getInstance().checkBeforeAfterDay(patientBirthday, Date.getInstance().getDateFromDateFormat(inputDayStr))) {
            System.out.println("----- -----");
            System.out.println("! - NGÀY LẬP HỒ SƠ BỆNH ÁN KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
            inputDayStr = sc.nextLine();
           System.out.println("----- -----");
        }
        Date inputDayObj = Date.getInstance().getDateFromDateFormat(inputDayStr);
        // Nhập ngày khoá Hồ sơ Bệnh án
		// - Nếu là Khám bệnh thì mặc định ngày mở bằng ngày đóng
		// - Nếu là Chữa bệnh thì cho phép chọn ngày đóng (ngày đóng phải sau ngày mở)
		String outputDayStr = inputDayStr;
		if(condition.equals("is not test")) {
			System.out.print(" - Nhập ngày khoá Hồ sơ Bệnh án (dd-mm-yyyy hoặc ddmmyyyy): ");
			outputDayStr = sc.nextLine();
			while(!Date.getInstance().isDateFormat(outputDayStr)
					|| !Date.getInstance().getDateFromDateFormat(outputDayStr).isDate()
					|| !Date.getInstance().checkBeforeAfterDay(inputDayObj, Date.getInstance().getDateFromDateFormat(outputDayStr))) {
				System.out.println("----- -----");
				System.out.println("! - NGÀY KHOÁ HỒ SƠ BỆNH ÁN KHÔNG HỢP LỆ");
				System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
				outputDayStr = sc.nextLine();
			   System.out.println("----- -----");
			}
		}
        Date outputDayObj = Date.getInstance().getDateFromDateFormat(outputDayStr);
        // Chọn Bệnh cho Bệnh nhân đang mắc phải
        System.out.println(" - Chọn loại Bệnh)");
        int numberList1 = 0;
        for(Sick sick : SickManager.getInstance().getList()) {
            System.out.println(++numberList1 + " - " + sick.getId() + " | " + sick.getName());
        }
        System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh): ");
        String info1 = sc.nextLine();
        while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
                    && SickManager.getInstance().findObjectById(info1) == null)
                || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
                    && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info1) - 1) == null)) {
            System.out.println("----- -----");
            System.out.println("! - BỆNH KHÔNG HỢP LỆ");
            System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
            info1 = sc.nextLine();
            System.out.println("----- -----");
        }
        String sickID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
            ? SickManager.getInstance().findObjectById(info1).getId()
            : SickManager.getInstance().findObjectByIndex(Integer.parseInt(info1) - 1).getId();
        // Nhập mức độ Bệnh cho Bệnh vừa chọn
        System.out.print(" - Nhập mức độ Bệnh (Nhẹ, Vừa hoặc Nặng): ");
        String sickLevel = sc.nextLine();
        while(!sickLevel.equals("Nhẹ") && !sickLevel.equals("Vừa") && !sickLevel.equals("Nặng")) {
            System.out.println("----- -----");
            System.out.println("! - MỨC ĐỘ BỆNH KHÔNG HỢP LỆ");
            System.out.print("?! - Chọn lại: ");
            sickLevel = sc.nextLine();
            System.out.println("----- -----");
        }
        // Chọn Nhân viên Y tế khám/chữa cho Bệnh nhân này (vì phải biết Bệnh mới biết cần Nhân viên thuộc Khoa nào khám)
        // - Nếu là Khám bệnh thì có thể là Bác sĩ hoặc Y tá khám
        // - Nếu là Chữa bệnh thì chỉ có thể Bác sĩ chữa
        System.out.println(" - Chọn Nhân viên Y tế");
        int numberList2 = 0;
		int healthcareWorkerFiltered = 0;
        if(condition.equals("is test")) {
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                if(healthcareWorker.getMedicalRecordID() == null
                        && SickManager.getInstance().findObjectById(sickID).getDepartmentID().equals(healthcareWorker.getDepartmentID())) {
                    System.out.println((numberList2 + 1) + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
					healthcareWorkerFiltered++;
                }
                numberList2++;
            }
        } else if(condition.equals("is not test")) {
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                if(healthcareWorker.getType().equals("Bác sĩ") && healthcareWorker.getMedicalRecordID() == null
                        && SickManager.getInstance().findObjectById(sickID).getDepartmentID().equals(healthcareWorker.getDepartmentID())) {
                    System.out.println((numberList2 + 1) + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
					healthcareWorkerFiltered++;
                }
                numberList2++;
            }
        }
		if(healthcareWorkerFiltered >= 1) {
			System.out.print("? - Chọn (số thứ tự hoặc mã Nhân viên Y tế): ");
			String info2 = sc.nextLine();
			while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
						&& (HealthcareWorkerManager.getInstance().findObjectById(info2) == null
							|| !HealthcareWorkerManager.getInstance().findObjectById(info2).getDepartmentID().equals(
									SickManager.getInstance().findObjectById(sickID).getDepartmentID()
								)))
					|| (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
						&& HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info2) - 1) == null)) {
				System.out.println("----- -----");
				System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
				System.out.print("?! - Chọn lại (số thứ tự hoặc mã Nhân viên Y tế): ");
				info2 = sc.nextLine();
				System.out.println("----- -----");
			}
			String healthcareWorkerID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
				? HealthcareWorkerManager.getInstance().findObjectById(info2).getId()
				: HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info2) - 1).getId();

			// Gán dữ liệu đã nhập cho Bệnh án
			countMedicalRecordCreated++;
			this.id = getFormatId();
			this.inputDay = inputDayObj;
			this.outputDay = outputDayObj;
			this.type = condition.equals("is test") ? "Khám bệnh" : "Chữa bệnh";
			this.sickID = sickID;
			this.sickLevel = sickLevel;
			this.healthcareWorkerID = healthcareWorkerID;
		}
	}
	// - Hàm lấy ra thông tin của Bệnh án
    public String getInfo() {
        return this.id + " | " + this.inputDay.getDateFormatByCondition("has cross")
			+ " | " + this.outputDay.getDateFormatByCondition("has cross") + " | " + this.type
			+ " | " + this.fee + " | " + this.patientID + " | " + this.healthcareWorkerID 
			+ " | " + this.sickID + " | " + this.sickLevel;
    }
}
