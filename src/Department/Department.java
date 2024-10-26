package Department;

public class Department {
    // Properties
    private String id;
    private String name;
    private String idManager;
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
        this.idManager = null;
        this.room = null;
    }
    public Department(String name, String idManager, String room) {
        Department.countDepartmentCreated++;
        this.id = getFormatId();
        this.name = name;
        this.idManager = idManager;
        this.room = room;
    }
    public Department(String id, String name, String idManager, String room) {
        this.id = id;
        this.name = name;
        this.idManager = idManager;
        this.room = room;
    }
    public Department(Department department) {
        this.id = department.id;
        this.name = department.name;
        this.idManager = department.idManager;
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
    public void setIdManager(String idManager) {
        this.idManager = idManager;
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
    public String getIdManager() {
        return this.idManager;
    }
    public String getRoom() {
        return this.room;
    }
    public int getCountDepartmentCreated() {
        return Department.countDepartmentCreated;
    }

    // Methods
    // Methods
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
        for(int i = 0; i < postfix.length(); i++) {
            int charUnicode = (int) postfix.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
	// - Lấy id có đúng định dạng DEPxxxxx
    private String getFormatId() {
        String postfix = String.format("%05d", Department.countDepartmentCreated);
        return "DEP" + postfix;
    }
    // - Hàm lấy ra thông tinnn của Khoa
    public String getInfo() {
        return this.id + " | " + this.name + " | " + this.idManager + " | " + this.room;
    }
}
