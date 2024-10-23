package MedicalRecord;

import Common.Date;
import HealthcareWorker.Nurse;
import Patient.NormalPatient;
import Patient.Patient;
import Patient.PremiumPatient;
public class TestRecord extends MedicalRecord {
    // Properties
	private Patient patient;
	private Nurse nurse;
	private String reason;
	private String diagnose;

    // Constructor
	public TestRecord() {
		super();
		this.patient = null;
		this.nurse = null;
		this.reason = null;
		this.diagnose = null;
	}
	public TestRecord(Date inputDay, Date outputDay, Patient patient, Nurse nurse, String reason, String diagnose) {
		super(inputDay, outputDay);
		this.patient = patient;
		this.nurse = nurse;
		this.reason = reason;
		this.diagnose = diagnose;
	}
	public TestRecord(TestRecord testRecord) {
		super(testRecord.getInputDay(), testRecord.getOutputDay());
		if(testRecord.getPatient().getType() == "Thường") {
			this.patient = new NormalPatient(testRecord.getPatient().getFullname(),
				testRecord.getPatient().getBirthday(), testRecord.getPatient().getGender(),
				testRecord.getPatient().getCountry(), testRecord.getPatient().getPhone(),
				testRecord.getPatient().getId(), testRecord.getPatient().getIsTest(),
				testRecord.getPatient().getType(), testRecord.getPatient().getRelatives());

		} else {
			this.patient = new PremiumPatient(testRecord.getPatient().getFullname(),
				testRecord.getPatient().getBirthday(), testRecord.getPatient().getGender(),
				testRecord.getPatient().getCountry(), testRecord.getPatient().getPhone(),
				testRecord.getPatient().getId(), testRecord.getPatient().getIsTest(),
				testRecord.getPatient().getType(), testRecord.getPatient().getRelatives());
		}
		this.nurse = new Nurse(testRecord.getNurse());
		this.reason = testRecord.getReason();
		this.diagnose = testRecord.getDiagnose();
	}

    // Setter - Getter
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public Patient getPatient() {
		return this.patient;
	}
	public Nurse getNurse() {
		return this.nurse;
	}
	public String getReason() {
		return this.reason;
	}
	public String getDiagnose() {
		return this.diagnose;
	}

	// Methods
}
