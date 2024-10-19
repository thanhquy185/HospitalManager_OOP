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

    // Constructor
    public HealthcareWorker() {
        super();
    }
    public HealthcareWorker(String fullname, Date birthday, String gender, String country, String phone,
                            String id, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, country, phone);
        this.id = id;
        this.department = department;
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }
    public HealthcareWorker(HealthcareWorker healthcareWorker) {
        super(healthcareWorker.fullname, healthcareWorker.birthday, healthcareWorker.gender, healthcareWorker.country, healthcareWorker.phone);
        this.id = healthcareWorker.id;
        this.department = healthcareWorker.department;
        this.position = healthcareWorker.position;
        this.yearsOfExperience = healthcareWorker.yearsOfExperience;
        this.salary = healthcareWorker.salary;
    }

    // Setter - Getter
    public void setId(String id) {
        this.id = id;
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
        return id;
    }
    public String getDepartment() {
        return department;
    }
    public String getPosition() {
        return position;
    }
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public double getSalary() {
        return salary;
    }

    // Methods
    
}
