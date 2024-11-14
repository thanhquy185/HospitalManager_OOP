package Common;

public interface ActionsInHospital {
    // Khám các Bệnh nhân
    void testPatient();

    // Đưa các Bệnh nhân khẩu phần ăn
    void giveFoodToPatient();

    // Đưa các Bệnh nhân thuốc uống (mức độ Bệnh: Nhẹ hoặc Vừa)
    void giveCurativeToPatient();

    // Tiêm thuốc cho các Bệnh nhân (mức độ Bệnh: Nặng)
    void injectCurativePatient();
}
