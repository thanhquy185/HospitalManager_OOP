package Patient;

import java.util.ArrayList;

import Common.Date;

public class PatientManager {
    //Properties
    /* Không phải tạo đối tượng khi sử dụng:
        PatientManager.getInstance().createObject() */ 
    private static PatientManager instance;      
    private static ArrayList<Patient> list;
    private static int numbersOfElement;

    // Constructors
    public PatientManager(){
        list = new ArrayList<>();
        numbersOfElement = 0;
    }
    public PatientManager(ArrayList<Patient> list, int numbersOfElement){
        PatientManager.list = list;
        PatientManager.numbersOfElement = numbersOfElement;
    }
    
    // Setter - Getter
    public static void setList(ArrayList<Patient> list){
        PatientManager.list = list;
    }
    public static void setNumbersOfElement(int numbersOfElement){
        PatientManager.numbersOfElement = numbersOfElement;
    }
    public static PatientManager getInstance(){
        if(instance == null)
            instance = new PatientManager();
        return instance;
    }
    public static ArrayList<Patient> getList(){
        return list;
    }
    public static int getNumbersOfElement(){
        return numbersOfElement;
    }

    public void createObject(Patient patient){
        list.add(patient);
        numbersOfElement++;
        System.out.println("A new obj is created");
    }

    // Methods
    int findIndexOfObject(Patient obj){
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getID().equals(obj.getID())){
                index = i;
                break;
            }
        }
        return index;
    }
    // int getIDNumber(Patient obj){
    //     String lastFiveDigits = obj.getID().substring(obj.getID().length() - 5);
    //     int IDNumber = Integer.parseInt(lastFiveDigits);
    //     return IDNumber - 1;    // STT: n, but in array, index = stt - 1;
    // }
    void add(Patient obj){
        list.add(obj);
        numbersOfElement++;
    }
    // Update information of Patient which exist (has ID)
    void update(Patient obj){
        list.set(findIndexOfObject(obj), obj);
    }
    void removeOne(Patient obj){
        list.remove(findIndexOfObject(obj));
        numbersOfElement--;
    }
    void removeAll(){
        list.clear();
        numbersOfElement = 0;
    }
    Patient find(Patient obj){
        return list.get(findIndexOfObject(obj));
    }
    // Patient findOneByCondition(String condition){
        
    // }
    // ArrayList<Patient> findAllByCondition(String condition){

    // }
    void sort(){
        list.sort(null);
    }
    public static void main(String[] args){
        Date date = new Date();
        Patient patientOne = new Patient("ABC", date, "Men", "VietNam", "0123456789");
        Patient patientTwo = new Patient("Do Duy Quy", date, "Men", "VietNam", "0329762629");

        PatientManager.getInstance().createObject(patientOne);
        PatientManager.getInstance().createObject(patientTwo);

        PatientManager.getInstance().removeOne(patientOne);
        System.out.println(PatientManager.getInstance().getList().get(0).getFullname());
        System.out.println(patientTwo.getID());    
    }
}

