package Hospital;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class AccountManager {
    // Properties
    private static ArrayList<Account> list;
    private static int numbers;
    private static AccountManager instance;

    // Constructors
    public AccountManager() {
        // - Mặc định sẽ luôn có duy nhất một tài khoản admin quản lý bệnh viện
        AccountManager.list = new ArrayList<>();
        list.add(new Account("admin", "1", "Quản lý", null));
        AccountManager.numbers = 1;
    }

    // Setter - Getter
    static void setList(ArrayList<Account> list) {
        AccountManager.list = list;
    }
    static void setNumbers(int numbers) {
        AccountManager.numbers = numbers;
    }
    static ArrayList<Account> getList() {
        return AccountManager.list;
    }
    static int getNumbers() {
        return AccountManager.numbers;
    }
    static AccountManager getInstance() {
        if(AccountManager.instance == null)
            AccountManager.instance = new AccountManager();
        return AccountManager.instance;
    }

    // Methods
    // - Kiểm tra tài khoản có thể truy cập được hay không ?
    // -- Nếu chưa tồn tại thì không thể truy cập
    boolean canAccessAccount(String username, String password) {
        for(Account account : list) {
            if(account.getUsername().equals(username)
                && account.getPassword().equals(password))
                return true;
        }
        return false;
    }
    /* -- Nếu chưa tồn tại thì có thể tạo tài khoản đăng ký mới, mật khẩu
    có thể trùng nhưng tên tài khoản phải khác nhau */
    boolean canRegisterAccount(String username) {
        for(Account account : list) {
            if(account.getUsername().equals(username)) return true;
        }
        return false;
    }
    // - Kiểm tra có phải là tài khoản Qu1ản lý hay không ?
    // -- Tài khoản Quản lý mặc định là admin - 1
    boolean isAdmin(String username, String password) {
        if(!username.equals("admin")
            || !password.equals("1")) return false;
        return true;
    }
    // - Kiểm tra có phải là tài khoản Người dùng trong Bệnh viện hay không ? (bao gồm Bệnh nhân, Bác sĩ, Y tá)
    // -- Tài khoản Người dùng sẽ là id (của đối tượng tương ứng, phân biệt qua đây rồi) - ngaysinh (ddmmyyyy)
    boolean isUserInHospital(String username, String password) {
        // --- Kiểm tra tên tài khoản
        // ---- Nếu tên tài khoản nằm ngoài đoạn [8, 9] (xem lại hàm thiết lập id của từng đối tượng)
        if(username.length() < 8
            || username.length() > 9) return false;
        // ---- Kiểm tra tiền tố của tên tài khoản
        String prefixUsername = (username.length() == 8 ? username.substring(0, 3)
            : username.substring(0, 4));
        if(!prefixUsername.equals("NPAT") && !prefixUsername.equals("PPAT")
                && !prefixUsername.equals("DOC") && !prefixUsername.equals("NUR"))
            return false;
        // ---- Kiểm tra hậu tố của tên tài khoản
        String postfixUsername = (username.length() == 8
            ? username.substring(3) : username.substring(4));
        for(int i = 0; i < postfixUsername.length(); i++) {
            int charUnicode = (int) postfixUsername.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        // --- Kiểm tra mật khẩu
        for(int i = 0; i < password.length(); i++) {
            int charUnicode = (int) password.charAt(i);
            if(charUnicode < 48 || charUnicode > 57) return false;
        }
        return true;
    }
    // --- Kiểm tra có phải Người dùng là Bệnh nhân
    boolean isUserInHospitalIsPatient(String username) {
        if(!username.substring(0, 4).equals("NPAT")
                && !username.substring(0, 4).equals("PPAT")) 
            return false;
        return true;
    }
    // --- Kiểm tra có phải Người dùng là Bác sĩ / Y tá (Nhân viên y tế)
    boolean isUserInHospitalIsHealthcareWorker(String username) {
        if(!username.substring(0, 3).equals("DOC")
                && !username.substring(0, 3).equals("NUR"))
            return false;
        return true;
    }
    // - Kiểm tra có phải là tài khoản Người dùng ngoài Bệnh viện hay không ? (Người dùng đăng ký mới)
    // -- Tài khoản Người dùng sẽ là chuỗi - chuỗi (chuỗi chỉ gồm các ký tự chữ và số)
    boolean isUserNotInHospital(String username, String password) {
        // --- Kiểm tra tên tài khoản
        for(int i = 0; i < username.length(); i++) {
            int charUnicode = (int) username.charAt(i);
            if((charUnicode < 48 || charUnicode > 57)
                    && (charUnicode < 65 || charUnicode > 90)
                    && (charUnicode < 97 || charUnicode > 122))
                return false;
        }
        // --- Kiểm tra mật khẩu
        for(int i = 0; i < password.length(); i++) {
            int charUnicode = (int) password.charAt(i);
            if((charUnicode < 48 || charUnicode > 57)
                    && (charUnicode < 65 || charUnicode > 90)
                    && (charUnicode < 97 || charUnicode > 122))
                return false;
        }
        return true;
    }
    // - CRUD (Thêm sửa xoá với các tài khoản trong lớp quản lý)
    // -- Tìm kiếm vị trị của một tài khoản trong danh sách thông qua thông tin đã cho
    private int findIndexByAccount(String username, String password) {
        for(int i = 0; i < AccountManager.numbers; i++) {
            Account accountExisting = AccountManager.list.get(i);
            if(accountExisting.getUsername().equals(username)
                && accountExisting.getPassword().equals(password))
                return i;
        }
        return -1;
    }
    // -- In danh sách các tài khoản
    public void show() {
        // System.out.println("+--------------------------------------------------------+");
	    // System.out.println("| TÊN TÀI KHOẢN | MẬT KHẨU | LOẠI |");
	    // System.out.println("+--------------------------------------------------------+");
        // for(Account account : list) {
        //     System.out.println(String.format("| %-16s | %-24s | %-8s |",
        //         account.getUsername(), account.getPassword(), account.getType()));
        // }
		// if(AccountManager.numbers >= 1)
        //     System.out.println("+--------------------------------------------------------+");
    }
    // -- Thêm một tài khoản
    void add(Account account) {
        AccountManager.list.add(account);
        AccountManager.numbers++;
    }
    // -- Cập nhật một tài khoản (đã tồn tại, tài khoản Người dùng ngoài Bệnh viện)
    void update(Account account) {}
    // -- Xoá một tài khoản (đã tồn tại)
    void remove(String username, String password) {
        AccountManager.list.remove(findIndexByAccount(username, password));
        AccountManager.numbers--;
    }
    public void loadFromFile() {
        File file = new File("HospitalManager/src/Database/AccountDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Tài khoản không tồn tại");
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(true) {
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] info = line.split("\\|");
                String username = info[0].trim();
                String password = info[1].trim();
                String type = info[2].trim();
                String idObject = info[3].trim();
                Account account = new Account(username, password, type, idObject);
                add(account);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void saveToFile() {
        File file = new File("HospitalManager/src/Database/AccountDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Tài khoản không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Account account : AccountManager.list) {
                bufferedWriter.write(account.getInfo() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
