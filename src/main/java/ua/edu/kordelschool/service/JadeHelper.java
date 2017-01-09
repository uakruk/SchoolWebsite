package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.entity.User;
import ua.edu.kordelschool.entity.UserRole;

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

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    public boolean isAdmin() {
        return isSomebody(UserRole.ADMIN);
    }

    public boolean isTeacher() {
        return isSomebody(UserRole.TEACHER);
    }

    public boolean isStudent() {
        return isSomebody(UserRole.STUDENT);
    }

    public boolean isAuthenticated() {
        return userAuthenticationService.getLoggedInUser() != null;
    }

    private boolean isSomebody(UserRole role) {
        User user = userAuthenticationService.getLoggedInUser();
        if (user != null) {
            return user.getRoles().contains(role);
        }
        return false;
    }
}
