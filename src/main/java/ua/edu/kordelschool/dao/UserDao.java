package ua.edu.kordelschool.dao;

import ua.edu.kordelschool.entity.User;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public interface UserDao extends CRUDA<User> {

    User readByEmail(String email);
}
