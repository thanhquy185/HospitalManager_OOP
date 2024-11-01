package Common;

import java.util.ArrayList;

public interface CRUD<T> {
    //Methods
    // - CRUD (Thao tác với các đối tượng trong lớp quản lý)
    // -- In danh sách các đối tượng trong lớp quản lý
    void show();

    // -- Lấy thông tin của một đối tượng thông qua vị trị (Chuyển thông tin của đối tượng sang chuỗi)
    String getInfoByIndex(int index);

    // -- Lấy thông tin của một đối tượng thông qua id (Chuyển thông tin của đối tượng sang chuỗi)
    String getInfoById(String id);

     // -- Tìm kiếm vị trí của đối tượng thông qua id (giúp xử lý các hàm set(), get(), remove() trong ArrayList)
    int findIndexById(String id);

    // -- Tìm kiếm thông tin một đối tượng thông qua vị trí (đã tồn tại)
    T findObjectByIndex(int index);

    // -- Tìm kiếm thông tin một đối tượng thông qua id (đã tồn tại)
    T findObjectById(String id);

    // -- Thêm một đối tượng
    void add(T obj);

    // -- Cập nhật một đối tượng (đã tồn tại)
    void update(T obj);

    // -- Xoá một đối tượng (đã tồn tại)
    void removeOne(String id);

    // -- Xoá tất cả đối tượng
    void removeAll();

    // -- Sắp xếp danh sách quản lý theo một lựa chọn
    void sort(String condition);

    // -- Truy xuất dữ liệu từ file
    void loadFromFile();

    // -- Sao lưu dữ liệu lên file
    void saveToFile();
}
