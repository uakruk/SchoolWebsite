package ua.edu.kordelschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yaroslav Kruk on 12/28/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginForm() {
        return "fragments/login";
    }
}
