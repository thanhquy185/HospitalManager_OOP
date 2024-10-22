package Common;

import java.util.ArrayList;
public interface CRUD<T> {
    //Methods
    void add(T obj);

    void update(T obj);

    void removeOne(String id);

    void removeAll();

    int findIndexById(String id);

    T find(String id);

    T findOneByCondition(String condition);

    ArrayList<T> findAllByCondition(String condition);

    void sort();
}
