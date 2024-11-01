package Sick;

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
	public String getInfo() {
		return this.id + " | " + this.name + " | " + this.departmentID;
	}
}
