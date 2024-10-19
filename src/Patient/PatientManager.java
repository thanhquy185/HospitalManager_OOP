package Patient;

import java.util.ArrayList;

public class PatientManager {
    // Properties
    private static PatientManager instance;
    private static ArrayList<Patient> list;
    private static int numbersOfList;

    // Constructors
    public PatientManager() {
        list = new ArrayList<Patient>();
        numbersOfList = 0;
    }
    public PatientManager(ArrayList<Patient> list, int numbersOfList) {
        PatientManager.list = list;
        PatientManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setList(ArrayList<Patient> list) {
        PatientManager.list = list;
    }
    public static void setNumbersOfList(int numbersOfList) {
        PatientManager.numbersOfList = numbersOfList;
    }
    public static PatientManager getInstance() {
        if(instance == null) {
            instance = new PatientManager();
        }
        return instance;
    }
    public static ArrayList<Patient> getList() {
        return PatientManager.list;
    }
    public static int getNumbersOfList() {
        return PatientManager.numbersOfList;
    }

    // Methods
}

