package ua.edu.kordelschool.dao;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public class GenericDao<T> extends GeneralDAO implements CRUD<T> {

    private Class<T> tClass;

    protected GenericDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    @Transactional
    public T create(T object) {
        entityManager.persist(object);

        return object;
    }

    @Override
    @Transactional
    public T read(Long id) {
        T obj = entityManager.find(tClass, id);

        return obj;
    }

    @Override
    @Transactional
    public T update(T object) {

        return entityManager.merge(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        T obj = read(id);

        entityManager.remove(obj);
    }
}
