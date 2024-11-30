package Common;

public interface ActionsInHospital {
    // Khám các Bệnh nhân (có thể là khám bệnh hoặc chữa bệnh)
    void testPatient();

    // Đưa các Bệnh nhân khẩu phần ăn (đang chữa bệnh)
    void giveFoodToPatient();

    // Đưa các Bệnh nhân thuốc uống (đang chữa bệnh, mức độ bệnh: Nhẹ hoặc Vừa)
    void giveCurativeToPatient();

    // Tiêm thuốc cho các Bệnh nhân (đang chữa bệnh, mức độ bệnh: Nặng)
    void injectCurativePatient();
}
