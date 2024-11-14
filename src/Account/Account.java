package Account;

import java.util.Scanner;

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
    public String getInfo() {
        return this.username + " | " + this.password + " | " + this.type;
    }
}
