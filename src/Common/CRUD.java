package Common;

public interface CRUD<T> {
    // CRUD (Thao tác với các đối tượng trong lớp quản lý)
    // - Tìm kiếm thông tin một đối tượng thông qua vị trí (đã tồn tại)
    T findObjectByIndex(int index);

    // - Tìm kiếm thông tin một đối tượng thông qua id (đã tồn tại)
    T findObjectById(String id);

    // - Lấy thông tin của một đối tượng thông qua vị trị (Chuyển thông tin của đối tượng sang chuỗi)
    String getInfoByIndex(int index);

    // - Lấy thông tin của một đối tượng thông qua id (Chuyển thông tin của đối tượng sang chuỗi)
    String getInfoById(String id);

    // - In danh sách các đối tượng trong lớp quản lý
    void show();

    // - Thêm một đối tượng
    void add(T obj);

    // - Cập nhật một đối tượng (đã tồn tại)
    void update(T obj, int choice);

    // - Xoá một đối tượng (đã tồn tại)
    void remove(String id);
    
    // - Tìm kiếm thông tin của một đối tượng trong danh sách
    void find();

    // - Sắp xếp danh sách quản lý theo một lựa chọn
    void sort(String condition);

    // - Truy xuất dữ liệu từ file
    void loadFromFile();

    // - Sao lưu dữ liệu lên file
    void saveToFile();
}
