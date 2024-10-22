package HealthcareWorker;

import Common.Date;
import Common.Person;

// HealthcareWorker class
public class HealthcareWorker extends Person {
    //Properties
    protected String id;
    protected String department;
    protected String position;
    protected int yearsOfExperience;
    protected double salary;
    private static int countHealthcareWorkerCreated;

    // Static
    static {
        HealthcareWorker.countHealthcareWorkerCreated = 0;
    }

    // Constructor
    public HealthcareWorker() {
        super();
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String country, String phone,
                            String id, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, country, phone);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.department = department;
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.id = getFormatId();
    }
    public HealthcareWorker(HealthcareWorker healthcareWorker) {
        super(healthcareWorker.fullname, healthcareWorker.birthday, healthcareWorker.gender, healthcareWorker.country, healthcareWorker.phone);
        HealthcareWorker.countHealthcareWorkerCreated++;
        this.id = healthcareWorker.getId();
        this.department = healthcareWorker.getDepartment();
        this.position = healthcareWorker.getPosition();
        this.yearsOfExperience = healthcareWorker.getYearsOfExperience();
        this.salary = healthcareWorker.getSalary();
    }

    // Setter - Getter
    public void setId(String id) {
        if(isFormatId(id))
            this.id = id;
        this.id = "?";
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getId() {
        return this.id;
    }
    public String getDepartment() {
        return this.department;
    }
    public String getPosition() {
        return this.position;
    }
    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }
    public double getSalary() {
        return this.salary;
    }

    // Methods
    private boolean isFormatId(String id) {
        // Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 8)
            return false;
        // Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("DOC") && !prefix.equals("NUR"))
            return false;
        // Kiểm tra hậu tố
        String postfix = id.substring(3);
        for(int i = 0; i < postfix.length(); i++) {
            // Chuyển ký tự về mã số Unicode
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 58) return false;
        }
        return true;
    }
    private String getFormatId() {
        String postfix = String.format("%05d", HealthcareWorker.countHealthcareWorkerCreated);
        if(this.position.equals(("Bác sĩ"))){
            return "DOC" + postfix;
        }
        return "NUR" + postfix;
    }
}
