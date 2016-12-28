package ua.edu.kordelschool.dao;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public interface CRUDA<T> extends CRUD<T> {

    List<T> getAll();

    List<T> getAllByField(String fieldName, Object fieldValue);
}
