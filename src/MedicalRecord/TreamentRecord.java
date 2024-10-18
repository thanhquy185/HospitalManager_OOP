package MedicalRecord;

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
		this.patient = new Patient();
		this.doctor = new Doctor();
		nurse = new Nurse();
		sick = new Sick();
	}

	public TreamentRecord(Patient patient, Doctor doctor, Nurse nurse, Sick sick) {
		this.patient = patient;
		this.doctor = doctor;
		this.nurse = nurse;
		this.sick = sick;
	}

	public TreamentRecord(TreamentRecord treamentRecord) {
		this.patient = new Patient(treamentRecord.patient);
		this.doctor = new Doctor(treamentRecord.doctor);
		this.nurse = new Nurse(treamentRecord.nurse);
		this.sick = new Sick(treamentRecord.sick);
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