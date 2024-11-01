package Department;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;
import HealthcareWorker.*;

public class DepartmentManager implements CRUD<Department> {
    // Properties
    private static DepartmentManager instance;
    private static ArrayList<Department> list;
    private static int numbers;

    // Constructors
    public DepartmentManager() {
        DepartmentManager.list = new ArrayList<>();
        DepartmentManager.numbers = 0;
    }
    public DepartmentManager(ArrayList<Department> list, int numbers) {
        DepartmentManager.list = list;
        DepartmentManager.numbers = numbers;
    }

    // Setter - Getter
    public void setList(ArrayList<Department> list) {
        DepartmentManager.list = list;
    }
    public void setNumbers(int numbers) {
        DepartmentManager.numbers = numbers;
    }
    public static DepartmentManager getInstance() {
        if(DepartmentManager.instance == null) {
            DepartmentManager.instance = new DepartmentManager();
        }
        return DepartmentManager.instance;
    }
    public ArrayList<Department> getList() {
        return DepartmentManager.list;
    }
    public int getNumbers() {
        return DepartmentManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public String getInfoByIndex(int index) {
        if(index < 0 || index > DepartmentManager.numbers) return null;
        return DepartmentManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        Department department = findObjectById(id);
        if(department == null) return null;
        return department.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*-----------------------------------------------------------------------------------------*");
	    System.out.println("| MÃ KHOA  |            TÊN KHOA            |             TRƯỞNG KHOA             | PHÒNG |");
	    System.out.println("*----------+--------------------------------+-------------------------------------+-------*");
        for(Department department : list) {
            String id = department.getId();
            String name = department.getName();
            String managerID = null;
            if(department.getManagerID() != null) {
                if(!department.getManagerID().equals("null"))
                    managerID = department.getManagerID();
            }
            String nameManager = null;
            if(managerID != null) {
                nameManager = HealthcareWorkerManager.getInstance().findObjectById(managerID).getFullname();
            }
            String room = department.getRoom();
            System.out.println(String.format("| %-8s | %-30s | %8s - %-24s | %-4s |", id, name, managerID, nameManager, room));
        }
        if(DepartmentManager.numbers >= 1)
	        System.out.println("*-----------------------------------------------------------------------------------------*");

    }
    @Override
    public int findIndexById(String id){
        for(int i = 0; i < DepartmentManager.numbers; i++){
            if(DepartmentManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Department findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return DepartmentManager.list.get(index);
    }
    @Override
    public Department findObjectByIndex(int index){
        if(index < 0 || index > DepartmentManager.numbers) return null;
        return DepartmentManager.list.get(index);
    }
    @Override
    public void add(Department Department){
        DepartmentManager.list.add(Department);
        DepartmentManager.numbers++;
    }
    @Override
    public void update(Department Department){
        DepartmentManager.list.set(findIndexById(Department.getId()), Department);
    }
    @Override
    public void removeOne(String id){
        DepartmentManager.list.remove(findIndexById(id));
        DepartmentManager.numbers--;
    }
    @Override
    public void removeAll(){
        DepartmentManager.list.clear();
        DepartmentManager.numbers = 0;
    }
    @Override
    public void sort(String condition){
        DepartmentManager.list.sort(null);
    }
    @Override
    public void loadFromFile() {
        File file = new File("HospitalManager/src/Database/DepartmentDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String id = info[0].trim();
                String name = info[1].trim();
                String managerID = info[2].trim();
                String room = info[3].trim();
                System.out.println(id + "|" + name + "|" + managerID + "|" + room);
                Department newDepartment = new Department(id, name, managerID, room);
                add(newDepartment);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveToFile() {
        File file = new File("HospitalManager/src/Database/DepartmentDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Department department : DepartmentManager.list) {
                bufferedWriter.write(String.format("| %8s | %-30s | %-8s | %4s |\n", department.getId(),
                    department.getName(),department.getManagerID(),department.getRoom()));
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
