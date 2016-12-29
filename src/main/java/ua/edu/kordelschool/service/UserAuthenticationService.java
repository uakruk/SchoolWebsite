package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class UserAuthenticationService{

    @Autowired
    private UserService userService;

    public Long getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                User userDetails = (User) principal;
                return userService.readByEmail(userDetails.getUsername()).getId();
            }
        }
        return null;
    }

    public ua.edu.kordelschool.entity.User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object prinipal = auth.getPrincipal();
            if (prinipal instanceof User) {
                User userDetails = (User) prinipal;
                return userService.readByEmail(userDetails.getUsername());
            }
        }
        return null;
    }
}

