package MedicalRecord;

import java.util.ArrayList;

public class MedicalRecordManager {
    // Properties
    private static ArrayList<MedicalRecord> medicalRecordList;
    private static int numbersOfList;

    // Constructors
    public MedicalRecordManager() {
        MedicalRecordManager.medicalRecordList = new ArrayList<MedicalRecord>();
        MedicalRecordManager.numbersOfList = 0;
    }
    public MedicalRecordManager(ArrayList<MedicalRecord> medicalRecordList, int numbersOfList) {
        MedicalRecordManager.medicalRecordList = medicalRecordList;
        MedicalRecordManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setMedicalRecordList(ArrayList<MedicalRecord> medicalRecordList) {
        MedicalRecordManager.medicalRecordList = medicalRecordList;
    }
    public static void setNumbersOfList(int numbersOfList) {
        MedicalRecordManager.numbersOfList = numbersOfList;
    }
    public static ArrayList<MedicalRecord> getMedicalRecordList() {
        return MedicalRecordManager.medicalRecordList;
    }
    public static int getNumbersOfList() {
        return MedicalRecordManager.numbersOfList;
    }

    // Methods
}
