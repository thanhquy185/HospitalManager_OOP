package Department;

import java.util.Scanner;

import Common.myClass;

public class Department {
    // Properties
    private String id;
    private String name;
    private String managerID;
    private String room;
    private static int countDepartmentCreated;

    // Static
    static {
        Department.countDepartmentCreated = 0;
    }

    // Construtors
    public Department() {
        this.id = null;
        this.name = null;
        this.managerID = null;
        this.room = null;
    }
    public Department(String name, String managerID, String room) {
        countDepartmentCreated++;
        this.id = getFormatId();
        this.name = name;
        this.managerID = managerID;
        this.room = room;
    }
    public Department(String id, String name, String managerID, String room) {
        this.id = id;
        this.name = name;
        this.managerID = managerID;
        this.room = room;
    }
    public Department(Department department) {
        this.id = department.id;
        this.name = department.name;
        this.managerID = department.managerID;
        this.room = department.room;
    }

    // Setter - Getter
    public void setId(String id) {
        if(!isFormatId(id))
            this.id = null;
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public static void setCountDepartmentCreated(int countDepartmentCreated) {
        Department.countDepartmentCreated = countDepartmentCreated;
    }
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getManagerID() {
        return this.managerID;
    }
    public String getRoom() {
        return this.room;
    }
    public int getCountDepartmentCreated() {
        return Department.countDepartmentCreated;
    }

    // Methods  
    // - Hàm kiểm tra tên phòng có hợp lệ hay không (A.001, A.002, ..., Z.999)
    boolean isValidRoomNameFormat(String name) {
        if(name.length() != 5) return false;
        // -- Kiểm tra ký tự đầu tiên có là ký tự chữ cái in hoa
        if("QWERTYUIOPASDFGHJKLZXCVBNM".indexOf(name.charAt(0)) == -1) return false;
        // -- Kiểm tra ký tự thứ hai có là dấu chấm
        if(name.charAt(1) != '.') return false;
        // -- Kiểm tra các ký tự còn lại có là ký tự số hay không
        for(int i = 2; i < name.length(); i++) {
            int unicode = (int) name.charAt(i);
            if(unicode < 48 || unicode > 57) return false;
        }
        // -- Kiểm tra số phòng đó đã tồn tại hay chưa ?
        for(Department department : DepartmentManager.getInstance().getList()) {
            if(department.getRoom().equals(name)) return false;
        }
        return true;
    }
	// - Kiểm tra có đúng định dạng DEPxxxxx
	private boolean isFormatId(String id) {
        // -- Nếu không phải là chuỗi 8 ký tự
        if(id.length() != 8)
            return false;
        // -- Kiểm tra tiền tối
        String prefix = id.substring(0, 3);
        if(!prefix.equals("DEP")) return false;
        // -- Kiểm tra hậu tố
        String postfix = id.substring(3);
        if(!myClass.getInstance().hasAllCharacterIsNumber(postfix)) return false;
        return true;
    }
	// - Lấy id có đúng định dạng DEPxxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", Department.countDepartmentCreated);
        return "DEP" + postfix;
    }
    // - Hàm gán thông tin của Khoa
    public void setInfoWithNoManager() {
        Scanner sc = new Scanner(System.in);
        // Nhập tên Khoa
        System.out.print(" - Nhập tên Khoa: ");
        String name = sc.nextLine();
        while(!myClass.getInstance().isValidName(name)) {
            System.out.println("----- -----");
            System.out.println("! - TÊN KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại: ");
            name = sc.nextLine();
            System.out.println("----- -----");
        }
        // Nhập số phòng Khoa
        System.out.print(" - Nhập số phòng: ");
        String room = sc.nextLine();
        while(!isValidRoomNameFormat(room)) {
            System.out.println("----- -----");
            System.out.println("! - SỐ PHÒNG KHÔNG HỢP LỆ");
            System.out.print("?! - Nhập lại: ");
            room = sc.nextLine();
        }

        // Gán dữ liệu đã nhập cho đối tượng
        countDepartmentCreated++;
        this.id = getFormatId();
        this.name = name;
        this.managerID = null;
        this.room = room;
    }
    // - Hàm lấy ra thông tin của Khoa
    public String getInfo() {
        return this.id + " | " + this.name + " | " + this.managerID + " | " + this.room;
    }
}
