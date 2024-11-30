package Patient;

import Common.Date;
import MedicalRecord.MedicalRecord;
import MedicalRecord.MedicalRecordManager;

public class PremiumPatient extends Patient {
    //Properties

    //Constructors
    public PremiumPatient() {
        super();
    }
    public PremiumPatient(String fullname, Date birthday,
            String gender, String phone, String country, String type) {
        super(fullname, birthday, gender, phone, country, type);
    }
    public PremiumPatient(String fullname, Date birthday, String gender,
            String phone, String country, String id, String type, String idMedicalRecord) {
        super(fullname, birthday, gender, phone, country, id, type, idMedicalRecord);
    }
    public PremiumPatient(PremiumPatient premiumPatient) {
        super(premiumPatient.getFullname(), premiumPatient.getBirthday(),
            premiumPatient.getGender(), premiumPatient.getPhone(), premiumPatient.getCountry(),
            premiumPatient.getId(),premiumPatient.getType(), premiumPatient.getMedicalRecordID());
    }

    //Methods
	@Override
	public void giveCurativeToPatient() {
        MedicalRecord currentMedicalRecord = MedicalRecordManager.getInstance().findObjectById(this.medicalRecordID);
        if(currentMedicalRecord.getType().equals("Chữa bệnh") 
                && (currentMedicalRecord.getSickLevel().equals("Nhẹ") || currentMedicalRecord.getSickLevel().equals("Vừa"))) {
            System.out.println(" - Bệnh nhân " + this.fullname + " đã nhận được thuốc uống, trái cây, nước lọc, nước trái cây và một chút bánh");
        } else {
            System.out.println(" - Bệnh nhân " + this.fullname + " không làm gì và tiếp tục nghỉ ngơi");
        }
	}
}
