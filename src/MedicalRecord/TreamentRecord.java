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
		patient = new Patient();
		doctor = new Doctor();
		nurse = new Nurse();
		sick = new Sick();
	}
	public TreamentRecord(String id, Date inputDay, Date outputDay, Patient patient, Doctor doctor, Nurse nurse, Sick sick) {
		super(id, inputDay, outputDay);
		this.patient = patient;
		this.doctor = doctor;
		this.nurse = nurse;
		this.sick = sick;
	}
	public TreamentRecord(TreamentRecord treamentRecord) {
		super(treamentRecord.id, treamentRecord.inputDay, treamentRecord.outputDay);
		patient = new Patient(treamentRecord.patient);
		doctor = new Doctor(treamentRecord.doctor);
		nurse = new Nurse(treamentRecord.nurse);
		sick = new Sick(treamentRecord.sick);
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
		return patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public Nurse getNurse() {
		return nurse;
	}
	public Sick getSick() {
		return sick;
	}

	//Methods
}