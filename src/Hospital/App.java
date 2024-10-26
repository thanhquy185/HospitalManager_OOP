package Hospital;

import java.util.Scanner;
import java.util.ArrayList;

import Common.CharacterFormat;
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
    public static App getInstance() {
        if(App.instance == null) {
            App.instance = new App();
        }
        return App.instance;
    }
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
                while(!AccountManager.getInstance().canAccessAccount(username, password) && countCanNotLoginAccount < 2) {
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
                        System.out.println("2 - Quản lý Khoa");
                        System.out.println("3 - Quản lý Bệnh");
                        System.out.println("4 - Quản lý Nhân viên Y tế");
                        System.out.println("5 - Quản lý Bệnh nhân");
                        System.out.println("6 - Quản lý Bệnh án");
                        System.out.println("7 - Truy xuất dữ liệu Bệnh viện");
                        System.out.println("8 - Sao lưu dữ liệu Bệnh viện");
                        System.out.print("? - Chọn: ");
                        String subChoose1 = sc.nextLine();
                        while(!subChoose1.equals("0") && !subChoose1.equals("1")
                                && !subChoose1.equals("2") && !subChoose1.equals("3")
                                && !subChoose1.equals("4") && !subChoose1.equals("5")
                                && !subChoose1.equals("6") && !subChoose1.equals("7")
                                && !subChoose1.equals("8")) {
                            System.out.println("---------- ----------");
                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                            System.out.print("?! - Chọn lại: ");
                            subChoose1 = sc.nextLine();
                        }
                        if(subChoose1.equals("0")) {
                            System.out.println("Đã chọn Kết thúc");
                            break mainLoop;
                        } else if(subChoose1.equals("1")) {
                            System.out.println("Đã chọn Quay lại");
                            clearTerminal();
                            continue mainLoop;
                        } else if(subChoose1.equals("2")) {
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

                                    // Nhập tên Khoa
                                    System.out.print(" -- Nhập tên Khoa: ");
                                    String name = sc.nextLine();

                                    // Biến tạm giữ mã trưởng Khoa
                                    String idManager = null;
                                    // Lọc ra danh sách các Bác sĩ chưa là trưởng Khoa
                                    ArrayList<HealthcareWorker> list =
                                         HealthcareWorkerManager.getInstance().findObjectsByCondition("doctor is not manager department");
                                    if(list != null) {
                                        System.out.println(" -- Chọn trưởng Khoa");
                                        // 1 -- DEP00001 | Mắt
                                        // 2 -- DEP00002 | Nội
                                        // ...
                                        int serial = 1;
                                        for(HealthcareWorker healthcareWorker : list) {
                                            System.out.println(serial++ + " -- " + healthcareWorker.getId()
                                                + " | " + healthcareWorker.getFullname());
                                        }
                                        // Cho phép chọn serial hoặc id
                                        System.out.print("? -- Chọn: ");
                                        String subChoose3 = sc.nextLine();
                                        while(subChoose3.length() == 1
                                                ? (int) subChoose3.charAt(0) < 49
                                                    || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                                : HealthcareWorkerManager.getInstance().findObjectById(subChoose3) == null) {
                                            System.out.println("----- -----");
                                            System.out.println("! -- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                            System.out.print("?! -- Chọn lại: ");
                                            subChoose3 = sc.nextLine();
                                        }

                                        // ...
                                        idManager = (subChoose3.length() == 1
                                            ? HealthcareWorkerManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1).getId()
                                            : HealthcareWorkerManager.getInstance().findObjectById(subChoose3).getId());

                                        // Thiết lập trưởng Khoa cho mã tương ứng
                                        HealthcareWorkerManager.getInstance().findObjectById(idManager).setIsManagerDepartment(true);
                                    } else {
                                        System.out.println(" -- Hiện tại, Bệnh viện không có Bác sĩ nào chưa là trưởng Khoa");
                                    }

                                    // Nhập số phòng
                                    System.out.print(" -- Nhập số phòng: ");
                                    String room = sc.nextLine();

                                    // Thêm Khoa
                                    Department newDepartment = new Department(name, idManager, room);
                                    DepartmentManager.getInstance().add(newDepartment);

                                    // Thông báo đã thêm một Khoa
                                    System.out.println("! -- Đã thêm Khoa: " + newDepartment.getInfo());

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

                                    // Chọn Khoa cần sửa từ danh sách
                                    System.out.println(" -- Chọn Khoa cần sửa");
                                    // 1 -- DEP00001 | Nội
                                    // 2 -- DEP00002 | Mắt
                                    // 3 -- DEP00008 | Răng-Hàm-Mặt (chưa có trưởng Khoa)
                                    int serial = 1;
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        String infoDepartment = serial++ + " -- "
                                                + department.getId() + " | " + department.getName();
                                        if(department.getIdManager() == null) {
                                            infoDepartment = infoDepartment + " (chưa có trưởng Khoa)";
                                        }
                                        System.out.println(infoDepartment);
                                    }
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn SIxxxxx)
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while(subChoose3.length() == 1
                                            ? (int) subChoose3.charAt(0) < 49
                                                || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                            : DepartmentManager.getInstance().findObjectById(subChoose3) == null) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }

                                    // Biến tạm giữ thông tin của Khoa cần sửa thông tin
                                    Department departmentSearch = null;
                                    if(subChoose3.length() == 1) {
                                        departmentSearch =  DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    } else {
                                        departmentSearch = DepartmentManager.getInstance().findObjectById(subChoose3);
                                    }

                                    // Tạo một from lựa chọn
                                    // - Id là cố định không thể sửa
                                    // - Chạy vòng lặp để người dùng có thể lựa chọn nhiều lần
                                    // - Xoá đi lựa chọn trước đó của người dùng
                                    // - Nếu chọn "Tất cả" thì xoá hết
                                    ArrayList<String> serialFrom = new ArrayList<>();
                                    serialFrom.add("Sửa tên Khoa");
                                    serialFrom.add("Sửa mã trưởng Khoa");
                                    serialFrom.add("Sửa số phòng");
                                    serialFrom.add("Tất cả");

                                    // Thông báo thông tin của Khoa đã được chọn để sửa thông tin
                                    clearTerminal();
                                    System.out.println("Đã chọn Khoa có thông tin: " + departmentSearch.getInfo());
                                    
                                    // Thông báo Khoa đã được sửa thông tin mới cho những lần sửa
                                    boolean isUpdated = false;

                                    // Vòng lặp sẽ dừng khi form sửa thông tin hết hoặc khi chọn "Tất cả" lần đầu tiên
                                    while(serialFrom.size() >= 1 && !serialFrom.get(0).equals("Tất cả")) {
                                        // Thông báo thông tin mới của Khoa khi đã sửa thông tin bất kỳ trừ "Tất cả"
                                        if(isUpdated) {
                                            isUpdated = false;
                                            clearTerminal();
                                            System.out.println("Thông tin mới của Khoa sau khi sửa: " + departmentSearch.getInfo());
                                        }

                                        // Các lựa chọn...
                                        System.out.println("0 --- Quay lại");
                                        for(int i = 0; i < serialFrom.size(); i++) {
                                            System.out.println((i + 1) + " --- " + serialFrom.get(i));
                                        }
                                        System.out.print("? --- Chọn thông tin cần sửa: ");
                                        String subChoose4 = sc.nextLine();
                                        while(subChoose4.length() > 1 || (int) subChoose4.charAt(0) < 48
                                                || (int) subChoose4.charAt(0) > (int) String.valueOf(serialFrom.size()).charAt(0)) {
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
                                            for(int i = 0; i < serialFrom.size(); i++) {
                                                if(i + 1 == Integer.parseInt(subChoose4)) {
                                                    if(serialFrom.get(i).equals("Sửa tên Khoa")) {
                                                        System.out.println("Đã chọn Sửa tên Khoa");

                                                        // Nhập tên Khoa mới
                                                        System.out.print(" --- Nhập tên Khoa mới: ");
                                                        String newName = sc.nextLine();

                                                        // Tạo Khoa mới, dữ liệu như Khoa cũ chỉ đổi tên Khoa
                                                        departmentUpdate = new Department(departmentSearch.getId(), newName,
                                                            departmentSearch.getIdManager(), departmentSearch.getRoom());
                                                        serialFrom.remove(i);

                                                    } else if(serialFrom.get(i).equals("Sửa mã trưởng Khoa")) {
                                                        System.out.println("Đã chọn Sửa mã trưởng Khoa");

                                                        // Mảng chứa các Bác sĩ chưa là trưởng Khoa
                                                        ArrayList<HealthcareWorker> list =
                                                            HealthcareWorkerManager.getInstance().findObjectsByCondition("doctor is not manager department");
                                                        if(list == null) {
                                                            System.out.println(" --- Hiện tại, Bệnh viện không có Bác sĩ nào chưa là trưởng Khoa");
                                                            // Tạo Khoa mới, không thiết lập trưởng Khoa
                                                            departmentUpdate = new Department(departmentSearch.getId(),
                                                                departmentSearch.getName(), null, departmentSearch.getRoom());
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
                                                            while(subChoose5.length() == 1
                                                                    ? (int) subChoose5.charAt(0) < 49
                                                                        || (int) subChoose5.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                                                    : HealthcareWorkerManager.getInstance().findObjectById(subChoose5) == null) {
                                                                System.out.println("----- -----");
                                                                System.out.println("! --- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                                                System.out.print("?! --- Chọn lại: ");
                                                                subChoose5 = sc.nextLine();
                                                            }

                                                            // Biến giữ mã trưởng Khoa đã chọn
                                                            String idHealthcareWorker = (subChoose5.length() == 1
                                                                ? list.get(Integer.parseInt(subChoose5) - 1).getId()
                                                                : HealthcareWorkerManager.getInstance().findObjectById(subChoose5).getId());

                                                            // Tạo Khoa và thiết lập trưởng Khoa
                                                            departmentUpdate = new Department(departmentSearch.getId(),
                                                                departmentSearch.getName(), idHealthcareWorker, departmentSearch.getRoom());

                                                            // Thiết lập là trưởng Khoa cho Bác sĩ đã chọn
                                                            HealthcareWorkerManager.getInstance().findObjectById(idHealthcareWorker).setIsManagerDepartment(true);
                                                        }
                                                        serialFrom.remove(i);

                                                    } else if(serialFrom.get(i).equals("Sửa số phòng")) {
                                                        System.out.println("Đã chọn Sửa số phòng");

                                                        // Nhập số phòng mới
                                                        System.out.print("? --- Nhập số phòng mới: ");
                                                        String newRoom = sc.nextLine();

                                                        // ...
                                                        departmentUpdate = new Department(departmentSearch.getId(),
                                                            departmentSearch.getName(), departmentSearch.getIdManager(), newRoom);
                                                        serialFrom.remove(i);

                                                    } else if(serialFrom.get(i).equals("Tất cả")) {
                                                        System.out.println("Đã chọn Tất cả");
                                                        
                                                        // Nhập tên Khoa mới
                                                        System.out.print(" --- Nhập tên Khoa mới: ");
                                                        String newName = sc.nextLine();
    
                                                        // Chọn mã trưởng Khoa
                                                        // - Biến giữ mã trưởng Khoa
                                                        String newIdHealthcareWorker = null;
                                                        // - Mảng chứa các Bác sĩ chưa là trưởng Khoa
                                                        ArrayList<HealthcareWorker> list =
                                                            HealthcareWorkerManager.getInstance().findObjectsByCondition("doctor is not manager department");
                                                        if(list == null) {
                                                            System.out.println(" --- Hiện tại, Bệnh viện không có Bác sĩ nào chưa là trưởng Khoa");
                                                        } else {
                                                            System.out.println(" --- Chọn trưởng Khoa");
                                                            int subSerial = 1;
                                                            for(HealthcareWorker healthcareWorker : list) {
                                                                System.out.println(subSerial++ + " -- " + healthcareWorker.getId()
                                                                    + " | " + healthcareWorker.getFullname());
                                                            }
                                                            // Cho phép chọn serial hoặc id
                                                            System.out.print("? --- Chọn: ");
                                                            String subChoose5 = sc.nextLine();
                                                            while(subChoose5.length() == 1
                                                                    ? (int) subChoose5.charAt(0) < 49
                                                                        || (int) subChoose5.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                                                    : HealthcareWorkerManager.getInstance().findObjectById(subChoose5) == null) {
                                                                System.out.println("----- -----");
                                                                System.out.println("! --- NHÂN VIÊN Y TẾ KHÔNG HỢP LỆ");
                                                                System.out.print("?! --- Chọn lại: ");
                                                                subChoose5 = sc.nextLine();
                                                            }

                                                            // Biến giữ mã trưởng Khoa đã chọn
                                                            newIdHealthcareWorker = (subChoose5.length() == 1
                                                                ? list.get(Integer.parseInt(subChoose5) - 1).getId()
                                                                : HealthcareWorkerManager.getInstance().findObjectById(subChoose5).getId());

                                                            // Thiết lập là trưởng Khoa cho Bác sĩ đã chọn
                                                            HealthcareWorkerManager.getInstance().findObjectById(newIdHealthcareWorker).setIsManagerDepartment(true);
                                                        }
    
                                                        // Nhập số phòng mới
                                                        System.out.print(" --- Nhập số phòng mới: ");
                                                        String newRoom = sc.nextLine();
    
                                                        // Tạo khoa với những thông tin đã sửa và mã Khoa vẫn giữ nguyên
                                                        departmentUpdate = new Department(departmentSearch.getId(), newName, newIdHealthcareWorker, newRoom);
                                                        serialFrom.clear();

                                                    }

                                                    // Nếu khác null là đã cập nhật thông tin Khoa
                                                    if(departmentUpdate != null) {
                                                        // Sửa lại thông tin của Khoa đã chỉ định
                                                        DepartmentManager.getInstance().update(departmentUpdate);
                                                        // Sửa lại thông tin của Khoa đã tìm kiếm để đồng bộ dữ liệu cho những lần tìm kiếm sau
                                                        departmentSearch = new Department(departmentUpdate);
                                                    }

                                                    // Thông báo thông tin mới của Khoa sau khi sửa lần lượt cho đến hết danh mục hoặc sửa "Tất cả"
                                                    if(serialFrom.size() == 0 || serialFrom.get(0).equals("Tất cả")) {
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
                                    while((subChoose3.length() == 1
                                            ? (int) subChoose3.charAt(0) < 49
                                                || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                            : DepartmentManager.getInstance().findObjectById(subChoose3) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }

                                    // Tìm thông tin của Khoa cần xoá
                                    Department oldDepartment = null;
                                    if(subChoose3.length() == 1) {
                                        oldDepartment =  DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    } else {
                                        oldDepartment = DepartmentManager.getInstance().findObjectById(subChoose3);
                                    }

                                    // Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Đối tượng Bệnh
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getIdDepartment() == null) continue;
                                        if(sick.getIdDepartment().equals(oldDepartment.getId())) {
                                            sick.setIdDepartment(null);
                                        }
                                    }
                                    // - Đối tượng Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        if(healthcareWorker.getIdDepartment() == null) continue;
                                        if(healthcareWorker.getIdDepartment().equals(oldDepartment.getId())) {
                                            healthcareWorker.setIdDepartment(null);
                                            healthcareWorker.setIsManagerDepartment(null);
                                        }
                                    }
                                    // - Đối tượng Bệnh án (tôi chưa nghĩ là Khoa sẽ quản lý hay Bệnh viện sẽ quản lý)

                                    // Xoá Khoa đã tìm
                                    DepartmentManager.getInstance().removeOne(oldDepartment.getId());

                                    // Thông báo thông tin của Khoa đã xoá
                                    System.out.println("! -- Đã xoá một Khoa: " + oldDepartment.getInfo());

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

                                    // Tìm những đối tượng có liên quan đến Khoa để xoá sự liên kết
                                    // - Đối tượng Bệnh
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        sick.setIdDepartment(null);
                                    }
                                    // - Đối tượng Nhân viên Y tế
                                    for(HealthcareWorker healthcareWorker : HealthcareWorkerManager.getInstance().getList()) {
                                        healthcareWorker.setIdDepartment(null);
                                        healthcareWorker.setIsManagerDepartment(null);
                                    }
                                    // - Đối tượng Bệnh án

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

                                    // Tìm kiếm bằng mã Khoa hay tên Khoa đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Khoa hoặc tên Khoa");
                                    System.out.print(" -- Nhập thông tin Khoa cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Department> departmentSearchList = new ArrayList<>();
                                    for(Department department : DepartmentManager.getInstance().getList()) {
                                        if(department.getId().equals(info)
                                                || CharacterFormat.normalCharacterFormat(
                                                        department.getName().toLowerCase()
                                                    ).indexOf(
                                                        CharacterFormat.normalCharacterFormat(
                                                            info.trim().toLowerCase()
                                                        )
                                                    ) >= 0) {
                                            departmentSearchList.add(department);
                                        }
                                    }

                                    // Thông báo kết quả tìm được
                                    if(departmentSearchList.size() == 0) {
                                        System.out.println("! -- Không tìm được thông tin Khoa");
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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Khoa");


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
                        } else if(subChoose1.equals("3")) {
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
                                        System.out.println("Hiện tại Bệnh viện chưa có Khoa, cần tạo ít nhất một Khoa");
                                        continue subLoop2;
                                    }

                                    // Nếu đã có Khoa được tạo
                                    System.out.print(" -- Nhập tên Bệnh: ");
                                    String name = sc.nextLine();
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
                                    while((subChoose3.length() == 1
                                            ? (int) subChoose3.charAt(0) < 49
                                                || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                            : DepartmentManager.getInstance().findObjectById(subChoose3) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- KHOA KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }

                                    // Thêm một bệnh, Sick(name, idDepartment), vì id sẽ tự tạo
                                   Sick newSick = new Sick(name, (subChoose3.length() == 1
                                           ? DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1).getId()
                                           : DepartmentManager.getInstance().findObjectById(subChoose3).getId()
                                       )
                                   );
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
                                    // Cho phép chọn serial - id (chọn 1 hoặc chọn SIxxxxx)
                                    System.out.print("? -- Chọn: ");
                                    String subChoose3 = sc.nextLine();
                                    while((subChoose3.length() == 1
                                            ? (int) subChoose3.charAt(0) < 49
                                                || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                            : SickManager.getInstance().findObjectById(subChoose3) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }
                                    Sick sickSearch = null;
                                    if(subChoose3.length() == 1) {
                                        sickSearch =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    } else {
                                        sickSearch = SickManager.getInstance().findObjectById(subChoose3);
                                    }

                                    // Thông báo đã chọn Bệnh cần sửa thông tin
                                    clearTerminal();
                                    System.out.println("Đã chọn Bệnh có thông tin: " + sickSearch.getInfo());

                                    // Tạo một from lựa chọn
                                    // - Id là cố định không thể sửa
                                    // - Chạy vòng lặp để người dùng có thể lựa chọn nhiều lần
                                    // - Xoá đi lựa chọn trước đó của người dùng
                                    // - Nếu chọn "Tất cả" thì xoá hết
                                    ArrayList<String> serialFrom = new ArrayList<>();
                                    serialFrom.add("Sửa tên Bệnh");
                                    serialFrom.add("Sửa mã Khoa");
                                    serialFrom.add("Tất cả");

                                    // Thông báo Bệnh đã được sửa thông tin mới cho những lần sửa
                                    boolean isUpdated = false;

                                    while(serialFrom.size() >= 1 && !serialFrom.get(0).equals("Tất cả")) {
                                        // Thông báo thông tin mới của Khoa khi đã sửa thông tin bất kỳ trừ "Tất cả"
                                        if(isUpdated) {
                                            isUpdated = false;
                                            clearTerminal();
                                            System.out.println("Thông tin mới của Khoa sau khi sửa: " + sickSearch.getInfo());
                                        }

                                        System.out.println("0 -- Quay lại");
                                        for(int i = 0; i < serialFrom.size(); i++) {
                                            System.out.println((i + 1) + " -- " + serialFrom.get(i));
                                        }
                                        System.out.print("? -- Chọn: ");
                                        String subChoose4 = sc.nextLine();
                                        while(subChoose4.length() > 1 || (int) subChoose4.charAt(0) < 48
                                                || (int) subChoose4.charAt(0) > (int) String.valueOf(serialFrom.size()).charAt(0)) {
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
                                            for(int i = 0; i < serialFrom.size(); i++) {
                                                if(i + 1 == Integer.parseInt(subChoose4)) {
                                                    if(serialFrom.get(i).equals("Sửa tên Bệnh")) {
                                                        clearTerminal();
                                                        System.out.println("Đã chọn Sửa tên Bệnh");
                                                        System.out.print(" --- Nhập tên Bệnh mới: ");
                                                        String newName = sc.nextLine();
                                                        sickUpdate = new Sick(sickSearch.getId(), newName, sickSearch.getIdDepartment());
                                                        serialFrom.remove(i);
                                                    } else if(serialFrom.get(i).equals("Sửa mã Khoa")) {
                                                        clearTerminal();
                                                        System.out.println("Đã chọn Sửa mã Khoa");
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
                                                        while(subChoose5.length() == 1
                                                                ? (int) subChoose5.charAt(0) < 49
                                                                    || (int) subChoose5.charAt(0) > (int) String.valueOf(subSerial).charAt(0)
                                                                : DepartmentManager.getInstance().findObjectById(subChoose5) == null) {
                                                            System.out.println("----- -----");
                                                            System.out.println("! -- LỰA CHỌN KHÔNG HỢP LỆ");
                                                            System.out.print("?! -- Chọn lại: ");
                                                            subChoose5 = sc.nextLine();
                                                        }
                                                        sickUpdate = new Sick(sickSearch.getId(), sickSearch.getName(), (subChoose5.length() == 1
                                                                ? DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId()
                                                                : DepartmentManager.getInstance().findObjectById(subChoose5).getId()
                                                            )
                                                        );
                                                        serialFrom.remove(i);
                                                    } else if(serialFrom.get(i).equals("Tất cả")) {
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
                                                        while(subChoose5.length() == 1
                                                                ? (int) subChoose5.charAt(0) < 49
                                                                    || (int) subChoose5.charAt(0) > (int) String.valueOf(subSerial).charAt(0)
                                                                : DepartmentManager.getInstance().findObjectById(subChoose5) == null) {
                                                            System.out.println("----- -----");
                                                            System.out.println("! --- LỰA CHỌN KHÔNG HỢP LỆ");
                                                            System.out.print("?! --- Chọn lại: ");
                                                            subChoose5 = sc.nextLine();
                                                        }
                                                        sickUpdate = new Sick(sickSearch.getId(), newName, (subChoose5.length() == 1
                                                                ? DepartmentManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose5) - 1).getId()
                                                                : DepartmentManager.getInstance().findObjectById(subChoose5).getId()
                                                            )
                                                        );
                                                        serialFrom.clear();
    
                                                    }

                                                    // Nếu khác null là đã cập nhật thông tin Bệnh
                                                    if(sickUpdate != null) {
                                                        // Sửa lại thông tin cho Bệnh
                                                        SickManager.getInstance().update(sickUpdate);
                                                        // Sửa lại thông tin của Bệnh đã tìm kiếm để đồng bộ dữ liệu cho những lần tìm kiếm sau
                                                        sickSearch = new Sick(sickUpdate);
                                                    }

                                                    // Thông báo khi sửa lần lượt cho đến hết thông tin hoặc sửa "Tất cả"
                                                    if(serialFrom.size() == 0 || serialFrom.get(0).equals("Tất cả")) {
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
                                    while((subChoose3.length() == 1
                                            ? (int) subChoose3.charAt(0) < 49
                                                || (int) subChoose3.charAt(0) > (int) String.valueOf(serial).charAt(0)
                                            : SickManager.getInstance().findObjectById(subChoose3) == null)) {
                                        System.out.println("----- -----");
                                        System.out.println("! -- BỆNH KHÔNG HỢP LỆ");
                                        System.out.print("?! -- Chọn lại: ");
                                        subChoose3 = sc.nextLine();
                                    }
                                    // Xoá Bệnh nếu hợp lệ
                                    Sick sickRemove = null;
                                    if(subChoose3.length() == 1) {
                                        sickRemove =  SickManager.getInstance().findObjectByIndex(Integer.parseInt(subChoose3) - 1);
                                    } else {
                                        sickRemove = SickManager.getInstance().findObjectById(subChoose3);
                                    }
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

                                    // Tìm kiếm bằng mã Bệnh hay tên Bệnh đều được phép
                                    System.out.println("Bạn có thể tìm bằng mã Bệnh hoặc tên Bệnh");
                                    System.out.print(" -- Nhập thông tin Bệnh cần tìm: ");
                                    String info = sc.nextLine();
                                    ArrayList<Sick> sickSearchList = new ArrayList<>();
                                    for(Sick sick : SickManager.getInstance().getList()) {
                                        if(sick.getId().equals(info)
                                                || CharacterFormat.normalCharacterFormat(
                                                        sick.getName().toLowerCase()
                                                    ).indexOf(
                                                        CharacterFormat.normalCharacterFormat(
                                                            info.trim().toLowerCase()
                                                        )
                                                    ) >= 0) {
                                            sickSearchList.add(sick);
                                        }
                                    }

                                    // Thông báo kết quả tìm được
                                    if(sickSearchList.size() == 0) {
                                        System.out.println("! -- Không tìm được thông tin Bệnh");
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

                                } else if(subChoose2.equals("8")) {
                                    clearTerminal();
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh");


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
                        } else if(subChoose1.equals("4")) {
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
                        } else if(subChoose1.equals("5")) {
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
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh nhân");



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
                                    System.out.println("Đã chọn Thêm một Bệnh nhân");
                                    


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
                                    System.out.println("Đã chọn Sửa một Bệnh nhân");



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
                                    System.out.println("Đã chọn Xoá một Bệnh nhân");



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
                                    System.out.println("Đã chọn Xoá một Bệnh nhân");



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
                                    System.out.println("Đã chọn Tìm kiếm Bệnh nhân");



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
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh nhân");



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
                                    System.out.println("Đã chọn Truy vấn dữ liệu Bệnh nhân");



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
                                    System.out.println("Đã chọn Sao lưu dữ liệu Bệnh nhân");



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
                                String subChoose2 = sc.nextLine();
                                while(!subChoose2.equals("0") && !subChoose2.equals("1")
                                    && !subChoose2.equals("2") && !subChoose2.equals("3")
                                    && !subChoose2.equals("4") && !subChoose2.equals("5")
                                    && !subChoose2.equals("6") && !subChoose2.equals("7")) {
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
                        } else if(subChoose1.equals("7")) {
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

                        } else if(subChoose1.equals("8")) {
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
                } else if(AccountManager.getInstance().isUserInHospital(username, password)) {
                    clearTerminal();
                    System.out.println("Là tài khoản người dùng trong bệnh viện");
                    if(AccountManager.getInstance().isUserInHospitalIsHealthcareWorker(username)) {
                        System.out.println("/********** TÀI KHOẢN NHÂN VIÊN Y TẾ **********/");
                        while(true) {

                        }

                    } else if(AccountManager.getInstance().isUserInHospitalIsPatient(username)) {
                        System.out.println("/********** TÀI KHOẢN BỆNH NHÂN **********/");
                        while(true) {
                            
                        }

                    }
                } else if(AccountManager.getInstance().isUserNotInHospital(username, password)) {
                    clearTerminal();
                    System.out.println("Là tài khoản người dùng ngoài bệnh viện");
                    System.out.println("/********** TÀI KHOẢN ĐĂNG KÝ MỚI **********/");
                    while(true) {
                            
                    }

                }
            } else if(mainChoose.equals("2")) {
                clearTerminal();
                System.out.println("Đã chọn Đăng ký");
                System.out.println("/********** ĐĂNG KÝ **********/");
                System.out.print(" - Nhập tên đăng nhập đăng ký : ");
                String newUsername = sc.nextLine();
                System.out.print(" - Nhập mật khẩu đăng ký: ");
                String newPassword = sc.nextLine();
                // Nếu tài khoản đã tồn tại (là không thể đăng ký tài khoản) thì đăng ký lại
                while(AccountManager.getInstance().canRegisterAccount(newUsername)) {
                    System.out.println("---------- ----------");
                    System.out.println("! - TÀI KHOẢN ĐÃ TỒN TẠI");
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
}
