package Sick;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;
import Common.myCharacterClass;
import Department.*;

public class SickManager implements CRUD<Sick> {
    // Properties
    private static SickManager instance;
    private static ArrayList<Sick> list;
    private static int numbers;

    // Constructors
    public SickManager() {
        SickManager.list = new ArrayList<>();
        SickManager.numbers = 0;
    }
    public SickManager(ArrayList<Sick> list, int numbers) {
        SickManager.list = list;
        SickManager.numbers = numbers;
    }

    // Setter - Getter
    public void setList(ArrayList<Sick> list) {
        SickManager.list = list;
    }
    public void setNumbers(int numbers) {
        SickManager.numbers = numbers;
    }
    public static SickManager getInstance() {
        if(SickManager.instance == null) {
            SickManager.instance = new SickManager();
        }
        return SickManager.instance;
    }
    public ArrayList<Sick> getList() {
        return SickManager.list;
    }
    public int getNumbers() {
        return SickManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
   public Sick findObjectByIndex(int index){
       if(index < 0 || index > SickManager.numbers - 1) return null;
       return SickManager.list.get(index);
   }
    @Override
    public Sick findObjectById(String id){
        if(SickManager.numbers == 0) return null;
        for(Sick sick : SickManager.list) {
            if(sick.getId().equals(id)) return sick;
        }
        return null;
    }
    @Override
    public String getInfoByIndex(int index) {
        if(index < 0 || index > SickManager.numbers - 1) return null;
        return SickManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        Sick sick = findObjectById(id);
        if(sick == null) return null;
        return sick.getInfo();
    }
    @Override
    public void show() {
        System.out.println("*----------------------------------------------------------------------------------*");
	    System.out.println("|  MÃ BỆNH  |         TÊN BỆNH         |                    KHOA                   |");
	    System.out.println("*-----------+--------------------------+-------------------------------------------*");
        for(Sick sick : list) {
            String id = sick.getId();
            String name = sick.getName();
            String departmentID = sick.getDepartmentID();
            String departmentName = null;
            if(departmentID != null) {
                departmentName = DepartmentManager.getInstance().findObjectById(departmentID).getName();
            }
            System.out.println(String.format("| %-9s | %-24s | %8s - %-30s |", id, name, departmentID, departmentName));
        }
		if(SickManager.numbers >= 1)
            System.out.println("*----------------------------------------------------------------------------------*");
    }
    @Override
    public void add(Sick Sick){
        SickManager.list.add(Sick);
        SickManager.numbers++;
    }
    @Override
    public void update(Sick sickUpdate, int choice) {
        Scanner sc = new Scanner(System.in);

        if(choice == 1 || choice == 3) {
            System.out.print(" - Nhập tên mới: ");
            String newName = sc.nextLine();
            sickUpdate.setName(newName);
        }
        if(choice == 2 || choice == 3) {
            System.out.println(" - Chọn Khoa quản lý mới");
            // 1 - DEP00001 | Tai-Mũi-Họng
            // 2 - DEP00002 | Thận
            // ...
            int numberList = 1;
            for(Department department : DepartmentManager.getInstance().getList()) {
                System.out.println(numberList++ + " - " + department.getId() + " | " + department.getName());
            }
            // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
            System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
            String info = sc.nextLine();
            while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                        && DepartmentManager.getInstance().findObjectById(info) == null)
                    || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                        && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                System.out.println("----- -----");
                System.out.println("! - KHOA KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                info = sc.nextLine();
            }
            // Lấy mã Khoa đã được chọn
            String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                ? DepartmentManager.getInstance().findObjectById(info).getId()
                : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();
            sickUpdate.setDepartmentID(newDepartmentID);
        }
    }
    @Override
    public void remove(String id){
        if(SickManager.numbers == 0) return;
        for(int i = 0; i < SickManager.numbers; i++) {
            if(SickManager.list.get(i).equals(id)) {
                SickManager.list.remove(i);
                SickManager.numbers--;
            }
        }
    }
    @Override
    public void sort(String condition){
        // SickManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch (condition) {
            case "id asc": {
                SickManager.list.sort(Comparator.comparing((Sick sick) -> sick.getId()));
                break;
            }
            case "id desc": {
                SickManager.list.sort((Comparator.comparing((Sick sick) -> sick.getId())).reversed());
                break;
            }
            case "name asc": {
                SickManager.list.sort(Comparator.comparing((Sick sick) -> sick.getName()));
                break;
            }
            case "name desc": {
                SickManager.list.sort((Comparator.comparing((Sick sick) -> sick.getName())).reversed());
                break;
            }
        }
    }
    @Override
    public void loadFromFile() {
        File file = new File("src/Database/SickDatabase.txt");
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
                String id = info[0];
                String name = info[1];
                String departmentID = null;
                if(!info[2].equals("null")) {
                    departmentID = info[2];
                }
                Sick newSick = new Sick(id, name, departmentID);
                add(newSick);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void saveToFile() {
        File file = new File("src/Database/SickDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Sick sick : SickManager.list) {
                String id = sick.getId();
                String name = sick.getName();
                String departmentID = sick.getDepartmentID();
                bufferedWriter.write(id + "|" + name + "|" + departmentID + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
