package Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

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
    @Override
    public String getInfoByIndex(int index) {
        if(index < 0  || index > PatientManager.numbers - 1) return null;
        return PatientManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        Patient patient = findObjectById(id);
        if(patient == null) return null;
        return patient.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*-------------------------------------------------------------------------------------------------------------------------*");
	    System.out.println("|     HỌ TÊN BỆNH NHÂN     |  NGÀY SINH | PHÁI | ĐIỆN THOẠI |    QUỐC TỊCH    | MÃ BỆNH NHÂN | LOẠI CHĂM SÓC | MÃ BỆNH ÁN |");
	    System.out.println("*--------------------------+------------+------+------------+-----------------+--------------+---------------+------------*");
        for(Patient patient : PatientManager.list) {
            String fullname = patient.getFullname();
            String birthday = patient.getBirthday().getDateFormatByCondition("has cross");
            String gender = patient.getGender();
            String phone = patient.getPhone();
            String country = patient.getCountry();
            String id = patient.getId();
            String type = patient.getType();
            String medicalRecordID = patient.getMedicalRecordID();
            System.out.println(String.format("| %-24s | %-10s | %-4s | %-10s | %-15s | %-12s | %-13s | %-10s |",
                fullname, birthday, gender, phone,  country, id, type, medicalRecordID));
        }
        if(PatientManager.numbers >= 1)
            System.out.println("*-------------------------------------------------------------------------------------------------------------------------*");
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
        if(index < 0  || index > PatientManager.numbers - 1) return null;
        return PatientManager.list.get(index);
    }
    @Override
    public Patient findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return PatientManager.list.get(index);
    }
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
    public void sort(String condition){
        // PatientManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch (condition) {
            case "id asc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> patient.getId()));
                break;
            case "id desc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> patient.getId()).reversed());
                break;
            case "name asc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> {
                    String[] nameParts = patient.getFullname().split(" ");
                    return nameParts[nameParts.length - 1];     // Lấy tên, là phần tử cuối của nameParts
                }));
                break;
            case "name desc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> {
                    String[] nameParts = patient.getFullname().split(" ");
                    return nameParts[nameParts.length - 1];     // Lấy tên, là phần tử cuối của nameParts
                }));
                break;
            case "birthday asc":
                PatientManager.list.sort(
                    Comparator.comparing((Patient patient) -> patient.getBirthday().getYear())
                        .thenComparing(patient -> patient.getBirthday().getMonth())
                        .thenComparing(patient -> patient.getBirthday().getDay())
                );
                break;
            case "birthday desc":
                PatientManager.list.sort(
                    Comparator.comparing((Patient patient) -> patient.getBirthday().getYear()).reversed()
                        .thenComparing(patient -> patient.getBirthday().getMonth()).reversed()
                        .thenComparing(patient -> patient.getBirthday().getDay()).reversed()
                );
                break;
            default:
                System.out.println("Lựa chọn sắp xếp không hợp lệ.");
                break;
        }
    }
    @Override
    public void loadFromFile() {
        File file = new File("src/Database/PatientDatabase.txt");
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh nhân không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            /**
             * 1. Đọc toàn bộ file line by line (với format xác định trước)
             * 2. Tách ra theo dấu ngăn cách, cắt bỏ các khoảng trắng thừa
             * 3. Tạo Object mới với thông in đã được cắt (Lưu ý: String <=> Date)
             * 4. Thêm vào list.
             */
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String fullName = info[0];
                Date birthday = Date.getInstance().getDateFromDateFormat(info[1]);
                String gender = info[2];
                String phone = info[3];
                String country = info[4];
                String id = info[5];
                String type = info[6];
                String medicalRecordID = null;
                if(!info[7].equals("null"))
                    medicalRecordID = info[7];
                Patient newPatient = new Patient(fullName, birthday, gender, phone, country, id, type, medicalRecordID);
                add(newPatient);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void saveToFile() {
        File file = new File("src/Database/PatientDatabase.txt");
        // Kiểm tra file có tồn tại với path ở trên.
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh nhân không tồn tại");
            return;
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            /* 
                Write toàn bộ list vào file (theo format xác định trước), thứ tự và dấu ngăn cách:
                Fullname|Birthday|Gender|Phone|Country|ID|Type|Medical Record ID  
            */
            for(Patient patient : PatientManager.list){
                String fullname = patient.getFullname();
                String birthday = patient.getBirthday().getDateFormatByCondition("has cross");
                String gender = patient.getGender();
                String phone = patient.getPhone();
                String country = patient.getCountry();
                String id = patient.getId();
                String type = patient.getType();
                String medicalRecordID = patient.getMedicalRecordID();
                bufferedWriter.write(fullname + "|" + birthday + "|" + gender + "|" + phone + "|" + country
                    + "|" + id + "|" + type + "|" + medicalRecordID + "\n");
            }
            // Đóng bufferedWrite và fileWrite sau khi write xong.
            bufferedWriter.close();
            fileWriter.close();   
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

