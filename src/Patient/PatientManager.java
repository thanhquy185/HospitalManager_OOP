package Patient;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;

public class PatientManager implements CRUD<Patient> {
    //Properties
    /* Không phải tạo đối tượng khi sử dụng:
        PatientManager.getInstance().createpatientect() */
    private static PatientManager instance;
    private static ArrayList<Patient> list;
    private static int numbers;

    // Constructors
    public PatientManager(){
        PatientManager.list = new ArrayList<>();
        PatientManager.numbers = 0;
    }
    public PatientManager(ArrayList<Patient> list, int numbers){
        PatientManager.list = list;
        PatientManager.numbers = numbers;
    }

    // Setter - Getter
    public void setList(ArrayList<Patient> list){
        PatientManager.list = list;
    }
    public void setNumbers(int numbers){
        PatientManager.numbers = numbers;
    }
    public static PatientManager getInstance(){
        if(PatientManager.instance == null)
            PatientManager.instance = new PatientManager();
        return PatientManager.instance;
    }
    public ArrayList<Patient> getList(){
        return PatientManager.list;
    }
    public int getNumbers(){
        return PatientManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public void show() {}
    @Override
    public void add(Patient patient){
        PatientManager.list.add(patient);
        PatientManager.numbers++;
    }
    @Override
    public void update(Patient patient){
        PatientManager.list.set(findIndexById(patient.getId()), patient);
    }
    @Override
    public void removeOne(String id){
        PatientManager.list.remove(findIndexById(id));
        PatientManager.numbers--;
    }
    @Override
    public void removeAll(){
        PatientManager.list.clear();
        PatientManager.numbers = 0;
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
        for(int i = 0; i < PatientManager.numbers; i++){
            if(PatientManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Patient findObjectByIndex(int index){
        if(index < 0  || index > PatientManager.numbers) return null;
        return PatientManager.list.get(index);
    }
    @Override
    public Patient findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return PatientManager.list.get(index);
    }
    @Override
    public Patient findObjectByCondition(String condition){
        return null;
    }
    @Override
    public ArrayList<Patient> findObjectsByCondition(String condition){
        return null;
    }
    @Override
    public void sort(String condition){
        PatientManager.list.sort(null);
    }
    @Override
    public void loadFromFile() {

    }
    @Override
    public void saveToFile() {
        
    }
}

