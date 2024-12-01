package Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.*;
import Account.AccountManager;
import MedicalRecord.MedicalRecordManager;
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
    public Patient findObjectByIndex(int index){
        if(index < 0  || index > PatientManager.numbers - 1) return null;
        return PatientManager.list.get(index);
    }
    @Override
    public Patient findObjectById(String id){
        if(PatientManager.numbers == 0) return null;
        for(Patient patient : PatientManager.list) {
            if(patient.getId().equals(id)) return patient;
        }
        return null;
    }
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
    public void add(Patient patient){
        PatientManager.list.add(patient);
        PatientManager.numbers++;
    }
    @Override
    public void update(Patient patientUpdate, int choice) {
        Scanner sc = new Scanner(System.in);
        if(choice == 1 || choice == 6) {
            System.out.print(" - Nhập họ và tên mới: ");
            String newFullname = sc.nextLine();
            while(!myClass.getInstance().isValidName(newFullname)) {
                System.out.println("----- -----");
                System.out.println("! - HỌ TÊN KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại: ");
                newFullname = sc.nextLine();
                System.out.println("----- -----");
            }
            patientUpdate.setFullname(newFullname);
        }
        if(choice == 2 || choice == 6) {
            System.out.print(" - Nhập ngày sinh mới (dd-mm-yyyy hoặc ddmmyyyy): ");
            String newBirthdayStr = sc.nextLine();
            while(!Date.getInstance().isDateFormat(newBirthdayStr)
                    || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()
                    || !Date.getInstance().checkBeforeAfterDate(Date.getInstance().getDateFromDateFormat(newBirthdayStr),
                            MedicalRecordManager.getInstance().findObjectById(patientUpdate.getMedicalRecordID()).getInputDay()
                        )) {
                System.out.println("----- -----");
                System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                newBirthdayStr = sc.nextLine();
            System.out.println("----- -----");
            }
            Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
            patientUpdate.setBirthday(newBirthdayObj);
            AccountManager.getInstance().findObjectById(patientUpdate.getId()).setPassword(newBirthdayObj.getDateFormatByCondition("has not cross"));
        }
        if(choice == 3 || choice == 6) {
            System.out.print(" - Nhập giới tính mới (Nam hoặc Nữ): ");
            String newGender = sc.nextLine();
            while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                System.out.println("----- -----");
                System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                newGender = sc.nextLine();
                System.out.println("----- -----");
            }
            patientUpdate.setGender(newGender);
        }
        if(choice == 4 || choice == 6) {
            System.out.print(" - Nhập số điện thoại mới (10 số): ");
            String newPhone = sc.nextLine();
            while(newPhone.length() != 10 || !myClass.getInstance().hasAllCharacterIsNumber(newPhone)) {
                System.out.println("----- -----");
                System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại (10 số): ");
                newPhone = sc.nextLine();
                System.out.println("----- -----");
            }
            patientUpdate.setPhone(newPhone);
        }
        if(choice == 5 || choice == 6) {
            System.out.print(" - Nhập quốc tịch mới: ");
            String newCountry = sc.nextLine();
            while(!myClass.getInstance().isValidName(newCountry)) {
                System.out.println("----- -----");
                System.out.println("! - QUỐC TỊCH KHÔNG HỢP LỆ");
                System.out.print("?! - Nhập lại: ");
                newCountry = sc.nextLine();
                System.out.println("----- -----");
            }
            patientUpdate.setCountry(newCountry);
        }
    }
    @Override
    public void remove(String id){
        if(PatientManager.numbers == 0) return;
        for(int i = 0; i < PatientManager.numbers; i++) {
            if(PatientManager.list.get(i).getId().equals(id)) {
                PatientManager.list.remove(i);
                PatientManager.numbers--;
                return;
            }
        }
    }
    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        // Tìm kiếm bằng mã Bệnh nhân hay tên Bệnh nhân đều được phép
        System.out.println("Bạn có thể tìm bằng mã Bệnh nhân, họ tên Bệnh nhân hoặc mã Bệnh án");
        System.out.print(" - Nhập thông tin Bệnh nhân cần tìm: ");
        String info = sc.nextLine();
        ArrayList<Patient> patientSearchList = new ArrayList<>();
        for(Patient patient : PatientManager.list) {
            if(patient.getFullname().toLowerCase().contains(info.trim().toLowerCase())
                    || patient.getId().equals(info) || patient.getMedicalRecordID().equals(info))
                patientSearchList.add(patient);
        }
        // Thông báo kết quả tìm được
        if(patientSearchList.size() == 0) {
            System.out.println("! - Không tìm được Khoa nào với thông tin đã cho");
        } else {
            for(Patient patientSearch : patientSearchList) {
                System.out.println(patientSearch.getInfo());
            }
        }
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
                    return nameParts[nameParts.length - 1];
                }));
                break;
            case "name desc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> {
                    String[] nameParts = patient.getFullname().split(" ");
                    return nameParts[nameParts.length - 1];
                }));
                break;
            case "birthday asc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> patient.getBirthday().calDays()));
                break;
            case "birthday desc":
                PatientManager.list.sort(Comparator.comparing((Patient patient) -> patient.getBirthday().calDays()).reversed());
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
            // Đặt lại tất cả dữ liệu của Tài khoản
            PatientManager.list = new ArrayList<>();
            PatientManager.numbers = 0;
            // Đọc dữ liệu từ file
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
                Patient newPatient = null;
                if(type.equals("Bình thường"))
                    newPatient = new NormalPatient(fullName, birthday, gender, phone, country, id, type, medicalRecordID);
                else if(type.equals("Cao cấp"))
                    newPatient = new PremiumPatient(fullName, birthday, gender, phone, country, id, type, medicalRecordID);
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

