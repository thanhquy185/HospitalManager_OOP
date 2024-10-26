package HealthcareWorker;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;
// import Common.Date;
public class HealthcareWorkerManager implements CRUD<HealthcareWorker> {
    // Properties
    private static HealthcareWorkerManager instance;
    private static ArrayList<HealthcareWorker> list;
    private static int numbers;

    // Constructors
    public HealthcareWorkerManager() {
        HealthcareWorkerManager.list = new ArrayList<>();
        HealthcareWorkerManager.numbers = 0;
    }
    public HealthcareWorkerManager(ArrayList<HealthcareWorker> list, int numbers) {
        HealthcareWorkerManager.list = list;
        HealthcareWorkerManager.numbers = numbers;
    }

    // Setter - Getter
    public static void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public static void setNumbers(int numbers) {
        HealthcareWorkerManager.numbers = numbers;
    }
    public static HealthcareWorkerManager getInstance() {
        if(HealthcareWorkerManager.instance == null) {
            HealthcareWorkerManager.instance = new HealthcareWorkerManager();
        }
        return HealthcareWorkerManager.instance;
    }
    public static ArrayList<HealthcareWorker> getList() {
        return HealthcareWorkerManager.list;
    }
    public static int getNumbers() {
        return HealthcareWorkerManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public void show() {}
    @Override
    public void add(HealthcareWorker healthcareWorker){
        HealthcareWorkerManager.list.add(healthcareWorker);
        HealthcareWorkerManager.numbers++;
    }
    @Override
    public void update(HealthcareWorker healthcareWorker){
        HealthcareWorkerManager.list.set(findIndexById(healthcareWorker.getId()), healthcareWorker);
    }
    @Override
    public void removeOne(String id){
        HealthcareWorkerManager.list.remove(findIndexById(id));
        HealthcareWorkerManager.numbers--;
    }
    @Override
    public void removeAll(){
        HealthcareWorkerManager.list.clear();
        HealthcareWorkerManager.numbers = 0;
    }
    @Override
    public String getInfoByIndex(int index) {
        return "";
    }
    @Override
    public String getInfoById(String id) {
        return "";
    }
    @Override
    public int findIndexById(String id){
        for(int i = 0; i < HealthcareWorkerManager.numbers; i++){
            if(HealthcareWorkerManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public HealthcareWorker findObjectByIndex(int index){
        if(index < 0 || index > HealthcareWorkerManager.numbers) return null;
        return HealthcareWorkerManager.list.get(index);
    }
    @Override
    public HealthcareWorker findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return HealthcareWorkerManager.list.get(index);
    }
    @Override
    public HealthcareWorker findObjectByCondition(String condition){
        return null;
    }
    @Override
    public ArrayList<HealthcareWorker> findObjectsByCondition(String condition){
        ArrayList<HealthcareWorker> list = null;
        // - Mảng các Bác sĩ đã là trưởng Khoa
        if(condition.equals("doctor is manager department")
                && HealthcareWorkerManager.numbers >= 1) {
            list = new ArrayList<>();
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list) {
                if(healthcareWorker.getType() == "Bác sĩ"
                        && healthcareWorker.getIsManagerDepartment()) {
                    list.add(healthcareWorker);
                }
            }
        }
        // - Mảng các Bác sĩ đã là trưởng Khoa
        if(condition.equals("doctor is not manager department")
                && HealthcareWorkerManager.numbers >= 1) {
            list = new ArrayList<>();
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list) {
                if(healthcareWorker.getType() == "Bác sĩ"
                        && !healthcareWorker.getIsManagerDepartment()) {
                    list.add(healthcareWorker);
                }
            }
        }
        return list;
    }
    @Override
    public void sort(String condition){
        HealthcareWorkerManager.list.sort(null);
    }
    @Override
    public void loadFromFile() {

    }
    @Override
    public void saveToFile() {
        
    }
}
