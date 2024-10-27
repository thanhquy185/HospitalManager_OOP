package HealthcareWorker;

import Common.Person;
import Common.Date;

// HealthcareWorker class
public abstract class HealthcareWorker extends Person {
    //Properties
    protected String id;
    protected String type;
    protected Integer yearsOfExperience;
    protected Double salary;
    protected String idDepartment;
    protected Boolean isManagerDepartment;
    protected String idMedicalRecord;
    private static int countHealthcareWorkerCreated;

    // Static
    static {
        HealthcareWorker.countHealthcareWorkerCreated = 0;
    }

    // Constructor
    public HealthcareWorker() {
        super();
        this.id = null;
        this.type = null;
        this.yearsOfExperience = null;
        this.salary = null;
        this.idDepartment = null;
        this.isManagerDepartment = null;
        this.idMedicalRecord = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String phone,
            String country, String type, int yearsOfExperience, double salary, String idDepartment) {
        super(fullname, birthday, gender, phone, country);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.type = type;
        this.id = getFormatId();
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.idDepartment = idDepartment;
        this.isManagerDepartment = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String phone,
            String country, String id, String type, int yearsOfExperience, double salary,
            String idDepartment, boolean isManagerDepartment, String idMedicalRecord) {
        super(fullname, birthday, gender, phone, country);
        this.id = id;
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.idDepartment = idDepartment;
        this.isManagerDepartment = isManagerDepartment;
        this.idMedicalRecord = idMedicalRecord;
    }
    public HealthcareWorker(HealthcareWorker healthcareWorker) {
        super(healthcareWorker.fullname, healthcareWorker.birthday,
            healthcareWorker.gender, healthcareWorker.phone, healthcareWorker.country);
        this.id = healthcareWorker.id;
        this.type = healthcareWorker.type;
        this.yearsOfExperience = healthcareWorker.yearsOfExperience;
        this.salary = healthcareWorker.salary;
        this.idDepartment = healthcareWorker.idDepartment;
        this.isManagerDepartment = healthcareWorker.isManagerDepartment;
        this.idMedicalRecord = healthcareWorker.idMedicalRecord;
    }

    // Setter - Getter
    public void setId(String id) {
        if(!isFormatId(id))
            this.id = null;
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    public void setIsManagerDepartment(Boolean isManagerDepartment) {
        if(isManagerDepartment == null)
            this.isManagerDepartment = null;
        this.isManagerDepartment = (boolean) isManagerDepartment;
    }
    public void setIdMedicalRecord(String idMedicalRecord) {
        this.idMedicalRecord = idMedicalRecord;
    }
    public static void setCountHealthcareWorkerCreated(int countHealthcareWorkerCreated) {
        HealthcareWorker.countHealthcareWorkerCreated = countHealthcareWorkerCreated;
    }
    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.type;
    }
    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }
    public double getSalary() {
        return this.salary;
    }
    public String getIdDepartment() {
        return this.idDepartment;
    }
    public Boolean getIsManagerDepartment() {
        return this.isManagerDepartment;
    }
    public String getIdMedicalRecord() {
        return this.idMedicalRecord;
    }
    public static int getCountHealthcareWorkerCreated() {
        return HealthcareWorker.countHealthcareWorkerCreated;
    }

    // Methods
    // - Kiểm tra có đúng định dạng (DOC/NUR)xxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 8)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("DOC")
            && !prefix.equals("NUR")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(3);
        for(int i = 0; i < postfix.length(); i++) {
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
    // - Lấy id có đúng định dạng (DOC/NUR)xxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", HealthcareWorker.countHealthcareWorkerCreated);
        if(this.type.equals(("Bác sĩ"))){
            return "DOC" + postfix;
        }
        return "NUR" + postfix;
    }
    // - Hàm lấy ra thông tinnn của Bệnh nhân
    public String getInfo() {
        return null;
    }
}
