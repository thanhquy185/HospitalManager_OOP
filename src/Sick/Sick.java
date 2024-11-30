package Sick;

import java.util.Scanner;

import Common.*;
import Department.*;

public class Sick {
    // Properties
	private String id;
	private String name;
	private String departmentID;
	private static int countSickCreated;

	// Static
	static {
		Sick.countSickCreated = 0;
	}

    // Constructor
	public Sick() {
		this.id = null;
		this.name = null;
		this.departmentID = null;
	}
	public Sick(String name, String departmentID) {
		Sick.countSickCreated++;
		this.departmentID = departmentID;
		this.id = getFormatId();
		this.name = name;
	}
	public Sick(String id, String name, String departmentID) {
		this.id = id;
		this.departmentID = departmentID;
		this.name = name;
	}
	public Sick (Sick sick) {
		this.id = sick.id;
		this.name = sick.name;
		this.departmentID = sick.departmentID;
	}

    // Setter-Getter
	public void setId(String id) {
		if(!isFormatId(id))
			this.id = null;
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public static void setCountSickCreated(int countSickCreated) {
		Sick.countSickCreated = countSickCreated;
	}
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getDepartmentID() {
		return this.departmentID;
	}
	public static int getCountSickCreated() {
		return Sick.countSickCreated;
	}

	// Methods
    // - Kiểm tra có đúng định dạng SICKxxxxx
    private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 9 ký tự
        if(id.length() != 9) return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 4);
        if(!prefix.equals("SICK")) return false;
        // -- Kiểm tra hậu tố
		String postfix = id.substring(4);
		for(int i = 0; i < postfix.length(); i++) {
			int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
	// - Lấy id có đúng định dạng SICKxxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", Sick.countSickCreated);
        return "SICK" + postfix;
    }
	public void setInfo() {
		Scanner sc = new Scanner(System.in);
		// Nhập tên Bệnh
		System.out.print(" - Nhập tên Bệnh: ");
		String name = sc.nextLine();
        while(!myCharacterClass.getInstance().isValidName(name)) {
            System.out.println("----- -----");
            System.out.println("! - TÊN KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại: ");
            name = sc.nextLine();
            System.out.println("----- -----");
        }
		// Chọn Khoa sẽ quản lý Bệnh được tạo
		System.out.println(" - Chọn Khoa thuộc về");
		// 1 - DEP00001 | Tai-Mũi-Họng
		// 2 - DEP00002 | Thận
		// ...
		int numberList = 0;
		for(Department department : DepartmentManager.getInstance().getList()) {
		    System.out.println(++numberList + " - " + department.getId() + " | " + department.getName());
		}
		// Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
		System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
		String info = sc.nextLine();
		while(!myCharacterClass.getInstance().onlyHasLetterAndDigit(info)
				|| (!myCharacterClass.getInstance().hasAllCharacterIsLetter(info) 
						&& !myCharacterClass.getInstance().hasAllCharacterIsNumber(info))
                || (myCharacterClass.getInstance().hasAllCharacterIsLetter(info)
                        && DepartmentManager.getInstance().findObjectById(info) == null)
                || (myCharacterClass.getInstance().hasAllCharacterIsNumber(info)
                    	&& DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
		    System.out.println("----- -----");
		    System.out.println("! - KHOA KHÔNG HỢP LỆ");
		    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
		    info = sc.nextLine();
		}
		// Lấy mã Khoa đã được chọn
		String departmentID = myCharacterClass.getInstance().hasAllCharacterIsLetter(info)
		    ? DepartmentManager.getInstance().findObjectById(info).getId()
		    : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();

		// Gán dữ liệu đã nhập cho đối tượng
		countSickCreated++;
		this.id = getFormatId();
		this.name = name;
		this.departmentID = departmentID;
	}
	public String getInfo() {
		return this.id + " | " + this.name + " | " + this.departmentID;
	}
}
