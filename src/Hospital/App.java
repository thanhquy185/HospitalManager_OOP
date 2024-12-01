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
    
    // Setter - Getter
    public static App getInstance() {
        if(App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }

    // Methods
    // - Hàm xoá những nội dung trước đó trên màn hình console
    public void clearTerminal() {
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
            while(!myClass.getInstance().isValidChoice(mainChoice, 0, 2)) {
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

                // Nếu tài khoản hợp lệ thì có biến giữ thông tin của tài khoản đó
                Account currentAccount = null;
                for(Account account : AccountManager.getInstance().getList()) {
                    if(account.getUsername().equals(username)
                            && account.getPassword().equals(password)) {
                        currentAccount = account;
                        break;
                    }
                }

                // Nếu tài khoản hợp lệ
                if(currentAccount.isAdmin()) {
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
                        System.out.println("8 - Quản lý Hành động trong Bệnh viện");
                        System.out.println("9 - Truy xuất dữ liệu Bệnh viện");
                        System.out.println("10 - Sao lưu dữ liệu Bệnh viện");
                        System.out.print("? - Chọn: ");
                        String subChoice1 = sc.nextLine();
                        while(!myClass.getInstance().isValidChoice(subChoice1, 0, 10)) {
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
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 9)) {
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
                                    Account newAccount = new Account();
                                    newAccount.setInfo();
                                    AccountManager.getInstance().add(newAccount);

                                    // Thông báo đã thêm một tài khoản Người dùng mới
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
                                    int numberList = 0;
                                    for(Account account : newUserList) {
                                        System.out.println(++numberList + " - " + account.getUsername() + " | " + account.getType());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? - Chọn (chọn số thự tự hoặc tên tài khoản): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && AccountManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && (!myClass.getInstance().isValidChoice(info, 1, numberList) 
                                                            || newUserList.get(Integer.parseInt(info) - 1) == null))) {
                                        System.out.println("----- -----");
                                        System.out.println("! - TÀI KHOẢN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc tên tài khoản): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Tài khoản cần sửa thông tin
                                    Account accountUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        accountUpdate = AccountManager.getInstance().findObjectById(info);
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
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 3)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            subChoice3 = sc.nextLine();
                                        }
                                        switch (Integer.parseInt(subChoice3)) {
                                            case 0: {
                                                clearTerminal();
                                                System.out.println("Đã chọn quay lại");
                                                continue subLoop2;
                                            }
                                            case 1: {
                                                System.out.println("Đã chọn cập nhật Tên tài khoản");
                                                AccountManager.getInstance().update(accountUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Mật khẩu");
                                                AccountManager.getInstance().update(accountUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                AccountManager.getInstance().update(accountUpdate, 3);
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
                                    System.out.println(" - Chọn Tài khoản cần sửa");
                                    // 1 - username1 | Người dùng mới
                                    // 2 - username2 | Người dùng mới
                                    // 3 - username3 | Người dùng mới
                                    // ...
                                    int numberList = 0;
                                    for(Account account : newUserList) {
                                        System.out.println(++numberList + " - " + account.getUsername() + " | " + account.getType());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? - Chọn (chọn số thự tự hoặc tên tài khoản): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && AccountManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && (!myClass.getInstance().isValidChoice(info, 1, numberList) 
                                                            || newUserList.get(Integer.parseInt(info) - 1) == null))) {
                                        System.out.println("----- -----");
                                        System.out.println("! - TÀI KHOẢN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc tên tài khoản): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Tài khoản cần sửa thông tin
                                    Account accountRemove = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        accountRemove = AccountManager.getInstance().findObjectById(info);
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

                                    // Tìm kiếm thông tin tài khoản
                                    AccountManager.getInstance().find();

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
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 2)) {
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
                                System.out.println("6 - Tìm kiếm Khoa");
                                System.out.println("7 - Sắp xếp danh sách các Khoa");
                                System.out.println("8 - Truy xuất dữ liệu Khoa");
                                System.out.println("9 - Sao lưu dữ liệu Khoa");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 9)) {
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
                                    
                                    // Tạo một Khoa mới
                                    Department newDepartment = new Department();
                                    newDepartment.setInfoWithNoManager();
                                    DepartmentManager.getInstance().add(newDepartment);

                                    // Hỏi xem có cần tạo một Bác sĩ làm trưởng Khoa mới tạo này
                                    System.out.println("Bạn có muốn tạo một Nhân viên Y tế mới là Bác sĩ để làm trưởng Khoa trên ?");
                                    System.out.print(" - Nhập 'YES' để chấp nhận: ");
                                    String conform = sc.nextLine();
                                    if(!conform.equals("YES")) {
                                        clearTerminal();
                                        System.out.println("Bạn đã không nhập 'YES' nên Khoa mới được tạo chưa có trưởng Khoa");
                                        continue subLoop2;
                                    }
                                    
                                    // Tạo một Bác sĩ mới là Trưởng khoa 
                                    System.out.println("Nhập thông tin trưởng Khoa cho Khoa mới được tạo");
                                    HealthcareWorker newHealthcareWorker = new Doctor();
                                    newHealthcareWorker.setInfo("is not nurse", "is manager");
                                    HealthcareWorkerManager.getInstance().add(newHealthcareWorker);

                                    // Tạo tài khoản đăng nhập riêng cho trưởng Khoa mới tạo đó
                                    String newUsername = newHealthcareWorker.getId();
                                    String newPassword = newHealthcareWorker.getBirthday().getDateFormatByCondition("has not cross");
                                    Account newAccount = new Account(newUsername, newPassword, "Nhân viên");
                                    AccountManager.getInstance().add(newAccount);

                                    // Thiết lập Trưởng Khoa (vì đã hoàn tất việc tạo một Bác sĩ)
                                    newDepartment.setManagerID(newHealthcareWorker.getId());
                                    newHealthcareWorker.setDepartmentID(newDepartment.getId());

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
                                    System.out.println(" - Chọn Khoa cần sửa");
                                    // 1 - DEP00001 | Nội
                                    // 2 - DEP00002 | Mắt
                                    // 3 - DEP00008 | Răng-Hàm-Mặt
                                    // ...
                                    int numberList = 0;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? - Chọn (chọn số thự tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Khoa cần sửa thông tin
                                    Department departmentUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        departmentUpdate = DepartmentManager.getInstance().findObjectById(info);
                                    } else {
                                        departmentUpdate =  DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Khoa đã chọn để sửa: " + departmentUpdate.getInfo());

                                    // Cập nhật thông tin của Khoa đã chọn
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
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 4)) {
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
                                                DepartmentManager.getInstance().update(departmentUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Trưởng Khoa");
                                                DepartmentManager.getInstance().update(departmentUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Số phòng");
                                                DepartmentManager.getInstance().update(departmentUpdate, 3);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                DepartmentManager.getInstance().update(departmentUpdate, 4);
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
                                    int numberList = 0;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Khoa): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && DepartmentManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Khoa): ");
                                        info = sc.nextLine();
                                    }

                                    // Tìm thông tin của Khoa cần xoá
                                    Department departmentRemove = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        departmentRemove = DepartmentManager.getInstance().findObjectById(info);
                                    } else {
                                        departmentRemove = DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Khoa sắp được xoá để xử lý những việc bên dưới
                                    String departmentRemoveID = departmentRemove.getId();

                                    // Tìm những đối tượng có liên quan đến Khoa để kiểm tra sự liên kết
                                    // - Nếu có quản lý ít nhất một Bệnh thì không thể xoá
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getDepartmentID() == null) continue;
                                        if(sick.getDepartmentID().equals(departmentRemoveID)) {
                                            clearTerminal();
                                            System.out.println("! - Vì Khoa " + departmentRemoveID +  " có quản lý ít nhất một Bệnh nên không thể xoá");
                                            continue subLoop2;
                                        }
                                    }
                                    // - Nếu có quản lý ít nhất một Nhân viên Y tế thì không thể xoá
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getDepartmentID() == null) continue;
                                        if(healthcareWorker.getDepartmentID().equals(departmentRemoveID)) {
                                            clearTerminal();
                                            System.out.println("! - Vì Khoa " + departmentRemoveID +  " có quản lý ít nhất một Nhân viên Y tế nên không thể xoá");
                                            continue subLoop2;
                                        }
                                    }

                                    // Xoá Khoa đã tìm
                                    DepartmentManager.getInstance().remove(departmentRemoveID);

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
                                    System.out.println("Đã chọn Tìm kiếm Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm thông tin Khoa
                                    DepartmentManager.getInstance().find();

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
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 6)) {
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

                                } else if(subChoice2.equals("8")) {
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

                                } else if(subChoice2.equals("9")) {
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
                                System.out.println("6 - Tìm kiếm Bệnh");
                                System.out.println("7 - Sắp xếp danh sách các Bệnh");
                                System.out.println("8 - Truy xuất dữ liệu Bệnh");
                                System.out.println("9 - Sao lưu dữ liệu Bệnh");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 9)) {
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

                                    // Thêm một bệnh, Sick(name, departmentID), vì id sẽ tự tạo
                                    Sick newSick = new Sick();
                                    newSick.setInfo();
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
                                    int numberList = 0;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn SICKxxxxx)
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && SickManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
                                        info = sc.nextLine();
                                    }
                                    Sick sickUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
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
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 3)) {
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
                                                SickManager.getInstance().update(sickUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Khoa");
                                                SickManager.getInstance().update(sickUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                SickManager.getInstance().update(sickUpdate, 3);
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
                                    int numberList = 0;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && SickManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh): ");
                                        info = sc.nextLine();
                                    }
                                    // Lấy thông tin của Bệnh cần xoá
                                    Sick sickRemove = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        sickRemove = SickManager.getInstance().findObjectById(info);
                                    } else {
                                        sickRemove =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Bệnh sắp được xoá để xử lý những việc sau
                                    String sickRemoveID = sickRemove.getId();

                                    // Nếu Bệnh nằm trong Bệnh án bất kỳ thì không thể xoá
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getSickID() == null) continue;
                                        if(medicalRecord.getSickID().equals(sickRemoveID)) {
                                            clearTerminal();
                                            System.out.println("! - Vì Bệnh " + sickRemoveID + " đang nằm trong ít nhất một Hồ sơ Bệnh án nên không thể xoá");
                                            continue subLoop2;
                                        }
                                    }

                                    // Xoá Bệnh
                                    SickManager.getInstance().remove(sickRemoveID);

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
                                    System.out.println("Đã chọn Tìm kiếm Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể tìm kiếm Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm thông tin Bệnh
                                    SickManager.getInstance().find();

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
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 4)) {
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
                                        SickManager.getInstance().sort("id asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo mã tăng dần");
                                    } else if(subChoice3.equals("2")) {
                                        SickManager.getInstance().sort("id desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo mã giảm dần");
                                    } else if(subChoice3.equals("3")) {
                                        SickManager.getInstance().sort("name asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh theo tên tăng dần");
                                    } else if(subChoice3.equals("4")) {
                                        SickManager.getInstance().sort("name desc");
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

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh");

                                    // Đặt lại tất cả dữ liệu của Bệnh
                                    SickManager.getInstance().setList(new ArrayList<>());
                                    SickManager.getInstance().setNumbers(0);

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

                                } else if(subChoice2.equals("9")) {
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
                                System.out.println("6 - Tìm kiếm Nhân viên Y tế");
                                System.out.println("7 - Sắp xếp danh sách các Nhân viên Y tế");
                                System.out.println("8 - Truy xuất dữ liệu Nhân viên Y tế");
                                System.out.println("9 - Sao lưu dữ liệu Nhân viên Y tế");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 9)) {
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

                                    // Tạo một Nhân viên Y tế mới
                                    HealthcareWorker newHealthcareWorker = null;
                                    System.out.print(" - Chọn loại công việc (Y tá hoặc Bác sĩ): ");
                                    String healthcareWorkerType = sc.nextLine();
                                    while(!healthcareWorkerType.equals("Y tá") && !healthcareWorkerType.equals("Bác sĩ")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LOẠI CÔNG VIỆC KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Y tá hoặc Bác sĩ): ");
                                        healthcareWorkerType = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Nhập các thông tin khác khi đã biết loại công việc
                                    if(healthcareWorkerType.equals("Y tá")) {
                                        newHealthcareWorker = new Nurse();
                                        newHealthcareWorker.setInfo("is nurse", "is not manager");
                                    } else if(healthcareWorkerType.equals("Bác sĩ")) {
                                        newHealthcareWorker = new Doctor();
                                        newHealthcareWorker.setInfo("is not nurse", "is not manager");
                                    }
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
                                    int numberList = 0;
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Nhân viên Y tế): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && HealthcareWorkerManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Nhân viên Y tế): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Nhân viên Y tế cần sửa
                                    HealthcareWorker healthcareWorkerUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
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
                                        System.out.println("6 - Số năm kinh nghiệm");
                                        System.out.println("7 - Khoa quản lý");
                                        System.out.println("8 - Làm trưởng Khoa");
                                        System.out.println("9 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 9)) {
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
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Ngày sinh");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Giới tính");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 3);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Số điện thoại");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 4);
                                                break;
                                            }
                                            case 5: {
                                                System.out.println("Đã chọn cập nhật Quốc tịch");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 5);
                                                break;
                                            }
                                            case 6: {
                                                System.out.println("Đã chọn cập nhật Số năm kinh nghiệm");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 6);
                                                break;
                                            }
                                            case 7: {
                                                System.out.println("Đã chọn cập nhật Khoa quản lý");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 7);
                                                break;
                                            }
                                            case 8: {
                                                System.out.println("Đã chọn cập nhật Làm trưởng Khoa");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 8);
                                                System.out.print(" - Nhập 'YES' để tiếp tục: ");
                                                String wantContinue = sc.nextLine();
                                                if(!wantContinue.equals("YES")) {
                                                    System.out.println("Bạn đã không nhập 'YES' nên chương trình sẽ dừng lại");
                                                    break mainLoop;
                                                }
                                                break;
                                            }
                                            case 9: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                HealthcareWorkerManager.getInstance().update(healthcareWorkerUpdate, 9);
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
                                    int numberList = 0;
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + healthcareWorker.getId() + " | " + healthcareWorker.getFullname());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn DOC/NUR00001)
                                    System.out.print("? - Chọn: ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && HealthcareWorkerManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        info = sc.nextLine();
                                    }

                                    // Lấy thông tin của Nhân viên Y tế cần xoá
                                    HealthcareWorker healthcareWorkerRemove = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        healthcareWorkerRemove = HealthcareWorkerManager.getInstance().findObjectById(info);
                                    } else {
                                        healthcareWorkerRemove =  HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Nhân viên Y tế sắp được xoá để xử lý những việc sau
                                    String healthcareWorkerRemoveID = healthcareWorkerRemove.getId();

                                    // Nếu Nhân viên sắp xoá nằm trong Bệnh án bất kỳ thì không thể xoá
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getHealthcareWorkerID() == null) continue;
                                        if(medicalRecord.getHealthcareWorkerID().equals(healthcareWorkerRemoveID)) {
                                            clearTerminal();
                                            System.out.println("! - Vì Nhân viên Y tế " + healthcareWorkerRemoveID + " đã nằm trong Hồ sơ Bệnh án " + medicalRecord.getId() + " nên không thể xoá");
                                            continue subLoop2;
                                        }
                                    }

                                    // Huỷ liên với các đối tượng liên quan
                                    // - Tài khoản
                                    AccountManager.getInstance().remove(healthcareWorkerRemoveID);
                                    // - Khoa (nếu Nhân viên sắp xoá là Bác sĩ và là trưởng Khoa của một Khoa)
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        if(department.getManagerID() == null) continue;
                                        if(HealthcareWorkerManager.getInstance().findObjectById(healthcareWorkerRemoveID).getIsManagerDepartment() == true
                                            && department.getManagerID().equals(healthcareWorkerRemoveID))  department.setManagerID(null);
                                    }

                                    // Xoá Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().remove(healthcareWorkerRemoveID);

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
                                    System.out.println("Đã chọn Tìm kiếm Nhân viên Y tế");

                                    // Nếu Bệnh viện không có Nhân viên Y tế nào thì không thể tìm kiếm Nhân viên Y tế
                                    if(HealthcareWorkerManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Nhân viên Y tế, cần tạo ít nhất một Nhân viên Y tế");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm thông tin Nhân viên Y tế
                                    HealthcareWorkerManager.getInstance().find();

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
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 10)) {
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

                                } else if(subChoice2.equals("8")) {
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

                                } else if(subChoice2.equals("9")) {
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
                                System.out.println("6 - Tìm kiếm Bệnh nhân");
                                System.out.println("7 - Sắp xếp danh sách các Bệnh nhân");
                                System.out.println("8 - Truy xuất dữ liệu Bệnh nhân");
                                System.out.println("9 - Sao lưu dữ liệu Bệnh nhân");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 9)) {
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

                                    // Tạo một Bệnh nhân mới
                                    Patient newPatient = null;
                                    System.out.print(" - Chọn loại chăm sóc (Bình thường hoặc Cao cấp): ");
                                    String patientType = sc.nextLine();
                                    while(!patientType.equals("Bình thường") && !patientType.equals("Cao cấp")) {
                                        System.out.println("----- -----");
                                        System.out.println("! - LOẠI CHĂM SÓC KHÔNG HỢP LỆ");
                                        System.out.print("?! - Nhập lại (Bình thường hoặc Cao cấp): ");
                                        patientType = sc.nextLine();
                                        System.out.println("----- -----");
                                    }
                                    // - Nhập các thông tin khác khi đã biết loại chăm sóc
                                    if(patientType.equals("Bình thường")) {
                                        newPatient = new NormalPatient();
                                        newPatient.setInfoWithNoMedicalRecord("is normal patient");
                                    } else if(patientType.equals("Cao cấp")) {
                                        newPatient = new PremiumPatient();
                                        newPatient.setInfoWithNoMedicalRecord("is not normal patient");
                                    }
                                    PatientManager.getInstance().add(newPatient);

                                    // Thông báo đã thêm một Bệnh nhân
                                    clearTerminal();
                                    System.out.println("! - Đã thêm Bệnh nhân: " + newPatient.getInfo());

                                    // Tạo một Hồ sơ Bệnh án cho Bệnh nhân vừa mới được tạo
                                    System.out.println("Bạn cần tạo Hồ sơ Bệnh án cho Bệnh nhân " + newPatient.getId() + " - " + newPatient.getFullname());
                                    MedicalRecord newMedicalRecord = null;
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
                                    // - Nhập các thông tin khác khi đã biết loại Hồ sơ
                                    if(medicalRecordType.equals("Khám bệnh")) {
                                        newMedicalRecord = new TestRecord();
                                        newMedicalRecord.setInfoWithNoPatient("is test", newPatient.getBirthday());
                                    } else if(medicalRecordType.equals("Chữa bệnh")) {
                                        newMedicalRecord = new TreatmentRecord();
                                        newMedicalRecord.setInfoWithNoPatient("is not test", newPatient.getBirthday());
                                    }
                                    // - Nếu không có mã Bệnh án thì biết là đang thiếu Nhân viên để có thể chữa bệnh mà Bệnh nhân đang mắc phải
                                    if(newMedicalRecord.getId() == null) {
                                        clearTerminal();
                                        System.out.println("! - Tạo Bệnh án không thành công vì Bệnh viện chúng tôi hiện không có đủ Nhân viên Y tế để chữa bệnh mà Bệnh nhân mắc phải");
                                        System.out.println("! - Vì thế Bệnh viện sẽ xoá dữ liệu mà đã được nhập từ trước đó");
                                        Patient.setCountPatientCreated(Patient.getCountPatientCreated() - 1);
                                        PatientManager.getInstance().remove(newPatient.getId());
                                        continue subLoop2;
                                    }
                                    MedicalRecordManager.getInstance().add(newMedicalRecord);
                                    
                                    // Thiết lập sự liên kết
                                    newPatient.setMedicalRecordID(newMedicalRecord.getId());
                                    HealthcareWorkerManager.getInstance().findObjectById(newMedicalRecord.getHealthcareWorkerID()).setMedicalRecordID(newMedicalRecord.getId());
                                    newMedicalRecord.setPatientID(newPatient.getId());
                                    newMedicalRecord.setFee(newMedicalRecord.calFee());

                                    // Tạo tài khoản đăng nhập riêng cho Bệnh nhân mới tạo đó
                                    String newUsername = newPatient.getId();
                                    String newPassword = newPatient.getBirthday().getDateFormatByCondition("has not cross");
                                    Account newAccount = new Account(newUsername, newPassword, "Bệnh nhân");
                                    AccountManager.getInstance().add(newAccount);

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
                                    int numberList = 0;
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + patient.getId() + " | " + patient.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh nhân): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && PatientManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - BỆNH NHÂN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh nhân): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Bệnh nhân cần sửa
                                    Patient patientUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
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
                                        System.out.println("6 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 6)) {
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
                                                PatientManager.getInstance().update(patientUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Ngày sinh");
                                                PatientManager.getInstance().update(patientUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Giới tính");
                                                PatientManager.getInstance().update(patientUpdate, 3);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Số điện thoại");
                                                PatientManager.getInstance().update(patientUpdate, 4);
                                                break;
                                            }
                                            case 5: {
                                                System.out.println("Đã chọn cập nhật Quốc tịch");
                                                PatientManager.getInstance().update(patientUpdate, 5);
                                                break;
                                            }
                                            case 6: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                PatientManager.getInstance().update(patientUpdate, 6);
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
                                    int numberList = 0;
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        System.out.println(++numberList + " - " + patient.getId() + " | " + patient.getFullname());
                                    }
                                    // Cho phép chọn numberList hoặc id
                                    System.out.print("? - Chọn (số thứ tự hoặc mã Bệnh nhân): ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && PatientManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! - Bệnh nhân KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại (số thứ tự hoặc mã Bệnh nhân): ");
                                        info = sc.nextLine();
                                        System.out.println("----- -----");
                                    }

                                    // Tìm thông tin của Bệnh nhân cần xoá
                                    Patient patientRemove = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        patientRemove = PatientManager.getInstance().findObjectById(info);
                                    } else {
                                        patientRemove = PatientManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    // Lấy ra mã Bệnh nhân sắp được xoá để xử lý những việc bên dưới
                                    String patientRemoveID = patientRemove.getId();

                                    // Tìm những đối tượng có liên quan đến Bệnh nhân để xoá sự liên kết
                                    // - Tài khoản thì xoá luôn
                                    AccountManager.getInstance().remove(patientRemoveID);
                                    // - Bệnh án thì xoá luôn
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if(medicalRecord.getPatientID().equals(patientRemoveID)) {
                                            MedicalRecordManager.getInstance().remove(medicalRecord.getId());
                                            break;
                                        }
                                    }

                                    // Xoá Bệnh nhân đã tìm
                                    PatientManager.getInstance().remove(patientRemoveID);

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
                                    System.out.println("Đã chọn Tìm kiếm Bệnh nhân");

                                    // Nếu không có Bệnh nhân nào được tạo thì không thể tìm kiếm Bệnh nhân
                                    if(PatientManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh nhân, cần tạo ít nhất một Bệnh nhân");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm thông tin Bệnh nhân
                                    PatientManager.getInstance().find();

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
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 6)) {
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

                                } else if(subChoice2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh nhân");

                                    // Đặt lại tất cả dữ liệu của Bệnh nhân
                                    PatientManager.getInstance().setList(new ArrayList<>());
                                    PatientManager.getInstance().setNumbers(0);

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

                                } else if(subChoice2.equals("9")) {
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
                                System.out.println("3 - Sửa một Bệnh án");
                                System.out.println("4 - Tìm kiếm Bệnh án");
                                System.out.println("5 - Sắp xếp danh sách các Bệnh án");
                                System.out.println("6 - Truy xuất dữ liệu Bệnh án");
                                System.out.println("7 - Sao lưu dữ liệu Bệnh án");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 7)) {
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
                                    int numberList = 0;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println(++numberList + " -- "
                                            + medicalRecord.getId() + " | " + medicalRecord.getPatientID() + " - "
                                            + PatientManager.getInstance().findObjectById(medicalRecord.getPatientID()).getFullname());
                                    }
                                    // Cho phép chọn numberList - id (chọn 1 hoặc chọn MER00001)
                                    System.out.print("? -- Chọn: ");
                                    String info = sc.nextLine();
                                    while(!myClass.getInstance().onlyHasLetterAndNumber(info)
                                            || (!myClass.getInstance().hasAllCharacterIsNumber(info)
                                                    && MedicalRecordManager.getInstance().findObjectById(info) == null)
                                            || (myClass.getInstance().hasAllCharacterIsNumber(info) 
                                                    && MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH ÁN KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        info = sc.nextLine();
                                    }
                                    MedicalRecord medicalRecordUpdate = null;
                                    if(!myClass.getInstance().hasAllCharacterIsNumber(info)) {
                                        medicalRecordUpdate = MedicalRecordManager.getInstance().findObjectById(info);
                                    } else {
                                        medicalRecordUpdate =  MedicalRecordManager.getInstance().findObjectByIndex(Integer.parseInt(info) - 1);
                                    }

                                    clearTerminal();
                                    System.out.println("Thông tin của Bệnh án đã chọn để sửa: " + medicalRecordUpdate.getInfo());

                                    // Cập nhật thông tin của Bệnh án đã chọn
                                    while(true) {
                                        System.out.println("! - Chọn thông tin cần sửa");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Ngày mở Hồ sơ Bệnh án");
                                        System.out.println("2 - Ngày đóng Hồ sơ Bệnh án (Chữa bệnh)");
                                        System.out.println("3 - Mức độ Bệnh");
                                        System.out.println("4 - Tất cả");
                                        System.out.print("? - Chọn: ");
                                        String subChoice3 = sc.nextLine();
                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(!myClass.getInstance().isValidChoice(subChoice3, 0, 4)) {
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
                                                System.out.println("Đã chọn cập nhật Ngày lập Hồ sơ Bệnh án");
                                                MedicalRecordManager.getInstance().update(medicalRecordUpdate, 1);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Đã chọn cập nhật Ngày khoá Hồ sơ Bệnh án (Chữa bệnh)");
                                                MedicalRecordManager.getInstance().update(medicalRecordUpdate, 2);
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Đã chọn cập nhật Mức độ Bệnh");
                                                MedicalRecordManager.getInstance().update(medicalRecordUpdate, 3);
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("Đã chọn cập nhật Tất cả");
                                                MedicalRecordManager.getInstance().update(medicalRecordUpdate, 4);
                                            }
                                        }

                                        clearTerminal();
                                        System.out.println("! - Thông tin của Khoa sau khi sửa: " + medicalRecordUpdate.getInfo());
                                    }

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh án");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Tìm kiếm thông tin Bệnh án
                                    MedicalRecordManager.getInstance().find();

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

                                } else if(subChoice2.equals("5")) {
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
                                    System.out.println("5 - Sắp xếp theo ngày tiền viện phí tăng dần");
                                    System.out.println("6 - Sắp xếp theo ngày tiền viện phí giảm dần");
                                    System.out.print("? - Chọn: ");
                                    String subChoice3 = sc.nextLine();
                                    while(!myClass.getInstance().isValidChoice(subChoice3, 0, 6)) {
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
                                    } else if(subChoice3.equals("5")) {
                                        MedicalRecordManager.getInstance().sort("fee asc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo tiền viện phí tăng dần");
                                    } else if(subChoice3.equals("6")) {
                                        MedicalRecordManager.getInstance().sort("fee desc");
                                        System.out.println("! - Đã sắp xếp danh sách Bệnh án theo tiền viện phí giảm dần");
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

                                } else if(subChoice2.equals("6")) {
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

                                } else if(subChoice2.equals("7")) {
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
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý hành động trong Bệnh viện");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ HÀNH ĐỘNG TRONG BỆNH VIỆN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Khám các Bệnh nhân");
                                System.out.println("3 - Đưa các Bệnh nhân khẩu phần ăn (đang được chữa bệnh)");
                                System.out.println("4 - Đưa các Bệnh nhân thuốc uống (đang được chữa bệnh, mức độ Bệnh: Nhẹ hoặc Vừa)");
                                System.out.println("5 - Tiêm thuốc cho các Bệnh nhân (đang được chữa bệnh, mức độ Bệnh: Nặng)");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 5)) {
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
                                    System.out.println("Đã chọn Khám các Bệnh nhân");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Thực hiện công việc Khám
                                    int numberList = 1;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println("Hồ sơ Bệnh án thứ " + numberList++);
                                        medicalRecord.testPatient();
                                        HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).testPatient();
                                        PatientManager.getInstance().findObjectById(medicalRecord.getPatientID()).testPatient();
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

                                } else if(subChoice2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Đưa các Bệnh nhân khẩu phần ăn (đang được chữa bệnh)");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Thực hiện công việc Khám
                                    int numberList = 1;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println("Hồ sơ Bệnh án thứ " + numberList++);
                                        medicalRecord.giveFoodToPatient();
                                        HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).giveFoodToPatient();
                                        PatientManager.getInstance().findObjectById(medicalRecord.getPatientID()).giveFoodToPatient();
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

                                } else if(subChoice2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Đưa các Bệnh nhân thuốc uống (đang được chữa bệnh, mức độ Bệnh: Nhẹ hoặc Vừa)");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Thực hiện công việc Đưa thuốc uống cho các Bệnh nhân
                                    int numberList = 1;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println("Hồ sơ Bệnh án thứ " + numberList++);
                                        medicalRecord.giveCurativeToPatient();
                                        HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).giveCurativeToPatient();
                                        PatientManager.getInstance().findObjectById(medicalRecord.getPatientID()).giveCurativeToPatient();
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

                                } else if(subChoice2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tiêm thuốc cho các Bệnh nhân (đang được chữa bệnh, mức độ Bệnh: Nặng)");

                                    // Nếu không có Khoa nào được tạo thì không thể tìm kiếm Bệnh án
                                    if(MedicalRecordManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh án, cần tạo ít nhất một Bệnh án");
                                        continue subLoop2;
                                    }

                                    // Thực hiện công việc Khám
                                    int numberList = 1;
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        System.out.println("Hồ sơ Bệnh án thứ " + numberList++);
                                        medicalRecord.injectCurativePatient();
                                        HealthcareWorkerManager.getInstance().findObjectById(medicalRecord.getHealthcareWorkerID()).injectCurativePatient();
                                        PatientManager.getInstance().findObjectById(medicalRecord.getPatientID()).injectCurativePatient();
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
                                }
                            }

                        } else if(subChoice1.equals("9")) {
                            System.out.println("Đã chọn Truy xuất dữ liệu Bệnh viện");

                            // Truy xuất dữ liệu từ cả các file
                            AccountManager.getInstance().loadFromFile();
                            DepartmentManager.getInstance().loadFromFile();
                            SickManager.getInstance().loadFromFile();
                            HealthcareWorkerManager.getInstance().loadFromFile();
                            PatientManager.getInstance().loadFromFile();
                            MedicalRecordManager.getInstance().loadFromFile();

                            // Thông báo đã truy xuất dữ liệu Bệnh viện thành công
                            System.out.println("! - Đã truy xuất dữ liệu Bệnh viện thành công");

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

                        } else if(subChoice1.equals("10")) {
                            System.out.println("Đã chọn Sao lưu dữ liệu Bệnh viện");

                            // Sao lưu dữ liệu từ cả các file
                            AccountManager.getInstance().saveToFile();
                            DepartmentManager.getInstance().saveToFile();
                            SickManager.getInstance().saveToFile();
                            HealthcareWorkerManager.getInstance().saveToFile();
                            PatientManager.getInstance().saveToFile();
                            MedicalRecordManager.getInstance().saveToFile();

                            // Thông báo đã sao lưu dữ liệu Bệnh viện thành công
                            System.out.println("! - Đã sao lưu dữ liệu Bệnh viện thành công");

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
                } else if(currentAccount.isHealthcareWorker()) {
                    clearTerminal();
                    System.out.println("Là tài khoản Nhân viên Y tế trong bệnh viện");
                    
                    // Biến giữ thông tin của Nhân viên Y tế
                    HealthcareWorker healthcareWorker = HealthcareWorkerManager.getInstance().findObjectById(currentAccount.getUsername());
                        
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
                        while(!myClass.getInstance().isValidChoice(subChoice1, 0, 3)) {
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
                            System.out.println(" - Trưởng Khoa: " + (healthcareWorker.getIsManagerDepartment() ? "Có" : "Không"));
                            System.out.println(" - Bệnh án đang làm việc: "
                                + (healthcareWorker.getMedicalRecordID() == null ? "Không" : healthcareWorker.getMedicalRecordID()
                                    + " - " + MedicalRecordManager.getInstance().findObjectById(healthcareWorker.getMedicalRecordID()).getPatientID()
                                    + " - " + MedicalRecordManager.getInstance().findObjectById(healthcareWorker.getMedicalRecordID()).getType()));

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

                } else if(currentAccount.isPatient()) {
                    clearTerminal();
                    System.out.println("Là tài khoản Bệnh nhân trong bệnh viện");

                    // Biến giữ thông tin của Bệnh nhân
                    Patient patient = PatientManager.getInstance().findObjectById(currentAccount.getUsername());

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
                        while(!myClass.getInstance().isValidChoice(subChoice1, 0, 3)) {
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
                } else {
                    clearTerminal();
                    System.out.println("Là tài khoản Người dùng đăng ký mới");

                    // Danh mục các lựa chọn
                    subLoop1:
                    while(true) {
                        System.out.println("/********** TÀI KHOẢN ĐĂNG KÝ MỚI **********/");
                        System.out.println("0 - Kết thúc");
                        System.out.println("1 - Quay lại");
                        System.out.println("2 - Đăng ký khám - chữa bệnh");
                        System.out.println("3 - Thay đổi thông tin đăng nhập");
                        System.out.println("4 - Xoá tài khoản");
                        System.out.print("? - Chọn: ");
                        String subChoice1 = sc.nextLine();
                        while(!myClass.getInstance().isValidChoice(subChoice1, 0, 4)) {
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
                            System.out.println("Đã chọn Đăng ký khám - chữa bệnh");

                            // Nếu không có Khoa nào được tạo thì không thể đăng ký khám bệnh
                            if(DepartmentManager.getInstance().getNumbers() == 0) {
                                clearTerminal();
                                System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                continue subLoop1;
                            }
                            // Nếu không có Bệnh nào được tạo thì không thể đăng ký khám bệnh
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

                            // Thiết lập người đăng ký khám thành một Bệnh nhân
                            Patient newPatient = null;
                            System.out.print(" - Chọn loại chăm sóc (Bình thường hoặc Cao cấp): ");
                            String patientType = sc.nextLine();
                            while(!patientType.equals("Bình thường") && !patientType.equals("Cao cấp")) {
                                System.out.println("----- -----");
                                System.out.println("! - LOẠI CHĂM SÓC KHÔNG HỢP LỆ");
                                System.out.print("?! - Nhập lại (Bình thường hoặc Cao cấp): ");
                                patientType = sc.nextLine();
                                System.out.println("----- -----");
                            }
                            // - Nhập các thông tin khác khi đã biết loại chăm sóc
                            if(patientType.equals("Bình thường")) {
                                newPatient = new NormalPatient();
                                newPatient.setInfoWithNoMedicalRecord("is normal patient");
                            } else if(patientType.equals("Cao cấp")) {
                                newPatient = new PremiumPatient();
                                newPatient.setInfoWithNoMedicalRecord("is not normal patient");
                            }
                            PatientManager.getInstance().add(newPatient);

                            // Người dùng mới có thể đăng ký Khám bệnh hoặc Chữa bệnh
                            // Tạo một Hồ sơ Bệnh án cho Bệnh nhân vừa mới được tạo
                            MedicalRecord newMedicalRecord = null;
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
                            // - Nhập các thông tin khác khi đã biết loại Hồ sơ
                            if(medicalRecordType.equals("Khám bệnh")) {
                                newMedicalRecord = new TestRecord();
                                newMedicalRecord.setInfoWithNoPatient("is test", newPatient.getBirthday());
                            } else if(medicalRecordType.equals("Chữa bệnh")) {
                                newMedicalRecord = new TreatmentRecord();
                                newMedicalRecord.setInfoWithNoPatient("is not test", newPatient.getBirthday());
                            }
                            // - Nếu không có mã Bệnh án thì biết là đang thiếu Nhân viên để có thể chữa bệnh mà Bệnh nhân đang mắc phải
                            if(newMedicalRecord.getId() == null) {
                                clearTerminal();
                                System.out.println("! - Đăng ký không thành công vì Bệnh viện chúng tôi hiện không có đủ Nhân viên Y tế để chữa bệnh đang mắc phải");
                                System.out.println("! - Vì thế Bệnh viện sẽ xoá dữ liệu mà đã được nhập từ trước đó");
                                PatientManager.getInstance().remove(newPatient.getId());
                                continue subLoop1;
                            }
                            MedicalRecordManager.getInstance().add(newMedicalRecord);
                            
                            // Thiết lập sự liên kết
                            newPatient.setMedicalRecordID(newMedicalRecord.getId());
                            HealthcareWorkerManager.getInstance().findObjectById(newMedicalRecord.getHealthcareWorkerID()).setMedicalRecordID(newMedicalRecord.getId());
                            newMedicalRecord.setPatientID(newPatient.getId());
                            newMedicalRecord.setFee(newMedicalRecord.calFee());

                            // Tạo tài khoản người dùng trong Bệnh viện khi đã đăng ký khám thành công
                            AccountManager.getInstance().remove(currentAccount.getUsername());
                            String newUsername = newPatient.getId();
                            String newPassword = newPatient.getBirthday().getDateFormatByCondition("has not cross");
                            Account newAccount = new Account(newUsername, newPassword, "Bệnh nhân");
                            AccountManager.getInstance().add(newAccount);

                            // Thông báo đã đăng ký khám thành công
                            System.out.println("! - Bạn đã đăng ký thành công");
                            System.out.println("! - Chúng tôi xin xoá tài khoản hiện tại và cấp cho bạn tài khoản mới");
                            System.out.println("! - Bạn có thể sử dụng tài khoản sau để kiểm tra thông tin: " + newUsername + " - " + newPassword);
                            
                            // Hỏi đã đọc xong thông báo chưa ?
                            System.out.print("Bạn đã đọc xong thông báo. Nhập 'YES' để quay về trang Đăng nhập - Đăng ký: ");
                            String wantContinue = sc.nextLine();
                            while(!wantContinue.equals("YES")) {
                                System.out.print("- Bạn đã không nhập 'YES', nhập lại nếu đã đọc xong: ");
                                wantContinue = sc.nextLine();
                            }
                            clearTerminal();
                            continue mainLoop;
                        } else if(subChoice1.equals("3")) {
                            clearTerminal();
                            System.out.println("Đã chọn Thay đổi thông tin tài khoản");
                            System.out.println("Thông tin của tài khoản Người dùng mới đã chọn để sửa: " + currentAccount.getInfo());

                            while(true) {
                                System.out.println("! - Chọn thông tin cần sửa");
                                System.out.println("0 - Quay lại");
                                System.out.println("1 - Tên tài khoản");
                                System.out.println("2 - Mật khẩu");
                                System.out.println("3 - Tất cả");
                                System.out.print("? - Chọn: ");
                                String subChoice2 = sc.nextLine();
                                // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                while(!myClass.getInstance().isValidChoice(subChoice2, 0, 3)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoice2 = sc.nextLine();
                                }
                                switch (Integer.parseInt(subChoice2)) {
                                    case 0: {
                                        clearTerminal();
                                        System.out.println("Đã chọn quay lại");
                                        continue subLoop1;
                                    }
                                    case 1: {
                                        System.out.println("Đã chọn cập nhật Tên tài khoản");
                                        AccountManager.getInstance().update(currentAccount, 1);
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Đã chọn cập nhật Mật khẩu");
                                        AccountManager.getInstance().update(currentAccount, 2);
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Đã chọn cập nhật Tất cả");
                                        AccountManager.getInstance().update(currentAccount, 3);
                                    }
                                }
                                clearTerminal();
                                System.out.println("Thông tin của tài khoản sau khi sửa: " + currentAccount.getInfo());
                            }
                        } else if(subChoice1.equals("4")) {
                            clearTerminal();
                            System.out.println("Đã chọn Xoá tài khoản");

                            // Hỏi lần nữa có muốn xoá tài khoản hay không ?
                            System.out.print("Nhập 'YES' nếu bạn chắc chắn sẽ xoá tài khoản này: ");
                            String wantRemove = sc.nextLine();
                            if(wantRemove.equals("YES")) {
                                // Xoá tài khoản
                                AccountManager.getInstance().remove(currentAccount.getUsername());
                                // Vì xoá tài khoản nên đưa về trang Đăng nhập - Đăng ký
                                clearTerminal();
                                System.out.println("Tài khoản đã được xoá. Bạn có thể tạo tài khoản mới nếu muốn");
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
                String username = sc.nextLine();
                System.out.print(" - Nhập mật khẩu đăng ký: ");
                String password = sc.nextLine();
                // Nếu tài khoản đã tồn tại (là không thể đăng ký tài khoản) thì đăng ký lại
                while(!AccountManager.getInstance().canRegister(username, password)) {
                    System.out.println("---------- ----------");
                    System.out.println("! - TÀI KHOẢN ĐÃ TỒN TẠI HOẶC KHÔNG HỢP LỆ");
                    System.out.print(" -! Nhập lại tên đăng nhập: ");
                    username = sc.nextLine();
                    System.out.print(" -! Nhập lại mật khẩu: ");
                    password = sc.nextLine();
                }
                Account newAccount = new Account(username, password, "Người dùng mới");
                AccountManager.getInstance().add(newAccount);

                // Thông báo đã đăng ký thành công
                System.out.println("Đã đăng ký tài khoản mới - " + username);
            }
            clearTerminal();
        }
    }
}
