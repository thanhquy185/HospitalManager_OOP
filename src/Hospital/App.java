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
            String mainChoose = sc.nextLine();
            while(!mainChoose.equals("0")
                && !mainChoose.equals("1")
                && !mainChoose.equals("2")) {
                System.out.print("---------- ----------");
                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại: ");
                mainChoose = sc.nextLine();
            }
            if(mainChoose.equals("0")) {
                System.out.println("Đã chọn Kết thúc");
                break mainLoop;
            } else if(mainChoose.equals("1")) {
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
                        String subChoose1 = sc.nextLine();
                        while(!subChoose1.equals("0") && !subChoose1.equals("1")
                                && !subChoose1.equals("2") && !subChoose1.equals("3")
                                && !subChoose1.equals("4") && !subChoose1.equals("5")
                                && !subChoose1.equals("6") && !subChoose1.equals("7")
                                && !subChoose1.equals("8") && !subChoose1.equals("9")) {
                            System.out.println("---------- ----------");
                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                            System.out.print("?! - Chọn lại: ");
                            subChoose1 = sc.nextLine();
                        }
                        if(subChoose1.equals("0")) {
                            System.out.println("Đã chọn Kết thúc");
                            break mainLoop;
                        } else if(subChoose1.equals("1")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quay lại");
                            continue mainLoop;
                        } else if(subChoose1.equals("2")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Tài khoản");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ TÀI KHOẢN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Tài khoản");
                                System.out.println("3 - Thêm một tài khoản là Nhân viên y tế");
                                System.out.println("4 - Thêm một tài khoản là Bệnh nhân");
                                System.out.println("5 - Thêm một tài khoản là Người dùng mới");
                                System.out.println("6 - Xoá một tài khoản");
                                System.out.println("7 - Tìm kiếm tài khoản");
                                System.out.println("8 - Sắp xếp danh sách các Tài khoản");
                                System.out.println("9 - Truy xuất dữ liệu Tài khoản");
                                System.out.println("10 - Sao lưu dữ liệu Tài khoản");
                                System.out.print("? - Chọn: ");
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                    && !subChoose2.equals("2") && !subChoose2.equals("3")
                                    && !subChoose2.equals("4") && !subChoose2.equals("5")
                                    && !subChoose2.equals("6") && !subChoose2.equals("7")
                                    && !subChoose2.equals("8") && !subChoose2.equals("9")
                                    && !subChoose2.equals("10")) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoose2 = sc.nextLine();
                                }
                                if(subChoose2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Tài khoản");



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

                                } else if(subChoose2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một tài khoản là Nhân viên Y tế");



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

                                } else if(subChoose2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một tài khoản là Bệnh nhân");



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

                                } else if(subChoose2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một tài khoản là Người dùng mới");



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

                                } else if(subChoose2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn xoá một tài khoản");



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

                                } else if(subChoose2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm tài khoản");



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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các tài khoản");



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

                                } else if(subChoose2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu tài khoản");



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

                                } else if(subChoose2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu tài khoản");



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

                        } else if(subChoose1.equals("3")) {
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
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                        && !subChoose2.equals("2") && !subChoose2.equals("3")
                                        && !subChoose2.equals("4") && !subChoose2.equals("5")
                                        && !subChoose2.equals("6") && !subChoose2.equals("7")
                                        && !subChoose2.equals("8") && !subChoose2.equals("9")
                                        && !subChoose2.equals("10")) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoose2 = sc.nextLine();
                                }
                                if(subChoose2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
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

                                } else if(subChoose2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Khoa");

                                    // Lọc ra danh sách các Bác sĩ chưa thuộc về Khoa nào
                                    ArrayList<HealthcareWorker> list = new ArrayList<>();
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getDepartmentID() == null
                                                || healthcareWorker.getDepartmentID().equals("null"))
                                            list.add(healthcareWorker);
                                    }

                                    if(list.size() == 0) {
                                        System.out.println("! -- Hiện tại, Bệnh viện không có Bác sĩ nào chưa thuộc về Khoa nào để có thể làm trưởng Khoa cho Khoa mới được tạo");
                                    } else {
                                        // Nhập tên Khoa
                                        System.out.print(" -- Nhập tên Khoa: ");
                                        String newName = sc.nextLine();

                                        // Chọn trưởng Khoa (Khoa mới được tạo luôn phải có một trưởng Khoa)
                                        System.out.println(" -- Chọn trưởng Khoa");
                                        // 1 -- DOC00001 | Trần Văn A
                                        // 2 -- DOC00002 | Trần Văn B
                                        // ...
                                        int serial = 1;
                                        for(HealthcareWorker healthcareWorker : list) {
                                            System.out.println(serial++ + " -- " + healthcareWorker.getId()
                                                + " | " + healthcareWorker.getFullname());
                                        }
                                        // Cho phép chọn serial - id (chọn 1 hoặc chọn DOCxxxxx)
                                        System.out.print("? -- Chọn (chọn số thứ tự hoặc mã Nhân viên y tế): ");
                                        String subChoose3 = sc.nextLine();
                                        while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                    && HealthcareWorkerManager.getInstance().findObjectById(subChoose3) == null)
                                                || HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                            System.out.println("----- -----");
                                            System.out.println("! -- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                            System.out.print("?! -- Chọn lại: ");
                                            subChoose3 = sc.nextLine();
                                        }
                                        // Mã của Bác sĩ là trưởng Khoa
                                        String newManagerID = subChoose3.length() != 1
                                            ? HealthcareWorkerManager.getInstance().findObjectById(subChoose3).getId()
                                            : HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1).getId();
                                        // Thiết lập trưởng Khoa cho mã tương ứng
                                        HealthcareWorkerManager.getInstance().findObjectById(newManagerID).setIsManagerDepartment(true);

                                        // Nhập số phòng
                                        System.out.print(" -- Nhập số phòng: ");
                                        String newRoom = sc.nextLine();

                                        // Thêm Khoa mới
                                        Department newDepartment = new Department(newName, newManagerID, newRoom);
                                        DepartmentManager.getInstance().add(newDepartment);

                                        // Thông báo đã thêm một Khoa
                                        System.out.println("! -- Đã thêm Khoa: " + newDepartment.getInfo());
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
                                    
                                } else if(subChoose2.equals("4")) {
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
                                    int serial = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(serial++ + " -- "
                                            + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn DEPxxxxx)
                                    System.out.print("? -- Chọn (chọn số thự tự hoặc mã Khoa): ");
                                    String subChoose3 = sc.nextLine();
                                    while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                && DepartmentManager.getInstance().findObjectById(subChoose3) == null)
                                            || DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Khoa cần sửa thông tin
                                    Department departmentSearch = null;
                                    if(subChoose3.length() != 1) {
                                        departmentSearch = DepartmentManager.getInstance().findObjectById(subChoose3);
                                    } else {
                                        departmentSearch =  DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    }

                                    // Thông báo thông tin của Khoa đã được chọn để sửa thông tin
                                    clearTerminal();
                                    System.out.println("Đã chọn Khoa có thông tin: " + departmentSearch.getInfo());

                                    // Tạo một from lựa chọn
                                    // - Id là cố định không thể sửa
                                    // - Chạy vòng lặp để người dùng có thể lựa chọn nhiều lần
                                    // - Xoá đi lựa chọn trước đó của người dùng
                                    // - Nếu chọn "Tất cả" thì xoá hết
                                    ArrayList<String> serialForm = new ArrayList<>();
                                    serialForm.add("Sửa tên Khoa");
                                    serialForm.add("Sửa mã trưởng Khoa");
                                    serialForm.add("Sửa số phòng");
                                    serialForm.add("Tất cả");
                                    
                                    // Thông báo Khoa đã được sửa thông tin mới cho những lần sửa
                                    boolean isUpdated = false;

                                    // Vòng lặp sẽ dừng khi form sửa thông tin hết hoặc khi chọn "Tất cả" lần đầu tiên
                                    while(serialForm.size() >= 1 && !serialForm.get(0).equals("Tất cả")) {
                                        // Thông báo thông tin mới của Khoa khi đã sửa thông tin bất kỳ trừ "Tất cả"
                                        if(isUpdated) {
                                            isUpdated = false;
                                            clearTerminal();
                                            System.out.println("Thông tin mới của Khoa sau khi sửa: " + departmentSearch.getInfo());
                                        }

                                        // Các lựa chọn...
                                        System.out.println("0 --- Quay lại");
                                        for(int i = 0; i < serialForm.size(); i++) {
                                            System.out.println((i + 1) + " --- " + serialForm.get(i));
                                        }
                                        System.out.print("? --- Chọn thông tin cần sửa (chọn số thứ tự): ");
                                        String subChoose4 = sc.nextLine();
                                        while(myCharacterClass.hasOneCharacterIsLetter(subChoose4) 
                                                || subChoose4.length() > 1 || !subChoose4.equals("0")
                                                || !subChoose4.equals("1") || !subChoose4.equals("2") 
                                                || !subChoose4.equals("3")) {
                                            System.out.println("----- -----");
                                            System.out.println("! --- LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! --- Chọn lại: ");
                                            subChoose4 = sc.nextLine();
                                        }
                                        if(subChoose4.equals("0")) {
                                            clearTerminal();
                                            System.out.println("Đã chọn Quay lại");
                                            continue subLoop2;
                                        }
                                        else {
                                            // Vào đây đảm bảo thông tin của một Khoa luôn được sửa
                                            isUpdated = true;

                                            // Tạo biến tạm để giữ thông tin của Khoa sau những lần sửa thông tin
                                            Department departmentUpdate = null;
                                            for(int i = 0; i < serialForm.size(); i++) {
                                                if(i + 1 == Integer.parseInt(subChoose4)) {
                                                    if(serialForm.get(i).equals("Sửa tên Khoa")) {
                                                        System.out.println("Đã chọn Sửa tên Khoa");

                                                        // Nhập tên Khoa mới
                                                        System.out.print(" --- Nhập tên Khoa mới: ");
                                                        String newName = sc.nextLine();

                                                        // Tạo Khoa mới, dữ liệu như Khoa cũ chỉ đổi tên Khoa
                                                        departmentUpdate = new Department(departmentSearch.getId(), newName,
                                                            departmentSearch.getManagerID(), departmentSearch.getRoom());

                                                        // Xoá form vì đã sửa thông tin
                                                        serialForm.remove(i);

                                                    } else if(serialForm.get(i).equals("Sửa mã trưởng Khoa")) {
                                                        System.out.println("Đã chọn Sửa mã trưởng Khoa");

                                                        // Mảng chứa các Bác sĩ chưa là trưởng Khoa trong Khoa đang được sửa
                                                        ArrayList<HealthcareWorker> list = new ArrayList<>();
                                                        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                                            if(healthcareWorker.getDepartmentID().equals(departmentSearch.getId())
                                                                && healthcareWorker.getIsManagerDepartment() == false) list.add(healthcareWorker);
                                                        }
                                                        if(list.size() == 0) {
                                                            // Nếu trong Khoa hiện tại chỉ có một bác sĩ thì không thể thay đổi, vì đã là trưởng Khoa
                                                            System.out.println(" --- Hiện tại, trong Khoa chỉ có duy nhất một Bác sĩ đã là trưởng Khoa");
                                                        } else {
                                                            // 1 --- DOC00001 | Thanh Quy
                                                            // 2 --- DOC00002 | Duy Quý
                                                            // 3 --- DOC00003 | Minh Đức
                                                            // 4 --- DOC00004 | Phước An
                                                            System.out.println(" --- Chọn trưởng Khoa");
                                                            int subSerial = 1;
                                                            for(HealthcareWorker healthcareWorker : list) {
                                                                System.out.println(subSerial++ + " --- " + healthcareWorker.getId()
                                                                    + " | " + healthcareWorker.getFullname());
                                                            }
                                                            // Cho phép chọn serial hoặc id
                                                            System.out.print("? --- Chọn: ");
                                                            String subChoose5 = sc.nextLine();
                                                            while((myCharacterClass.hasOneCharacterIsLetter(subChoose5)
                                                                        && HealthcareWorkerManager.getInstance().findObjectById(subChoose5) == null)
                                                                    || HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1) == null) {
                                                                System.out.println("----- -----");
                                                                System.out.println("! --- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                                                System.out.print("?! --- Chọn lại: ");
                                                                subChoose5 = sc.nextLine();
                                                            }

                                                            // Mã của Bác sĩ là trưởng Khoa
                                                            String newManagerID = subChoose5.length() != 1
                                                                ? HealthcareWorkerManager.getInstance().findObjectById(subChoose5).getId()
                                                                : HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId();

                                                            // Tạo Khoa và thiết lập trưởng Khoa
                                                            departmentUpdate = new Department(departmentSearch.getId(),
                                                                departmentSearch.getName(), newManagerID, departmentSearch.getRoom());

                                                            // Thiết lập là trưởng Khoa cho Bác sĩ đã chọn
                                                            HealthcareWorkerManager.getInstance().findObjectById(newManagerID).setIsManagerDepartment(true);
                                                        }

                                                        // Xoá form vì đã sửa thông tin
                                                        serialForm.remove(i);

                                                    } else if(serialForm.get(i).equals("Sửa số phòng")) {
                                                        System.out.println("Đã chọn Sửa số phòng");

                                                        // Nhập số phòng mới
                                                        System.out.print("? --- Nhập số phòng mới: ");
                                                        String newRoom = sc.nextLine();

                                                        // ...
                                                        departmentUpdate = new Department(departmentSearch.getId(),
                                                            departmentSearch.getName(), departmentSearch.getManagerID(), newRoom);

                                                        // Xoá form vì đã sửa thông tin
                                                        serialForm.remove(i);

                                                    } else if(serialForm.get(i).equals("Tất cả")) {
                                                        System.out.println("Đã chọn Tất cả");

                                                        // Nhập tên Khoa mới
                                                        System.out.print(" --- Nhập tên Khoa mới: ");
                                                        String newName = sc.nextLine();
    
                                                        // Chọn mã trưởng Khoa
                                                        // - Biến giữ mã trưởng Khoa
                                                        String newIdHealthcareWorker = null;
                                                        // Mảng chứa các Bác sĩ chưa là trưởng Khoa trong Khoa đang được sửa
                                                        ArrayList<HealthcareWorker> list = new ArrayList<>();
                                                        for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                                            if(healthcareWorker.getDepartmentID().equals(departmentSearch.getId())
                                                                && healthcareWorker.getIsManagerDepartment() == false) list.add(healthcareWorker);
                                                        }
                                                        if(list.size() == 0) {
                                                            // Nếu trong Khoa hiện tại chỉ có một bác sĩ thì không thể thay đổi, vì đã là trưởng Khoa
                                                            System.out.println(" --- Hiện tại, trong Khoa chỉ có duy nhất một Bác sĩ đã là trưởng Khoa");
                                                        } else {
                                                            // 1 --- DOC00001 | Thanh Quy
                                                            // 2 --- DOC00002 | Duy Quý
                                                            // 3 --- DOC00003 | Minh Đức
                                                            // 4 --- DOC00004 | Phước An
                                                            System.out.println(" --- Chọn trưởng Khoa");
                                                            int subSerial = 1;
                                                            for(HealthcareWorker healthcareWorker : list) {
                                                                System.out.println(subSerial++ + " --- " + healthcareWorker.getId()
                                                                    + " | " + healthcareWorker.getFullname());
                                                            }
                                                            // Cho phép chọn serial hoặc id
                                                            System.out.print("? --- Chọn: ");
                                                            String subChoose5 = sc.nextLine();
                                                            while((myCharacterClass.hasOneCharacterIsLetter(subChoose5)
                                                                        && HealthcareWorkerManager.getInstance().findObjectById(subChoose5) == null)
                                                                    || HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1) == null) {
                                                                System.out.println("----- -----");
                                                                System.out.println("! --- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                                                System.out.print("?! --- Chọn lại: ");
                                                                subChoose5 = sc.nextLine();
                                                            }

                                                            // Mã của Bác sĩ là trưởng Khoa
                                                            newIdHealthcareWorker = subChoose5.length() != 1
                                                                ? HealthcareWorkerManager.getInstance().findObjectById(subChoose5).getId()
                                                                : HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId();

                                                            // Tạo Khoa và thiết lập trưởng Khoa
                                                            departmentUpdate = new Department(departmentSearch.getId(),
                                                                departmentSearch.getName(), newIdHealthcareWorker, departmentSearch.getRoom());

                                                            // Thiết lập là trưởng Khoa cho Bác sĩ đã chọn
                                                            HealthcareWorkerManager.getInstance().findObjectById(newIdHealthcareWorker).setIsManagerDepartment(true);
                                                        }

                                                        // Nhập số phòng mới
                                                        System.out.print(" --- Nhập số phòng mới: ");
                                                        String newRoom = sc.nextLine();

                                                        // Tạo khoa với những thông tin đã sửa và mã Khoa vẫn giữ nguyên
                                                        departmentUpdate = new Department(departmentSearch.getId(), newName, newIdHealthcareWorker, newRoom);

                                                        // Xoá hết tất cả các form vì đã sửa thông tin
                                                        serialForm.clear();

                                                    }

                                                    // Nếu khác null là đã cập nhật thông tin Khoa
                                                    if(departmentUpdate != null) {
                                                        // Sửa lại thông tin của Khoa đã chỉ định
                                                        DepartmentManager.getInstance().update(departmentUpdate);
                                                        // Sửa lại thông tin của Khoa đã tìm kiếm để đồng bộ dữ liệu cho những lần tìm kiếm sau
                                                        departmentSearch = new Department(departmentUpdate);
                                                    }

                                                    // Thông báo thông tin mới của Khoa sau khi sửa lần lượt cho đến hết danh mục hoặc sửa "Tất cả"
                                                    if(serialForm.size() == 0 || serialForm.get(0).equals("Tất cả")) {
                                                        clearTerminal();
                                                        System.out.println("Thông tin mới của Khoa sau khi sửa: " + departmentSearch.getInfo());
                                                    }
                                                }
                                            }
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

                                } else if(subChoose2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể xoá Khoa
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần xoá từ danh sách
                                    System.out.println(" -- Chọn Khoa cần xoá");
                                    // 1 -- DEP00001 | Tai-Mũi-Họng
                                    // 2 -- DEP00002 | Mắt
                                    // ...
                                    int serial = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(serial++ + " -- "
                                            + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn serial hoặc id
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                && DepartmentManager.getInstance().findObjectById(subChoose3) == null)
                                            || DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }

                                    // Tìm thông tin của Khoa cần xoá
                                    Department departmentRemove = null;
                                    if(subChoose3.length() != 1) {
                                        departmentRemove = DepartmentManager.getInstance().findObjectById(subChoose3);
                                    } else {
                                        departmentRemove = DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    }

                                    // Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Đối tượng Bệnh
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getDepartmentID() == null) continue;
                                        if(sick.getDepartmentID().equals(departmentRemove.getId())) {
                                            sick.setDepartmentID(null);
                                        }
                                    }
                                    // - Đối tượng Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getDepartmentID() == null) continue;
                                        if(healthcareWorker.getDepartmentID().equals(departmentRemove.getId())) {
                                            healthcareWorker.setDepartmentID(null);
                                            healthcareWorker.setIsManagerDepartment(null);
                                        }
                                    }

                                    // Xoá Khoa đã tìm
                                    DepartmentManager.getInstance().removeOne(departmentRemove.getId());

                                    // Thông báo thông tin của Khoa đã xoá
                                    System.out.println("! -- Đã xoá một Khoa: " + departmentRemove.getInfo());

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

                                } else if(subChoose2.equals("6")) {
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
                                    System.out.println("! -- Đã xoá tất cả Khoa");

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

                                } else if(subChoose2.equals("7")) {
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
                                    System.out.print(" -- Nhập thông tin Khoa cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Department> list = new ArrayList<>();
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        if(department.getName().toLowerCase().contains(info.trim().toLowerCase())
                                                || department.getId().equals(info)) list.add(department);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(list.size() == 0) {
                                        System.out.println("! -- Không tìm được Khoa nào với thông tin đã cho");
                                    } else {
                                        for(Department departmentSearch : list) {
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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Khoa");

                                    // Nếu không có Khoa nào được tạo thì không thể sắp xếp Bệnh
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
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

                                } else if(subChoose2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Khoa ");

                                    // Truy xuất dữ liệu từ Khoa
                                    DepartmentManager.getInstance().loadFromFile();
                                    System.out.println("Đã truy xuất dữ liệu từ Khoa thành công");

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

                                } else if(subChoose2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Khoa ");

                                    // Sao lưu dữ liệu từ Khoa
                                    DepartmentManager.getInstance().saveToFile();
                                    System.out.println("Đã sao lưu dữ liệu từ Khoa thành công");

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
                        } else if(subChoose1.equals("4")) {
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
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                        && !subChoose2.equals("2") && !subChoose2.equals("3")
                                        && !subChoose2.equals("4") && !subChoose2.equals("5")
                                        && !subChoose2.equals("6") && !subChoose2.equals("7")
                                        && !subChoose2.equals("8") && !subChoose2.equals("9")
                                        && !subChoose2.equals("10")) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoose2 = sc.nextLine();
                                }
                                if(subChoose2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh");

                                    // Mấy ông xem ở class SickManager
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

                                } else if(subChoose2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Bệnh");

                                    // Nếu không có Khoa nào được tạo thì không thể tạo Bệnh
                                    if(DepartmentManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa vì Bệnh cần một Khoa quản lí");
                                        continue subLoop2;
                                    }

                                    // Nếu đã có Khoa được tạo
                                    System.out.print(" -- Nhập tên Bệnh: ");
                                    String newName = sc.nextLine();

                                    // Chọn Khoa sẽ quản lý Bệnh được tạo
                                    System.out.println(" -- Chọn Khoa thuộc về");
                                    // 1 -- DEP00001 | Tai-Mũi-Họng
                                    // 2 -- DEP00002 | Thận
                                    // ...
                                    int serial = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        System.out.println(serial++ + " -- "
                                            + department.getId() + " | " + department.getName());
                                    }
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                && DepartmentManager.getInstance().findObjectById(subChoose3) == null)
                                            || DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }
                                    // Lấy mã Khoa đã được chọn
                                    String newDepartmentID = subChoose3.length() == 1
                                        ? DepartmentManager.getInstance().findObjectById(subChoose3).getId()
                                        : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1).getId();

                                    // Thêm một bệnh, Sick(name, idDepartment), vì id sẽ tự tạo
                                    Sick newSick = new Sick(newName, newDepartmentID);
                                    SickManager.getInstance().add(newSick);

                                   // Thông báo lại là đã thêm
                                   System.out.println("! -- Đã thêm Bệnh: " + newSick.getInfo());

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

                                } else if(subChoose2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể sửa Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần sửa từ danh sách
                                    System.out.println(" -- Chọn Bệnh cần sửa");
                                    // 1 -- SI00001 | Ung thư
                                    // 2 -- SI00001 | Suy thận
                                    // ...
                                    int serial = 1;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(serial++ + " -- "
                                            + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn SICKxxxxx)
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                && SickManager.getInstance().findObjectById(subChoose3) == null)
                                            || SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }
                                    Sick sickSearch = null;
                                    if(subChoose3.length() != 1) {
                                        sickSearch = SickManager.getInstance().findObjectById(subChoose3);
                                    } else {
                                        sickSearch =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    }

                                    // Thông báo đã chọn Bệnh cần sửa thông tin
                                    clearTerminal();
                                    System.out.println("Đã chọn Bệnh có thông tin: " + sickSearch.getInfo());

                                    // Tạo một from lựa chọn
                                    // - Id là cố định không thể sửa
                                    // - Chạy vòng lặp để người dùng có thể lựa chọn nhiều lần
                                    // - Xoá đi lựa chọn trước đó của người dùng
                                    // - Nếu chọn "Tất cả" thì xoá hết
                                    ArrayList<String> serialForm = new ArrayList<>();
                                    serialForm.add("Sửa tên Bệnh");
                                    serialForm.add("Sửa mã Khoa");
                                    serialForm.add("Tất cả");

                                    // Thông báo Bệnh đã được sửa thông tin mới cho những lần sửa
                                    boolean isUpdated = false;

                                    while(serialForm.size() >= 1 && !serialForm.get(0).equals("Tất cả")) {
                                        // Thông báo thông tin mới của Khoa khi đã sửa thông tin bất kỳ trừ "Tất cả"
                                        if(isUpdated) {
                                            isUpdated = false;
                                            clearTerminal();
                                            System.out.println("Thông tin mới của Khoa sau khi sửa: " + sickSearch.getInfo());
                                        }

                                        System.out.println("0 -- Quay lại");
                                        for(int i = 0; i < serialForm.size(); i++) {
                                            System.out.println((i + 1) + " -- " + serialForm.get(i));
                                        }
                                        System.out.print("? -- Chọn: ");
                                        String subChoose4 = sc.nextLine();
                                        while(myCharacterClass.hasOneCharacterIsLetter(subChoose4)
                                                || subChoose4.length() > 1 || !subChoose4.equals("0")
                                                || !subChoose4.equals("1") || !subChoose4.equals("2")) {
                                            System.out.println("----- -----");
                                            System.out.println("! -- LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! -- Chọn lại: ");
                                            subChoose4 = sc.nextLine();
                                        }
                                        if(subChoose4.equals("0")) {
                                            clearTerminal();
                                            System.out.println("Đã chọn Quay lại");
                                            continue subLoop2;
                                        } else {
                                            // Vào đây đảm bảo thông tin của một Bệnh luôn được sửa
                                            isUpdated = true;
                                            // Biến tạm giữ thông tin của Bệnh sau khi sửa
                                            Sick sickUpdate = null;
                                            for(int i = 0; i < serialForm.size(); i++) {
                                                if(i + 1 == Integer.parseInt(subChoose4)) {
                                                    if(serialForm.get(i).equals("Sửa tên Bệnh")) {
                                                        clearTerminal();
                                                        System.out.println("Đã chọn Sửa tên Bệnh");
                                                        System.out.print(" --- Nhập tên Bệnh mới: ");
                                                        String newName = sc.nextLine();
                                                        sickUpdate = new Sick(sickSearch.getId(), newName, sickSearch.getDepartmentID());
                                                        serialForm.remove(i);
                                                    } else if(serialForm.get(i).equals("Sửa mã Khoa")) {
                                                        clearTerminal();
                                                        System.out.println("Đã chọn Sửa mã Khoa");

                                                        // Lọc ra các Khoa hiện có trong Bệnh viên khác với Khoa quản lý Bệnh hiện tại
                                                        ArrayList<Department> list =  new ArrayList<>();
                                                        for(Department department : DepartmentManager.getInstance().getList()) {
                                                            if(!department.getId().equals(sickSearch.getDepartmentID()))
                                                                list.add(department);
                                                        }

                                                        System.out.println(" --- Chọn mã Khoa");
                                                        // 1 -- DEP00001 | Mắt
                                                        // 2 -- DEP00002 | Nội
                                                        // ...
                                                        int subSerial = 1;
                                                        for(Department department : list) {
                                                            System.out.println(subSerial++ + " -- " 
                                                                + department.getId() + " | " + department.getName());
                                                        }
                                                        // Cho phép chọn serial hoặc mã
                                                        System.out.print("? -- Chọn: ");
                                                        String subChoose5 = sc.nextLine();
                                                        while((myCharacterClass.hasOneCharacterIsLetter(subChoose5)
                                                                    && DepartmentManager.getInstance().findObjectById(subChoose5) == null)
                                                                || DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1) == null) {
                                                            System.out.println("----- -----");
                                                            System.out.println("! -- LỰA CHỌN KHÔNG HỢP LỆ");
                                                            System.out.print("?! -- Chọn lại: ");
                                                            subChoose5 = sc.nextLine();
                                                        }
                                                        // Lấy ra mã Khoa mà người dùng đã chọn
                                                        String newDepartmentID = subChoose5.length() == 1
                                                                ? DepartmentManager.getInstance().findObjectById(subChoose5).getId()
                                                                : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId();

                                                        sickUpdate = new Sick(sickSearch.getId(), sickSearch.getName(), newDepartmentID);

                                                        // Xoá một form vì thông tin đã sửa
                                                        serialForm.remove(i);

                                                    } else if(serialForm.get(i).equals("Tất cả")) {
                                                        clearTerminal();
                                                        System.out.println("Đã chọn Tất cả");
                                                        System.out.print(" --- Nhập tên Bệnh mới: ");
                                                        String newName = sc.nextLine();
                                                        System.out.println(" --- Chọn mã Khoa");
                                                        // 1 -- DEP00001 | Mắt
                                                        // 2 -- DEP00002 | Nội
                                                        // ...
                                                        int subSerial = 1;
                                                        for(Department department : DepartmentManager.getInstance().getList()) {
                                                            System.out.println(subSerial++ + " -- " + department.getId() + " | " + department.getName());
                                                        }
                                                        // Cho phép chọn serial, id hoặc tên
                                                        System.out.print("? -- Chọn: ");
                                                        String subChoose5 = sc.nextLine();
                                                        while((myCharacterClass.hasOneCharacterIsLetter(subChoose5)
                                                                    && DepartmentManager.getInstance().findObjectById(subChoose5) == null)
                                                                || DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1) == null) {
                                                            System.out.println("----- -----");
                                                            System.out.println("! --- LỰA CHỌN KHÔNG HỢP LỆ");
                                                            System.out.print("?! --- Chọn lại: ");
                                                            subChoose5 = sc.nextLine();
                                                        }
                                                        // Lấy ra mã Khoa mà người dùng đã chọn
                                                        String newDepartmentID = subChoose5.length() == 1
                                                                ? DepartmentManager.getInstance().findObjectById(subChoose5).getId()
                                                                : DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId();

                                                        sickUpdate = new Sick(sickSearch.getId(), newName, newDepartmentID);

                                                        // Xoá tất cả form vì đã cập nhật hết thông tin
                                                        serialForm.clear();
    
                                                    }

                                                    // Nếu khác null là đã cập nhật thông tin Bệnh
                                                    if(sickUpdate != null) {
                                                        // Sửa lại thông tin cho Bệnh
                                                        SickManager.getInstance().update(sickUpdate);
                                                        // Sửa lại thông tin của Bệnh đã tìm kiếm để đồng bộ dữ liệu cho những lần tìm kiếm sau
                                                        sickSearch = new Sick(sickUpdate);
                                                    }

                                                    // Thông báo khi sửa lần lượt cho đến hết thông tin hoặc sửa "Tất cả"
                                                    if(serialForm.size() == 0 || serialForm.get(0).equals("Tất cả")) {
                                                        clearTerminal();
                                                        System.out.println("Thông tin mới của Khoa sau khi sửa: " + sickSearch.getInfo());
                                                    }

                                                }
                                            }
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

                                } else if(subChoose2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể xoá Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
                                    }

                                    // Chọn Bệnh cần xoá từ danh sách
                                    System.out.println(" -- Chọn Bệnh cần xoá");
                                    // 1 -- SI00001 | Ung thư
                                    // 2 -- SI00001 | Suy thận
                                    // ...
                                    int serial = 1;
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        System.out.println(serial++ + " -- "
                                            + sick.getId() + " | " + sick.getName());
                                    }
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn DEP00001)
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while((myCharacterClass.hasOneCharacterIsLetter(subChoose3)
                                                && SickManager.getInstance().findObjectById(subChoose3) == null)
                                            || SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }
                                    // Lấy thông tin của Bệnh cần xoá
                                    Sick sickRemove = null;
                                    if(subChoose3.length() != 1) {
                                        sickRemove = SickManager.getInstance().findObjectById(subChoose3);
                                    } else {
                                        sickRemove =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    }

                                    // Huỷ liên với các đối tượng liên quan
                                    // -- Bệnh án
                                    for(MedicalRecord medicalRecord : MedicalRecordManager.getInstance().getList()) {
                                        if((medicalRecord.getSickID() == null
                                                || medicalRecord.getSickID().equals("null"))
                                            && medicalRecord.getSickLevel() == null) continue;
                                        if(medicalRecord.getSickID().equals(sickRemove.getId())) {
                                            medicalRecord.setSickID(null);
                                            medicalRecord.setSickLevel(null);
                                        }
                                    }

                                    // Xoá Bệnh
                                    SickManager.getInstance().removeOne(sickRemove.getId());

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

                                } else if(subChoose2.equals("6")) {
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
                                    System.out.println("! -- Đã xoá tất cả Bệnh");

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

                                } else if(subChoose2.equals("7")) {
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
                                    System.out.print(" -- Nhập thông tin Bệnh cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Sick> list = new ArrayList<>();
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getName().toLowerCase().contains(info.trim().toLowerCase())
                                            || sick.getId().equals(info)) list.add(sick);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(list.size() == 0) {
                                        System.out.println("! -- Không tìm được thông tin Bệnh");
                                    } else {
                                        for(Sick sickSearch : list) {
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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh");

                                    // Nếu không có Bệnh nào được tạo thì không thể sắp xếp Bệnh
                                    if(SickManager.getInstance().getNumbers() == 0) {
                                        clearTerminal();
                                        System.out.println("Hiện tại Bệnh viện chưa có Bệnh, cần tạo ít nhất một Bệnh");
                                        continue subLoop2;
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

                                } else if(subChoose2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh");

                                    // Truy xuất dữ liệu từ Bệnh
                                    SickManager.getInstance().loadFromFile();

                                    // Thông báo đã truy xuất dữ liệu từ Bệnh thành công
                                    System.out.println("Đã truy xuất dữ liệu từ Bệnh thành công");

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

                                } else if(subChoose2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Bệnh");

                                    // Sao lưu dữ liệu Bệnh
                                    SickManager.getInstance().saveToFile();

                                    // Thông báo đã sao lưu dữ liệu Bệnh thành công
                                    System.out.println("Đã sao lưu dữ liệu từ Bệnh thành công");

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
                        } else if(subChoose1.equals("5")) {
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
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                        && !subChoose2.equals("2") && !subChoose2.equals("3")
                                        && !subChoose2.equals("4") && !subChoose2.equals("5")
                                        && !subChoose2.equals("6") && !subChoose2.equals("7")
                                        && !subChoose2.equals("7") && !subChoose2.equals("9")
                                        && !subChoose2.equals("10")) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoose2 = sc.nextLine();
                                }
                                if(subChoose2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Nhân viên Y tế");



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

                                } else if(subChoose2.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Nhân viên Y tế");



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

                                } else if(subChoose2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Nhân viên Y tế");



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

                                } else if(subChoose2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Nhân viên Y tế");



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

                                } else if(subChoose2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Nhân viên Y tế");



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

                                } else if(subChoose2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Nhân viên Y tế");



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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Nhân viên Y tế");



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

                                } else if(subChoose2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Nhân viên Y tế");



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

                                } else if(subChoose2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu dữ liệu Nhân viên Y tế");



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
                        } else if(subChoose1.equals("6")) {
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
                                String managerPatientChoice = sc.nextLine();
                                while(false == isValidChoice(managerPatientChoice, 10)) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    managerPatientChoice = sc.nextLine();
                                }
                                if(managerPatientChoice.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(managerPatientChoice.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(managerPatientChoice.equals("2")) {
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh nhân");

                                    // Gọi method show() trong PatientManager để show toàn bộ Object trong (list) với format xác định.
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

                                } else if(managerPatientChoice.equals("3")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Thêm một Bệnh nhân");
                                    
                                    /** THÊM BỆNH NHÂN VỚI THÔNG TIN NHẬP TỪ NGƯỜI DÙNG
                                     * 1. Tạo một bệnh nhân mới với thông tin từ người dùng
                                     *      createPatientWithDataFromUser(): Patient
                                     * 2. Thêm Patient mới đó vào list
                                     */
                                    Patient patient = createPatientWithDataFromUser();
                                    PatientManager.getInstance().getList().add(patient);
                                    // System.out.println("*" + patient.getId() + "*");
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

                                } else if(managerPatientChoice.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh nhân");

                                    /** SỬA MỘT BỆNH NHÂN VỚI ID (được nhập)
                                     * 1. Tìm ID của bệnh nhân
                                     * 2. Tùy chọn thông tin của bệnh nhân đó để sửa.
                                     */
                                    // Lấy ID đúng định dạng và tồn tại trong list để update
                                    String id = getValidIdFromUser();
                                    // Tham chiếu đến Patient với ID đó để sửa:
                                    Patient updatePatient = PatientManager.getInstance().findObjectById(id);
                                    
                                    // Cập nhật thông tin của Patient với ID được nhập
                                    boolean goback = false;
                                    while(false == goback) {
                                        System.out.println("/********** CẬP NHẬT THÔNG TIN BỆNH NHÂN **********/");
                                        System.out.println("0 - Quay lại");
                                        System.out.println("1 - Họ và tên");
                                        System.out.println("2 - Ngày sinh");
                                        System.out.println("3 - Giới tính");
                                        System.out.println("4 - Số điện thoại");
                                        System.out.println("5 - Quốc tịch");
                                        System.out.println("6 - Thay đổi loại bệnh nhân");
                                        // System.out.println("10 - Thay đổi toàn bộ thông tin");
                                        System.out.print("? - Chọn: ");
                                        String updatePatientChoice = sc.nextLine();

                                        // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                        while(false == isValidChoice(updatePatientChoice, 6)) {
                                            System.out.println("---------- ----------");
                                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                            System.out.print("?! - Chọn lại: ");
                                            updatePatientChoice = sc.nextLine();
                                        }
                                        // Chuyển [updatePatientChoice] sang int -> switch
                                        switch (Integer.parseInt(updatePatientChoice)) {
                                            case 1: 
                                                System.out.println("Đã chọn cập nhật Họ và tên");
                                                System.out.print("Nhập lại họ và tên mới: "); 
                                                updatePatient.setFullname(sc.nextLine());
                                                break;
                                            case 2:
                                                System.out.println("Đã chọn cập nhật Ngày sinh");
                                                System.out.print("Nhập lại ngày sinh mới: "); 
                                                // Chuyển birthday (string) -> Date Object
                                                updatePatient.setBirthday(Date.convertStringToDate(sc.nextLine()));
                                                break;
                                            case 3:
                                                System.out.println("Đã chọn cập nhật Giới tính");
                                                System.out.print("Nhập lại giới tính mới: "); 
                                                updatePatient.setGender(sc.nextLine());
                                                break;
                                            case 4:
                                                System.out.println("Đã chọn cập nhật Số điện thoại");
                                                System.out.print("Nhập lại số điện thoại mới: "); 
                                                updatePatient.setPhone(sc.nextLine());
                                                break;
                                            case 5: 
                                                System.out.println("Đã chọn cập nhật Quốc tịch");
                                                System.out.print("Nhập lại quốc tịch mới: "); 
                                                updatePatient.setCountry(sc.nextLine());
                                                break;
                                            case 6:
                                                System.out.println("Đã chọn cập nhật Loại bệnh nhân");
                                                if(updatePatient.getType().equals("Premium")){
                                                    System.out.println("Loại hiện tại: Cao cấp. Bạn có muốn đổi sang loại Thường không? ");
                                                    System.out.print("Nhập 'YES' để xác nhận: "); 
                                                    String confirm = sc.nextLine();
                                                    if(confirm.equals("YES")) {
                                                        updatePatient.setType("Normal");
                                                        System.out.println("Đã đổi loại bệnh nhân sang Thường");
                                                        break;
                                                    } else {
                                                        System.out.println("Bạn đã không nhập 'YES' nên không cập nhật Loại bệnh nhân");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Loại hiện tại: Thường. Bạn có muốn đổi sang loại Cao cấp không? ");
                                                    System.out.print("Nhập 'YES' để xác nhận: "); 
                                                    String confirm = sc.nextLine();
                                                    if(confirm.equals("YES")) {
                                                        updatePatient.setType("Primium");
                                                        System.out.println("Đã đổi loại bệnh nhân sang Cao cấp");
                                                        break;
                                                    } else {
                                                        System.out.println("Bạn đã không nhập 'YES' nên không cập nhật Loại bệnh nhân");
                                                        break;
                                                    }
                                                }
                                            case 0:
                                                System.out.println("Đã chọn quay lại");
                                                // Thoát ra khỏi luồng Cập nhật thông tin bệnh nhân, tiếp tục chương trình 
                                                goback = true;      
                                                break;
                                            default:
                                                goback = true;      
                                                break;
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

                                } else if(managerPatientChoice.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh nhân");

                                    /* XÓA MỘT BỆNH NHÂN VỚI ID ĐƯỢC NHẬP */
                                    // Lấy ID đúng định dạng và tồn tại trong list để xóa
                                    String id = getValidIdFromUser();
                                    // Xác nhận lại khi xóa
                                    System.out.println("Bạn đã yêu cầu xóa một bệnh nhân với ID: " + id);
                                    if(true == confirmAgain()){
                                        PatientManager.getInstance().removeOne(id);
                                        System.out.println("!!! Bạn đã xác nhận xóa");
                                    } else {
                                        System.out.println("!!! Bạn đã hủy xóa");
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

                                } else if(managerPatientChoice.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Bệnh nhân");
                                    
                                    /* XÓA TOÀN BỘ BỆNH NHÂN TRONG LIST */
                                    System.out.println("!!! WARNINIG: BẠN ĐÃ YÊU CẦU XÓA TOÀN BỘ BỆNH NHÂN");
                                    if(true == confirmAgain()){
                                        PatientManager.getInstance().removeAll();
                                        System.out.println("!!! Bạn đã xác nhận xóa");
                                    } else {
                                        System.out.println("!!! Bạn đã hủy xóa");
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

                                } else if(managerPatientChoice.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh nhân");
                                    /** TÌM KIẾM BỆNH NHÂN THÔNG QUA ID
                                     * 1. Nhập ID đúng theo format 
                                     * 2. Tìm kiếm, trả về info của Bệnh Nhân (getInfo()) với ID được nhập | null (nếu ID không tồn tại)
                                     */
                                    // System.out.println("Bạn hãy nhập ID của bệnh nhân cần tìm.");
                                    // String searchID = getTrueFormatIdFromUser();
                                    // String infoPatientFromSearchID = PatientManager.getInstance().getInfoById(searchID);
                                    // System.out.println(infoPatientFromSearchID);
                                    // if(null == infoPatientFromSearchID){
                                    //     System.out.println("!!! ID không tồn tại.");
                                    // } else {
                                    //     System.out.println("Thông tin của bệnh nhân với ID - " + searchID + " :" );
                                    //     System.out.println(infoPatientFromSearchID);
                                    // }

                                    // Tìm kiếm bằng mã Khoa hay tên Khoa đều được phép
                                    System.out.println("Bạn có thể tìm bằng id");
                                    System.out.print(" -- Nhập thông tin id cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Patient> list = new ArrayList<>();
                                    for(Patient patient : PatientManager.getInstance().getList()) {
                                        if(patient.getFullname().toLowerCase().contains(info.trim().toLowerCase())
                                                || patient.getId().equals(info)) list.add(patient);
                                    }

                                    // Thông báo kết quả tìm được
                                    if(list.size() == 0) {
                                        System.out.println("! -- Không tìm được Khoa nào với thông tin đã cho");
                                    } else {
                                        for(Patient PatientSearch : list) {
                                            System.out.println(PatientSearch.getInfo());
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

                                } else if(managerPatientChoice.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh nhân");
                                    /** SẮP XẾP BỆNH NHÂN THEO TÙY CHỌN
                                     * 1. Họ và tên (Alphabet)
                                     * 2. Ngày sinh
                                     * 3. ID
                                     */
                                    System.out.println("Nhập tùy chọn để sắp xếp:");
                                    System.out.println("1 - Tên bệnh nhân");
                                    System.out.println("2 - Ngày sinh bệnh nhân");
                                    System.out.println("3 - ID bệnh nhân");
                                    System.out.print("? - Chọn: ");
                                    String sortChoice = sc.nextLine();

                                    // Kiểm tra input cho lựa chọn hợp lệ, cho nhập lại đến khi hợp lệ
                                    while(false == isValidChoice(sortChoice, 3)) {
                                        System.out.println("---------- ----------");
                                        System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! - Chọn lại: ");
                                        sortChoice = sc.nextLine();
                                    }

                                    // Tạo sortList, copy src_list qua sortList
                                    // ArrayList<Patient> sortList = new ArrayList<>(PatientManager.getInstance().getList()); 
                                    switch (sortChoice) {
                                        case "1":
                                            PatientManager.getInstance().sort("name");
                                            PatientManager.getInstance().show();
                                            break;
                                        case "2":
                                            PatientManager.getInstance().sort("birthday");
                                            PatientManager.getInstance().show();
                                            break;
                                        case "3":
                                            PatientManager.getInstance().sort("id");
                                            PatientManager.getInstance().show();
                                            break;
                                        default:
                                            PatientManager.getInstance().show();
                                            break;
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

                                } else if(managerPatientChoice.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy vấn dữ liệu Bệnh nhân");

                                    // Bỏ 

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

                                } else if(managerPatientChoice.equals("10")) {
                                    System.out.println("Đã chọn Sao lưu dữ liệu Bệnh nhân");

                                    // Sao lưu toàn bộ dữ liệu bệnh nhân vô file
                                    PatientManager.getInstance().saveToFile();
                                    // Thông báo đã sao lưu thành công
                                    System.out.println("Bạn đã sao lưu toàn bộ dữ liệu bệnh nhân thành công.");

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
                        } else if(subChoose1.equals("7")) {
                            clearTerminal();
                            System.out.println("Đã chọn Quản lý Bệnh án");
                            subLoop2:
                            // MER
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
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                    && !subChoose2.equals("2") && !subChoose2.equals("3")
                                    && !subChoose2.equals("4") && !subChoose2.equals("5")
                                    && !subChoose2.equals("6") && !subChoose2.equals("7")
                                    && !subChoose2.equals("8") && !subChoose2.equals("9")
                                    && !subChoose2.equals("10")) {
                                    System.out.println("---------- ----------");
                                    System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                    System.out.print("?! - Chọn lại: ");
                                    subChoose2 = sc.nextLine();
                                }
                                if(subChoose2.equals("0")) {
                                    System.out.println("Đã chọn Kết thúc");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Quay lại");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh án");


                                    
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

                                } else if(subChoose2.equals("3")) {
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

                                } else if(subChoose2.equals("4")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sửa một Bệnh án");



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

                                } else if(subChoose2.equals("5")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá một Bệnh án");



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

                                } else if(subChoose2.equals("6")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Xoá tất cả Bệnh án");



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

                                } else if(subChoose2.equals("7")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Tìm kiếm Bệnh án");



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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh án");



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

                                } else if(subChoose2.equals("9")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Truy xuất dữ liệu Bệnh án");



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

                                } else if(subChoose2.equals("10")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sao lưu Bệnh án");



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
                        } else if(subChoose1.equals("8")) {
                            System.out.println("Đã chọn Truy xuất dữ liệu Bệnh viện");

                            // Truy xuất dữ liệu từ cả các file
                            AccountManager.getInstance().loadFromFile();
                            DepartmentManager.getInstance().loadFromFile();
                            SickManager.getInstance().loadFromFile();
                            HealthcareWorkerManager.getInstance().loadFromFile();
                            PatientManager.getInstance().loadFromFile();
                            MedicalRecordManager.getInstance().loadFromFile();
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

                        } else if(subChoose1.equals("9")) {
                            System.out.println("Đã chọn Sao lưu dữ liệu Bệnh viện");

                            // Sao lưu dữ liệu từ cả các file
                            AccountManager.getInstance().saveToFile();
                            DepartmentManager.getInstance().saveToFile();
                            SickManager.getInstance().saveToFile();
                            HealthcareWorkerManager.getInstance().saveToFile();
                            PatientManager.getInstance().saveToFile();
                            MedicalRecordManager.getInstance().saveToFile();
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
                            HealthcareWorkerManager.getInstance().findObjectById(account.getObjectID());
                        
                        // Danh mục các lựa chọn
                        subLoop1:
                        while(true) {
                            System.out.println("/********** TÀI KHOẢN NHÂN VIÊN Y TẾ **********/");
                            System.out.println("0 - Kết thúc");
                            System.out.println("1 - Quay lại");
                            System.out.println("2 - Thông tin cơ bản");
                            System.out.println("3 - Thông tin trong Bệnh viện");
                            System.out.print("? - Chọn: ");
                            String subChoose1 = sc.nextLine();
                            while(!subChoose1.equals("0") && !subChoose1.equals("1")
                                    && !subChoose1.equals("2") && !subChoose1.equals("3")) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoose1 = sc.nextLine();
                            }
                            if(subChoose1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoose1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoose1.equals("2")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin cơ bản");

                                // In thông tin cơ bản của Nhân viên Y tế
                                System.out.println("/********** THÔNG TIN CƠ BẢN **********/");
                                System.out.println(" - Họ và tên: " + healthcareWorker.getFullname());
                                System.out.println(" - Ngày sinh: " + healthcareWorker.getBirthday());
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

                            } else if(subChoose1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin trong Bệnh viện");

                                // In thông tin trong Bệnh viện của Nhân viên Y tế
                                System.out.println("/********** THÔNG TIN TRONG BỆNH VIỆN **********/");
                                System.out.println(" - Mã Nhân viên: " + healthcareWorker.getId());
                                System.out.println(" - Loại: " + healthcareWorker.getType());
                                System.out.println(" - Số năm kinh nghiệm: " + healthcareWorker.getYearsOfExperience());
                                System.out.println(" - Tiền lương: " + healthcareWorker.getSalary());
                                System.out.println(" - Mã Khoa: " + healthcareWorker.getDepartmentID());
                                System.out.println(" - Có là trưởng Khoa: "
                                    + (healthcareWorker.getIsManagerDepartment() == null
                                        ? "Không" : (healthcareWorker.getIsManagerDepartment() == false
                                            ? "Không" : "Có")));
                                System.out.println(" - Bệnh án đang làm việc: " + healthcareWorker.getMedicalRecordID());

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
                            PatientManager.getInstance().findObjectById(account.getObjectID());

                        // Danh mục các lựa chọn
                        subLoop1:
                        while(true) {
                            System.out.println("/********** TÀI KHOẢN BỆNH NHÂN **********/");
                            System.out.println("0 - Kết thúc");
                            System.out.println("1 - Quay lại");
                            System.out.println("2 - Thông tin cơ bản");
                            System.out.println("3 - Thông tin trong Bệnh viện");
                            System.out.println("4 - ...");
                            System.out.print("? - Chọn: ");
                            String subChoose1 = sc.nextLine();
                            while(!subChoose1.equals("0") && !subChoose1.equals("1")
                                    && !subChoose1.equals("2") && !subChoose1.equals("3")) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoose1 = sc.nextLine();
                            }
                            if(subChoose1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoose1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoose1.equals("2")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin cơ bản");

                                // In thông tin cơ bản của Bệnh nhân
                                System.out.println("/********** THÔNG TIN CƠ BẢN **********/");
                                System.out.println(" - Họ và tên: " + patient.getFullname());
                                System.out.println(" - Ngày sinh: " + patient.getBirthday());
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

                            } else if(subChoose1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Thông tin trong Bệnh viện");

                                // In thông tin trong Bệnh viện của Bệnh nhân
                                System.out.println("/********** THÔNG TIN TRONG BỆNH VIỆN **********/");
                                System.out.println(" - Mã Bệnh nhân: " + patient.getId());
                                System.out.println(" - Loại: " + patient.getType());
                                System.out.println(" - Có đang khám bệnh: "
                                    + (MedicalRecordManager.getInstance().findObjectById(patient.getMedicalRecordID()).getType() == null
                                        ? "Không (Đang chữa bệnh)" : (MedicalRecordManager.getInstance().findObjectById(patient.getMedicalRecordID()).getType().equals("Chữa bệnh")
                                            ? "Không (Đang chữa bệnh)" : "Có")));
                                System.out.println(" - Bệnh án đang làm việc: " + patient.getMedicalRecordID());

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
                        String subChoose1 = sc.nextLine();
                            while(!subChoose1.equals("0") && !subChoose1.equals("1")
                            && !subChoose1.equals("2") && !subChoose1.equals("3")) {
                                System.out.println("---------- ----------");
                                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                                System.out.print("?! - Chọn lại: ");
                                subChoose1 = sc.nextLine();
                            }
                            if(subChoose1.equals("0")) {
                                System.out.println("Đã chọn Kết thúc");
                                break mainLoop;
                            } else if(subChoose1.equals("1")) {
                                clearTerminal();
                                System.out.println("Đã chọn Quay lại");
                                continue mainLoop;
                            } else if(subChoose1.equals("2")) {
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
                                    String subChoose2 = sc.nextLine();
                                    while(subChoose2.length() == 1
                                            ? (int) subChoose2.charAt(0) < 49
                                                || (int) subChoose2.charAt(0) > (int) String.valueOf(serial2).charAt(0)
                                            : DepartmentManager.getInstance().findObjectById(subChoose2) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- LỰA CHỌN KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose2 = sc.nextLine();
                                    }
                                    String idHealthcareWorker = (subChoose2.length() == 1
                                    ? HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose2) - 1).getId()
                                    : HealthcareWorkerManager.getInstance().findObjectById(subChoose2).getId());
                                    // - Tạo một Bệnh nhân đăng ký khám
                                    Patient patient = null;
                                    if(type.equals("Thường")) {
                                        patient = new NormalPatient(fullname, new Date(day, month, year), gender, phone, country, type);
                                    } else {
                                        patient = new PremiumPatient(fullname, new Date(day, month, year), gender, phone, country, type);
                                    }
                                    
                                    // Tạo một hồ sơ Bệnh án với các thông tin đã được nhập từ phía người dùng
                                    // - Lấy ngày hiện tại là ngày đăng ký, còn ngày khám xong tuỳ thuộc vào Quản lý Bệnh án
                                    String date = Date.getInstance().changeDateFormat(String.valueOf(java.time.LocalDate.now()));
                                    Date inputDay = new Date(Integer.parseInt(date.substring(0, 2)),
                                    Integer.parseInt(date.substring(3, 5)), Integer.parseInt(date.substring(6)));
                                    Date outputDay = new Date(inputDay);
                                    // - Tạo Bệnh án
                                    MedicalRecord testRecord = null;
                                    if(idHealthcareWorker.substring(0, 3).equals("DOC")) {
                                        testRecord = new TestRecord(inputDay, outputDay, "Khám bệnh", patient.getId(), idHealthcareWorker, null, reason);
                                    } else {
                                        testRecord = new TestRecord(inputDay, outputDay, "Khám bệnh", patient.getId(), null, idHealthcareWorker, reason);
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
                                
                            } else if(subChoose1.equals("3")) {
                                clearTerminal();
                                System.out.println("Đã chọn Xoá tài khoản");

                                // Hỏi lần nữa có muốn xoá tài khoản hay không ?
                                System.out.print("Nhập 'YES' nếu bạn chắc chắn sẽ xoá tài khoản này: ");
                                String wantRemove = sc.nextLine();
                                if(wantRemove.equals("YES")) {
                                    // Xoá tài khoản
                                    AccountManager.getInstance().remove(account.getUsername(), account.getPassword());
                                    
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
                } else if(mainChoose.equals("2")) {
                    clearTerminal();
                    System.out.println("Đã chọn Đăng ký");
                    System.out.println("/********** ĐĂNG KÝ **********/");
                    System.out.print(" - Nhập tên đăng nhập đăng ký: ");
                    String newUsername = sc.nextLine();
                    System.out.print(" - Nhập mật khẩu đăng ký: ");
                    String newPassword = sc.nextLine();
                    // Nếu tài khoản đã tồn tại (là không thể đăng ký tài khoản) thì đăng ký lại
                    while(AccountManager.getInstance().canNotRegister(newUsername, newPassword)) {
                        System.out.println("---------- ----------");
                        System.out.println("! - TÀI KHOẢN ĐÃ TỒN TẠI HOẶC KHÔNG HỢP LỆ");
                        System.out.print(" -! Nhập lại tên đăng nhập: ");
                        newUsername = sc.nextLine();
                        System.out.print(" -! Nhập lại mật khẩu: ");
                        newPassword = sc.nextLine();
                    }
                    System.out.println("Đã đăng ký tài khoản mới - " + newUsername);
                    AccountManager.getInstance().add(new Account(newUsername, newPassword, "Tài khoản mới", null));
                }
                clearTerminal();
        }
    }


    /* UTILITIES */
    // Lấy toàn bộ thông tin cho bệnh nhân, return: Patient có đầy đủ thông tin -> add vào list
    public Patient createPatientWithDataFromUser(){
        // Không cho nhập 2 properties: ID và MedicalRecordID.
        String fullName, birthdayString, gender, phone, country, type;

        System.out.println("/********** NHẬP THÔNG TIN CHO BỆNH NHÂN **********/");
        System.out.print("1 - Họ và tên: "); fullName = sc.nextLine();
        System.out.print("2 - Ngày sinh: "); birthdayString = sc.nextLine();
        System.out.print("3 - Giới tính: "); gender = sc.nextLine();
        System.out.print("4 - Số điện thoại: "); phone = sc.nextLine();
        System.out.print("5 - Quốc tịch: "); country = sc.nextLine();
        System.out.println("6 - Loại bệnh nhân [0: Thường | 1: Cao cấp]: "); 
        System.out.print("? Chọn [0|1]: ");  int choice = sc.nextInt(); 
        type = (choice == 1) ? "Premium" : "Normal";
        // Loại bỏ dấu \n  sau khi nhập số nguyên.
        sc.nextLine();

        // Chuyển birthdayString -> (Date) birthday
        Date birthday = Date.convertStringToDate(birthdayString);
        // Tạo Patient object từ các data được nhập
        Patient newPatient = new Patient(fullName, birthday, gender, phone, country, type);
        
        return newPatient;
    }
    
    // Lấy ID từ người dùng: đúng format.
    private String getTrueFormatIdFromUser(){
        boolean isTrueFormatID = false;
        String id = ""; 
        System.out.print("Nhập ID của bệnh nhân: ");
        while (false == isTrueFormatID) {
            id = sc.nextLine();
            if(false == Patient.isFormatId(id)){
                System.out.print("!!! ID không hợp lệ. Nhập lại: ");
            } else {
                isTrueFormatID = true;
            }
        }
        return id;
    }
    // Lấy ID từ người dùng: đúng format và tồn tại trong list
    private String getValidIdFromUser(){
        String id = null;
        boolean isExistID = false;
        while(false == isExistID){
            id = getTrueFormatIdFromUser();
            if(null == PatientManager.getInstance().findObjectById(id)){
                System.out.println("!!! ID không tồn tại.");
            } else {
                isExistID = true;
            }  
        }
        return id;
    }

    /** Kiểm tra input khi chọn option [0; n]
     * 1. Chuyển choice sang int
     * 2. Nếu không nằm trong khoảng [0; n] hay không phải số nguyên -> false  
     * 3. Ngược lại -> True
     *  */ 
    private static boolean isValidChoice(String choice, int maxOption){
        try{
            // Ép kiểu [choice] sang int
            int option = Integer.parseInt(choice);
                // So sánh [option] trong khoảng cho phép.
            return (0 <= option) && (option <= maxOption);
        } catch (NumberFormatException e){
            return false;   // không phải số hợp lệ (là float | string |,...)
        }
    }

    // Xác nhận "YES" để xóa
    private boolean confirmAgain(){
        System.out.print("!!! Nhập 'YES' để xác nhận: ");
        boolean check = false;
        if(sc.nextLine().equals("YES")){
            check = true;
        }
        return check;
    }
}
