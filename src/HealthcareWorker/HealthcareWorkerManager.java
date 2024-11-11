package HealthcareWorker;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;
import Common.Date;
import Patient.Patient;
import Patient.PatientManager;
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
    public void setList(ArrayList<HealthcareWorker> list) {
        HealthcareWorkerManager.list = list;
    }
    public void setNumbers(int numbers) {
        HealthcareWorkerManager.numbers = numbers;
    }
    public static HealthcareWorkerManager getInstance() {
        if(HealthcareWorkerManager.instance == null) {
            HealthcareWorkerManager.instance = new HealthcareWorkerManager();
        }
        return HealthcareWorkerManager.instance;
    }
    public ArrayList<HealthcareWorker> getList() {
        return HealthcareWorkerManager.list;
    }
    public int getNumbers() {
        return HealthcareWorkerManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public String getInfoByIndex(int index) {
        if(index < 0 || index > HealthcareWorkerManager.numbers - 1) return null;
        return HealthcareWorkerManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        HealthcareWorker healthcareWorker = findObjectById(id);
        if(healthcareWorker == null) return null;
        return healthcareWorker.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------------*");
	    System.out.println("|     HỌ TÊN NHÂN VIÊN     |  NGÀY SINH | PHÁI | ĐIỆN THOẠI |    QUỐC TỊCH    | MÃ NHÂN VIÊN | LOẠI NHÂN VIÊN | KN | LƯƠNG |  MÃ KHOA  | TRKHOA | MÃ BỆNH ÁN |");
	    System.out.println("*--------------------------+------------+------+------------+-----------------+--------------+----------------+----+-------+-----------+--------+------------*");
        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list) {
            String fullname = healthcareWorker.getFullname();
            String birthday = healthcareWorker.getBirthday().getDateFormatByCondition("has cross");
            String gender = healthcareWorker.getGender();
            String phone = healthcareWorker.getPhone();
            String country = healthcareWorker.getCountry();
            String id = healthcareWorker.getId();
            String type = healthcareWorker.getType();
            int yearsOfExperience = healthcareWorker.getYearsOfExperience();
            int salary = healthcareWorker.getSalary();
            String departmentID = healthcareWorker.getDepartmentID();
            String isManagerDepartment = healthcareWorker.getIsManagerDepartment();
            String medicalRecordID = healthcareWorker.getMedicalRecordID();
            System.out.println(String.format("| %-24s | %-10s | %-4s | %-10s | %-15s | %-12s | %-14s | %-2s | %-5s | %-9s | %-6s | %-10s |",
                fullname, birthday, gender, phone, country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID));
        }
        if(HealthcareWorkerManager.numbers >= 1)
	        System.out.println("*------------------------------------------------------------------------------------------------------------------------------------------------------------*");
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
        if(index < 0 || index > HealthcareWorkerManager.numbers - 1) return null;
        return HealthcareWorkerManager.list.get(index);
    }
    @Override
    public HealthcareWorker findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return HealthcareWorkerManager.list.get(index);
    }
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
    public void sort(String condition){
        // HealthcareWorkerManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch(condition) {
            case "id asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getId()
                    ));
                break;
            }
            case "id desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getId()
                    ).reversed());
                break;
            }
            case "name asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getFullname()
                    ));
                break;
            }
            case "name desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getFullname()
                    ).reversed());
                break;
            }
            case "birthday asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getBirthday().getDateFormatByCondition("has not cross")
                    ));
                break;
            }
            case "birthday desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getBirthday().getDateFormatByCondition("has not cross")
                    ).reversed());
                break;
            }
            case "years asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getYearsOfExperience()
                    ));
                break;
            }
            case "years desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getYearsOfExperience()
                    ).reversed());
                break;
            }
            case "salary asc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getSalary()
                    ));
                break;
            }
            case "salary desc": {
                HealthcareWorkerManager.list.sort(Comparator.comparing(
                        (HealthcareWorker healthcareWorker) -> healthcareWorker.getSalary()
                    ).reversed());
                break;
            }
        }
    }
    @Override
    public void loadFromFile() {
        File file = new File("src/Database/HealthcareWorkerDatabase.txt");
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Nhân viên Y tế không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
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
                int yearsOfExperience = Integer.parseInt(info[7]);
                int salary = Integer.parseInt(info[8]);
                String departmentID = null;
                if(!info[9].equals("null")) {
                    departmentID = info[9];
                }
                String isManagerDepartment = null;
                if(!info[10].equals("null")) {
                    isManagerDepartment = info[10];
                }
                String medicalRecordID = null;
                if(!info[11].equals("null"))
                    medicalRecordID = info[11];
                HealthcareWorker newHealthcareWorker = new HealthcareWorker(fullName, birthday, gender, phone,
                    country, id, type, yearsOfExperience, salary, departmentID, isManagerDepartment, medicalRecordID);
                add(newHealthcareWorker);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void saveToFile() {
        File file = new File("src/Database/HealthcareWorkerDatabase.txt");
        // Kiểm tra file có tồn tại với path ở trên.
        if(!file.exists()){
            System.out.println("Tệp tin quản lý dữ liệu về Nhân viên Y tế không tồn tại");
            return;
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.list){
                String fullname = healthcareWorker.getFullname();
                String birthday = healthcareWorker.getBirthday().getDateFormatByCondition("has cross");
                String gender = healthcareWorker.getGender();
                String phone = healthcareWorker.getPhone();
                String country = healthcareWorker.getCountry();
                String id = healthcareWorker.getId();
                String type = healthcareWorker.getType();
                int yearsOfExperience = healthcareWorker.getYearsOfExperience();
                int salary = healthcareWorker.getSalary();
                String departmentID = healthcareWorker.getDepartmentID();
                String isManagerDepartment = healthcareWorker.getIsManagerDepartment();
                String medicalRecordID = healthcareWorker.getMedicalRecordID();
                bufferedWriter.write(fullname + "|" + birthday + "|" + gender + "|" + phone + "|" + country 
                    + "|" + id + "|" + type + "|" + yearsOfExperience + "|" + salary + "|" + departmentID
                    + "|" + isManagerDepartment + "|" + medicalRecordID + "\n");
            }
            // Đóng bufferedWrite và fileWrite sau khi write xong.
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
