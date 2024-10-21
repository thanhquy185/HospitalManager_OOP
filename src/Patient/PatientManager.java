package Patient;

import java.util.ArrayList;

import Common.Date;
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
        list = new ArrayList<>();
        numbers = 0;
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
        if(instance == null)
            instance = new PatientManager();
        return instance;
    }
    public ArrayList<Patient> getList(){
        return list;
    }
    public int getNumbers(){
        return numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    public void add(Patient patient){
        list.add(patient);
        numbers++;
    }
    // -- Cập nhật thông tin của đối tượng thông qua id của đối tượng đó
    public void update(Patient patient){
        list.set(findIndexById(patient.getId()), patient);
    }
    public void removeOne(String id){
        list.remove(findIndexById(id));
        numbers--;
    }
    public void removeAll(){
        list.clear();
        numbers = 0;
    }
    // -- Tìm vị trí của đối tượng trong lớp quản lý
    public int findIndexById(String idSearch){
        for(int i = 0; i < numbers; i++){
            if(list.get(i).getId().equals(idSearch)) {
                return i;
            }
        }
        return -1;
    }
    public Patient find(String id){
        return list.get(findIndexById(id));
    }
    public Patient findOneByCondition(String condition){
        return new Patient();   
    }
    public ArrayList<Patient> findAllByCondition(String condition){
        return new ArrayList<Patient>();
    }
    public void sort(){
        list.sort(null);
    }
    public static void main(String[] args){
        // Date date = new Date();
        // Patient patientOne = new Patient("ABC", date, "Male", "VietNam", "0123456789");
        // Patient patientTwo = new Patient("Do Duy Quy", date, "Male", "VietNam", "0329762629");

        // PatientManager.getInstance().add(patientOne);
        // PatientManager.getInstance().add(patientTwo);

        // PatientManager.getInstance().removeOne(patientOne);
        // System.out.println(PatientManager.getInstance().getList().get(0).getFullname());
        // System.out.println(patientTwo.getID());
    }
}

