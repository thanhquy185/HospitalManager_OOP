package Patient;

import Common.Date;

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
    // - Hàm khám cho Bệnh nhân
	@Override
	public void testPatient() {
		System.out.println(" - Bệnh nhân đã được khám xong. Sức khoẻ có lẽ có tiến triển hơn chút");
	}
	// - Hàm đưa khẩu phần ăn cho Bệnh nhân
	@Override
	public void giveFoodToPatient() {
		System.out.println(" - Bệnh nhân đã nhận được khẩu phần ăn loại Bình thường");
	}
	// - Hàm đưa thuốc uống cho Bệnh nhân (mức độ Bệnh: Nhẹ hoặc Vừa)
	@Override
	public void giveCurativeToPatient() {
		System.out.println(" - Bệnh nhân đã nhận được thuốc uống, nước lọc và một chút bánh ngọt");

	}
	// - Hàm tiêm thuốc cho Bệnh nhân (mức độ Bệnh: Nặng)
	@Override
	public void injectCurativePatient() {
		System.out.println(" - Bệnh nhân đã được tiêm thuốc xong. Bệnh nhân đang nghỉ ngơi");
	}
}
