package ua.edu.kordelschool.dao;

import ua.edu.kordelschool.entity.MethodGroup;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public interface MethodGroupDao extends CRUD<MethodGroup> {

    List<MethodGroup> getAllMethodGroups();
}
