package Patient;

import java.util.ArrayList;

import Common.CRUD;
import Common.Date;

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
    public void add(Patient patient){
        PatientManager.list.add(patient);
        PatientManager.numbers++;
    }
    // -- Cập nhật thông tin của đối tượng thông qua id của đối tượng đó
    public void update(Patient patient){
        PatientManager.list.set(findIndexById(patient.getId()), patient);
    }
    public void removeOne(String id){
        PatientManager.list.remove(findIndexById(id));
        PatientManager.numbers--;
    }
    public void removeAll(){
        PatientManager.list.clear();
        PatientManager.numbers = 0;
    }
    // -- Tìm vị trí của đối tượng trong lớp quản lý
    public int findIndexById(String idSearch){
        for(int i = 0; i < PatientManager.numbers; i++){
            if(PatientManager.list.get(i).getId().equals(idSearch)) {
                return i;
            }
        }
        return -1;
    }
    public Patient find(String id){
        return PatientManager.list.get(findIndexById(id));
    }
    public Patient findOneByCondition(String condition){
        return new Patient();   
    }
    public ArrayList<Patient> findAllByCondition(String condition){
        return new ArrayList<Patient>();
    }
    public void sort(){
        PatientManager.list.sort(null);
    }

}

