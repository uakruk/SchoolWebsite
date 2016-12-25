package ua.edu.kordelschool.dao;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public interface CRUD<T> {

    T create(T object);

    T read(Long id);

    T update(T object);

    void delete(Long id);
}
