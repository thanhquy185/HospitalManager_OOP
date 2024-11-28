package MedicalRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Common.*;
import Sick.*;
import HealthcareWorker.HealthcareWorker;
import HealthcareWorker.HealthcareWorkerManager;
import Patient.*;
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
    public void setList(ArrayList<MedicalRecord> list) {
        MedicalRecordManager.list = list;
    }
    public void setNumbers(int numbers) {
        MedicalRecordManager.numbers = numbers;
    }
    public static MedicalRecordManager getInstance() {
        if(MedicalRecordManager.instance == null) {
            MedicalRecordManager.instance = new MedicalRecordManager();
        }
        return MedicalRecordManager.instance;
    }
    public ArrayList<MedicalRecord> getList() {
        return MedicalRecordManager.list;
    }
    public int getNumbers() {
        return MedicalRecordManager.numbers;
    }

    // Methods
    // - CRUD (Thêm sửa xoá các đối tượng trong lớp quản lý)
    @Override
    public String getInfoByIndex(int index) {
        MedicalRecord medicalRecord = findObjectByIndex(index);
		if(medicalRecord == null) return null;
		return medicalRecord.getInfo();
    }
    @Override
    public String getInfoById(String id) {
        MedicalRecord medicalRecord = findObjectById(id);
		if(medicalRecord == null) return null;
		return medicalRecord.getInfo();
    }
    @Override
    public void show() {
		System.out.println("*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*");
	    System.out.println("| MÃ BỆNH ÁN | NGÀY LẬP HỒ SƠ | NGÀY KHOÁ HỒ SƠ | LOẠI HỒ SƠ | TIỀN PHÍ |               BỆNH NHÂN              |              NHÂN VIÊN              |              BỆNH              | MỨC ĐỘ |");  
	    System.out.println("*------------+----------------+-----------------+------------+----------+--------------------------------------+-------------------------------------+--------------------------------+--------*");
		for(MedicalRecord medicalRecord : MedicalRecordManager.list) {
			String id = medicalRecord.getId();
            String inputDay = medicalRecord.getInputDay().getDateFormatByCondition("has cross");
            String outputDay = medicalRecord.getOutputDay().getDateFormatByCondition("has cross");
            String type = medicalRecord.getType();
            Double fee = medicalRecord.getFee();

			String patientID = medicalRecord.getPatientID();
			String patientName = null;
			if(patientID != null) {
				for(Patient patient : PatientManager.getInstance().getList()) {
					if(patient.getId().equals(patientID)) {
						patientName = patient.getFullname();
						break;
					}
				}
			}

			String healthcareWorkerID = medicalRecord.getHealthcareWorkerID();
			String healthcareWorkerFullname = null;
			if(healthcareWorkerID != null) {
				for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
					if(healthcareWorker.getId().equals(healthcareWorkerID)) {
						healthcareWorkerFullname = healthcareWorker.getFullname();
						break;
					}
				}
			}

            String sickID = medicalRecord.getSickID();
			String sickName = null;
			if(sickID != null) {
				for(Sick sick : SickManager.getInstance().getList()) {
					if(sick.getId().equals(sickID)) {
						sickName = sick.getName();
						break;
					}
				}
			}

			String sickLevel = medicalRecord.getSickLevel();

			System.out.println(String.format("| %-10s | %-14s | %-15s | %-10s | %-8s | %-9s - %-24s | %-8s - %-24s | %-9s - %-18s | %-6s |",
				id, inputDay, outputDay, type, fee, patientID, patientName, healthcareWorkerID, healthcareWorkerFullname, sickID, sickName, sickLevel));
		}
		if(MedicalRecordManager.numbers >= 1)
	    	System.out.println("*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*");
	}
    @Override
	public MedicalRecord findObjectByIndex(int index) {
        if(index < 0 || index > MedicalRecordManager.numbers - 1) return null;
        return MedicalRecordManager.list.get(index);
	}
    @Override
    public MedicalRecord findObjectById(String id) {
        if(MedicalRecordManager.numbers == 0) return null;
        for(MedicalRecord medicalRecord : MedicalRecordManager.list) {
            if(medicalRecord.getId().equals(id)) return medicalRecord;
        }
        return null;
    }
    @Override
	public void add(MedicalRecord medicalRecord) {
		MedicalRecordManager.list.add(medicalRecord);
        MedicalRecordManager.numbers++;
	}
    @Override
	public void update(MedicalRecord medicalRecordUpdate, int choice) {
		Scanner sc = new Scanner(System.in);
		if(choice == 1 || choice == 4) {
			System.out.print(" - Nhập ngày lập Hồ sơ Bệnh án mới (dd-mm-yyyy hoặc ddmmyyyy): ");
			String inputDayStr = sc.nextLine();
			while(!Date.getInstance().isDateFormat(inputDayStr)
					|| !Date.getInstance().getDateFromDateFormat(inputDayStr).isDate()
					|| !Date.getInstance().checkBeforeAfterDate(
							PatientManager.getInstance().findObjectById(medicalRecordUpdate.getPatientID()).getBirthday(), Date.getInstance().getDateFromDateFormat(inputDayStr))
					|| !Date.getInstance().checkBeforeAfterDate(Date.getInstance().getDateFromDateFormat(inputDayStr), medicalRecordUpdate.getOutputDay())) {
				System.out.println("----- -----");
				System.out.println("! - NGÀY LẬP HỒ SƠ KHÔNG HỢP LỆ");
				System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
				inputDayStr = sc.nextLine();
			System.out.println("----- -----");
			}
			Date inputDayObj = Date.getInstance().getDateFromDateFormat(inputDayStr);
			medicalRecordUpdate.setInputDay(inputDayObj);
		}
		if(choice == 2 || choice == 4) {
			if(medicalRecordUpdate.getType().equals("Khám bệnh")) {
				System.out.println(" - Là Hồ sơ Khám bệnh nên ngày mở bằng ngày đóng");
			} else if(medicalRecordUpdate.getType().equals("Chữa bệnh")) {
				System.out.print(" - Nhập ngày khoá Hồ sơ Bệnh án mới (dd-mm-yyyy hoặc ddmmyyyy): ");
				String outputDayStr = sc.nextLine();
				while(!Date.getInstance().isDateFormat(outputDayStr)
						|| !Date.getInstance().getDateFromDateFormat(outputDayStr).isDate()
						|| !Date.getInstance().checkBeforeAfterDate(medicalRecordUpdate.getInputDay(), Date.getInstance().getDateFromDateFormat(outputDayStr))) {
					System.out.println("----- -----");
					System.out.println("! - NGÀY KHOÁ HỒ SƠ BỆNH ÁN KHÔNG HỢP LỆ");
					System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
					outputDayStr = sc.nextLine();
				System.out.println("----- -----");
				}
				Date outputDayObj = Date.getInstance().getDateFromDateFormat(outputDayStr);
				medicalRecordUpdate.setOutputDay(outputDayObj);
			}
		}
		if(choice == 3 || choice == 4) {
			System.out.print(" - Nhập mức độ Bệnh mới (Nhẹ, Vừa hoặc Nặng): ");
            String newSickLevel = sc.nextLine();
            while(!newSickLevel.equals("Nhẹ") && !newSickLevel.equals("Vừa") 
					&& !newSickLevel.equals("Nặng")) {
                System.out.println("----- -----");
                System.out.println("! - MỨC ĐỘ BỆNH KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại: ");
                newSickLevel = sc.nextLine();
            }
            medicalRecordUpdate.setSickLevel(newSickLevel);
		}
		medicalRecordUpdate.setFee(medicalRecordUpdate.calFee());
	}
	@Override
	public void remove(String id) {
		if(MedicalRecordManager.numbers == 0) return;
        for(int i = 0; i < MedicalRecordManager.numbers; i++) {
            if(MedicalRecordManager.list.get(i).getId().equals(id)) {
                MedicalRecordManager.list.remove(i);
                MedicalRecordManager.numbers--;
				return;
            }
        }
	}
	@Override
	public void sort(String condition) {
		// MedicalRecordManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
		switch (condition) {
			case "id asc": {
				MedicalRecordManager.list.sort(Comparator.comparing((MedicalRecord medicalRecord) -> medicalRecord.getId()));
				break;
			}
			case "id desc": {
				MedicalRecordManager.list.sort(Comparator.comparing((MedicalRecord medicalRecord) -> medicalRecord.getId()).reversed());
				break;
			}
			case "inputDay asc": {
				MedicalRecordManager.list.sort(Comparator.comparing(
						(MedicalRecord medicalRecord) -> medicalRecord.getInputDay().getDateFormatByCondition("has not cross")
					));
				break;
			}
			case "inputDay desc": {
				MedicalRecordManager.list.sort(Comparator.comparing(
						(MedicalRecord medicalRecord) -> medicalRecord.getInputDay().getDateFormatByCondition("has not cross")
					).reversed());
				break;
			}
			case "fee asc": {
				MedicalRecordManager.list.sort(Comparator.comparing((MedicalRecord medicalRecord) -> medicalRecord.getFee()));
				break;
			}
			case "fee desc": {
				MedicalRecordManager.list.sort(Comparator.comparing((MedicalRecord medicalRecord) -> medicalRecord.getFee()).reversed());
			}
		}
	}
    @Override
	public void loadFromFile() {
		File file = new File("src/Database/MedicalRecordDatabase.txt");
		if (!file.exists()) {
			System.out.println("Tệp tin quản lý dữ liệu về Bệnh án không tồn tại");
			return;
		}
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (true) {
				String line = bufferedReader.readLine();
				if (line == null) break;
				String[] info = line.split("\\|");
				String id = info[0];
				String inputDay = info[1];
				String outputDay = info[2];
				String type = info[3];
				Double fee = Double.parseDouble(info[4]);
				String patientID = null;
				if(!info[5].equals("null")) {
					patientID = info[5];
				}
				String healthcareWorkerID = null;
				if(!info[6].equals("null")) {
					healthcareWorkerID = info[6];
				}
				String sickID = null;
				if(!info[7].equals("null")) {
					sickID = info[7];
				}
				String sickLevel = null;
				if(!info[8].equals("null")) {
					sickLevel = info[8];
				}
				MedicalRecord newMedicalRecord = null;
                if(type.equals("Khám bệnh")) {
                    newMedicalRecord = new TestRecord(id, Date.getInstance().getDateFromDateFormat(inputDay),
                        Date.getInstance().getDateFromDateFormat(inputDay), type, fee, patientID, sickID, sickLevel, healthcareWorkerID);
                } else {
                    newMedicalRecord = new TreatmentRecord(id, Date.getInstance().getDateFromDateFormat(inputDay),
                        Date.getInstance().getDateFromDateFormat(outputDay), type, fee, patientID, sickID, sickLevel,  healthcareWorkerID);
                }
				add(newMedicalRecord);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void saveToFile() {
		File file = new File("src/Database/MedicalRecordDatabase.txt");
		if (!file.exists()) {
			System.out.println("Tệp tin quản lý dữ liệu về Bệnh án không tồn tại");
			return;
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (MedicalRecord medicalRecord : MedicalRecordManager.list) {
				String id = medicalRecord.getId();
				String inputDay = medicalRecord.getInputDay().getDateFormatByCondition("has cross");
				String outputDay = medicalRecord.getOutputDay().getDateFormatByCondition("has cross");
				String type = medicalRecord.getType();
				Double fee = medicalRecord.getFee();
				String patientID = medicalRecord.getPatientID();
				String healthcareWorkerID = medicalRecord.getHealthcareWorkerID();
				String sickID = medicalRecord.getSickID();
				String sickLevel = medicalRecord.getSickLevel();
				bufferedWriter.write(id + "|" + inputDay + "|" + outputDay + "|" + type + "|" + fee + "|" + patientID 
					+ "|" + healthcareWorkerID + "|" + sickID + "|" + sickLevel + "\n");
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
