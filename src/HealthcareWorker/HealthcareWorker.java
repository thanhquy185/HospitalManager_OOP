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
    protected Boolean isWorking;
    protected String idDepartment;
    protected Boolean isManagerDepartment;
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
        this.isWorking = null;
        this.idDepartment = null;
        this.isManagerDepartment = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String country, String phone,
            String type, int yearsOfExperience, double salary, boolean isWorking, String idDepartment) {
        super(fullname, birthday, gender, country, phone);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.type = type;
        this.id = getFormatId();
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.isWorking = isWorking;
        this.idDepartment = idDepartment;
        this.isManagerDepartment = null;
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String country,
            String phone, String id, String type, int yearsOfExperience, double salary,
            boolean isWorking, String idDepartment, boolean isManagerDepartment) {
        super(fullname, birthday, gender, country, phone);
        this.id = id;
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.isWorking = isWorking;
        this.idDepartment = idDepartment;
        this.isManagerDepartment = isManagerDepartment;
    }
    public HealthcareWorker(HealthcareWorker healthcareWorker) {
        super(healthcareWorker.fullname, healthcareWorker.birthday, healthcareWorker.gender, healthcareWorker.country, healthcareWorker.phone);
        this.id = healthcareWorker.id;
        this.type = healthcareWorker.type;
        this.yearsOfExperience = healthcareWorker.yearsOfExperience;
        this.salary = healthcareWorker.salary;
        this.isWorking = healthcareWorker.isWorking;
        this.idDepartment = healthcareWorker.idDepartment;
        this.isManagerDepartment = healthcareWorker.isManagerDepartment;
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
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }
    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    public void setIsManagerDepartment(boolean isManagerDepartment) {
        this.isManagerDepartment = isManagerDepartment;
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
    public boolean getIsWorking() {
        return this.isWorking;
    }
    public String getIdDepartment() {
        return this.idDepartment;
    }
    public boolean getIsManagerDepartment() {
        return this.isManagerDepartment;
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
}
