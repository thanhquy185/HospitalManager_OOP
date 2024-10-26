package MedicalRecord;

import Common.CRUD;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.ArrayList;

public class MedicalRecordManager implements CRUD<MedicalRecord> {
    // Properties
    private static MedicalRecordManager instance;
    private static ArrayList<MedicalRecord> list;
    private static int numbers;

    // Constructors
    public MedicalRecordManager() {
        MedicalRecordManager.list = new ArrayList<MedicalRecord>();
        MedicalRecordManager.numbers = 0;
    }
    public MedicalRecordManager(ArrayList<MedicalRecord> list, int numbers) {
        MedicalRecordManager.list = list;
        MedicalRecordManager.numbers = numbers;
    }

    // Setter - Getter
    public static void setList(ArrayList<MedicalRecord> list) {
        MedicalRecordManager.list = list;
    }
    public static void setNumbers(int numbers) {
        MedicalRecordManager.numbers = numbers;
    }
    public static MedicalRecordManager getInstance() {
        if(MedicalRecordManager.instance == null) {
            MedicalRecordManager.instance = new MedicalRecordManager();
        }
        return MedicalRecordManager.instance;
    }
    public static ArrayList<MedicalRecord> getList() {
        return MedicalRecordManager.list;
    }
    public static int getNumbers() {
        return MedicalRecordManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public void show() {}
    @Override
	public void add(MedicalRecord medicalRecord) {
		MedicalRecordManager.list.add(medicalRecord);
        MedicalRecordManager.numbers++;
	}
    @Override
	public void update(MedicalRecord medicalRecord) {
		MedicalRecordManager.list.set(findIndexById(medicalRecord.getId()), medicalRecord);
	}
	@Override
	public void removeOne(String id) {
		MedicalRecordManager.list.remove(findIndexById(id));
        MedicalRecordManager.numbers--;
	}
	@Override
	public void removeAll() {
		MedicalRecordManager.list.clear();
        MedicalRecordManager.numbers = 0;
	}
    @Override
    public String getInfoByIndex(int index) {
        return "";
    }
    @Override
    public String getInfoById(String id) {
        return "";
    }
	@Override
    public int findIndexById(String id) {
        for(int i = 0; i < MedicalRecordManager.numbers; i++){
            if(MedicalRecordManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
	public MedicalRecord findObjectByIndex(int index) {
        if(index < 0 || index > MedicalRecordManager.numbers) return null;
        return MedicalRecordManager.list.get(index);
	}
    @Override
    public MedicalRecord findObjectById(String id) {
        int index = findIndexById(id);
        if(index == -1) return null;
        return MedicalRecordManager.list.get(index);
    }
	@Override
	public MedicalRecord findObjectByCondition(String condition) {
        return null;
	}
	@Override
	public ArrayList<MedicalRecord> findObjectsByCondition(String medicalRecord) {
		return null;
	}
	@Override
	public void sort(String condition) {
		// Collections.sort(list, new Comparator<MedicalRecord>() {
		// 	@Override
		// 	public int compare(MedicalRecord o1, MedicalRecord o2) {
		// 		return o1.getId().compareTo(o2.getId());
		// 	}
		// });
	}
    @Override
    public void loadFromFile() {

    }
    @Override
    public void saveToFile() {
        
    }
}
