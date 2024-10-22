package MedicalRecord;

import Common.CRUD;

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
    public int findIndexById(String id) {
        for(int i = 0; i < MedicalRecordManager.numbers; i++){
            if(MedicalRecordManager.list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
	public MedicalRecord findOneById(String id) {
		return MedicalRecordManager.list.get(findIndexById(id));
	}
	@Override
	public MedicalRecord findOneByCondition(String condition) {
		// for (int i = 0; i < list.size(); i++) {
		// 	if (list.get(i).getId() == medicalRecord) {
		// 		System.out.println("Tìm thấy hồ sơ bệnh án");
		// 		medicalRecord.toString();
		// 		return list.get(i);
		// 	}
		// }
		// System.out.println("Không tìm thấy hồ sơ bệnh án");
		// return null;
        return new MedicalRecord();
	}
	@Override
	public ArrayList<MedicalRecord> findAllByCondition(String medicalRecord) {
		return new ArrayList<MedicalRecord>();
	}
	@Override
	public void sort() {
		// Collections.sort(list, new Comparator<MedicalRecord>() {
		// 	@Override
		// 	public int compare(MedicalRecord o1, MedicalRecord o2) {
		// 		return o1.getId().compareTo(o2.getId());
		// 	}
		// });
	}
}
