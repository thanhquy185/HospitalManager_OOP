package MedicalRecord;

import java.util.ArrayList;

public class MedicalRecordManager {
    // Properties
    private static MedicalRecordManager instance;
    private static ArrayList<MedicalRecord> list;
    private static int numbersOfList;

    // Constructors
    public MedicalRecordManager() {
        list = new ArrayList<MedicalRecord>();
        numbersOfList = 0;
    }
    public MedicalRecordManager(ArrayList<MedicalRecord> list, int numbersOfList) {
        MedicalRecordManager.list = list;
        MedicalRecordManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setList(ArrayList<MedicalRecord> list) {
        MedicalRecordManager.list = list;
    }
    public static void setNumbersOfList(int numbersOfList) {
        MedicalRecordManager.numbersOfList = numbersOfList;
    }
    public static MedicalRecordManager getInstance() {
        if(instance == null) {
            instance = new MedicalRecordManager();
        }
        return instance;
    }
    public static ArrayList<MedicalRecord> getList() {
        return list;
    }
    public static int getNumbersOfList() {
        return numbersOfList;
    }

    // Methods
}
