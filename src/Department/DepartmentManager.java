package Department;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.*;
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
    public Department findObjectByIndex(int index){
        if(index < 0 || index > DepartmentManager.numbers - 1) return null;
        return DepartmentManager.list.get(index);
    }
    @Override
    public Department findObjectById(String id){
        if(DepartmentManager.numbers == 0) return null;
        for(Department department : DepartmentManager.list) {
            if(department.getId().equals(id)) return department;
        }
        return null;
    }
    @Override
    public String getInfoByIndex(int index) {
        Department department = findObjectByIndex(index);
        if(department == null) return null;
        return department.getInfo();
    }
    @Override
    public String getInfoById(String id) {
        Department department = findObjectById(id);
        if(department == null) return null;
        return department.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*------------------------------------------------------------------------------------------*");
	    System.out.println("|  MÃ KHOA  |            TÊN KHOA            |             TRƯỞNG KHOA             | PHÒNG |");
	    System.out.println("*-----------+--------------------------------+-------------------------------------+-------*");
        for(Department department : list) {
            String id = department.getId();
            String name = department.getName();
            String managerID = department.getManagerID();
            String managerName = null;
            if(managerID != null) {
                managerName = HealthcareWorkerManager.getInstance().findObjectById(managerID).getFullname();
            }
            String room = department.getRoom();
            System.out.println(String.format("| %-9s | %-30s | %-8s - %-24s | %-5s |", id, name, managerID, managerName, room));
        }
        if(DepartmentManager.numbers >= 1)
	        System.out.println("*------------------------------------------------------------------------------------------*");

    }
    @Override
    public void add(Department Department){
        DepartmentManager.list.add(Department);
        DepartmentManager.numbers++;
    }
    @Override
    public void update(Department departmentUpdate, int choice) {
        Scanner sc = new Scanner(System.in);
        if(choice == 1 || choice == 4) {
            System.out.print(" - Nhập tên mới: ");
            String newName = sc.nextLine();
            departmentUpdate.setName(newName);
        }
        if(choice == 2 || choice == 4) {
            // Chọn Bác sĩ cần làm trưởng Khoa từ danh sách (phải cùng Khoa hiện tại và là Bác sĩ)
            // - Lọc ra các Bác sĩ cùng Khoa, chưa là trưởng Khoa và là Bác sĩ
            ArrayList<HealthcareWorker> doctorSearchList = new ArrayList<>();
            for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                if(healthcareWorker.getDepartmentID().equals(departmentUpdate.getId())
                    && healthcareWorker.getIsManagerDepartment().equals("Không")
                    && healthcareWorker.getType().equals("Bác sĩ")) doctorSearchList.add(healthcareWorker);
            }
            if(doctorSearchList.size() == 0) {
                System.out.println("Hiện tại Khoa " + departmentUpdate.getId() + " - " + departmentUpdate.getName() 
                    + " không có Bác sĩ cùng Khoa nào để có thể bổ nhiểm làm trưởng Khoa");
            } else {
                // Thiết lập lại trưởng Khoa cũ
                HealthcareWorkerManager.getInstance().findObjectById(departmentUpdate.getManagerID()).setIsManagerDepartment("Không");

                // - Chọn Bác sĩ từ danh sách
                System.out.println(" - Chọn Bác sĩ cần bổ nhiệm");
                // 1 - HEW00001 | Thanh Quy
                // 2 - HEW00002 | Đức Quý An
                // ...
                int numberList = 0;
                for(HealthcareWorker healthcareWorker : doctorSearchList) {
                    System.out.println(++numberList + " - " + healthcareWorker.getId() 
                        + " | " + healthcareWorker.getFullname() + " | " + healthcareWorker.getType());
                }
                // Cho phép chọn numberList - id (chọn 1 hoặc chọn DOC00001)
                System.out.print("? - Chọn (số thứ tự hoặc mã Bác sĩ): ");
                String info = sc.nextLine();
                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                            && (HealthcareWorkerManager.getInstance().findObjectById(info) == null
                                || !HealthcareWorkerManager.getInstance().findObjectById(info).getDepartmentID().equals(departmentUpdate.getId())))
                        || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info) && (Integer.parseInt(info) < 1 || Integer.parseInt(info) > numberList
                            || doctorSearchList.get(Integer.parseInt(info) - 1) == null))) {
                    System.out.println("----- -----");
                    System.out.println("! - BÁC SĨ KHÔNG HỢP LỆ");
                    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bác sĩ): ");
                    info = sc.nextLine();
                }
                // Lấy thông tin của Bác sĩ bổ nhiệm làm trưởng Khoa
                HealthcareWorker doctorSelect = null;
                if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                    doctorSelect = HealthcareWorkerManager.getInstance().findObjectById(info);
                } else {
                    doctorSelect =  doctorSearchList.get(Integer.parseInt(info) - 1);
                }

                // Thiết lập trưởng Khoa mới
                departmentUpdate.setManagerID(doctorSelect.getId());
                doctorSelect.setIsManagerDepartment("Có");
            }
        }
        if(choice == 3 || choice == 4) {
            System.out.print(" - Nhập số phòng mới: ");
            String newRoom = sc.nextLine();
            departmentUpdate.setRoom(newRoom);
        }
    }
    @Override
    public void remove(String id){
        if(DepartmentManager.numbers == 0) return;
        for(int i = 0; i < DepartmentManager.numbers; i++) {
            if(DepartmentManager.list.get(i).getId().equals(id)) {
                DepartmentManager.list.remove(i);
                DepartmentManager.numbers--;
                return;
            }
        }
    }
    @Override
    public void sort(String condition){
        // DepartmentManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch (condition) {
            case "id asc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getId()));
                break;
            }
            case "id desc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getId()).reversed());
                break;
            }
            case "name asc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getName()));
                break;
            }
            case "name desc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getName()).reversed());
                break;
            }
            case "room asc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getRoom()));
                break;
            }
            case "room desc": {
                DepartmentManager.list.sort(Comparator.comparing((Department department) -> department.getRoom()).reversed());
                break;
            }
        }
    }
    @Override
    public void loadFromFile() {
        File file = new File("src/Database/DepartmentDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Khoa không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String id = info[0];
                String name = info[1];
                String managerID = null;
                if(!info[2].equals("null"))
                    managerID = info[2];
                String room = info[3];
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
        File file = new File("src/Database/DepartmentDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Khoa không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Department department : DepartmentManager.list) {
                String id = department.getId();
                String name = department.getName();
                String managerID = department.getManagerID();
                String room = department.getRoom();
                bufferedWriter.write(id + "|" + name + "|" + managerID + "|" + room + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
