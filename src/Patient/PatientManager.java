package Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
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

    // String: trả về thông tin của Object với [index]
    @Override   // Done
    public String getInfoByIndex(int index) {
        // if(index < 0 || PatientManager.list.size() <= index){
            //     // Nếu index không hợp lệ thì throw exception: IndexOutOfBoundsExceptions
            //     throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
            // }
        if(index < 0  || index > PatientManager.numbers) return null;
        return PatientManager.list.get(index).getInfo();
    }
    // String : trả về thông tin của Object thông qua [ID]
    @Override   // Done
    public String getInfoById(String id) {
        Patient patient = findObjectById(id);
        if(patient == null) return null;
        return patient.getInfo();
    }
    
    // Show toàn bộ thông tin của Object trong Arraylist (list)
    @Override   // Done
    public void show() {
        System.out.println("*-------------------------------------------------------------------------------------------------------------------------*");
	    System.out.println("|     TÊN BỆNH NHÂN     | NGÀY SINH | GIỚI TÍNH | SỐ ĐIỆN THOẠI |     QUÊ QUÁN     | MÃ BỆNH NHÂN |   LOẠI   | MÃ BỆNH ÁN |");
	    System.out.println("*-----------------------+-----------+-----------+---------------+------------------+--------------+----------+------------+*");
        for(Patient patient : PatientManager.list){                     // 8 property
            System.out.println(String.format("| %-21s | %-9s | %-9s | %-13s | %-16s | %-12s | %-8s | %-10s |",
                                            patient.getFullname(), patient.getBirthday(), patient.getGender(), patient.getPhone(), patient.getCountry(), 
                                            patient.getId(), patient.getType(), patient.getMedicalRecordID()));
        }
    }
    // int: trả về index của Object thông qua ID của Object đó.
    @Override   // Ok
    public int findIndexById(String id){
        for(int i = 0; i < PatientManager.numbers; i++){
            if(PatientManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    // Patient: trả về Object(Patient) thông qua index.
    @Override   // Ok
    public Patient findObjectByIndex(int index){
        if(index < 0  || index > PatientManager.numbers) return null;
        return PatientManager.list.get(index);
    }
    // Patient: trả về Object(Patient) thông qua ID.
    @Override   // Ok
    public Patient findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return PatientManager.list.get(index);
    }
    
    /** Thêm một Patient vào list
     * 1. Nhập thông tin của Patient
     * 2. Truyền vào một Patient 
     */
    @Override   // ok
    public void add(Patient patient){
        PatientManager.list.add(patient);
        PatientManager.numbers++;
    }

    @Override   // Ok
    public void update(Patient patient){
        PatientManager.list.set(findIndexById(patient.getId()), patient);
    }
    @Override   // Ok
    public void removeOne(String id){
        PatientManager.list.remove(findIndexById(id));
        PatientManager.numbers--;
    }
    @Override   // Ok
    public void removeAll(){
        PatientManager.list.clear();
        PatientManager.numbers = 0;
    }
    // Không sử dụng vì khi sort, tạo ra một sortList(clone) để thao tác
    // không thao tác trực tiếp trên list ban đầu.
    @Override  
    public void sort(String condition){
        switch (condition) {
            // Sắp xếp theo tên của bệnh nhân, Alphabet
            case "name":
                // PatientManager.getInstance().getList().sort(Comparator.comparing(()));
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> {
                    String[] nameParts = patient.getFullname().split(" ");
                    return nameParts[nameParts.length - 1];     // Lấy tên, là phần tử cuối của nameParts
                }));
                break;
            // Sắp xếp theo birthday của bệnh nhân. Năm 2000 trước 2024, tháng 1 trước tháng 2...
            case "birthday":
                PatientManager.list.sort(
                    Comparator.comparing((Patient patient) -> patient.getBirthday().getYear())
                              .thenComparing(patient -> patient.getBirthday().getMonth())
                              .thenComparing(patient -> patient.getBirthday().getDay())
                );
                break;
            // Sắp xếp theo ID của bệnh nhân, vì mặc định đã được sắp xếp như thế.
            case "id":
                break;
            default:
                System.out.println("Lựa chọn sắp xếp không hợp lệ.");
                break;
        } 
    }
    @Override
    public void loadFromFile() {
        File file = new File("HospitalManager/src/Database/PatientDatabase.txt");
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh Nhân không tồn tại");
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
            while(true){
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String fullName = info[0].trim();

                // Convert birthdayString(from file) to Date object
                String birthdayString = info[1].trim();
                Date birthday = new Date();
                birthday = birthday.convertStringToDate(birthdayString);

                String gender = info[2].trim();
                String phone = info[3].trim();
                String country = info[4].trim();
                String id = info[5].trim();
                String type = info[6].trim();
                String medicalRecordID = info[7].trim();

                // Show to console all data in are loaded
                System.out.println( fullName + "|" + birthday + "|" + gender + "|" + phone + "|" + country + "|" +
                                    id + "|" + type + "|" + medicalRecordID);

                // Add vào list toàn bộ bệnh nhân từ file
                Patient newPatient = new Patient(fullName, birthday, gender, phone, country, type);
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
        // Tạo 
        File file = new File("HospitalManager/src/Database/PatientDatabase.txt");
        // Kiểm tra file có tồn tại với path ở trên.
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh Nhân không tồn tại");
            return;
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            /* 
                Write toàn bộ list vào file (theo format xác định trước), thứ tự và dấu ngăn cách:
                Fullname | Birthday | Gender | Phone | Country | ID | Type | Medical Record ID  
            */
            for(Patient patient : PatientManager.list){
                bufferedWriter.write(String.format
                        ("| %-21s | %-9s | %-9s | %-13s | %-16s | %-12s | %-8s | %-10s |\n",
                        patient.getFullname(), patient.getBirthday(), patient.getGender(), patient.getPhone(), patient.getCountry(), 
                        patient.getId(), patient.getType(), patient.getMedicalRecordID()));
            }
            // Đóng bufferedWrite và fileWrite sau khi write xong.
            bufferedWriter.close();
            fileWriter.close();   
        } catch (Exception e){
            e.printStackTrace();
        }
        

    }

}

