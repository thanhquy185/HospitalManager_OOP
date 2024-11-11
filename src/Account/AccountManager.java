package Account;

import java.util.ArrayList;
import java.util.Comparator;
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
        AccountManager.list.add(new Account("admin", "1", "Quản lý"));
        AccountManager.numbers = 1;
    }

    // Setter - Getter
    public void setList(ArrayList<Account> list) {
        AccountManager.list = list;
    }
    public void setNumbers(int numbers) {
        AccountManager.numbers = numbers;
    }
    public ArrayList<Account> getList() {
        return AccountManager.list;
    }
    public int getNumbers() {
        return AccountManager.numbers;
    }
    public static AccountManager getInstance() {
        if(AccountManager.instance == null)
            AccountManager.instance = new AccountManager();
        return AccountManager.instance;
    }

    // Methods
    // - Kiểm tra tên tài khoản hoặc mật khẩu có hợp lệ hay không ?
    public boolean isUsernameOrPassword(String input) {
        for(int i = 0; i < input.length(); i++) {
            int charUnicode = (int) input.charAt(i);
            if((charUnicode >= 48 && charUnicode <= 57) 
                || (charUnicode >= 65 && charUnicode <= 90)
                || (charUnicode >= 97 && charUnicode <= 122)) continue;
            return false;
        }
        return true;
    }
    // - Kiểm tra tài khoản có thể truy cập được hay không ?
    // - Nếu tài khoản chưa tồn tại thì không thể truy cập
    public boolean canAccess(String username, String password) {
        for(Account account : AccountManager.list) {
            if(account.getUsername().equals(username)
                    && account.getPassword().equals(password))
                return true;
        }
        return false;
    }
    // - Kiểm tra tài khoản có thể đăng ký được không ?
    public boolean canRegister(String username, String password) {
        // -- Nếu chưa tồn tại thì có thể tạo tài khoản đăng ký mới, mật khẩu có thể trùng nhưng tên tài khoản phải khác nhau
        for(Account account : list) {
            if(account.getUsername().equals(username)) return false;
        }

        // -- Ngăn cấm việc tạo tài khoản có tên tài khoản trùng với cách tạo tài khoản của Bệnh viện
        // --- Nếu là các tiền tố DEP, DOC, NUR, MER thì không được đăng ký
        if(username.startsWith("DEP") || username.startsWith("SICK")
            || username.startsWith("HEW") || username.startsWith("PAT")
            || username.startsWith("MER")) return false;

        // -- Kiểm tra tên tài khoản phải là ký tự chữ cái và chữ số
        if(!isUsernameOrPassword(username)) return false;

        // -- Kiểm tra mật khẩu phải là ký tự chữ cái và chữ số
        if(!isUsernameOrPassword(password)) return false;

        return true;
    }
    // - Kiểm tra có phải là tài khoản Quản lý hay không ?
    // - Tài khoản Quản lý mặc định là admin - 1
    public boolean isAdmin(String username, String password) {
        if(!username.equals("admin")
            || !password.equals("1")) return false;
        return true;
    }
    // - Kiểm tra có phải là tài khoản Người dùng trong Bệnh viện (Bệnh nhân, Bác sĩ, Y tá) hay không ?
    // -- Tài khoản Người dùng sẽ là id (của đối tượng tương ứng) - ngaysinh (ddmmyyyy)
    public boolean isUserInHospital(String username) {
        for(Account account : AccountManager.list) {
            if(account.getUsername().equals(username)
                    && account.getType().equals("Tài khoản mới"))
                return false;
        }
        return true;
    }
    // -- Kiểm tra có phải Người dùng là Bác sĩ / Y tá (Nhân viên y tế)
    public boolean isHealthcareWorker(String username) {
        if(username.substring(0, 3).equals("HEW"))
            return true;
        return false;
    }
    // -- Kiểm tra có phải Người dùng là Bệnh nhân
    public boolean isPatient(String username) {
        if(username.substring(0, 3).equals("PAT"))
            return true;
        return false;
    }
    // - CRUD (Thêm sửa xoá với các tài khoản trong lớp quản lý)
    // -- Tìm kiếm vị trị của một tài khoản trong danh sách thông qua thông tin đã cho
    private int findIndexByUsername(String username) {
        for(int i = 0; i < AccountManager.numbers; i++) {
            Account accountExisting = AccountManager.list.get(i);
            if(accountExisting.getUsername().equals(username)) return i;
        }
        return -1;
    }
    public Account findAccountByIndex(int index) {
        if(index < 1 || index > AccountManager.numbers) return null;
        return AccountManager.list.get(index);
    }
    public Account findAccountByUsername(String username) {
        for(Account account : AccountManager.list) {
            if(account.getUsername().equals(username))
                return account;
        }
        return null;
    }
    // -- In danh sách các tài khoản
    public void show() {
        System.out.println("+-------------------------------------------------------+");
	    System.out.println("| TÊN TÀI KHOẢN |       MẬT KHẨU       |      LOẠI      |");
	    System.out.println("+---------------+----------------------+----------------+");
        for(Account account : list) {
            String username = account.getUsername();
            String password = account.getPassword();
            String type = account.getType();
            System.out.println(String.format("| %-13s | %-20s | %-14s |", username, password, type));
        }
		if(AccountManager.numbers >= 1)
            System.out.println("+-------------------------------------------------------+");
    }
    // -- Thêm một tài khoản
    public void add(Account account) {
        AccountManager.list.add(account);
        AccountManager.numbers++;
    }
    // -- Cập nhật một tài khoản (đã tồn tại, tài khoản Người dùng ngoài Bệnh viện)
    public void update(Account account) {}
    // -- Xoá một tài khoản (đã tồn tại)
    public void remove(String username) {
        AccountManager.list.remove(findIndexByUsername(username));
        AccountManager.numbers--;
    }
    // -- Sắp xếp danh sách tài khoản
    public void sort(String condition) {
        // AccountManager.getInstance().getList().sort(Comparator.comparing(()));
        // asc: Sắp xếp tăng dần
        // desc: Sắp xếp giảm dần
        switch (condition) {
            case "username assc": {
                AccountManager.list.sort(Comparator.comparing((Account account) -> account.getUsername()));
                break;
            }
            case "username desc": {
                AccountManager.list.sort(Comparator.comparing((Account account) -> account.getUsername()).reversed());
            }
        }
    }
    public void loadFromFile() {
        File file = new File("src/Database/AccountDatabase.txt");
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
                String username = info[0];
                String password = info[1];
                String type = info[2];
                Account account = new Account(username, password, type);
                add(account);
            }
            bufferedReader.close();
            fileReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void saveToFile() {
        File file = new File("src/Database/AccountDatabase.txt");
        if(!file.exists()) {
            System.out.println("Tệp tin quản lý dữ liệu về Tài khoản không tồn tại");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Account account : AccountManager.list) {
                String username = account.getUsername();
                String password = account.getPassword();
                String type = account.getType();
                bufferedWriter.write(username + "|" + password + "|" + type + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
