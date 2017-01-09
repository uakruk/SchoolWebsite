package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.UserDao;
import ua.edu.kordelschool.dto.UserDto;
import ua.edu.kordelschool.entity.Student;
import ua.edu.kordelschool.entity.Teacher;
import ua.edu.kordelschool.entity.User;
import ua.edu.kordelschool.entity.UserRole;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    public User createUser(UserDto userDto) {
        User user = null;

        switch (userDto.getRole()) {
            case "ADMIN":
                user = new User();
                break;
            case "STUDENT":
                user = new Student();
                break;
            case "TEACHER":
                user = new Teacher();
                break;
            default:
                user = new Student();
        }

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.valueOf(userDto.getRole()));

        user.setRoles(roles);

        User response = userDao.create(user);
        return response;
    }

    public User readByEmail(String email) {

        return userDao.readByEmail(email);
    }

    public User getById(Long id) {
        return userDao.read(id);
    }

    public User updateUser(User user) {
        return userDao.update(user);
    }

    public void removeuser(Long id) {
        userDao.delete(id);
    }

}
