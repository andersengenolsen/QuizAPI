package springquiz.service;

import java.util.List;

/**
 * Customer Service, providing abstract layer above the DAO-layer.
 *
 * @see springquiz.dao.Dao
 */
public interface Service<T> {

    /**
     * @return all T objects
     */
    List<T> getAll();

    /**
     * @param t object to save
     */
    void save(T t);

    /**
     * @param id id of T
     * @return T with given id
     */
    T get(int id);

    /**
     * @param id to delete
     */
    void delete(int id);

    /**
     * @param T object to delete
     */
    void delete(T T);

    void update(T t);
}
