package ua.edu.kordelschool.service;

import org.springframework.stereotype.Service;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
@com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper("sec")
public class JadeHelper {

    public boolean isAdmin() {
        return true;
    }

    public boolean isTeacher() {
        return true;
    }

    public boolean isStudent() {
        return false;
    }

    public boolean isAuthenticated() {
        return false;
    }
}
