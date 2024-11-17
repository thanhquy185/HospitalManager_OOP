package Patient;

import Common.Date;

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
    // - Hàm khám cho Bệnh nhân
	@Override
	public void testPatient() {
		System.out.println(" - Bệnh nhân đã được khám xong. Sức khoẻ có lẽ có tiến triển hơn chút");
	}
	// - Hàm đưa khẩu phần ăn cho Bệnh nhân
	@Override
	public void giveFoodToPatient() {
        System.out.println(" - Bệnh nhân đã nhận được khẩu phần ăn loại Cao cấp");
	}
	// - Hàm đưa thuốc uống cho Bệnh nhân (mức độ Bệnh: Nhẹ hoặc Vừa)
	@Override
	public void giveCurativeToPatient() {
        System.out.println(" - Bệnh nhân đã nhận được thuốc uống, trái cây, nước lọc, nước trái cây và một chút bánh");

	}
	// - Hàm tiêm thuốc cho Bệnh nhân (mức độ Bệnh: Nặng)
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Bệnh nhân đã được tiêm thuốc xong. Bệnh nhân đang nghỉ ngơi");
	}
}
