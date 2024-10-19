package Common;

import java.util.ArrayList;
public interface CRUD<T> {
    //Methods
    void add(T obj);

    void update(T obj);

    void removeOne(T obj);

    void removeAll();

    T find(T obj);

    T findOneByCondition(String condition);

    ArrayList<T> findAllByCondition(String condition);

    void sort();
}
