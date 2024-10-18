package HealthcareWorker;

import java.util.ArrayList;

public class HealthcareWorkerManager {
    // Properties
    private static ArrayList<HealthcareWorker> healthcareWorkerList;
    private static int numbersOfList;

    // Constructors
    public HealthcareWorkerManager() {
        HealthcareWorkerManager.healthcareWorkerList = new ArrayList<>();
        HealthcareWorkerManager.numbersOfList = 0;
    }
    public HealthcareWorkerManager(ArrayList<HealthcareWorker> healthcareWorkerList, int numbersOfList) {
        HealthcareWorkerManager.healthcareWorkerList = healthcareWorkerList;
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }

    // Setter - Getter
    public static void setHealthcareWorkerList(ArrayList<HealthcareWorker> healthcareWorkerList) {
        HealthcareWorkerManager.healthcareWorkerList = healthcareWorkerList;
    }
    public static void setNumbersOfList(int numbersOfList) {
        HealthcareWorkerManager.numbersOfList = numbersOfList;
    }
    public static ArrayList<HealthcareWorker> getHealthcareWorkerList() {
        return HealthcareWorkerManager.healthcareWorkerList;
    }
    public static int getNumbersOfList() {
        return HealthcareWorkerManager.numbersOfList;
    }

    // Methods
}
