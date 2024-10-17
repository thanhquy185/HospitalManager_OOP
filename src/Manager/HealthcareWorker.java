package Manager;

import Common.Date;
import Common.Person;

// HealthcareWorker class
class HealthcareWorker extends Person {
    private String workerId;
    private String department;
    private String position;
    private int yearsOfExperience;
    private double salary;

    // Constructor
    public HealthcareWorker(String fullname, Date birthday, String gender, String address, String phone,
                            String workerId, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, address, phone);  // Call Person constructor
        // Setter
        this.workerId = workerId;
        this.department = department;
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }

    // Getter
    public String getWorkerId() {
        return workerId;
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

    // show details
    public String getWorkerDetails() {
        return "Worker ID: " + workerId + ", Department: " + department +
               "Position: " + position + ", Experience: " + yearsOfExperience + " years, Salary: $" + salary;
    }
}

// Doctor class
class Doctor extends HealthcareWorker {

    public Doctor(String fullname, Date birthday, String gender, String address, String phone,
                  String workerId, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, address, phone, workerId, department, position, yearsOfExperience, salary);
    }

    // Doctor-specific method
    public String prescribeMedicine() {
        return "Prescribing medicine";
    }
}

// Nurse class 
class Nurse extends HealthcareWorker {

    public Nurse(String fullname, Date birthday, String gender, String address, String phone,
                 String workerId, String department, String position, int yearsOfExperience, double salary) {
        super(fullname, birthday, gender, address, phone, workerId, department, position, yearsOfExperience, salary);
    }

    // Nurse-specific method
    public String provideCare() {
        return "Providing care to patients";
    }
}

// Test code 
class Main {
    public static void main(String[] args) {
        Date birthday = new Date(15, 6, 1985);
        Doctor doctor = new Doctor("John Doe", birthday, "Male", "123 Main St", "555-1234",
                                   "D001", "Cardiology", "Senior Doctor", 15, 150000.00);

        Nurse nurse = new Nurse("Jane Smith", birthday, "Female", "456 Main St", "555-5678",
                                "N001", "Pediatrics", "Senior Nurse", 10, 75000.00);

        System.out.println(doctor.getWorkerDetails());
        System.out.println(nurse.getWorkerDetails());

        
        System.out.println(doctor.prescribeMedicine());
        System.out.println(nurse.provideCare());
    }
}
