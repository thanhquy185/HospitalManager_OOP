package Patient;

import java.util.ArrayList;

public class PatientManager {
    // Properties
    private static ArrayList<Patient> patientList;
    private static int numbersOfList;

    // Constructors
    public PatientManager() {
        PatientManager.patientList = new ArrayList<Patient>();
        PatientManager.numbersOfList = 0;
    }
    public PatientManager(ArrayList<Patient> patientList, int numbersOfList) {
        PatientManager.patientList = patientList;
        PatientManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setPatientList(ArrayList<Patient> patientList) {
        PatientManager.patientList = patientList;
    }
    public static void setNumbersOfList(int numbersOfList) {
        PatientManager.numbersOfList = numbersOfList;
    }
    public static ArrayList<Patient> getPatientList() {
        return PatientManager.patientList;
    }
    public static int getNumbersOfList() {
        return PatientManager.numbersOfList;
    }

    // Methods
}

