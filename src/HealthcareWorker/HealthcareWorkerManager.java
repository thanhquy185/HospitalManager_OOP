package HealthcareWorker;

import java.util.ArrayList;

public class HealthcareWorkerManager {
    // Properties
    private static HealthcareWorkerManager instance;
    private static ArrayList<HealthcareWorker> list;
    private static int numbersOfList;

    // Constructors
    public HealthcareWorkerManager() {
        list = new ArrayList<>();
        numbersOfList = 0;
    }
    public HealthcareWorkerManager(ArrayList<HealthcareWorker> list, int numbersOfList) {
        HealthcareWorkerManager.list = list;
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public static void setNumbersOfList(int numbersOfList) {
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }
    public static HealthcareWorkerManager getInstance() {
        if(instance == null) {
            instance = new HealthcareWorkerManager();
        }
        return instance;
    }
    public static ArrayList<HealthcareWorker> getList() {
        return list;
    }
    public static int getNumbersOfList() {
        return numbersOfList;
    }
    
    // Methods
}
