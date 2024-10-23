package HealthcareWorker;

import java.util.ArrayList;

import Common.Date;
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
    public int findIndexById(String idSearch){
        for(int i = 0; i < HealthcareWorkerManager.numbers; i++){
            if(HealthcareWorkerManager.list.get(i).getId().equals(idSearch)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public HealthcareWorker findOneById(String id){
        return HealthcareWorkerManager.list.get(findIndexById(id));
    }
    @Override
    public HealthcareWorker findOneByCondition(String condition){
        return null;
    }
    @Override
    public ArrayList<HealthcareWorker> findAllByCondition(String condition){
        return null;
    }
    @Override
    public void sort(){
        HealthcareWorkerManager.list.sort(null);
    }
}
