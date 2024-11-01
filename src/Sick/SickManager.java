package Sick;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.CRUD;
import Department.DepartmentManager;

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
    public String getInfoByIndex(int index) {
        if(index < 0 || index > SickManager.numbers) return null;
        return SickManager.list.get(index).getInfo();
    }
    @Override
    public String getInfoById(String id) {
        int index = findIndexById(id);
        if(index == -1) return null;
        return SickManager.list.get(index).getInfo();
    }
    @Override
    public void show() {
        System.out.println("*----------------------------------------------------------------------------------*");
	    System.out.println("|  MÃ BỆNH  |         TÊN BỆNH         |                    KHOA                   |");
	    System.out.println("*-----------+--------------------------+-------------------------------------------*");
        for(Sick sick : list) {
            String id = sick.getId();
            String name = sick.getName();
            String idDepartment = null;
            if(sick.getDepartmentID() != null) {
                if(!sick.getDepartmentID().equals("null"))
                    idDepartment = sick.getDepartmentID();
            }
            String nameDepartment = null;
            if(idDepartment != null) {
                nameDepartment = DepartmentManager.getInstance().findObjectById(idDepartment).getName();
            }
            System.out.println(String.format("| %-9s | %-24s | %8s - %-30s |", id, name, idDepartment, nameDepartment));
        }
		if(SickManager.numbers >= 1)
            System.out.println("*----------------------------------------------------------------------------------*");
    }
    @Override
    public int findIndexById(String id){
        for(int i = 0; i < SickManager.numbers; i++){
            if(SickManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Sick findObjectById(String id){
        int index = findIndexById(id);
        if(index == -1) return null;
        return SickManager.list.get(index);
    }
     @Override
    public Sick findObjectByIndex(int index){
        if(index < 0 || index > SickManager.numbers) return null;
        return SickManager.list.get(index);
    }
    @Override
    public void add(Sick Sick){
        SickManager.list.add(Sick);
        SickManager.numbers++;
    }
    @Override
    public void update(Sick Sick){
        SickManager.list.set(findIndexById(Sick.getId()), Sick);
    }
    @Override
    public void removeOne(String id){
        SickManager.list.remove(findIndexById(id));
        SickManager.numbers--;
    }
    @Override
    public void removeAll(){
        SickManager.list.clear();
        SickManager.numbers = 0;
    }
    @Override
    public void sort(String condition){
        SickManager.list.sort(null);
    }
    @Override
    public void loadFromFile() {
        File file = new File("HospitalManager/src/Database/SickDatabase.txt");
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
                String departmentID = info[2].trim();
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
        File file = new File("HospitalManager/src/Database/SickDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Bệnh không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Sick sick : SickManager.list) {
                bufferedWriter.write(String.format(String.format("| %-9s | %-24s | %-8s |\n",
                    sick.getId(), sick.getName(), sick.getDepartmentID())));
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
