package MedicalRecord;

import Common.Date;
import Patient.Patient;
import HealthcareWorker.Doctor;
import HealthcareWorker.Nurse;

public class TreamentRecord extends MedicalRecord {
    //	Properties
	private Patient patient;
	private Doctor doctor;
	private Nurse nurse;
	private Sick sick;

    // Constructor
	public TreamentRecord() {
		super();
		this.patient = new Patient();
		this.doctor = new Doctor();
		this.nurse = new Nurse();
		this.sick = new Sick();
	}
	public TreamentRecord(Date inputDay, Date outputDay, Patient patient, Doctor doctor, Nurse nurse, Sick sick) {
		super(inputDay, outputDay);
		this.patient = patient;
		this.doctor = doctor;
		this.nurse = nurse;
		this.sick = sick;
	}
	public TreamentRecord(TreamentRecord treamentRecord) {
		super(treamentRecord.getInputDay(), treamentRecord.getOutputDay());
		patient = new Patient(treamentRecord.getPatient());
		doctor = new Doctor(treamentRecord.getDoctor());
		nurse = new Nurse(treamentRecord.getNurse());
		sick = new Sick(treamentRecord.getSick());
	}

    // Setter - Getter
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public void setSick(Sick sick) {
		this.sick = sick;
	}
	public Patient getPatient() {
		return this.patient;
	}
	public Doctor getDoctor() {
		return this.doctor;
	}
	public Nurse getNurse() {
		return this.nurse;
	}
	public Sick getSick() {
		return this.sick;
	}

	//Methods
}