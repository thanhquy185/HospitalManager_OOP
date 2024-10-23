package Hospital;

import java.util.Scanner;

public class App {
    // Properties

    // Methods
    public static App getInstance() {
        return new App();
    }
    public void init() {
    }
    public void run() {
        // Scanner
        System.out.println(123);
        Scanner sc = new Scanner(System.in);
        String mainChoose = "1";
        mainLoop:
        while(true) {
            System.out.println("/********** ĐĂNG NHẬP - ĐĂNG KÝ **********/");
            System.out.println("0 - Kết thúc");
            System.out.println("1 - Đăng nhập");
            System.out.println("2 - Đăng ký");
            System.out.print("? - Chọn: ");
            mainChoose = sc.nextLine();
            while(!mainChoose.equals("0")
                && !mainChoose.equals("1")
                && !mainChoose.equals("2")) {
                System.out.print("\n\n\n");
                System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                System.out.print("?! - Chọn lại: ");
                mainChoose = sc.nextLine();
            }
            if(mainChoose.equals("0")) {
                System.out.println("Đã chọn Kết thúc");
                break mainLoop;
            } else if(mainChoose.equals("1")) {
                System.out.println("Đã chọn Đăng nhập");
                System.out.print("\n\n\n\n\n");
                System.out.println("/********** ĐĂNG NHẬP **********/");
                System.out.print(" - Tên đăng nhập: ");
                String username = sc.nextLine();
                System.out.print(" - Mật khẩu: ");
                String password = sc.nextLine();
                System.out.println(username + password);
                // Nếu tài khoản không hợp lệ (không tồn tại, nhập sai,...) thì nhập lại
                while(!AccountManager.getInstance().canAccessAccount(username, password)) {
                    System.out.println("---------- ----------");
                    System.out.println("! - TÀI KHOẢN KHÔNG HỢP LỆ");
                    System.out.print(" -! Nhập lại tên đăng nhập: ");
                    username = sc.nextLine();
                    System.out.print(" -! Nhập lại mật khẩu: ");
                    password = sc.nextLine();
                }
                // Nếu tài khoản hợp lệ
                if(AccountManager.getInstance().isAdmin(username, password)) {
                    System.out.println("Là tài khoản admin");
                    System.out.print("\n\n\n\n\n");
                    subLoop1:
                    while(true) {
                        System.out.println("/********** TÀI KHOẢN QUẢN LÝ **********/");
                        System.out.println("0 - Kết thúc");
                        System.out.println("1 - Quay lại");
                        System.out.println("2 - Quản lý Nhân viên Y tế");
                        System.out.println("3 - Quản lý Bệnh nhân");
                        System.out.println("4 - Quản lý Bệnh án");
                        System.out.print("? - Chọn: ");
                        String subChoose1 = sc.nextLine();
                        while(!subChoose1.equals("0") && !subChoose1.equals("1")
                            && !subChoose1.equals("2") && !subChoose1.equals("3")
                            && !subChoose1.equals("4")) {
                            System.out.println("---------- ----------");
                            System.out.println("! - LỰA CHỌN KHÔNG HỢP LỆ");
                            System.out.print("?! - Chọn lại: ");
                            subChoose1 = sc.nextLine();
                        }
                        if(subChoose1.equals("0")) {
                            System.out.println("Đã chọn Kết thúc");
                            System.out.print("\n\n\n\n\n");
                            break mainLoop;
                        } else if(subChoose1.equals("1")) {
                            System.out.println("Đã chọn Quay lại");
                            System.out.print("\n\n\n\n\n");
                            continue mainLoop;
                        } else if(subChoose1.equals("2")) {
                            System.out.println("Đã chọn Quản lý Nhân viên Y tế");
                            System.out.print("\n\n\n\n\n");
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
                                    System.out.print("\n\n\n\n\n");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    System.out.println("Đã chọn Quay lại");
                                    System.out.print("\n\n\n\n\n");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    System.out.println("Đã chọn Danh sách thông tin các Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("3")) {
                                    System.out.println("Đã chọn Thêm một Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("4")) {
                                    System.out.println("Đã chọn Sửa một Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("5")) {
                                    System.out.println("Đã chọn Xoá một Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("6")) {
                                    System.out.println("Đã chọn Tìm kiếm Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("7")) {
                                    System.out.println("Đã chọn Sắp xếp danh sách các Nhân viên Y tế");

                                    System.out.print("\n\n\n\n\n");
                                }
                            }
                        } else if(subChoose1.equals("3")) {
                            System.out.println("Đã chọn Bệnh nhân");
                            System.out.print("\n\n\n\n\n");
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
                                    System.out.print("\n\n\n\n\n");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    System.out.println("Đã chọn Quay lại");
                                    System.out.print("\n\n\n\n\n");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("3")) {
                                    System.out.println("Đã chọn Thêm một Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("4")) {
                                    System.out.println("Đã chọn Sửa một Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("5")) {
                                    System.out.println("Đã chọn Xoá một Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("6")) {
                                    System.out.println("Đã chọn Tìm kiếm Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("7")) {
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh nhân");

                                    System.out.print("\n\n\n\n\n");
                                }
                            }
                        } else if(subChoose1.equals("4")) {
                            System.out.println("Đã chọn Bệnh án");
                            System.out.print("\n\n\n\n\n");
                            subLoop2:
                            while(true) {
                                System.out.println("/********** QUẢN LÝ BỆNH ÁN **********/");
                                System.out.println("0 - Kết thúc");
                                System.out.println("1 - Quay lại");
                                System.out.println("2 - Danh sách thông tin các Bệnh án");
                                System.out.println("3 - Thêm một Bệnh án");
                                System.out.println("4 - Sửa một Bệnh án");
                                System.out.println("5 - Xoá một Bệnh án");
                                System.out.println("6 - Tìm kiếm Bệnh án");
                                System.out.println("7 - Sắp xếp danh sách các Bệnh án");
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
                                    System.out.print("\n\n\n\n\n");
                                    break mainLoop;
                                } else if(subChoose2.equals("1")) {
                                    System.out.println("Đã chọn Quay lại");
                                    System.out.print("\n\n\n\n\n");
                                    continue subLoop1;
                                } else if(subChoose2.equals("2")) {
                                    System.out.println("Đã chọn Danh sách thông tin các Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("3")) {
                                    System.out.println("Đã chọn Thêm một Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("4")) {
                                    System.out.println("Đã chọn Sửa một Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("5")) {
                                    System.out.println("Đã chọn Xoá một Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("6")) {
                                    System.out.println("Đã chọn Tìm kiếm Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                } else if(subChoose2.equals("7")) {
                                    System.out.println("Đã chọn Sắp xếp danh sách các Bệnh án");

                                    System.out.print("\n\n\n\n\n");
                                }
                            }
                        }
                    }
                } else if(AccountManager.getInstance().isUserInHospital(username, password)) {
                    System.out.println("Là tài khoản người dùng trong bệnh viện");
                    System.out.print("\n\n\n\n\n");
                    if(AccountManager.getInstance().isUserInHospitalIsPatient(username)) {
                        System.out.println("/********** TÀI KHOẢN BỆNH NHÂN **********/");
                    } else if(AccountManager.getInstance().isUserInHospitalIsHealthcareWorker(username)) {
                        System.out.println("/********** TÀI KHOẢN NHÂN VIÊN Y TẾ **********/");
                    }
                } else if(AccountManager.getInstance().isUserNotInHospital(username, password)) {
                    System.out.println("Là tài khoản người dùng ngoài bệnh viện");
                    System.out.print("\n\n\n\n\n");
                    System.out.println("/********** TÀI KHOẢN ĐĂNG KÝ MỚI **********/");
                }
            } else if(mainChoose.equals("2")) {
                System.out.println("Đã chọn Đăng ký");
                System.out.print("\n\n\n\n\n");
                System.out.println("/********** ĐĂNG KÝ **********/");
                System.out.print(" - Nhập tên đăng nhập đăng ký : ");
                String newUsername = sc.nextLine();
                System.out.print(" - Nhập mật khẩu đăng ký: ");
                String newPassword = sc.nextLine();
                // Nếu tài khoản đã tồn tại (là có thể truy cập tài khoản) thì nhập lại
                while(AccountManager.getInstance().canRegisterAccount(newUsername)) {
                    System.out.println("---------- ----------");
                    System.out.println("! - TÀI KHOẢN ĐÃ TỒN TẠI");
                    System.out.print(" -! Nhập lại tên đăng nhập: ");
                    newUsername = sc.nextLine();
                    System.out.print(" -! Nhập lại mật khẩu: ");
                    newPassword = sc.nextLine();
                }
                System.out.println("Đã đăng ký tài khoản mới - " + newUsername);
                AccountManager.getInstance().add(new Account(newUsername, newPassword));
                // System.out.println(AccountManager.getInstance().getList().get(1).getUsername());
                // System.out.println(AccountManager.getInstance().getList().get(1).getPassword());
                // System.out.println(AccountManager.getInstance().getNumbers());
                System.out.print("\n\n\n\n\n");
            }
        }
    }
}
