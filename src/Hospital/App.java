package Hospital;

import java.util.Scanner;
import java.util.ArrayList;

import Common.*;
import Account.*;
import Department.*;
import Sick.*;
import HealthcareWorker.*;
import Patient.*;
import MedicalRecord.*;

public class App {
    // Properties
    private Scanner sc;
    private static App instance;

    // Constructors
    public App() {}

    // Methods
    // - 
    public static App getInstance() {
        if(App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }
    // - Hàm xoá những nội dung trước đó trên màn hình console
    private void clearTerminal() {
        try {
            // Kiểm tra hệ điều hành và thực thi lệnh phù hợp
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** Kiểm tra input khi chọn option [min; max]
     * 1. Chuyển choice sang int
     * 2. Nếu không nằm trong khoảng [min; max] hay không phải số nguyên -> false  
     * 3. Ngược lại -> True
     *  */ 
    private static boolean isValidChoice(String choice, int minOption, int maxOption){
        try{
            int option = Integer.parseInt(choice);
            return minOption <= option && option <= maxOption;
        } catch (NumberFormatException e){
            return false;
        }
    }
    // - Hàm khởi tạo chương trình
    public void init() {
        // Khởi tạo Scanner
        this.sc = new Scanner(System.in);

        // Mặc định khi chạy chương trình sẽ truy xuất dữ liệu từ tất cả các file dữ liệu
        AccountManager.getInstance().loadFromFile();
        DepartmentManager.getInstance().loadFromFile();
            Department.setCountDepartmentCreated(DepartmentManager.getInstance().getNumbers());
        SickManager.getInstance().loadFromFile();
            Sick.setCountSickCreated(SickManager.getInstance().getNumbers());
        HealthcareWorkerManager.getInstance().loadFromFile();
            HealthcareWorker.setCountHealthcareWorkerCreated(HealthcareWorkerManager.getInstance().getNumbers());
        PatientManager.getInstance().loadFromFile();
            Patient.setCountPatientCreated(PatientManager.getInstance().getNumbers());
        MedicalRecordManager.getInstance().loadFromFile();
            MedicalRecord.setCountMedicalRecordCreated(MedicalRecordManager.getInstance().getNumbers());
    }
    // - Hàm chạy chương trình
    public void run() {
        // Scanner
        mainLoop:
        while(true) {
            clearTerminal();
            System.out.println("/********** ĐĂNG NHẬP - ĐĂNG KÝ **********/");
            System.out.println("0 - Kết thúc");
            System.out.println("1 - Đăng nhập");
            System.out.println("2 - Đăng ký");
            System.out.print("? - Chọn: ");
            String mainChoice = sc.nextLine();
            while(!isValidChoice(mainChoice, 0, 2)) {
                System.out.println("---------- ----------");
                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại: ");
                mainChoice = sc.nextLine();
            }
            if(mainChoice.equals("0")) {
                System.out.println("Đã chọn Kết thúc");
                break mainLoop;
            } else if(mainChoice.equals("1")) {
                clearTerminal();
                System.out.println("Đã chọn Đăng nhập");
                System.out.println("/********** ĐĂNG NHẬP **********/");
                System.out.print(" - Tên đăng nhập: ");
                String username = sc.nextLine();
                System.out.print(" - Mật khẩu: ");
                String password = sc.nextLine();

                // Biến đếm số lần nhập tài khoản sai (tối đa là 3 lần)
                int countCanNotLoginAccount = 0;

                // Nếu tài khoản không hợp lệ (không tồn tại, nhập sai,...) thì nhập lại
                while(!AccountManager.getInstance().canAccess(username, password) && countCanNotLoginAccount < 2) {
                    countCanNotLoginAccount++;
                    System.out.println("---------- ----------");
                    System.out.println("Bạn đã nhập sai thông tin tài khoản lần " + countCanNotLoginAccount);
                    System.out.println("! - TÀI KHOẢN KHÔNG HỢP LỆ");
                    System.out.print(" -! Nhập lại tên đăng nhập: ");
                    username = sc.nextLine();
                    System.out.print(" -! Nhập lại mật khẩu: ");
                    password = sc.nextLine();
                }

                // Nếu nhập sai quá 3 lần thì chương trình dừng lại và in ra thông báo
                if(countCanNotLoginAccount >= 2) {
                    System.out.println("Bạn đã nhập sai tài khoản quá 3 lần");
                    System.out.println("Chương trình sẽ kết thúc");
                    break mainLoop;
                }

                // Nếu tài khoản hợp lệ
                if(AccountManager.getInstance().isAdmin(username, password)) {
                    clearTerminal();
                    System.out.println("Là tài khoản admin");
                    subLoop1:
                    while(true) {
                        System.out.println("/********** TÀI KHOẢN QUẢN LÝ **********/");
                        System.out.println("0 - Kết thúc");
                        System.out.println("1 - Quay lại");
                        System.out.println("2 - Quản lý Tài khoản");
                        System.out.println("3 - Quản lý Khoa");
                        System.out.println("4 - Quản lý Bệnh");
                        System.out.println("5 - Quản lý Nhân viên Y tế");
                        System.out.println("6 - Quản lý Bệnh nhân");
                        System.out.println("7 - Quản lý Bệnh án");
                        System.out.println("8 - Truy xuất dữ liệu Bệnh viện");
                        System.out.println("9 - Sao lưu dữ liệu Bệnh viện");
                        System.out.print("? - Chọn: ");
                        String subChoice1 = sc.nextLine();
                        while(!isValidChoice(subChoice1, 0, 9)) {
                            System.out.println("---------- ----------");
                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                            System.out.print("?! - Chọn lại: ");
                            subChoice1 = sc.nextLine();
                        }
                        if(subChoice1.equals("0")) {
                            System.out.println("Đã chọn Kết thúc");
                            break mainLoop;
                        } else if(subChoice1.equals("1")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quay lại");
                            continue mainLoop;
                        } else if(subChoice1.equals("2")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Tài khoản");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ TÀI KHOẢN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Tài khoản");
                                System.out.println("3 - Thêm một tài khoản (Người dùng mới)");
                                System.out.println("4 - Sửa một tài khoản (Người dùng mới)");
                                System.out.println("5 - Xoá một tài khoản (Người dùng mới)");
                                System.out.println("6 - Tìm kiếm tài khoản");
                                System.out.println("7 - Sắp xếp danh sách các Tài khoản");
                                System.out.println("8 - Truy xuất dữ liệu Tài khoản");
                                System.out.println("9 - Sao lưu dữ liệu Tài khoản");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 9)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Tài khoản");

                                    // In danh sách thông tin các tài khoản có trong Bệnh viện
                                    AccountManager.getInstance().show();

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một tài khoản (Người dùng mới)");

                                    // Thêm một tài khoản mới, tài khoản này là Tài khoản mới
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

                                    // Tạo một tài khoản mới
                                    Account newAccount = new Account(newUsername, newPassword, "Người dùng mới");
                                    AccountManager.getInstance().add(newAccount);

                                    // Thông báo đã thêm một tài khoản thành công
                                    System.out.println("! - Đã thêm Tài khoản: " + newAccount.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa đổi một tài khoản (Người dùng mới)");

                                    // Nếu không có tài khoản Người dùng mới nào được tạo thì không thể sửa Tài khoản
                                    ArrayList<Account> newUserList = new ArrayList<>();
                                    for(Account account : AccountManager.getInstance().getList()) {
                                        if(account.getType().equals("Người dùng mới")) newUserList.add(account);
                                    }
                                    if(newUserList.size() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có tài khoản Người dùng mới, cần tạo ít nhất một tài khoản");
                                        continue subLoop2;
                                    }

                                    // Chọn Tài khoản cần sửa từ danh sách (những tài khoản không phải Quản lí và vẫn còn hiệu lực)
                                    System.out.println(" - Chọn Tài khoản cần sửa");
                                    // 1 - username1 | Người dùng mới
                                    // 2 - username2 | Người dùng mới
                                    // 3 - username3 | Người dùng mới
                                    // ...
                                    int numberList = 1;
                                    for(Account account : newUserList) {
                                        System.out.println(numberList++ + " - " + account.getUsername() + " | " + account.getType());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? - Chọn (chọn số thự tự hoặc tên tài khoản): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && AccountManager.getInstance().findAccountByUsername(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && newUserList.get(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - TÀI KHOẢN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc tên tài khoản): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Tài khoản cần sửa thông tin
                                    Account accountUpdate = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        accountUpdate = AccountManager.getInstance().findAccountByUsername(info);
                                    } else {
                                        accountUpdate =  newUserList.get(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của tài khoản Người dùng mới đã chọn để sửa: " + accountUpdate.getInfo());

                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Tên tài khoản");
                                        System.out.println("2 - Mật khẩu");
                                        System.out.println("3 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 3)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Tên tài khoản");
                                                System.out.print(" - Nhập tên tài khoản mới: ");
                                                String newUsername = sc.nextLine();
                                                while(!AccountManager.getInstance().isUsernameOrPassword(newUsername)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHÔNG THỂ THAY ĐỔI");
                                                    System.out.print(" - Nhập lại tên tài khoản mới: ");
                                                    newUsername = sc.nextLine();
                                                }
                                                accountUpdate.setUsername(newUsername);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Mật khẩu");
                                                System.out.print(" - Nhập mật khẩu mới: ");
                                                String newPassword = sc.nextLine();
                                                while(!AccountManager.getInstance().isUsernameOrPassword(newPassword)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHÔNG THỂ THAY ĐỔI");
                                                    System.out.print(" - Nhập lại mật khẩu mới: ");
                                                    newPassword = sc.nextLine();
                                                }
                                                accountUpdate.setPassword(newPassword);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Tất cả");

                                                System.out.print(" - Nhập tên tài khoản mới: ");
                                                String newUsername = sc.nextLine();
                                                while(!AccountManager.getInstance().isUsernameOrPassword(newUsername)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHÔNG THỂ THAY ĐỔI");
                                                    System.out.print(" - Nhập lại tên tài khoản mới: ");
                                                    newUsername = sc.nextLine();
                                                }
                                                accountUpdate.setUsername(newUsername);

                                                System.out.print(" - Nhập mật khẩu mới: ");
                                                String newPassword = sc.nextLine();
                                                while(!AccountManager.getInstance().isUsernameOrPassword(newPassword)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHÔNG THỂ THAY ĐỔI");
                                                    System.out.print(" - Nhập lại mật khẩu mới: ");
                                                    newPassword = sc.nextLine();
                                                }
                                                accountUpdate.setPassword(newPassword);
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("Thông tin của tài khoản sau khi sửa: " + accountUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một tài khoản");

                                    // Nếu không có tài khoản Người dùng mới nào được tạo thì không thể sửa Tài khoản
                                    ArrayList<Account> newUserList = new ArrayList<>();
                                    for(Account account : AccountManager.getInstance().getList()) {
                                        if(account.getType().equals("Người dùng mới")) newUserList.add(account);
                                    }
                                    if(newUserList.size() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có tài khoản Người dùng mới, cần tạo ít nhất một tài khoản");
                                        continue subLoop2;
                                    }

                                    // Chọn Tài khoản cần sửa từ danh sách (những tài khoản không phải Quản lí và vẫn còn hiệu lực)
                                    System.out.println(" -- Chọn Tài khoản cần sửa");
                                    // 1 -- username1 | Người dùng mới
                                    // 2 -- username2 | Người dùng mới
                                    // 3 -- username3 | Người dùng mới
                                    // ...
                                    int numberList = 1;
                                    for(Account account : newUserList) {
                                        System.out.println(numberList++ + " -- " + account.getUsername() + " | " + account.getType());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? -- Chọn (chọn số thự tự hoặc tên tài khoản): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && AccountManager.getInstance().findAccountByUsername(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && newUserList.get(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- TÀI KHOẢN KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại (số thứ tự hoặc tên tài khoản): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Tài khoản cần sửa thông tin
                                    Account accountRemove = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        accountRemove = AccountManager.getInstance().findAccountByUsername(info);
                                    } else {
                                        accountRemove =  newUserList.get(Integer.parseInt(info) - 1);
                                    }

                                    // Xoá Tài khoản đã tìm
                                    AccountManager.getInstance().remove(accountRemove.getUsername());

                                    // Thông báo thông tin của Tài khoản đã xoá
                                    System.out.println("! - Đã xoá một tài khoản Người dùng mới: " + accountRemove.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm tài khoản");

                                    // Nếu không có Tài khoản nào được tạo thì không thể tìm kiếm Tài khoản
                                    if(AccountManager.getInstance().getNumbers() == 1) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Tài khoản, cần tạo ít nhất một Tài khoản");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm bằng mã Tài khoản hay tên Tài khoản đều được phép
                                    System.out.println("Bạn có thể tìm bằng tên Tài khoản hoặc loại Tài khoản");
                                    System.out.print(" - Nhập thông tin Tài khoản cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Account> accountSearchList = new ArrayList<>();
                                    for(Account account : AccountManager.getInstance().getList()) {
                                        if(account.getUsername().toLowerCase().contains(info.trim().toLowerCase())
                                                || account.getType().toLowerCase().contains(info.trim().toLowerCase()))
                                            accountSearchList.add(account);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(accountSearchList.size() == 0) {
                                        System.out.println("! - Không tìm được Tài khoản nào với thông tin đã cho");
                                    } else {
                                        for(Account accountSearch : accountSearchList) {
                                            System.out.println(accountSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các tài khoản");

                                    // Nếu không có Tài khoản nào được tạo thì không thể sắp xếp Tài khoản
                                    if(AccountManager.getInstance().getNumbers() == 1) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Tài khoản, cần tạo ít nhất một Tài khoản");
                                        continue subLoop2;
                                    }

                                    // Sắp xếp các Tài khoản trong Danh sách theo lựa chọn
                                    System.out.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo tên tài khoản tăng dần");
                                    System.out.println("2 - Sắp xếp theo tên tài khoản giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!isValidChoice(subChoice3, 0, 2)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if (subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        AccountManager.getInstance().sort("username asc");
                                        System.out.println("! - Đã sắp xếp danh sách Tài khoản theo tên tài khoản tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        AccountManager.getInstance().sort("username desc");
                                        System.out.println("! - Đã sắp xếp danh sách Tài khoản theo tên tài khoản giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu tài khoản");

                                    // Truy xuất dữ liệu từ Tài khoản
                                    AccountManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Tài khoản thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Tài khoản thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu tài khoản");

                                    // Sao lưu dữ liệu từ Tài khoản
                                    AccountManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu từ Tài khoản thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Tài khoản thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                            }

                        } else if(subChoice1.equals("3")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Khoa");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ KHOA **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Khoa");
                                System.out.println("3 - Thêm một Khoa");
                                System.out.println("4 - Sửa một Khoa");
                                System.out.println("5 - Xoá một Khoa");
                                System.out.println("6 - Xoá tất cả Khoa");
                                System.out.println("7 - Tìm kiếm Khoa");
                                System.out.println("8 - Sắp xếp danh sách các Khoa");
                                System.out.println("9 - Truy xuất dữ liệu Khoa");
                                System.out.println("10 - Sao lưu dữ liệu Khoa");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Khoa");

                                    // Mấy ông xem ở class DepartmentManager
                                    DepartmentManager.getInstance().show();

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Khoa");

                                    // Nhập tên Khoa
                                    System.out.print(" - Nhập tên Khoa: ");
                                    String name = sc.nextLine();
                                    // Mã trưởng Khoa sẽ có sau
                                    // Nhập số phòng Khoa
                                    System.out.print(" - Nhập số phòng (tối đa 5 ký tự): ");
                                    String room = sc.nextLine();
                                    while(room.length() > 5) {
                                        System.out.println("----- -----");
                                        System.out.println("! - SỐ PHÒNG KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (tối đa 5 ký tự): ");
                                        room = sc.nextLine();
                                    }
                                    
                                    // Thêm Khoa mới
                                    Department newDepartment = new Department(name, null, room);
                                    DepartmentManager.getInstance().add(newDepartment);

                                    // Lấy ra mã Khoa mới được để xử lý việc bổ nhiệm một Bác sĩ mới làm trưởng Khoa
                                    String newDepartmentID = newDepartment.getId();
                                    
                                    // Tạo một Bác sĩ mới là Trưởng khoa (Khoa mới được tạo luôn phải có một trưởng Khoa)
                                    System.out.println(" - Nhập thông tin Trưởng Khoa (Khoa mới được tạo nên cần một Bác sĩ để quản lý)");
                                    // - Nhập tên Trưởng Khoa
                                    System.out.print(" -+ Nhập họ tên: ");
                                    String fullname = sc.nextLine();
                                    // - Nhập ngày sinh Trưởng Khoa
                                    System.out.print(" -+ Nhập ngày sinh (dd-mm-yyyy hoặc ddmmyyyy): ");
                                    String birthdayStr = sc.nextLine();
                                    while(!Date.getInstance().isDateFormat(birthdayStr)
                                            || !Date.getInstance().getDateFromDateFormat(birthdayStr).isDate()) {
                                        System.out.println("----- -----");
                                        System.out.println("! -+ NGÀY SINH KHÔNG HỢP LỆ");
                                        System.out.print("?! -+ Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                        birthdayStr = sc.nextLine();
                                       System.out.println("----- -----");
                                    }
                                    Date birthdayObj = Date.getInstance().getDateFromDateFormat(birthdayStr);
                                    // - Nhập giới tính Trưởng Khoa
                                    System.out.print(" -+ Nhập giới tính (Nam / Nữ): ");
                                    String gender = sc.nextLine();
                                    while(!gender.equals("Nam") && !gender.equals("Nữ")) {
                                        System.out.println("----- -----");
                                        System.out.println("! -+ GIỚI TÍNH KHÔNG HỢP LỆ");
                                        System.out.print("?! -+ Nhập lại (Nam / Nữ): ");
                                        gender = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Nhập số điện thoại Trưởng Khoa
                                    System.out.print(" -+ Nhập số điện thoại (10 số): ");
                                    String phone = sc.nextLine();
                                    while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -+ SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                        System.out.print("?! -+ Nhập lại (10 số): ");
                                        phone = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Nhập quốc tịch Trưởng Khoa
                                    System.out.print(" -+ Nhập quốc tịch: ");
                                    String country = sc.nextLine();
                                    // - Nhập số năm kinh nghiệm Trưởng Khoa
                                    System.out.print(" -+ Nhập số năm kinh nghiệm (từ 2 năm trở lên): ");
                                    String yearsOfExperienceStr = sc.nextLine();
                                    while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                                            || Integer.parseInt(yearsOfExperienceStr) < 2) {
                                        System.out.println("----- -----");
                                        System.out.println("! -+ SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                                        System.out.print("?! -+ Nhập lại (từ 2 năm trở lên): ");
                                        yearsOfExperienceStr = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);
                                    // - Nhập tiền lương Trưởng khoa
                                    System.out.print(" -+ Nhập tiền lương (tối thiểu là 1000): ");
                                    String salaryStr = sc.nextLine();
                                    while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(salaryStr)
                                            || Integer.parseInt(salaryStr) < 1000) {
                                        System.out.println("----- -----");
                                        System.out.println("! -+ TIỀN LƯƠNG KHÔNG HỢP LỆ");
                                        System.out.print("?! -+ Nhập lại (tối thiểu là 1000): ");
                                        salaryStr = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    int salaryInt = Integer.parseInt(salaryStr);

                                    // Tạo một Bác sĩ mới
                                    HealthcareWorker newHealthcareWorker = new HealthcareWorker(fullname, birthdayObj, gender, phone,
                                        country, "Bác sĩ", yearsOfExperienceInt, salaryInt, newDepartmentID, "Có");
                                    HealthcareWorkerManager.getInstance().add(newHealthcareWorker);

                                    // Lấy ra mã Bác sĩ mới được để xử lý việc thiết lập mã trưởng Khoa
                                    String newHealthcareWorkerID = newHealthcareWorker.getId();

                                    // Thiết lập mã trưởng Khoa (vì đã hoàn tất việc tạo một Bác sĩ)
                                    DepartmentManager.getInstance().findObjectById(newDepartmentID).setManagerID(newHealthcareWorkerID);

                                    // Thông báo đã thêm một Khoa
                                    System.out.println("! - Đã thêm Khoa: " + newDepartment.getInfo());
                                
                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }
                                    
                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể sửa Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Chọn Khoa cần sửa từ danh sách
                                    System.out.println(" -- Chọn Khoa cần sửa");
                                    // 1 -- DEP00001 | Nội
                                    // 2 -- DEP00002 | Mắt
                                    // 3 -- DEP00008 | Răng-Hàm-Mặt
                                    // ...
                                    int numberList = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " -- " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? -- Chọn (chọn số thự tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Khoa cần sửa thông tin
                                    Department departmentUpdate = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        departmentUpdate = DepartmentManager.getInstance().findObjectById(info);
                                    } else {
                                        departmentUpdate =  DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Khoa đã chọn để sửa: " + departmentUpdate.getInfo());

                                    // Cập nhật thông tin của Patient đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Tên");
                                        System.out.println("2 - Trưởng Khoa");
                                        System.out.println("3 - Số phòng");
                                        System.out.println("4 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 4)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Tên");
                                                System.out.print(" - Nhập tên mới: ");
                                                String newName = sc.nextLine();
                                                departmentUpdate.setName(newName);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Trưởng Khoa");
                                                // Chọn Bác sĩ cần làm trưởng Khoa từ danh sách (phải cùng Khoa hiện tại và là Bác sĩ)
                                                // - Lọc ra các Bác sĩ cùng Khoa, chưa là trưởng Khoa và là Bác sĩ
                                                ArrayList<HealthcareWorker> doctorSearchList = new ArrayList<>();
                                                for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                                    if(healthcareWorker.getDepartmentID().equals(departmentUpdate.getId())
                                                        && healthcareWorker.getIsManagerDepartment().equals("Không")
                                                        && healthcareWorker.getType().equals("Bác sĩ")) doctorSearchList.add(healthcareWorker);
                                                }
                                                if(doctorSearchList.size() == 0) {
                                                    System.out.println("Hiện tại Khoa " + departmentUpdate.getId() + " - " + departmentUpdate.getName() 
                                                        + " không có Bác sĩ cùn Khoa nào để có thể bổ nhiểm làm trưởng Khoa");
                                                } else {
                                                    // Thiết lập lại trưởng Khoa cũ
                                                    HealthcareWorkerManager.getInstance().findObjectById(departmentUpdate.getManagerID()).setIsManagerDepartment("Không");

                                                    // - Chọn Bác sĩ từ danh sách
                                                    System.out.println(" - Chọn Bác sĩ cần bổ nhiệm");
                                                    // 1 - HEW00001 | Thanh Quy
                                                    // 2 - HEW00002 | Đức Quý An
                                                    // ...
                                                    int subNumberList = 1;
                                                    for(HealthcareWorker healthcareWorker : doctorSearchList) {
                                                        System.out.println(subNumberList++ + " - " + healthcareWorker.getId() 
                                                            + " | " + healthcareWorker.getFullname() + " | " + healthcareWorker.getType());
                                                    }
                                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DOC00001)
                                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bác sĩ): ");
                                                    String subInfo = sc.nextLine();
                                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                                && HealthcareWorkerManager.getInstance().findObjectById(subInfo) == null)
                                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                                && doctorSearchList.get(Integer.parseInt(subInfo) - 1) == null)) {
                                                        System.out.println("----- -----");
                                                        System.out.println("! - BÁC SĨ KHÔNG HỢP LỆ");
                                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bác sĩ): ");
                                                        subInfo = sc.nextLine();
                                                    }
                                                    // Lấy thông tin của Bác sĩ bổ nhiệm làm trưởng Khoa
                                                    HealthcareWorker doctorSelect = null;
                                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)) {
                                                        doctorSelect = HealthcareWorkerManager.getInstance().findObjectById(subInfo);
                                                    } else {
                                                        doctorSelect =  doctorSearchList.get(Integer.parseInt(subInfo) - 1);
                                                    }

                                                    // Thiết lập trưởng Khoa mới
                                                    departmentUpdate.setManagerID(doctorSelect.getId());
                                                    doctorSelect.setIsManagerDepartment("Có");
                                                }
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Số phòng");
                                                System.out.print(" - Nhập số phòng mới: ");
                                                String newRoom = sc.nextLine();
                                                departmentUpdate.setRoom(newRoom);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Tất cả");

                                                // Nhập tên Khoa mới
                                                System.out.print(" - Nhập tên mới: ");
                                                String newName = sc.nextLine();
                                                departmentUpdate.setName(newName);

                                                // Chọn Bác sĩ cần làm trưởng Khoa từ danh sách (phải cùng Khoa hiện tại và là Bác sĩ)
                                                // - Lọc ra các Bác sĩ cùng Khoa, chưa là trưởng Khoa và là Bác sĩ
                                                ArrayList<HealthcareWorker> doctorSearchList = new ArrayList<>();
                                                for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                                    if(healthcareWorker.getDepartmentID().equals(departmentUpdate.getId())
                                                        && healthcareWorker.getIsManagerDepartment().equals("Không")
                                                        && healthcareWorker.getType().equals("Bác sĩ")) doctorSearchList.add(healthcareWorker);
                                                }
                                                if(doctorSearchList.size() == 0) {
                                                    System.out.println("Hiện tại Khoa " + departmentUpdate.getId() + " - " + departmentUpdate.getName() 
                                                        + " không có Bác sĩ cùn Khoa nào để có thể bổ nhiểm làm trưởng Khoa");
                                                } else {
                                                    // - Chọn Bác sĩ từ danh sách
                                                    System.out.println(" - Chọn Bác sĩ cần bổ nhiệm");
                                                    // 1 - HEW00001 | Thanh Quy
                                                    // 2 - HEW00002 | Đức Quý An
                                                    // ...
                                                    int subNumberList = 1;
                                                    for(HealthcareWorker healthcareWorker : doctorSearchList) {
                                                        System.out.println(subNumberList++ + " - " + healthcareWorker.getId() 
                                                            + " | " + healthcareWorker.getFullname() + " | " + healthcareWorker.getType());
                                                    }
                                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DOC00001)
                                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bác sĩ): ");
                                                    String subInfo = sc.nextLine();
                                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                                && HealthcareWorkerManager.getInstance().findObjectById(subInfo) == null)
                                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                                && doctorSearchList.get(Integer.parseInt(subInfo) - 1) == null)) {
                                                        System.out.println("----- -----");
                                                        System.out.println("! - BÁC SĨ KHÔNG HỢP LỆ");
                                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bác sĩ): ");
                                                        subInfo = sc.nextLine();
                                                    }
                                                    // Lấy thông tin của Bác sĩ bổ nhiệm làm trưởng Khoa
                                                    HealthcareWorker doctorSelect = null;
                                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)) {
                                                        doctorSelect = HealthcareWorkerManager.getInstance().findObjectById(subInfo);
                                                    } else {
                                                        doctorSelect =  doctorSearchList.get(Integer.parseInt(subInfo) - 1);
                                                    }

                                                    // Thiết lập lại trưởng Khoa cũ
                                                    HealthcareWorkerManager.getInstance().findObjectById(departmentUpdate.getManagerID()).setIsManagerDepartment("Không");
                                                    // Thiết lập trưởng Khoa mới
                                                    departmentUpdate.setManagerID(doctorSelect.getId());
                                                    doctorSelect.setIsManagerDepartment("Có");
                                                }

                                                // Nhập số phòng mới
                                                System.out.print(" - Nhập số phòng mới (tối đa 5 ký tự): ");
                                                String newRoom = sc.nextLine();
                                                while(newRoom.length() > 5) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ PHÒNG KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (tối đa 5 ký tự): ");
                                                    newRoom = sc.nextLine();
                                                }
                                                departmentUpdate.setName(newRoom);
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("Thông tin của Khoa sau khi sửa: " + departmentUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể xoá Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Chọn Khoa cần xoá từ danh sách
                                    System.out.println(" - Chọn Khoa cần xoá");
                                    // 1 -- DEP00001 | Tai-Mũi-Họng
                                    // 2 -- DEP00002 | Mắt
                                    // ...
                                    int numberList = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }

                                    // Tìm thông tin của Khoa cần xoá
                                    Department departmentRemove = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        departmentRemove = DepartmentManager.getInstance().findObjectById(info);
                                    } else {
                                        departmentRemove = DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Khoa sắp được xoá để xử lý những việc bên dưới
                                    String departmentRemoveID = departmentRemove.getId();

                                    // Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Đối tượng Bệnh
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getDepartmentID() == null) continue;
                                        if(sick.getDepartmentID().equals(departmentRemoveID)) {
                                            sick.setDepartmentID(null);
                                            break;
                                        }
                                    }
                                    // - Đối tượng Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getDepartmentID() == null) continue;
                                        if(healthcareWorker.getDepartmentID().equals(departmentRemoveID)) {
                                            healthcareWorker.setDepartmentID(null);
                                            healthcareWorker.setIsManagerDepartment(null);
                                            break;
                                        }
                                    }

                                    // Xoá Khoa đã tìm
                                    DepartmentManager.getInstance().removeOne(departmentRemoveID);

                                    // Thông báo thông tin của Khoa đã xoá
                                    System.out.println("! - Đã xoá một Khoa: " + departmentRemove.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể xoá bất cứ Khoa nào
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Đối tượng Bệnh
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        sick.setDepartmentID(null);
                                    }
                                    // - Đối tượng Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        healthcareWorker.setDepartmentID(null);
                                        healthcareWorker.setIsManagerDepartment(null);
                                    }

                                    // Xoá tất cả Khoa hiện có
                                    DepartmentManager.getInstance().removeAll();

                                    // Thông báo là đã xoá hết
                                    System.out.println("! - Đã xoá tất cả Khoa");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm bằng mã Khoa hay tên Khoa đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Khoa hoặc tên Khoa");
                                    System.out.print(" - Nhập thông tin Khoa cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Department> departmentSearchList = new ArrayList<>();
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        if(department.getName().toLowerCase().contains(info.trim().toLowerCase())
                                                || department.getId().equals(info)) departmentSearchList.add(department);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(departmentSearchList.size() == 0) {
                                        System.out.println("! - Không tìm được Khoa nào với thông tin đã cho");
                                    } else {
                                        for(Department departmentSearch : departmentSearchList) {
                                            System.out.println(departmentSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể sắp xếp Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Sắp xếp các Khoa trong Danh sách theo lựa chọn
                                    System.out.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo mã tăng dần");
                                    System.out.println("2 - Sắp xếp theo mã giảm dần");
                                    System.out.println("3 - Sắp xếp theo tên tăng dần");
                                    System.out.println("4 - Sắp xếp theo tên giảm dần");
                                    System.out.println("5 - Sắp xếp theo số phòng tăng dần");
                                    System.out.println("6 - Sắp xếp theo số phòng giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!isValidChoice(subChoice3, 0, 6)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if (subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        DepartmentManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        DepartmentManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        DepartmentManager.getInstance().sort("name asc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo tên tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        DepartmentManager.getInstance().sort("name desc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo tên tăng dần");
                                    } else if(subChoice3.equals("5")) {
                                        DepartmentManager.getInstance().sort("room asc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo số phòng tăng dần");
                                    } else if(subChoice3.equals("6")) {
                                        DepartmentManager.getInstance().sort("room desc");
                                        System.out.println("! - Đã sắp xếp danh sách Khoa theo số phòng giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Khoa ");

                                    // Truy xuất dữ liệu từ Khoa
                                    DepartmentManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Khoa thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Khoa thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Khoa ");

                                    // Sao lưu dữ liệu từ Khoa
                                    DepartmentManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu từ Bệnh thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Khoa thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                                clearTerminal();
                            }
                        } else if(subChoice1.equals("4")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Bệnh");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ BỆNH **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Bệnh");
                                System.out.println("3 - Thêm một Bệnh");
                                System.out.println("4 - Sửa một Bệnh");
                                System.out.println("5 - Xoá một Bệnh");
                                System.out.println("6 - Xoá tất cả Bệnh");
                                System.out.println("7 - Tìm kiếm Bệnh");
                                System.out.println("8 - Sắp xếp danh sách các Bệnh");
                                System.out.println("9 - Truy xuất dữ liệu Bệnh");
                                System.out.println("10 - Sao lưu dữ liệu Bệnh");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh");

                                    // In danh sách các Bệnh mà Bệnh viện có thể khám và chữa
                                    SickManager.getInstance().show();

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Bệnh");

                                    // Nếu không có Khoa nào được tạo thì không thể tạo Bệnh
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa vì Bệnh cần một Khoa quản lí");
                                        continue subLoop2;
                                    }

                                    // Nếu đã có Khoa được tạo
                                    System.out.print(" - Nhập tên Bệnh: ");
                                    String name = sc.nextLine();

                                    // Chọn Khoa sẽ quản lý Bệnh được tạo
                                    System.out.println(" - Chọn Khoa thuộc về");
                                    // 1 - DEP00001 | Tai-Mũi-Họng
                                    // 2 - DEP00002 | Thận
                                    // ...
                                    int numberList = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }
                                    // Lấy mã Khoa đã được chọn
                                    String departmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                        ? DepartmentManager.getInstance().findObjectById(info).getId()
                                        : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();

                                    // Thêm một bệnh, Sick(name, departmentID), vì id sẽ tự tạo
                                    Sick newSick = new Sick(name, departmentID);
                                    SickManager.getInstance().add(newSick);

                                    // Thông báo đã thêm một Bệnh mới
                                    System.out.println("! - Đã thêm Bệnh: " + newSick.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể sửa Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần sửa từ danh sách
                                    System.out.println(" - Chọn Bệnh cần sửa");
                                    // 1 -- SI00001 | Ung thư
                                    // 2 -- SI00002 | Suy thận
                                    // ...
                                    int numberList = 1;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn SICKxxxxx)
                                    System.out.print("? -- Chọn (số thứ tự hoặc mã Bệnh): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && SickManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
                                        info = sc.nextLine();
                                    }
                                    Sick sickUpdate = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        sickUpdate = SickManager.getInstance().findObjectById(info);
                                    } else {
                                        sickUpdate =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Bệnh đã chọn để sửa: " + sickUpdate.getInfo());

                                    // Cập nhật thông tin của Bệnh đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Tên");
                                        System.out.println("2 - Khoa quản lý");
                                        System.out.println("3 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();
                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 3)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Tên");
                                                System.out.print(" - Nhập tên mới: ");
                                                String newName = sc.nextLine();
                                                sickUpdate.setName(newName);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Khoa");
                                                System.out.println(" - Chọn Khoa quản lý mới");
                                                // 1 - DEP00001 | Tai-Mũi-Họng
                                                // 2 - DEP00002 | Thận
                                                // ...
                                                int subNumberList = 1;
                                                for(Department department : DepartmentManager.getInstance().getList()) {
                                                    System.out.println(subNumberList++ + " - "
                                                        + department.getId() + " | " + department.getName());
                                                }
                                                // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                                String subInfo = sc.nextLine();
                                                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectById(subInfo) == null)
                                                        || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                                    subInfo = sc.nextLine();
                                                }
                                                // Lấy mã Khoa đã được chọn
                                                String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                    ? DepartmentManager.getInstance().findObjectById(subInfo).getId()
                                                    : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                sickUpdate.setDepartmentID(newDepartmentID);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Tất cả");

                                                // Nhập tên Khoa mới
                                                System.out.print(" - Nhập tên mới: ");
                                                String newName = sc.nextLine();
                                                sickUpdate.setName(newName);

                                                // Chọn Khoa quản lí mới
                                                System.out.println(" - Chọn Khoa thuộc về");
                                                // 1 - DEP00001 | Tai-Mũi-Họng
                                                // 2 - DEP00002 | Thận
                                                // ...
                                                int subNumberList = 1;
                                                for(Department department : DepartmentManager.getInstance().getList()) {
                                                    System.out.println(subNumberList++ + " - "
                                                        + department.getId() + " | " + department.getName());
                                                }
                                                // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                                String subInfo = sc.nextLine();
                                                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectById(subInfo) == null)
                                                        || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                                    subInfo = sc.nextLine();
                                                }
                                                // Lấy mã Khoa đã được chọn
                                                String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                    ? DepartmentManager.getInstance().findObjectById(subInfo).getId()
                                                    : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                sickUpdate.setDepartmentID(newDepartmentID);
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("Thông tin của Bệnh sau khi sửa: " + sickUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể xoá Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần xoá từ danh sách
                                    System.out.println(" - Chọn Bệnh cần xoá");
                                    // 1 - SI00001 | Ung thư
                                    // 2 - SI00001 | Suy thận
                                    // ...
                                    int numberList = 1;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - "
                                            + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && SickManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
                                        info = sc.nextLine();
                                    }
                                    // Lấy thông tin của Bệnh cần xoá
                                    Sick sickRemove = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        sickRemove = SickManager.getInstance().findObjectById(info);
                                    } else {
                                        sickRemove =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Bệnh sắp được xoá để xử lý những việc sau
                                    String sickRemoveID = sickRemove.getId();

                                    // Huỷ liên với các đối tượng liên quan
                                    // - Bệnh án
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getSickID() == null 
                                            && medicalRecord.getSickLevel() == null) continue;
                                        if(medicalRecord.getSickID().equals(sickRemoveID)) {
                                            medicalRecord.setSickID(null);
                                            medicalRecord.setSickLevel(null);
                                            break;
                                        }
                                    }

                                    // Xoá Bệnh
                                    SickManager.getInstance().removeOne(sickRemoveID);

                                    // Thông báo thông tin của bệnh đã xoá
                                    System.out.println("! -- Đã xoá một Bệnh: " + sickRemove.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể xoá Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Huỷ liên với các đối tượng liên quan
                                    // -- Bệnh án
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        medicalRecord.setSickID(null);
                                        medicalRecord.setSickLevel(null);
                                    }

                                    // Xoá tất cả Bệnh
                                    SickManager.getInstance().removeAll();

                                    // Thông báo đã xoá hết Bệnh trong danh sách
                                    System.out.println("! - Đã xoá tất cả Bệnh");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể tìm kiếm Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm bằng mã Bệnh hay tên Bệnh đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Bệnh hoặc tên Bệnh");
                                    System.out.print(" - Nhập thông tin Bệnh cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Sick> sickSearchList = new ArrayList<>();
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getName().toLowerCase().contains(info.trim().toLowerCase())
                                            || sick.getId().equals(info)) sickSearchList.add(sick);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(sickSearchList.size() == 0) {
                                        System.out.println("! - Không tìm được Bệnh nào với thông tin đã cho");
                                    } else {
                                        for(Sick sickSearch : sickSearchList) {
                                            System.out.println(sickSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể sắp xếp Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Sắp xếp các Bệnh trong Danh sách theo lựa chọn
                                    System.err.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo mã tăng dần");
                                    System.out.println("2 - Sắp xếp theo mã giảm dần");
                                    System.out.println("3 - Sắp xếp theo tên tăng dần");
                                    System.out.println("4 - Sắp xếp theo tên giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!!isValidChoice(subChoice3, 0, 4)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if(subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        DepartmentManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        DepartmentManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        DepartmentManager.getInstance().sort("name asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo tên tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        DepartmentManager.getInstance().sort("name desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo tên giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh");

                                    // Truy xuất dữ liệu từ Bệnh
                                    SickManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Bệnh thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Bệnh thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Bệnh");

                                    // Sao lưu dữ liệu Bệnh
                                    SickManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu Bệnh thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Bệnh thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                                clearTerminal();
                            }
                        } else if(subChoice1.equals("5")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Nhân viên Y tế");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ NHÂN VIÊN Y TẾ **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Nhân viên Y tế");
                                System.out.println("3 - Thêm một Nhân viên Y tế");
                                System.out.println("4 - Sửa một Nhân viên Y tế");
                                System.out.println("5 - Xoá một Nhân viên Y tế");
                                System.out.println("6 - Xoá tất cả Nhân viên Y tế");
                                System.out.println("7 - Tìm kiếm Nhân viên Y tế");
                                System.out.println("8 - Sắp xếp danh sách các Nhân viên Y tế");
                                System.out.println("9 - Truy xuất dữ liệu Nhân viên Y tế");
                                System.out.println("10 - Sao lưu dữ liệu Nhân viên Y tế");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Nhân viên Y tế");

                                    // In danh sách thông tin các Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().show();

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Khoa nào tồn tại thì không thể tạo một Nhân viên Y tế vì Khoa quản lý Nhân viên Y tế
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa vì Nhân viên Y tế cần một Khoa quản lí");
                                        continue subLoop1;
                                    }

                                    // Nhập tên
                                    System.out.print(" - Nhập họ tên: ");
                                    String fullname = sc.nextLine();

                                    // Nhập ngày sinh
                                    System.out.print(" - Nhập ngày sinh (dd-mm-yyyy hoặc ddmmyyyy): ");
                                    String birthdayStr = sc.nextLine();
                                    while(!Date.getInstance().isDateFormat(birthdayStr)
                                            || !Date.getInstance().getDateFromDateFormat(birthdayStr).isDate()) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                        birthdayStr = sc.nextLine();
                                       System.out.println("----- -----");
                                    }
                                    Date birthdayObj = Date.getInstance().getDateFromDateFormat(birthdayStr);

                                    // Nhập giới tính
                                    System.out.print(" - Nhập giới tính (Nam / Nữ): ");
                                    String gender = sc.nextLine();
                                    while(!gender.equals("Nam") && !gender.equals("Nữ")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Nam / Nữ): ");
                                        gender = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Nhập số điện thoại 
                                    System.out.print(" - Nhập số điện thoại (10 số): ");
                                    String phone = sc.nextLine();
                                    while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (10 số): ");
                                        phone = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Nhập quốc tịch 
                                    System.out.print(" - Nhập quốc tịch: ");
                                    String country = sc.nextLine();

                                    // Chọn loại Bác sĩ
                                    System.out.print(" - Nhập loại Nhân viên (Bác sĩ hoặc Y tá): ");
                                    String type = sc.nextLine();
                                    while(!type.equals("Bác sĩ") && !type.equals("Y tá")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LOẠI NHÂN VIÊN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Bác sĩ hoặc Y tá): ");
                                        type = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Nhập số năm kinh nghiệm 
                                    System.out.print(" - Nhập số năm kinh nghiệm (từ 0 trở lên): ");
                                    String yearsOfExperienceStr = sc.nextLine();
                                    while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                                            || Integer.parseInt(yearsOfExperienceStr) < 0) {
                                        System.out.println("----- -----");
                                        System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (từ 0 trở lên): ");
                                        yearsOfExperienceStr = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);

                                    // Nhập tiền lương
                                    System.out.print(" - Nhập tiền lương (tối thiểu là 1000): ");
                                    String salaryStr = sc.nextLine();
                                    while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(salaryStr)
                                            || Integer.parseInt(salaryStr) < 1000) {
                                        System.out.println("----- -----");
                                        System.out.println("! - TIỀN LƯƠNG KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (tối thiểu là 1000): ");
                                        salaryStr = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    int salaryInt = Integer.parseInt(salaryStr);

                                    // Chọn Khoa quản lí Nhân viên mới được tạo này
                                    System.out.println(" - Chọn Khoa thuộc về");
                                    // 1 - DEP00001 | Tai-Mũi-Họng
                                    // 2 - DEP00002 | Thận
                                    // ...
                                    int numberList = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }
                                    // Lấy mã Khoa đã được chọn
                                    String departmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                        ? DepartmentManager.getInstance().findObjectById(info).getId()
                                        : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1).getId();

                                    // Tạo một Nhân viên Y tế mới
                                    HealthcareWorker newHealthcareWorker = new HealthcareWorker(fullname, birthdayObj, gender, phone, country,
                                        type, yearsOfExperienceInt, salaryInt, departmentID, "Không");
                                    HealthcareWorkerManager.getInstance().add(newHealthcareWorker);

                                    // Tạo tài khoản đăng nhập riêng cho Nhân viên Y tế mới tạo đó
                                    String newUsername = newHealthcareWorker.getId();
                                    String newPassword = newHealthcareWorker.getBirthday().getDateFormatByCondition("has not cross");
                                    Account newAccount = new Account(newUsername, newPassword, "Nhân viên");
                                    AccountManager.getInstance().add(newAccount);

                                    // Thông báo lại là đã thêm
                                    System.out.println("! - Đã thêm Nhân viên Y tế: " + newHealthcareWorker.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Nhân viên Y tế");

                                    // Nếu không có Nhân viên Y tế nào được tạo thì không thể tạo Bệnh
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop2;
                                    }

                                    // Chọn Nhân viên Y tế cần sửa từ danh sách
                                    System.out.println(" - Chọn Nhân viên Y tế cần sửa");
                                    // 1 -- HEW00001 | Thanh Quy
                                    // 2 -- HEW00002 | Duy Quý
                                    // 3 -- HEW00003 | Minh Đức
                                    // 4 -- HEW00004 | Phước An
                                    // ...
                                    int numberList = 1;
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Nhân viên Y tế): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && HealthcareWorkerManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Nhân viên Y tế): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Nhân viên Y tế cần sửa
                                    HealthcareWorker healthcareWorkerUpdate = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        healthcareWorkerUpdate = HealthcareWorkerManager.getInstance().findObjectById(info);
                                    } else {
                                        healthcareWorkerUpdate = HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Nhân viên Y tế đã chọn để sửa: " + healthcareWorkerUpdate.getInfo());

                                    // Cập nhật thông tin của Nhân viên Y tế đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Họ tên");
                                        System.out.println("2 - Ngày sinh");
                                        System.out.println("3 - Giới tính");
                                        System.out.println("4 - Số điện thoại");
                                        System.out.println("5 - Quốc tịch");
                                        System.out.println("6 - Loại Nhân viên");
                                        System.out.println("7 - Số năm kinh nghiệm");
                                        System.out.println("8 - Khoa quản lý");
                                        System.out.println("9 - Làm trưởng Khoa");
                                        System.out.println("10 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 10)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Họ tên");
                                                System.out.print(" - Nhập họ và tên mới: "); 
                                                String newFullname = sc.nextLine();
                                                healthcareWorkerUpdate.setFullname(newFullname);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Ngày sinh");
                                                System.out.print(" - Nhập ngày sinh mới (dd-mm-yyyy hoặc ddmmyyyy): "); 
                                                String newBirthdayStr = sc.nextLine();
                                                while(!Date.getInstance().isDateFormat(newBirthdayStr)
                                                        || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                                    newBirthdayStr = sc.nextLine();
                                                System.out.println("----- -----");
                                                }
                                                Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
                                                healthcareWorkerUpdate.setBirthday(newBirthdayObj);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Giới tính");
                                                System.out.print(" - Nhập giới tính mới (Nam hoặc nữ): "); 
                                                String newGender = sc.nextLine();
                                                while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                                                    newGender = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                healthcareWorkerUpdate.setGender(newGender);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Số điện thoại");
                                                System.out.print(" - Nhập số điện thoại mới (10 số): ");
                                                String newPhone = sc.nextLine();
                                                while(newPhone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newPhone)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (10 số): ");
                                                    newPhone = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                healthcareWorkerUpdate.setPhone(newPhone);
                                                break;
                                            }
                                            case 5: {
                                                System.out.println("Đã chọn cập nhật Quốc tịch");
                                                System.out.print(" - Nhập quốc tịch mới: ");
                                                String newCountry = sc.nextLine();
                                                healthcareWorkerUpdate.setCountry(newCountry);
                                                break;
                                            }
                                            case 6: {
                                                System.out.println("Đã chọn cập nhật Loại Nhân viên");
                                                String type = healthcareWorkerUpdate.getType();
                                                String typeReverse = null;

                                                if(type.equals("Bác sĩ")) typeReverse = "Y tá";
                                                else typeReverse = "Bác sĩ";

                                                System.out.printf(" - Công việc hiện tại là %s. Bạn có muốn chuyển sang công việc %s hay không ?\n", type, typeReverse);
                                                System.out.print("Nhập 'YES' để xác nhận: ");
                                                String confirm = sc.nextLine();
                                                if(confirm.equals("YES")) {
                                                    healthcareWorkerUpdate.setType(typeReverse);
                                                    System.out.println(" - Đã đổi công việc Nhân viên sang " + typeReverse);

                                                    if(typeReverse.equals("Y tá") && healthcareWorkerUpdate.getIsManagerDepartment().equals("Có")) {
                                                        healthcareWorkerUpdate.setIsManagerDepartment("Không");
                                                        DepartmentManager.getInstance().findObjectById(healthcareWorkerUpdate.getDepartmentID()).setManagerID(null);
                                                    }
                                                } else {
                                                    System.out.println("Bạn đã không nhập 'YES' nên không cập nhật Loại bệnh nhân");
                                                }
                                                break;
                                            }
                                            case 7: {
                                                System.out.println("Đã chọn cập nhật Số năm kinh nghiệm");
                                                System.out.print(" - Nhập số năm kinh nghiệm (từ 0 trở lên): ");
                                                String yearsOfExperienceStr = sc.nextLine();
                                                while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(yearsOfExperienceStr)
                                                        || Integer.parseInt(yearsOfExperienceStr) < 0) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (từ 0 trở lên): ");
                                                    yearsOfExperienceStr = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                int yearsOfExperienceInt = Integer.parseInt(yearsOfExperienceStr);
                                                healthcareWorkerUpdate.setYearsOfExperience(yearsOfExperienceInt);
                                                break;
                                            }
                                            case 8: {
                                                System.out.println("Đã chọn cập nhật Khoa quản lý");

                                                // Xử lý những vấn đề chưa hợp logic ở Khoa cũ
                                                Department oldDepartment = DepartmentManager.getInstance().findObjectById(healthcareWorkerUpdate.getDepartmentID());
                                                if(oldDepartment != null) {
                                                    if(oldDepartment.getManagerID().equals(healthcareWorkerUpdate.getId())) {
                                                        oldDepartment.setManagerID(null);
                                                        healthcareWorkerUpdate.setIsManagerDepartment("Không");
                                                    }
                                                }

                                                // Chọn Khoa quản lý mới
                                                System.out.println(" - Chọn Khoa thuộc về");
                                                // 1 - DEP00001 | Tai-Mũi-Họng
                                                // 2 - DEP00002 | Thận
                                                // ...
                                                int subNumberList = 1;
                                                for(Department department : DepartmentManager.getInstance().getList()) {
                                                    System.out.println(subNumberList++ + " - " + department.getId() + " | " + department.getName());
                                                }
                                                // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                                String subInfo = sc.nextLine();
                                                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectById(subInfo) == null)
                                                        || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                                    subInfo = sc.nextLine();
                                                }
                                                // Lấy mã Khoa đã được chọn
                                                String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                    ? DepartmentManager.getInstance().findObjectById(subInfo).getId()
                                                    : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                healthcareWorkerUpdate.setDepartmentID(newDepartmentID);
                                                break;
                                            }
                                            case 9: {
                                                System.out.println("Đã chọn cập nhật Làm trưởng Khoa");
                                                // Lấy ra mã Khoa quản lí Nhân viên Y tế hiện tại
                                                String departmentID = healthcareWorkerUpdate.getDepartmentID();
                                                // Nếu Khoa quản lí hiện tại chưa có trưởng Khoa thì có thể thiết lập trưởng Khoa
                                                if(DepartmentManager.getInstance().findObjectById(departmentID).getManagerID() != null) {
                                                    System.out.println("Khoa " + departmentID + " hiện tại đã có trưởng Khoa");
                                                } else {
                                                    System.out.println("Vì Khoa " + departmentID + " hiện tại chưa có trưởng Khoa");
                                                    System.out.println("Bạn có muốn bổ nhiệm Nhân viên này làm trưởng Khoa ?");
                                                    System.out.print(" - Nhập 'YES' để xác nhận: ");
                                                    String conform = sc.nextLine();
                                                    if(conform.equals("YES")) {
                                                        if(healthcareWorkerUpdate.getType().equals("Bác sĩ")) {
                                                            DepartmentManager.getInstance().findObjectById(departmentID).setManagerID(healthcareWorkerUpdate.getId());
                                                            healthcareWorkerUpdate.setIsManagerDepartment("Có");
                                                            System.out.println(" - Đã bổ nhiểm Nhân viên Y tế làm trưởng Khoa");
                                                        } else {
                                                            System.out.println(" - Vì Nhân viên Y tế hiện tại là Y tá nên không thể bổ nhiệm làm trưởng Khoa");
                                                        }
                                                    } else {
                                                        System.out.println("Bạn đã không nhập 'YES' nên không bổ nhiệm");
                                                    }
                                                }
                                                System.out.print(" - Nhập 'YES' để tiếp tục: ");
                                                String wantContinue = sc.nextLine();
                                                if(!wantContinue.equals("YES")) {
                                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                                    break mainLoop;
                                                }
                                                break;
                                            }
                                            case 10: {
                                                System.out.println("Đã chọn cập nhật Tất cả");

                                                // Nhập lại họ tên mới
                                                System.out.print(" - Nhập lại họ và tên mới: "); 
                                                String newFullname = sc.nextLine();
                                                healthcareWorkerUpdate.setFullname(newFullname);

                                                // Nhập lại ngày sinh mới
                                                System.out.print(" - Nhập lại ngày sinh mới (dd-mm-yyyy hoặc ddmmyyyy): "); 
                                                String newBirthdayStr = sc.nextLine();
                                                while(!Date.getInstance().isDateFormat(newBirthdayStr)
                                                        || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                                    newBirthdayStr = sc.nextLine();
                                                System.out.println("----- -----");
                                                }
                                                Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
                                                healthcareWorkerUpdate.setBirthday(newBirthdayObj);

                                                // Nhập lại giới tính mới
                                                System.out.print(" - Nhập lại giới tính mới (Nam hoặc Nữ): "); 
                                                String newGender = sc.nextLine();
                                                while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                                                    newGender = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                healthcareWorkerUpdate.setGender(newGender);

                                                // Nhập lại số điện thoại mới
                                                System.out.print(" - Nhập lại số điện thoại mới (10 số): ");
                                                String newPhone = sc.nextLine();
                                                while(newPhone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newPhone)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (10 số): ");
                                                    newPhone = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                healthcareWorkerUpdate.setPhone(newPhone);

                                                // Nhập lại quốc tịch mới
                                                System.out.print(" - Nhập lại quốc tịch mới: ");
                                                String newCountry = sc.nextLine();
                                                healthcareWorkerUpdate.setCountry(newCountry);

                                                // Nhập lại loại Nhân viên mới
                                                String type = healthcareWorkerUpdate.getType();
                                                String typeReverse = null;
                                                String newType = null;
                                                if(type.equals("Bác sĩ")) typeReverse = "Y tá";
                                                else typeReverse = "Bác sĩ";
                                                System.out.printf(" - Loại hiện tại %s. Bạn có muốn chuyển sang loại %s hay không ?\n", type, typeReverse);
                                                System.out.print("Nhập 'YES' để xác nhận: ");
                                                String confirm = sc.nextLine();
                                                if(confirm.equals("YES")) {
                                                    newType = typeReverse;
                                                    System.out.println("! - Đã đổi loại Nhân viên");
                                                } else {
                                                    System.out.println("! - Bạn đã không nhập 'YES' nên không cập nhật Loại bệnh nhân");
                                                }
                                                healthcareWorkerUpdate.setType(newType);

                                                // Nhập lại số năm kinh nghiệm mới
                                                System.out.print(" - Nhập số năm kinh nghiệm (từ 0 trở lên): ");
                                                String newYearsOfExperienceStr = sc.nextLine();
                                                while(myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newYearsOfExperienceStr)
                                                        || Integer.parseInt(newYearsOfExperienceStr) < 0) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ NĂM KINH NGHIỆM KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (từ 0 trở lên): ");
                                                    newYearsOfExperienceStr = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                int newYearsOfExperienceInt = Integer.parseInt(newYearsOfExperienceStr);
                                                healthcareWorkerUpdate.setYearsOfExperience(newYearsOfExperienceInt);

                                                // Xử lý những vấn đề chưa hợp logic ở Khoa cũ
                                                Department oldDepartment = DepartmentManager.getInstance().findObjectById(healthcareWorkerUpdate.getDepartmentID());
                                                if(oldDepartment != null) {
                                                    if(oldDepartment.getManagerID().equals(healthcareWorkerUpdate.getId())) {
                                                        oldDepartment.setManagerID(null);
                                                        healthcareWorkerUpdate.setIsManagerDepartment("Không");
                                                    }
                                                }
                                                System.out.println(" - Chọn Khoa thuộc về");
                                                // 1 - DEP00001 | Tai-Mũi-Họng
                                                // 2 - DEP00002 | Thận
                                                // ...
                                                int subNumberList = 1;
                                                for(Department department : DepartmentManager.getInstance().getList()) {
                                                    System.out.println(subNumberList++ + " - "
                                                        + department.getId() + " | " + department.getName());
                                                }
                                                // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                                String subInfo = sc.nextLine();
                                                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectById(subInfo) == null)
                                                        || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                                    subInfo = sc.nextLine();
                                                }
                                                // Lấy mã Khoa đã được chọn
                                                String newDepartmentID = myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                    ? DepartmentManager.getInstance().findObjectById(subInfo).getId()
                                                    : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                healthcareWorkerUpdate.setDepartmentID(newDepartmentID);

                                                // Nếu Khoa quản lí mới chưa có trưởng Khoa thì có thể thiết lập trưởng Khoa
                                                if(DepartmentManager.getInstance().findObjectById(newDepartmentID).getManagerID() != null) {
                                                    System.out.println("Khoa " + newDepartmentID + " hiện tại đã có trưởng Khoa");
                                                } else {
                                                    System.out.println("Vì Khoa " + newDepartmentID + " hiện tại chưa có trưởng Khoa");
                                                    System.out.println("Bạn có muốn bổ nhiệm Nhân viên này làm trưởng Khoa ?");
                                                    System.out.print(" - Nhập 'YES' để xác nhận: ");
                                                    String conform = sc.nextLine();
                                                    if(conform.equals("YES")) {
                                                        if(healthcareWorkerUpdate.getType().equals("Bác sĩ")) {
                                                            DepartmentManager.getInstance().findObjectById(newDepartmentID).setManagerID(healthcareWorkerUpdate.getId());
                                                            healthcareWorkerUpdate.setIsManagerDepartment("Có");
                                                            System.out.println(" - Đã bổ nhiểm Nhân viên Y tế làm trưởng Khoa");
                                                        } else {
                                                            System.out.println(" - Vì Nhân viên Y tế hiện tại là Y tá nên không thể bổ nhiệm làm trưởng Khoa");
                                                        }
                                                    } else {
                                                        System.out.println("Bạn đã không nhập 'YES' nên không bổ nhiệm");
                                                    }
                                                }
                                                System.out.print(" - Nhập 'YES' để tiếp tục: ");
                                                String wantContinue = sc.nextLine();
                                                if(!wantContinue.equals("YES")) {
                                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                                    break mainLoop;
                                                }
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("Thông tin của Nhân viên Y tế sau khi sửa: " + healthcareWorkerUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Nhân viên Y tế nào thì không thể xoá một Nhân viên Y tế
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop1;
                                    }

                                    // Chọn Nhân viên Y tế cần xoá từ danh sách
                                    System.out.println(" - Chọn Nhân viên Y tế cần xoá");
                                    // 1 - HEW00001 | Thanh Quy
                                    // 2 - HEW00002 | Đức Quý An
                                    // ...
                                    int numberList = 1;
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DOC/NUR00001)
                                    System.out.print("? - Chọn: ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && HealthcareWorkerManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        info = sc.nextLine();
                                    }

                                    // Lấy thông tin của Nhân viên Y tế cần xoá
                                    HealthcareWorker healthcareWorkerRemove = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        healthcareWorkerRemove = HealthcareWorkerManager.getInstance().findObjectById(info);
                                    } else {
                                        healthcareWorkerRemove =  HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Nhân viên Y tế sắp được xoá để xử lý những việc sau
                                    String healthcareWorkerRemoveID = healthcareWorkerRemove.getId();

                                    // Huỷ liên với các đối tượng liên quan
                                    // - Tài khoản
                                    AccountManager.getInstance().remove(healthcareWorkerRemoveID);
                                    // - Khoa (nếu Nhân viên sắp xoá là Bác sĩ và là trưởng Khoa của một Khoa)
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        if(department.getManagerID() == null) continue;
                                        if(HealthcareWorkerManager.getInstance().findObjectById(healthcareWorkerRemoveID).getIsManagerDepartment().equals("Có")
                                            && department.getManagerID().equals(healthcareWorkerRemoveID))  department.setManagerID(null);
                                    }
                                    // - Bệnh án
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getHealthcareWorkerID() == null) continue;
                                        if(medicalRecord.getHealthcareWorkerID().equals(healthcareWorkerRemoveID)) 
                                            medicalRecord.setHealthcareWorkerID(null);
                                    }

                                    // Xoá Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().removeOne(healthcareWorkerRemoveID);

                                    // Thông báo thông tin của Nhân viên Y tế đã xoá
                                    System.out.println("! - Đã xoá một Nhân viên Y tế: " + healthcareWorkerRemove.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Nhân viên Y tế nào thì không thể xoá tất cả Nhân viên Y tế
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop1;
                                    }

                                    // Tìm những đối tượng có liên quan đến Nhân viên Y tế để xoá sự liên kết
                                    // - Tài khoản
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        AccountManager.getInstance().remove(healthcareWorker.getId());
                                    }
                                    // - Đối tượng Khoa
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getIsManagerDepartment().equals("Có")) {
                                            DepartmentManager.getInstance().findObjectById(healthcareWorker.getId()).setManagerID(null);;
                                        }
                                    }
                                    // - Đối tượng Bệnh án
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getMedicalRecordID() != null) {
                                            MedicalRecordManager.getInstance().findObjectById(healthcareWorker.getMedicalRecordID()).setHealthcareWorkerID(null);
                                        }
                                    }

                                    // Xoá tất cả Nhân viên Y tế hiện có
                                    HealthcareWorkerManager.getInstance().removeAll();

                                    // Thông báo là đã xoá hết Nhân viên Y tế
                                    System.out.println("! - Đã xoá tất cả Nhân viên Y tế");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Nhân viên Y tế nào thì không thể tìm kiếm Nhân viên Y tế
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop1;
                                    }

                                    // Tìm kiếm bằng mã Nhân viên Y tế hay tên Nhân viên Y tế đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Nhân viên Y tế hoặc tên Nhân viên Y tế");
                                    System.out.print(" - Nhập thông tin Nhân viên Y tế cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<HealthcareWorker> healthcareWorkerSearchList = new ArrayList<>();
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getFullname().toLowerCase().contains(info.trim().toLowerCase())
                                            || healthcareWorker.getId().equals(info)) healthcareWorkerSearchList.add(healthcareWorker);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(healthcareWorkerSearchList.size() == 0) {
                                        System.out.println("! - Không tìm được Nhân viên Y tế nào với thông tin đã cho");
                                    } else {
                                        for(HealthcareWorker healthcareWorkerSearch : healthcareWorkerSearchList) {
                                            System.out.println(healthcareWorkerSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Nhân viên Y tế nào thì không thể sắp xếp Nhân viên Y tế
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop1;
                                    }

                                    // Sắp xếp các Nhân viên Y tế trong Danh sách theo lựa chọn
                                    System.err.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo mã tăng dần");
                                    System.out.println("2 - Sắp xếp theo mã giảm dần");
                                    System.out.println("3 - Sắp xếp theo tên tăng dần");
                                    System.out.println("4 - Sắp xếp theo tên giảm dần");
                                    System.out.println("5 - Sắp xếp theo ngày sinh tăng dần");
                                    System.out.println("6 - Sắp xếp theo ngày sinh giảm dần");
                                    System.out.println("7 - Sắp xếp theo số kinh nghiệm tăng dần");
                                    System.out.println("8 - Sắp xếp theo số kinh nghiệm giảm dần");
                                    System.out.println("9 - Sắp xếp theo tiền lương tăng dần");
                                    System.out.println("10 - Sắp xếp theo tiền lương giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!isValidChoice(subChoice3, 0, 10)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if(subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        HealthcareWorkerManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        HealthcareWorkerManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        HealthcareWorkerManager.getInstance().sort("name asc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo tên tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        HealthcareWorkerManager.getInstance().sort("name desc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo tên giảm dần");
                                    } else if(subChoice3.equals("5")) {
                                        HealthcareWorkerManager.getInstance().sort("birthday asc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo ngày sinh tăng dần");
                                    } else if(subChoice3.equals("6")) {
                                        HealthcareWorkerManager.getInstance().sort("birthday desc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo ngày sinh giảm dần");
                                    } else if(subChoice3.equals("7")) {
                                        HealthcareWorkerManager.getInstance().sort("years asc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo số kinh nghiệm tăng dần");
                                    } else if(subChoice3.equals("8")) {
                                        HealthcareWorkerManager.getInstance().sort("years desc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo số kinh nghiệm giảm dần");
                                    } else if(subChoice3.equals("9")) {
                                        HealthcareWorkerManager.getInstance().sort("salary asc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo tiền lương tăng dần");
                                    } else if(subChoice3.equals("10")) {
                                        HealthcareWorkerManager.getInstance().sort("salary desc");
                                        System.out.println("! - Đã sắp xếp danh sách Nhân viên Y tế theo tiền lương giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Nhân viên Y tế");

                                    // Truy xuất dữ liệu từ Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Nhân viên Y tế thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Nhân viên Y tế thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Nhân viên Y tế");

                                    // Sao lưu dữ liệu từ Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu từ Nhân viên Y tế thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Nhân viên Y tế thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                                clearTerminal();
                            }
                        } else if(subChoice1.equals("6")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Bệnh nhân");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ BỆNH NHÂN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Bệnh nhân");
                                System.out.println("3 - Thêm một Bệnh nhân");
                                System.out.println("4 - Sửa một Bệnh nhân");
                                System.out.println("5 - Xoá một Bệnh nhân");
                                System.out.println("6 - Xoá tất cả Bệnh nhân");
                                System.out.println("7 - Tìm kiếm Bệnh nhân");
                                System.out.println("8 - Sắp xếp danh sách các Bệnh nhân");
                                System.out.println("9 - Truy xuất dữ liệu Bệnh nhân");
                                System.out.println("10 - Sao lưu dữ liệu Bệnh nhân");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh nhân");

                                    // In danh sách thông tin các Bệnh nhân
                                    PatientManager.getInstance().show();

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Bệnh nhân");
                                    
                                    // Nhập tên Bệnh nhân
                                    System.out.print(" -+ Nhập họ tên: ");
                                    String fullname = sc.nextLine();

                                    // Nhập ngày sinh Bệnh nhân
                                    System.out.print(" -+ Nhập ngày sinh (dd-mm-yyyy hoặc ddmmyyyy): ");
                                    String birthdayStr = sc.nextLine();
                                    while(!Date.getInstance().isDateFormat(birthdayStr)
                                            || !Date.getInstance().getDateFromDateFormat(birthdayStr).isDate()) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                        birthdayStr = sc.nextLine();
                                       System.out.println("----- -----");
                                    }
                                    Date birthdayObj = Date.getInstance().getDateFromDateFormat(birthdayStr);

                                    // Nhập giới tính Bệnh nhân
                                    System.out.print(" -+ Nhập giới tính (Nam / Nữ): ");
                                    String gender = sc.nextLine();
                                    while(!gender.equals("Nam") && !gender.equals("Nữ")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Nam / Nữ): ");
                                        gender = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Nhập số điện thoại Bệnh nhân
                                    System.out.print(" -+ Nhập số điện thoại (10 số): ");
                                    String phone = sc.nextLine();
                                    while(phone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(phone)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (10 số): ");
                                        phone = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Nhập quốc tịch Bệnh nhân
                                    System.out.print(" -+ Nhập quốc tịch: ");
                                    String country = sc.nextLine();

                                    // Nhập loại chăm sóc Bệnh nhân
                                    System.out.print(" -+ Nhập loại chăm sóc (Bình thường hoặc Cao cấp): ");
                                    String patientType = sc.nextLine();
                                    while(!patientType.equals("Bình thường") && !patientType.equals("Cao cấp")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LOẠI CHĂM SÓC KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Bình thường hoặc Cao cấp): ");
                                        patientType = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tạo một Bệnh nhân mới
                                    Patient newPatient = new Patient(fullname, birthdayObj, gender, phone, country, patientType);
                                    PatientManager.getInstance().add(newPatient);

                                    // Tạo tài khoản đăng nhập riêng cho Nhân viên Y tế mới tạo đó
                                    String newUsername = newPatient.getId();
                                    String newPassword = newPatient.getBirthday().getDateFormatByCondition("has not cross");
                                    Account newAccount = new Account(newUsername, newPassword, "Bệnh nhân");
                                    AccountManager.getInstance().add(newAccount);

                                    // Lấy ra mã Bệnh nhân để làm những việc sau
                                    String patientID = newPatient.getId();

                                    // Thông báo đã thêm một Bệnh nhân
                                    clearTerminal();
                                    System.out.println("! - Đã thêm Bệnh nhân: " + newPatient.getInfo());

                                    // Tạo một Hồ sơ Bệnh án cho Bệnh nhân vừa mới được tạo
                                    System.out.println("Bạn cần tạo Hồ sơ Bệnh án cho Bệnh nhân " + newPatient.getId() + " - " + newPatient.getFullname());
                                    // - Nhập ngày mở Hồ sơ
                                    System.out.print(" - Nhập ngày mở Hồ sơ (dd-mm-yyyy hoặc ddmmyyyy): ");
                                    String inputDayStr = sc.nextLine();
                                    while(!Date.getInstance().isDateFormat(inputDayStr)
                                            || !Date.getInstance().getDateFromDateFormat(inputDayStr).isDate()) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NGÀY MỞ HỒ SƠ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                        inputDayStr = sc.nextLine();
                                       System.out.println("----- -----");
                                    }
                                    Date inputDayObj = Date.getInstance().getDateFromDateFormat(inputDayStr);
                                    // - Nhập ngày đóng Hồ sơ
                                    System.out.print(" - Nhập ngày đóng Hồ sơ (dd-mm-yyyy hoặc ddmmyyyy): ");
                                    String outputDayStr = sc.nextLine();
                                    while(!Date.getInstance().isDateFormat(outputDayStr)
                                            || !Date.getInstance().getDateFromDateFormat(outputDayStr).isDate()) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NGÀY ĐÓNG HỒ SƠ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                        outputDayStr = sc.nextLine();
                                       System.out.println("----- -----");
                                    }
                                    Date outputDayObj = Date.getInstance().getDateFromDateFormat(outputDayStr);
                                    // - Nhập loại Hồ sơ Bệnh án (Hồ sơ Chữa bệnh hoặc Hồ sơ Khám bệnh)
                                    System.out.print(" - Nhập loại Hồ sơ (Khám bệnh hoặc Chữa bệnh): ");
                                    String medicalRecordType = sc.nextLine();
                                    while(!medicalRecordType.equals("Khám bệnh") && !medicalRecordType.equals("Chữa bệnh")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LOẠI HỒ SƠ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Khám bệnh hoặc Chữa bệnh): ");
                                        medicalRecordType = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Chọn Bệnh cho Bệnh nhân đang mắc phải
                                    System.out.println(" - Chọn loại Bệnh)");
                                    int numberList1 = 1;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(numberList1++ + " - " + sick.getId() + " | " + sick.getName());
                                    }
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh): ");
                                    String info1 = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
                                                && SickManager.getInstance().findObjectById(info1) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
                                                && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info1) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
                                        info1 = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    String sickID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info1)
                                        ? SickManager.getInstance().findObjectById(info1).getId()
                                        : SickManager.getInstance().findObjectByIndex(Integer.parseInt(info1) - 1).getId();
                                    // - Nhập mức độ Bệnh cho Bệnh vừa chọn
                                    System.out.print(" - Nhập mức độ Bệnh (Nhẹ, Vừa hoặc Nặng): ");
                                    String sickLevel = sc.nextLine();
                                    while(!sickLevel.equals("Nhẹ") && !sickLevel.equals("Vừa") && !sickLevel.equals("Nặng")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - MỨC ĐỘ BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        sickLevel = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Chọn Nhân viên Y tế khám/chữa cho Bệnh nhân này (vì phải biết Bệnh mới biết cần Nhân viên thuộc Khoa nào khám)
                                    // -- Nếu là Chữa bệnh thì chỉ có thể Bác sĩ chữa
                                    // -- Nếu là Khám bệnh thì có thể là Bác sĩ hoặc Y tá khám
                                    System.out.println(" - Chọn Nhân viên Y tế");
                                    int numberList2 = 1;
                                    if(medicalRecordType.equals("Khám bệnh")) {
                                        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                            if(healthcareWorker.getMedicalRecordID() == null
                                                    && SickManager.getInstance().findObjectById(sickID).getDepartmentID().equals(healthcareWorker.getDepartmentID())) {
                                                System.out.println(numberList2 + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                            }
                                            numberList2++;
                                        }
                                    } else {
                                        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                            if(healthcareWorker.getType().equals("Bác sĩ") && healthcareWorker.getMedicalRecordID() == null
                                                    && SickManager.getInstance().findObjectById(sickID).getDepartmentID().equals(healthcareWorker.getDepartmentID())) {
                                                System.out.println(numberList2 + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                            }
                                            numberList2++;
                                        }
                                    }
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Nhân viên Y tế): ");
                                    String info2 = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
                                                && HealthcareWorkerManager.getInstance().findObjectById(info2) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
                                                && HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info2) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Nhân viên Y tế): ");
                                        info2 = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    String healthcareWorkerID = myCharacterClass.getInstance().hasOneCharacterIsLetter(info2)
                                        ? HealthcareWorkerManager.getInstance().findObjectById(info2).getId()
                                        : HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info2) - 1).getId();

                                    // Tạo một Bệnh án mới
                                    MedicalRecord newMedicalRecord = null;
                                    if(medicalRecordType.equals("Khám bệnh")) {
                                        newMedicalRecord = new TestRecord(inputDayObj, outputDayObj, medicalRecordType, patientID, sickID, sickLevel, healthcareWorkerID);
                                    } else {
                                        newMedicalRecord = new TreatmentRecord(inputDayObj, outputDayObj, medicalRecordType, patientID, sickID, sickLevel, healthcareWorkerID);
                                    }
                                    MedicalRecordManager.getInstance().add(newMedicalRecord);

                                    // Thiết lập Bệnh nhân mới vừa được tạo thuộc về Bệnh án cũng mới vừa được tạo này
                                    newPatient.setMedicalRecordID(newMedicalRecord.getId());
                                    // Thiết lập Nhân viên Y tế thuộc về Bệnh án mới vừa được tạo
                                    HealthcareWorkerManager.getInstance().findObjectById(healthcareWorkerID).setMedicalRecordID(newMedicalRecord.getId());

                                    // Thông báo đã tạo Hồ sơ Bệnh án cho Bệnh nhân mới vừa tạo
                                    System.out.println("! - Đã tạo thành công Bệnh án " + newMedicalRecord.getId() + " cho Bệnh nhân " + newPatient.getId() + " - " + newPatient.getFullname());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể sửa Bệnh nhân
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh nhân cần sửa từ danh sách
                                    System.out.println(" - Chọn Bệnh nhân cần sửa");
                                    // 1 -- PAT00001 | Thanh Quy
                                    // 2 -- PAT00002 | Duy Quý
                                    // 3 -- PAT00003 | Minh Đức
                                    // 4 -- PAT00004 | Phước An
                                    // ...
                                    int numberList = 1;
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - "
                                            + patient.getId() + " | " + patient.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh nhân): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && PatientManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH NHÂN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh nhân): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Bệnh nhân cần sửa
                                    Patient patientUpdate = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        patientUpdate = PatientManager.getInstance().findObjectById(info);
                                    } else {
                                        patientUpdate = PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Bệnh nhân đã chọn để sửa: " + patientUpdate.getInfo());
                                    
                                    // Cập nhật thông tin của Bệnh nhân đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Họ tên");
                                        System.out.println("2 - Ngày sinh");
                                        System.out.println("3 - Giới tính");
                                        System.out.println("4 - Số điện thoại");
                                        System.out.println("5 - Quốc tịch");
                                        System.out.println("6 - Loại chăm sóc");
                                        System.out.println("7 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 7)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn Quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Họ tên");
                                                System.out.print(" - Nhập họ và tên mới: ");
                                                String newFullname = sc.nextLine();
                                                patientUpdate.setFullname(newFullname);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Ngày sinh");
                                                System.out.print(" - Nhập ngày sinh mới (dd-mm-yyyy hoặc ddmmyyyy): ");
                                                String newBirthdayStr = sc.nextLine();
                                                while(!Date.getInstance().isDateFormat(newBirthdayStr)
                                                        || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                                    newBirthdayStr = sc.nextLine();
                                                System.out.println("----- -----");
                                                }
                                                Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
                                                patientUpdate.setBirthday(newBirthdayObj);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Giới tính");
                                                System.out.print(" - Nhập giới tính mới (Nam hoặc nữ): "); 
                                                String newGender = sc.nextLine();
                                                while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                                                    newGender = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                patientUpdate.setGender(newGender);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Số điện thoại");
                                                System.out.print(" - Nhập số điện thoại mới (10 số): ");
                                                String newPhone = sc.nextLine();
                                                while(newPhone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newPhone)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (10 số): ");
                                                    newPhone = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                patientUpdate.setPhone(newPhone);
                                                break;
                                            }
                                            case 5: {
                                                System.out.println("Đã chọn cập nhật Quốc tịch");
                                                System.out.print(" - Nhập quốc tịch mới: ");
                                                String newCountry = sc.nextLine();
                                                patientUpdate.setCountry(newCountry);
                                                break;
                                            }
                                            case 6: {
                                                System.out.println("Đã chọn cập nhật Loại chăm sóc");

                                                String type = patientUpdate.getType();
                                                String typeReverse = null;
                                                if(type.equals("Bình thường")) typeReverse = "Cao cấp";
                                                else typeReverse = "Bình thường";

                                                System.out.printf(" - Loại chăm sóc hiện tại %s. Bạn có muốn chuyển sang loại chăm sóc %s hay không ?\n", type, typeReverse);
                                                System.out.print("Nhập 'YES' để xác nhận: ");
                                                String confirm = sc.nextLine();
                                                if(confirm.equals("YES")) {
                                                    patientUpdate.setType(typeReverse);
                                                    System.out.println(" - Đã đổi loại chăm sóc sang " + typeReverse);
                                                } else {
                                                    System.out.println("Bạn đã không nhập 'YES' nên không cập nhật Loại chăm sóc");
                                                }

                                                System.out.print(" - Nhập 'YES' để tiếp tục: ");
                                                String wantContinue = sc.nextLine();
                                                if(!wantContinue.equals("YES")) {
                                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                                    break mainLoop;
                                                }
                                                break;
                                            }
                                            case 7: {
                                                System.out.println("Đã chọn cập nhật Tất cả");

                                                // Nhập họ tên mới
                                                System.out.print(" - Nhập họ và tên mới: "); 
                                                String newFullname = sc.nextLine();
                                                patientUpdate.setFullname(newFullname);

                                                // Nhập ngày sinh mới
                                                System.out.print(" - Nhập ngày sinh mới: "); 
                                                String newBirthdayStr = sc.nextLine();
                                                while(!Date.getInstance().isDateFormat(newBirthdayStr)
                                                        || !Date.getInstance().getDateFromDateFormat(newBirthdayStr).isDate()) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - NGÀY SINH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (dd-mm-yyyy hoặc ddmmyyyy): ");
                                                    newBirthdayStr = sc.nextLine();
                                                System.out.println("----- -----");
                                                }
                                                Date newBirthdayObj = Date.getInstance().getDateFromDateFormat(newBirthdayStr);
                                                patientUpdate.setBirthday(newBirthdayObj);

                                                // Nhập giới tính mới
                                                System.out.print(" - Nhập giới tính mới (Nam hoặc Nữ): "); 
                                                String newGender = sc.nextLine();
                                                while(!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - GIỚI TÍNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (Nam hoặc Nữ): ");
                                                    newGender = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                patientUpdate.setGender(newGender);

                                                // Nhập số điện thoại mới
                                                System.out.print(" - Nhập số điện thoại mới (10 số): ");
                                                String newPhone = sc.nextLine();
                                                while(newPhone.length() != 10 || myCharacterClass.getInstance().hasOneCharacterIsNotNumber(newPhone)) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Nhập lại (10 số): ");
                                                    newPhone = sc.nextLine();
                                                    System.out.println("----- -----");
                                                }
                                                patientUpdate.setPhone(newPhone);

                                                // Nhập quốc tịch mới
                                                System.out.print(" - Nhập quốc tịch mới: ");
                                                String newCountry = sc.nextLine();
                                                patientUpdate.setCountry(newCountry);

                                                // Nhập loại chăm sóc mới
                                                String type = patientUpdate.getType();
                                                String typeReverse = null;
                                                String newType = null;
                                                if(type.equals("Bình thường")) typeReverse = "Cao cấp";
                                                else typeReverse = "Bình thường";
                                                System.out.printf(" - Loại chăm sóc hiện tại %s. Bạn có muốn chuyển sang loại chăm sóc %s hay không ?\n", type, typeReverse);
                                                System.out.print("Nhập 'YES' để xác nhận: ");
                                                String confirm = sc.nextLine();
                                                if(confirm.equals("YES")) {
                                                    newType = typeReverse;
                                                    System.out.println(" - Đã đổi loại chăm sóc sang " + typeReverse);
                                                } else {
                                                    System.out.println("Bạn đã không nhập 'YES' nên không cập nhật Loại chăm sóc");
                                                }
                                                patientUpdate.setType(newType);

                                                System.out.print(" - Nhập 'YES' để tiếp tục: ");
                                                String wantContinue = sc.nextLine();
                                                if(!wantContinue.equals("YES")) {
                                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                                    break mainLoop;
                                                }
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("Thông tin của Bệnh nhân sau khi sửa: " + patientUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể xoá Bệnh nhân
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần xoá từ danh sách
                                    System.out.println(" - Chọn Bệnh nhân cần xoá");
                                    // 1 -- PAT00001 | Thanh Quy
                                    // 2 -- PAT00002 | Duy Quý
                                    // 3 -- PAT00003 | Minh Đức
                                    // 4 -- PAT00004 | Phước An
                                    // ...
                                    int numberList = 1;
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " - " + patient.getId() + " | " + patient.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh nhân): ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && PatientManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - Bệnh nhân KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh nhân): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Bệnh nhân cần xoá
                                    Patient patientRemove = null;
                                    if(myCharacterClass.getInstance().hasOneCharacterIsLetter(info)) {
                                        patientRemove = PatientManager.getInstance().findObjectById(info);
                                    } else {
                                        patientRemove = PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Bệnh nhân sắp được xoá để xử lý những việc bên dưới
                                    String patientRemoveID = patientRemove.getId();

                                    // Tìm những đối tượng có liên quan đến Bệnh nhân để xoá sự liên kết
                                    // - Bệnh án thì xoá luôn
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getPatientID().equals(patientRemoveID)) {
                                            MedicalRecordManager.getInstance().removeOne(medicalRecord.getId());
                                            break;
                                        }
                                    }

                                    // Xoá Bệnh nhân đã tìm
                                    PatientManager.getInstance().removeOne(patientRemoveID);

                                    // Thông báo thông tin của Bệnh nhân đã xoá
                                    System.out.println("! - Đã xoá một Khoa: " + patientRemove.getInfo());

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể xoá bất cứ Bệnh nhân nào
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Tìm những đối tượng có liên quan đến Bệnh nhân để xoá sự liên kết
                                    // - Bệnh án
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        MedicalRecordManager.getInstance().removeOne(medicalRecord.getId());
                                    }

                                    // Xoá tất cả Bệnh nhân hiện có
                                    PatientManager.getInstance().removeAll();

                                    // Thông báo là đã xoá hết
                                    System.out.println("! - Đã xoá tất cả Khoa");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể tìm kiếm Bệnh nhân
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm bằng mã Bệnh nhân hay tên Bệnh nhân đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Bệnh nhân hoặc họ tên Bệnh nhân");
                                    System.out.print(" - Nhập thông tin Bệnh nhân cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Patient> patientSearchList = new ArrayList<>();
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        if(patient.getFullname().toLowerCase().contains(info.trim().toLowerCase())
                                            || patient.getId().equals(info)) patientSearchList.add(patient);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(patientSearchList.size() == 0) {
                                        System.out.println("! - Không tìm được Khoa nào với thông tin đã cho");
                                    } else {
                                        for(Patient patientSearch : patientSearchList) {
                                            System.out.println(patientSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể sắp xếp Bệnh nhân
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Sắp xếp các Bệnh nhân trong Danh sách theo lựa chọn
                                    System.out.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo mã tăng dần");
                                    System.out.println("2 - Sắp xếp theo mã giảm dần");
                                    System.out.println("3 - Sắp xếp theo tên tăng dần");
                                    System.out.println("4 - Sắp xếp theo tên giảm dần");
                                    System.out.println("5 - Sắp xếp theo ngày sinh tăng dần");
                                    System.out.println("6 - Sắp xếp theo ngày sinh giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!!isValidChoice(subChoice3, 0, 6)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if(subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        PatientManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        PatientManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        PatientManager.getInstance().sort("name asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo tên tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        PatientManager.getInstance().sort("name desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo tên tăng dần");
                                    } else if(subChoice3.equals("5")) {
                                        PatientManager.getInstance().sort("birthday asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo ngày sinh tăng dần");
                                    } else if(subChoice3.equals("6")) {
                                        PatientManager.getInstance().sort("birthday desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh nhân theo ngày sinh giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh nhân");

                                    // Truy xuất dữ liệu từ Bệnh nhân
                                    PatientManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Bệnh nhân thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Bệnh nhân thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("10")) {
                                    System.out.println("Đã chọn Sao lưu dữ liệu Bệnh nhân");

                                    // Sao lưu dữ liệu từ Bệnh nhân
                                    PatientManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu từ Bệnh nhân thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Bệnh nhân thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                                clearTerminal();
                            }
                        } else if(subChoice1.equals("7")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Bệnh án");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ BỆNH ÁN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Bệnh án");
                                System.out.println("3 - Thêm một Bệnh án");
                                System.out.println("4 - Sửa một Bệnh án");
                                System.out.println("5 - Xoá một Bệnh án");
                                System.out.println("6 - Xoá tất cả Bệnh án");
                                System.out.println("7 - Tìm kiếm Bệnh án");
                                System.out.println("8 - Sắp xếp danh sách các Bệnh án");
                                System.out.println("9 - Truy xuất dữ liệu Bệnh án");
                                System.out.println("10 - Sao lưu dữ liệu Bệnh án");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!isValidChoice(subChoice2, 0, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                if(subChoice2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoice2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoice2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh án");

                                    // In danh sách thông tin các Hồ sơ Bệnh án
									MedicalRecordManager.getInstance().show();
                                    
                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Bệnh án");

                                    

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh án");

                                    // Nếu không có Bệnh án nào được tạo thì không thể sửa Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh án viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh án cần sửa từ danh sách
                                    System.out.println(" -- Chọn Bệnh án cần sửa");
                                    // 1 -- MER00001 | PPAT00001 - Thanh Quy
                                    // 2 -- MER00002 | NPAT00002 - Quý Đức An
                                    // ...
                                    int numberList = 1;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println(numberList++ + " -- "
                                            + medicalRecord.getId() + " | " + medicalRecord.getPatientID() + " - "
                                            + PatientManager.getInstance().findObjectById( medicalRecord.getPatientID()));
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn MER00001)
                                    System.out.print("? -- Chọn: ");
                                    String info = sc.nextLine();
                                    while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && MedicalRecordManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH ÁN KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        info = sc.nextLine();
                                    }
                                    MedicalRecord medicalRecordUpdate = null;
                                    if(info.length() != 1) {
                                        medicalRecordUpdate = MedicalRecordManager.getInstance().findObjectById(info);
                                    } else {
                                        medicalRecordUpdate =  MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Cập nhật thông tin của Bệnh án đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Loại hồ sơ");
                                        System.out.println("2 - Bác sĩ");
                                        System.out.println("3 - Bệnh");
                                        System.out.println("4 - Mức độ Bệnh");
                                        System.out.println("5 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();
                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!isValidChoice(subChoice3, 0, 3)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        // Chuyển [subChoice3] sang int -> switch
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Loại hồ sơ");
                                                
                                                break;
                                            }
                                            case 2: {
                                                // System.out.println("Đã chọn cập nhật Bác sĩ");
                                                // System.out.println(" - Chọn Bác sĩ mới để chữa bệnh");
                                                // // 1 - DEP00001 | Tai-Mũi-Họng
                                                // // 2 - DEP00002 | Thận
                                                // // ...
                                                // int subNumberList = 1;
                                                // for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                                //     if(healthcareWorker.getType().equals("Bác sĩ")
                                                //             && healthcareWorker.getId().equals(medicalRecordUpdate.getHealthcareWorkerID())) {
                                                //         System.out.println(subNumberList++ + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                                //     }
                                                // }
                                                // // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                // System.out.print("? - Chọn: ");
                                                // String subInfo = sc.nextLine();
                                                // while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                //             && HealthcareWorkerManager.getInstance().findObjectById(subInfo) == null)
                                                //         || HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null) {
                                                //     System.out.println("----- -----");
                                                //     System.out.println("! - BÁC SĨ KHÔNG HỢP LỆ");
                                                //     System.out.print("?! - Chọn lại: ");
                                                //     subInfo = sc.nextLine();
                                                // }
                                                // // Lấy mã Khoa đã được chọn
                                                // String newHealthcareWorkerID = subInfo.length() == 1
                                                //     ? HealthcareWorkerManager.getInstance().findObjectById(subInfo).getId()
                                                //     : HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                // medicalRecordUpdate.setHealthcareWorkerID(newHealthcareWorkerID);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Bệnh");
                                                System.out.println(" - Chọn Bác sĩ mới để chữa bệnh");
                                                // 1 - SICK00001 | Đau bệnh
                                                // 2 - SICK00002 | Đau mắt
                                                // ...
                                                int subNumberList = 1;
                                                for(Sick sick : SickManager.getInstance().getList()) {
                                                    System.out.println(subNumberList++ + " - " + sick.getId() + " | " + sick.getName());
                                                }
                                                // Cho phép chọn subNumberList - id (chọn 1 hoặc chọn DEP00001)
                                                System.out.print("? - Chọn: ");
                                                String subInfo = sc.nextLine();
                                                while((myCharacterClass.getInstance().hasOneCharacterIsLetter(subInfo)
                                                            && SickManager.getInstance().findObjectById(subInfo) == null)
                                                        || SickManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1) == null) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại: ");
                                                    subInfo = sc.nextLine();
                                                }
                                                // Lấy mã Khoa đã được chọn
                                                String newSickID = subInfo.length() == 1
                                                    ? SickManager.getInstance().findObjectById(subInfo).getId()
                                                    : SickManager.getInstance().findObjectByIndex(Integer.parseInt(subInfo) - 1).getId();
                                                medicalRecordUpdate.setSickID(newSickID);
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Mức độ Bệnh");
                                                System.out.print(" - Nhập mức độ Bệnh mới (Nhẹ. Vừa hoặc Nặng):");
                                                String newSickLevel = sc.nextLine();
                                                while(!newSickLevel.equals("Nhẹ") || !!newSickLevel.equals("Vừa") || !newSickLevel.equals("Nặng")) {
                                                    System.out.println("----- -----");
                                                    System.out.println("! - MỨC ĐỘ BỆNH KHÔNG HỢP LỆ");
                                                    System.out.print("?! - Chọn lại: ");
                                                    newSickLevel = sc.nextLine();
                                                }
                                                medicalRecordUpdate.setSickLevel(newSickLevel);
                                            }
                                            case 5: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("! - Thông tin của Khoa sau khi sửa: " + medicalRecordUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("5")) { 
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh án");

                                    if (MedicalRecordManager.getInstance().getNumbers() == 0) {
										clearTerminal();
										System.out.println("Hiện tại bệnh viện chưa cập nhật bệnh án, cần tạo ít nhất một bệnh án");
									}
									// Chọn bệnh án cần xóa
									System.out.println("Danh sách các Bệnh án");
									int numberList = 1;
									for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
										System.out.println(numberList++ + " -- " + medicalRecord.getId() + " | "
												+ medicalRecord.getPatientID());
									}
									System.out.print("? -- Chọn (số thứ tự hoặc mã Bệnh án): ");
									String info = sc.nextLine();
									while((myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && MedicalRecordManager.getInstance().findObjectById(info) == null)
                                            || (!myCharacterClass.getInstance().hasOneCharacterIsLetter(info)
                                                && MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
										System.out.println("----- -----");
										System.out.println("! -- BỆNH ÁN KHÔNG HỢP LỆ");
										System.out.print("?! -- Chọn lại (số thứ tự hoặc mã Bệnh án): ");
										info = sc.nextLine();
									}
									MedicalRecord medicalRecordRemove = null;
									if(info.length() != 1) {
										medicalRecordRemove = MedicalRecordManager.getInstance().findObjectById(info);
									} else {
										medicalRecordRemove = MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
									}

                                    // Lấy ra mã Bệnh án sắp được xoá để thực hiện những việc sau
                                    String medicalRecordIDRemove = medicalRecordRemove.getId();

									// Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Bệnh nhân
									for(Patient patient : PatientManager.getInstance().getList()) {
										if(patient.getMedicalRecordID() == null) continue;
										if(patient.getMedicalRecordID().equals(medicalRecordIDRemove)) {
											patient.setMedicalRecordID(null);
										}
									}
									// - Nhân viên Y tế
									for (HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
										if(healthcareWorker.getMedicalRecordID() == null) continue;
										if(healthcareWorker.getMedicalRecordID().equals(medicalRecordIDRemove)) {
											healthcareWorker.setMedicalRecordID(null);
										}
									}

									// Xóa bệnh án
									MedicalRecordManager.getInstance().removeOne(medicalRecordIDRemove);

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Bệnh án");

                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
										clearTerminal();
										System.out.println("Hiện tại bệnh viện chưa cập nhật bệnh án, cần tạo ít nhất một bệnh án");
									}
									// Tìm những đối tượng có liên quan đến Bệnh án để xoá sự liên kết
									// - Bệnh nhân
									for(Patient patient : PatientManager.getInstance().getList()) {
										patient.setMedicalRecordID(null);
									}
									// - Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        healthcareWorker.setMedicalRecordID(null);
                                    }

									// Xóa tất cả bệnh án
									MedicalRecordManager.getInstance().removeAll();

									// Thông báo là đã xoá hết
									System.out.println("! -- Đã xoá tất cả Bệnh án");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh án");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm bằng mã Bệnh án hay mã Bệnh nhân đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Bệnh án, ngày mở Hồ sơ (dd-mm-yyyy hoặc ddmmyyyy) hoặc mã Bệnh nhân");
                                    System.out.print(" -- Nhập thông tin Bệnh án cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<MedicalRecord> medicalRecordSearchList = new ArrayList<>();
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getId().equals(info) || medicalRecord.getPatientID().equals(info)
                                                || medicalRecord.getInputDay().getDateFormatByCondition("has cross").equals(info)
                                                || medicalRecord.getInputDay().getDateFormatByCondition("has not cross").equals(info))
                                            medicalRecordSearchList.add(medicalRecord);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(medicalRecordSearchList.size() == 0) {
                                        System.out.println("! -- Không tìm được Bệnh án nào với thông tin đã cho");
                                    } else {
                                        for(MedicalRecord medicalRecordSearch : medicalRecordSearchList) {
                                            System.out.println(medicalRecordSearch.getInfo());
                                        }
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh án");

                                    // Nếu không có Bệnh án nào được tạo thì không thể sắp xếp Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Sắp xếp các Bệnh trong Danh sách theo lựa chọn
                                    System.err.println("0 - Quay lại");
                                    System.out.println("1 - Sắp xếp theo mã tăng dần");
                                    System.out.println("2 - Sắp xếp theo mã giảm dần");
                                    System.out.println("3 - Sắp xếp theo ngày nhập hồ sơ tăng dần");
                                    System.out.println("4 - Sắp xếp theo ngày nhập hồ sơ giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!!isValidChoice(subChoice3, 0, 4)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        subChoice3 = sc.nextLine();
                                    }
                                    if(subChoice3.equals("0")) {
                                        clearTerminal();
                                        System.out.println("! - Đã chọn Quay lại");
                                        continue subLoop2;
                                    }
                                    else if(subChoice3.equals("1")) {
                                        MedicalRecordManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        MedicalRecordManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        MedicalRecordManager.getInstance().sort("inputDay asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo ngày nhập hồ sơ tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        MedicalRecordManager.getInstance().sort("inputDay desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo ngày nhập hồ sơ giảm dần");
                                    }

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh án");

                                    // Truy xuất dữ liệu Bệnh án
                                    MedicalRecordManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Bệnh án thành công
                                    System.out.println("! - Đã truy xuất dữ liệu từ Bệnh án thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                } else if(subChoice2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu Bệnh án");

                                    // Sao lưu dữ liệu Bệnh án
                                    MedicalRecordManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu từ Bệnh án thành công
                                    System.out.println("! - Đã sao lưu dữ liệu từ Bệnh án thành công");

                                    // Thông báo hỏi có tiếp tục hay không
                                    System.out.print("Nhập 'YES' để tiếp tục: ");
                                    String wantContinue = sc.nextLine();
                                    if(wantContinue.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                        continue subLoop2;
                                    } else {
                                        System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                        break mainLoop;
                                    }

                                }
                                clearTerminal();
                            }
                        } else if(subChoice1.equals("8")) {
                            System.out.println("Đã chọn Truy xuất dữ liệu Bệnh viện");

                            // Truy xuất dữ liệu từ cả các file
                            AccountManager.getInstance().loadFromFile();
                            DepartmentManager.getInstance().loadFromFile();
                            SickManager.getInstance().loadFromFile();
                            HealthcareWorkerManager.getInstance().loadFromFile();
                            PatientManager.getInstance().loadFromFile();
                            MedicalRecordManager.getInstance().loadFromFile();

                            // Thông báo đã truy xuất dữ liệu Bệnh viện thành công
                            System.out.println("Đã truy xuất dữ liệu Bệnh viện thành công");

                            // Thông báo hỏi có tiếp tục hay không
                            System.out.print("Nhập 'YES' để tiếp tục: ");
                            String wantContinue = sc.nextLine();
                            if(wantContinue.equals("YES")) {
                                clearTerminal();
                                System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                continue subLoop1;
                            } else {
                                System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                break mainLoop;
                            }

                        } else if(subChoice1.equals("9")) {
                            System.out.println("Đã chọn Sao lưu dữ liệu Bệnh viện");

                            // Sao lưu dữ liệu từ cả các file
                            AccountManager.getInstance().saveToFile();
                            DepartmentManager.getInstance().saveToFile();
                            SickManager.getInstance().saveToFile();
                            HealthcareWorkerManager.getInstance().saveToFile();
                            PatientManager.getInstance().saveToFile();
                            MedicalRecordManager.getInstance().saveToFile();

                            // Thông báo đã sao lưu dữ liệu Bệnh viện thành công
                            System.out.println("Đã sao lưu dữ liệu Bệnh viện thành công");

                            // Thông báo hỏi có tiếp tục hay không
                            System.out.print("Nhập 'YES' để tiếp tục: ");
                            String wantContinue = sc.nextLine();
                            if(wantContinue.equals("YES")) {
                                clearTerminal();
                                System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                continue subLoop1;
                            } else {
                                System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                break mainLoop;
                            }
                        }
                    }
                } else if(AccountManager.getInstance().isUserInHospital(username)) {
                    clearTerminal();

                    // Biến tạm giữ thông tin tài khoản
                    Account account = AccountManager.getInstance().findAccountByUsername(username);

                    System.out.println("Là tài khoản người dùng trong bệnh viện");
                    if(AccountManager.getInstance().isHealthcareWorker(username)) {
                        // Biến giữ thông tin của Nhân viên Y tế
                        HealthcareWorker healthcareWorker =
                            HealthcareWorkerManager.getInstance().findObjectById(account.getUsername());
                        
                        // Danh mục các lựa chọn
                        subLoop1:
                        while(true) {
                            System.out.println("/********** TÀI KHOẢN NHÂN VIÊN Y TẾ **********/");
                            System.out.println("0 - Kết thúc");
                            System.out.println("1 - Quay lại");
                            System.out.println("2 - Thông tin cơ bản");
                            System.out.println("3 - Thông tin trong Bệnh viện");
                            System.out.print("? - Chọn: ");
                            String subChoice1 = sc.nextLine();
                            while(!isValidChoice(subChoice1, 0, 3)) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoice1 = sc.nextLine();
                            }
                            if(subChoice1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoice1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoice1.equals("2")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin cơ bản");

                                // In thông tin cơ bản của Nhân viên Y tế
                                System.out.println("/********** THÔNG TIN CƠ BẢN **********/");
                                System.out.println(" - Họ và tên: " + healthcareWorker.getFullname());
                                System.out.println(" - Ngày sinh: " + healthcareWorker.getBirthday().getDateFormatByCondition("has cross"));
                                System.out.println(" - Giới tính: " + healthcareWorker.getGender());
                                System.out.println(" - Số điện thoại: " + healthcareWorker.getPhone());
                                System.out.println(" - Quốc tịch: " + healthcareWorker.getCountry());

                                // Thông báo hỏi có tiếp tục hay không
                                System.out.print("Nhập 'YES' để tiếp tục: ");
                                String wantContinue = sc.nextLine();
                                if(wantContinue.equals("YES")) {
                                    clearTerminal();
                                    System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                    continue subLoop1;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                    break mainLoop;
                                }

                            } else if(subChoice1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin trong Bệnh viện");

                                // In thông tin trong Bệnh viện của Nhân viên Y tế
                                System.out.println("/********** THÔNG TIN TRONG BỆNH VIỆN **********/");
                                System.out.println(" - Mã Nhân viên: " + healthcareWorker.getId());
                                System.out.println(" - Loại công việc: " + healthcareWorker.getType());
                                System.out.println(" - Số năm kinh nghiệm: " + healthcareWorker.getYearsOfExperience());
                                System.out.println(" - Tiền lương: " + healthcareWorker.getSalary());
                                System.out.println(" - Khoa quản lý: " + healthcareWorker.getDepartmentID()
                                    + " - " + DepartmentManager.getInstance().findObjectById(healthcareWorker.getDepartmentID()).getName());
                                System.out.println(" - Trưởng Khoa: " + healthcareWorker.getIsManagerDepartment());
                                System.out.println(" - Bệnh án đang làm việc: "
                                    + (healthcareWorker.getMedicalRecordID() == null ? "Không" : healthcareWorker.getMedicalRecordID()));

                                // Thông báo hỏi có tiếp tục hay không
                                System.out.print("Nhập 'YES' để tiếp tục: ");
                                String wantContinue = sc.nextLine();
                                if(wantContinue.equals("YES")) {
                                    clearTerminal();
                                    System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                    continue subLoop1;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                    break mainLoop;
                                }

                            }
                        }

                    } else if(AccountManager.getInstance().isPatient(username)) {
                        // Biến giữ thông tin của Bệnh nhân
                        Patient patient =
                            PatientManager.getInstance().findObjectById(account.getUsername());

                        // Danh mục các lựa chọn
                        subLoop1:
                        while(true) {
                            System.out.println("/********** TÀI KHOẢN BỆNH NHÂN **********/");
                            System.out.println("0 - Kết thúc");
                            System.out.println("1 - Quay lại");
                            System.out.println("2 - Thông tin cơ bản");
                            System.out.println("3 - Thông tin trong Bệnh viện");
                            System.out.print("? - Chọn: ");
                            String subChoice1 = sc.nextLine();
                            while(!isValidChoice(subChoice1, 0, 3)) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoice1 = sc.nextLine();
                            }
                            if(subChoice1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoice1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoice1.equals("2")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin cơ bản");

                                // In thông tin cơ bản của Bệnh nhân
                                System.out.println("/********** THÔNG TIN CƠ BẢN **********/");
                                System.out.println(" - Họ và tên: " + patient.getFullname());
                                System.out.println(" - Ngày sinh: " + patient.getBirthday().getDateFormatByCondition("has cross"));
                                System.out.println(" - Giới tính: " + patient.getGender());
                                System.out.println(" - Số điện thoại: " + patient.getPhone());
                                System.out.println(" - Quốc tịch: " + patient.getCountry());

                                // Thông báo hỏi có tiếp tục hay không
                                System.out.print("Nhập 'YES' để tiếp tục: ");
                                String wantContinue = sc.nextLine();
                                if(wantContinue.equals("YES")) {
                                    clearTerminal();
                                    System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                    continue subLoop1;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                    break mainLoop;
                                }

                            } else if(subChoice1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin trong Bệnh viện");

                                // Lấy ra Bệnh án mà quản lý Bệnh nhân
                                String medicalRecordID = patient.getMedicalRecordID();
                                MedicalRecord medicalRecord = MedicalRecordManager.getInstance().findObjectById(medicalRecordID);

                                // In thông tin trong Bệnh viện của Bệnh nhân
                                System.out.println("/********** THÔNG TIN TRONG BỆNH VIỆN **********/");
                                System.out.println(" - Mã Bệnh nhân: " + patient.getId());
                                System.out.println(" - Loại chăm sóc: " + patient.getType());
                                System.out.println(" - Bệnh án đang quản lý: " + medicalRecordID);
                                if(medicalRecord.getType().equals("Khám bệnh")) {
                                    System.out.println(" -+ Ngày khám bệnh: " + medicalRecord.getInputDay().getDateFormatByCondition("has cross"));
                                    System.out.println(" -+ Ngày nhận kết quả: " + medicalRecord.getOutputDay().getDateFormatByCondition("has cross"));
                                } else {
                                    System.out.println(" -+ Ngày nhập viện: " + medicalRecord.getInputDay().getDateFormatByCondition("has cross"));
                                    System.out.println(" -+ Ngày xuất viện: " + medicalRecord.getOutputDay().getDateFormatByCondition("has cross"));
                                }
                                System.out.println(" -+ Loại Hồ sơ Bệnh án: " + medicalRecord.getType());
                                System.out.println(" -+ Tiền viện phí: " + medicalRecord.getFee());
                                System.out.println(" -+ Thông tin Bệnh: " + medicalRecord.getSickID()
                                    + " - " + SickManager.getInstance().findObjectById(medicalRecord.getSickID()).getName() + " - " + medicalRecord.getSickLevel());
                                System.out.println(" -+ Thông tin Nhân viên Y tế: " + medicalRecord.getHealthcareWorkerID()
                                    + " - " + HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).getFullname()
                                    + " - " + HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).getType());

                                // Thông báo hỏi có tiếp tục hay không
                                System.out.print("Nhập 'YES' để tiếp tục: ");
                                String wantContinue = sc.nextLine();
                                if(wantContinue.equals("YES")) {
                                    clearTerminal();
                                    System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                    continue subLoop1;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                    break mainLoop;
                                }

                            }
                        }
                    }
                } else if(!AccountManager.getInstance().isUserInHospital(username)) {
                    clearTerminal();
                    System.out.println("Là tài khoản người dùng ngoài bệnh viện");

                    // Biến tạm giữ thông tin tài khoản
                    Account account = AccountManager.getInstance().findAccountByUsername(username);

                    // Danh mục các lựa chọn
                    subLoop1:
                    while(true) {
                        System.out.println("/********** TÀI KHOẢN ĐĂNG KÝ MỚI **********/");
                        System.out.println("0 - Kết thúc");
                        System.out.println("1 - Quay lại");
                        System.out.println("2 - Đăng ký khám bệnh");
                        System.out.println("3 - Xoá tài khoản");
                        System.out.print("? - Chọn: ");
                        String subChoice1 = sc.nextLine();
                            while(!isValidChoice(subChoice1, 0, 3)) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoice1 = sc.nextLine();
                            }
                            if(subChoice1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoice1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoice1.equals("2")) {
                                clearTerminal();
                                System.out.println("Đã chọn Đăng ký khám bệnh");

                                // Nếu không có Nhân viên Y tế nào được tạo thì không thể đăng ký khám bệnh
                                if(DepartmentManager.getInstance().getNumbers() == 0) {
                                    clearTerminal();
                                    System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                    continue subLoop1;
                                }
                                // Nếu không có Nhân viên Y tế nào được tạo thì không thể đăng ký khám bệnh
                                if(SickManager.getInstance().getNumbers() == 0) {
                                    clearTerminal();
                                    System.out.println("Hiện tại Bệnh viện chưa có thông tin về các loại Bệnh, cần tạo ít nhất một Bệnh");
                                    continue subLoop1;
                                }
                                // Nếu không có Nhân viên Y tế nào được tạo thì không thể đăng ký khám bệnh
                                if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                    clearTerminal();
                                    System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                    continue subLoop1;
                                }

                                // Tạo thông tin cơ bản cho một người
                                // - Nhập họ và tên
                                System.out.print(" - Nhập họ và tên: ");
                                String fullname = sc.nextLine();
                                // - Nhập ngày sinh
                                System.out.println(" - Nhập ngày sinh: ");
                                System.out.print(" -+ Ngày: ");
                                int day = Integer.parseInt(sc.nextLine());
                                System.out.print(" -+ Tháng: ");
                                int month = Integer.parseInt(sc.nextLine());
                                System.out.print(" -+ Năm: ");
                                int year = Integer.parseInt(sc.nextLine());
                                // - Nhập giới tính
                                System.out.print(" - Nhập giới tính (Nam / Nữ): ");
                                String gender = sc.nextLine();
                                // - Nhập số điện thoại
                                System.out.print(" - Nhập số điện thoại: ");
                                String phone = sc.nextLine();
                                // - Nhập quốc tịch
                                System.out.print(" - Nhập quốc tịch: ");
                                String country = sc.nextLine();
                                // - Nhập chế độ khám bệnh
                                System.out.print(" - Nhập chế độ khám bệnh (Thường / Cao cấp): ");
                                String type = sc.nextLine();
                                // - Nhập lí do đăng ký khám
                                System.out.println(" - Chọn lí do đăng ký khám: ");
                                int serial1 = 1;
                                String[] sickArray;
                                for(Sick sick : SickManager.getInstance().getList()) {

                                    System.out.println(serial1++ + " - " + sick.getId() + " | " + sick.getName());
                                }
                                String reason = null;
                                
                                // - Chọn Bác sĩ hoặc Y tá ở khoa có bệnh tương ứng muốn khám
                                System.out.println(" - Chọn Bác sĩ hoặc Y tá mà bạn muốn được khám");
                                int serial2 = 1;
                                for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                    if(DepartmentManager.getInstance().findObjectById(healthcareWorker.getDepartmentID()).getName().equals("Nội")) {
                                        System.out.println(serial2++ + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                    }
                                }
                                if(serial2 == 1) {
                                    System.out.println("! - Hiện tại, Bệnh viện không có Bác sĩ hay Y tá thuộc khoa Nội có thể khám cho bạn.");
                                    System.out.println("! - Bệnh viện chúng tôi xin lỗi bạn vì đã điều này xảy ra. Mong bạn sẽ đăng ký lại ở lần sau.");
                                } else {
                                    System.out.print("? - Chọn: ");
                                    String subChoice2 = sc.nextLine();
                                    while(subChoice2.length() == 1
                                            ? (int) subChoice2.charAt(0) < 49
                                                || (int) subChoice2.charAt(0) > (int) String.valueOf(serial2).charAt(0)
                                            : DepartmentManager.getInstance().findObjectById(subChoice2) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoice2 = sc.nextLine();
                                    }
                                    String idHealthcareWorker = (subChoice2.length() == 1
                                    ? HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoice2) - 1).getId()
                                    : HealthcareWorkerManager.getInstance().findObjectById(subChoice2).getId());
                                    // - Tạo một Bệnh nhân đăng ký khám
                                    Patient patient = null;
                                    if(type.equals("Thường")) {
                                        patient = new NormalPatient(fullname, new Date(day, month, year), gender, phone, country, type);
                                    } else {
                                        patient = new PremiumPatient(fullname, new Date(day, month, year), gender, phone, country, type);
                                    }
                                    
                                    // Tạo một hồ sơ Bệnh án với các thông tin đã được nhập từ phía người dùng
                                    // - Lấy ngày hiện tại là ngày đăng ký, còn ngày khám xong tuỳ thuộc vào Quản lý Bệnh án
                                    String date = sc.nextLine();
                                    while(Date.getInstance().isDateFormat(date)) {

                                    }
                                    Date inputDay = new Date(Integer.parseInt(date.substring(0, 2)),
                                    Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(6)));
                                    Date outputDay = new Date(inputDay);
                                    // - Tạo Bệnh án
                                    MedicalRecord testRecord = null;
                                    if(idHealthcareWorker.substring(0, 3).equals("DOC")) {
                                        testRecord = new TestRecord(inputDay, outputDay, "Khám bệnh", patient.getId(), idHealthcareWorker, null, null);
                                    } else {
                                        testRecord = new TestRecord(inputDay, outputDay, "Khám bệnh", patient.getId(), idHealthcareWorker, null, null);
                                    }
                                    
                                    // Thông báo người dùng đã đăng ký khám bệnh thành công
                                    clearTerminal();
                                    System.out.println("Đã đăng ký khám bệnh thành công");
                                    System.out.println(" - Thông tin Bệnh nhân: " + patient.getInfo());
                                    System.out.println(" - Thông tin Bệnh án: " + testRecord.getInfo());
                                }
                                
                                // Thông báo hỏi có tiếp tục hay không
                                System.out.print("Nhập 'YES' để tiếp tục: ");
                                String wantContinue = sc.nextLine();
                                if(wantContinue.equals("YES")) {
                                    clearTerminal();
                                    System.out.println("Bạn đã nhập 'YES' nên chương trình sẽ tiếp tục");
                                    continue subLoop1;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                    break mainLoop;
                                }
                                
                            } else if(subChoice1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Xoá tài khoản");

                                // Hỏi lần nữa có muốn xoá tài khoản hay không ?
                                System.out.print("Nhập 'YES' nếu bạn chắc chắn sẽ xoá tài khoản này: ");
                                String wantRemove = sc.nextLine();
                                if(wantRemove.equals("YES")) {
                                    // Xoá tài khoản
                                    AccountManager.getInstance().remove(account.getUsername());
                                    
                                    // Vì xoá tài khoản nên đưa về trang Đăng nhập - Đăng ký
                                    continue mainLoop;
                                } else {
                                    System.out.println("Bạn đã không nhập 'YES' nên tài khoản chưa được xoá");
                                    System.out.println("Chương trình vẫn sẽ tiếp tục.");
                                    continue subLoop1;
                                }

                            }
                        }
                        
                    }
            } else if(mainChoice.equals("2")) {
                clearTerminal();
                System.out.println("Đã chọn Đăng ký");
                System.out.println("/********** ĐĂNG KÝ **********/");
                System.out.print(" - Nhập tên đăng nhập đăng ký: ");
                String newUsername = sc.nextLine();
                System.out.print(" - Nhập mật khẩu đăng ký: ");
                String newPassword = sc.nextLine();
                // Nếu tài khoản đã tồn tại (là không thể đăng ký tài khoản) thì đăng ký lại
                while(!AccountManager.getInstance().canRegister(newUsername, newPassword)) {
                    System.out.println("---------- ----------");
                    System.out.println("! - TÀI KHOẢN ĐÃ TỒN TẠI HOẶC KHÔNG HỢP LỆ");
                    System.out.print(" -! Nhập lại tên đăng nhập: ");
                    newUsername = sc.nextLine();
                    System.out.print(" -! Nhập lại mật khẩu: ");
                    newPassword = sc.nextLine();
                }
                System.out.println("Đã đăng ký tài khoản mới - " + newUsername);
                AccountManager.getInstance().add(new Account(newUsername, newPassword, "Người dùng mới"));
            }
            clearTerminal();
        }
    }
}
