package Account;

import java.util.Scanner;

import Common.myCharacterClass;

public class Account {
    // Properties
    private String username;
    private String password;
    private String type;

    // Constructors
    public Account() {
        this.username = null;
        this.password = null;
        this.type = null;
    }
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Account(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // Setter - Getter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getType() {
        return this.type;
    }

    // Methods
    // - Kiểm tra tên tài khoản hoặc mật khẩu có hợp lệ hay không ?
    public boolean isValidAccount(String condition) {
        String data = condition.equals("username") ? this.username : this.password;
        if(!myCharacterClass.getInstance().onlyHasLetterAndDigit(data)) return false;
        return true;
    }
    // - Kiểm tra có phải là tài khoản Quản lý hay không (mặc định là admin - 1)
    public boolean isAdmin() {
        if(!this.username.equals("admin")
            || !this.password.equals("1")
            || !this.type.equals("Quản lý")) return false;
        return true;
    }
    // -- Kiểm tra có phải Người dùng là Bác sĩ / Y tá (Nhân viên y tế)
    public boolean isHealthcareWorker() {
        if(!this.username.substring(0, 3).equals("HEW")
            && !this.type.equals("Nhân viên")) return false;
        return true;
    }
    // -- Kiểm tra có phải Người dùng là Bệnh nhân
    public boolean isPatient() {
        if(!this.username.substring(0, 3).equals("PAT")
            && !this.type.equals("Bệnh nhân")) return false;
        return true;
    }
    // 
    public void setInfo() {
        Scanner sc = new Scanner(System.in);
        // Nhập thông tin tài khoản
        System.out.print(" - Nhập tên tài khoản: ");
        String newUsername = sc.nextLine();
        System.out.print(" - Nhập mật khẩu: ");
        String newPassword = sc.nextLine();
        while(!AccountManager.getInstance().canRegister(newUsername, newPassword)) {
            System.out.println("----- -----");
            System.out.println("! - KHÔNG THỂ ĐĂNG KÝ");
            System.out.print(" - Nhập lại tên tài khoản: ");
            newUsername = sc.nextLine();
            System.out.print(" - Nhập lại mật khẩu: ");
            newPassword = sc.nextLine();
        }

        // Gán dữ liệu đã nhập cho đối tượng
        this.username = newUsername;
        this.password = newPassword;
        this.type = "Người dùng mới";
    }
    //
    public String getInfo() {
        return this.username + " | " + this.password + " | " + this.type;
    }
}
