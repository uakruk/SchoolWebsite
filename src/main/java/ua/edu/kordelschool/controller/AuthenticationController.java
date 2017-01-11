package ua.edu.kordelschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.edu.kordelschool.dto.LoginDto;
import ua.edu.kordelschool.dto.UserDto;
import ua.edu.kordelschool.entity.User;
import ua.edu.kordelschool.service.UserService;
import ua.edu.kordelschool.utils.ElasticSearchUtil;

/**
 * @author Yaroslav Kruk on 12/28/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Controller
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginForm() {
        ElasticSearchUtil.indexEvent("login", "user is trying to login");
        return "fragments/login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String logIn(LoginDto loginDto) {
//        User user = userService.readByEmail(loginDto.getEmail());
//
//
//    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register() {
        ElasticSearchUtil.indexEvent("register", "user is trying to register");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(UserDto userDto) {
        User user = userService.createUser(userDto);

        return "redirect:/dashboard";
    }
}
