package Patient;

import Common.Date;
import MedicalRecord.MedicalRecord;
import MedicalRecord.MedicalRecordManager;

public class NormalPatient extends Patient {
    //Properties

    //Constructors
    public NormalPatient() {
        super();
    }
    public NormalPatient(String fullname, Date birthday,
            String gender, String phone, String country, String type) {
        super(fullname, birthday, gender, phone, country, type);
    }
    public NormalPatient(String fullname, Date birthday, String gender,
            String phone, String country, String id, String type, String idMedicalRecord) {
        super(fullname, birthday, gender, phone, country, id, type, idMedicalRecord);
    }
    public NormalPatient(NormalPatient normalPatient) {
        super(normalPatient.getFullname(), normalPatient.getBirthday(),
            normalPatient.getGender(), normalPatient.getPhone(), normalPatient.getCountry(),
            normalPatient.getId(),normalPatient.getType(), normalPatient.getMedicalRecordID());
    }

    //Methods
	@Override
	public void giveCurativeToPatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh") 
                && (currentMedicalRecord.getSickLevel().equals("Nhẹ") || currentMedicalRecord.getSickLevel().equals("Vừa"))) {
		    System.out.println(" - Bệnh nhân " + this.fullname + " đã nhận được thuốc uống, nước lọc");
        } else {
            System.out.println(" - Bệnh nhân " + this.fullname + " không làm gì và tiếp tục nghỉ ngơi");
        }
	}
}
