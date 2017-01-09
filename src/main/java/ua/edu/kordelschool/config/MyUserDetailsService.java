package ua.edu.kordelschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.service.UserService;

import java.util.Collection;
import java.util.List;

/**
 * @author Yaroslav Kruk on 12/8/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ua.edu.kordelschool.entity.User user = userService.readByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(user);
        return createUser(user, authorities);
    }

    @SuppressWarnings("unchecked")
    public UserDetails userToUserDetails(ua.edu.kordelschool.entity.User user) {

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(user);
        return createUser(user, authorities);
    }

    public void signin(ua.edu.kordelschool.entity.User user) {

        if (user != null) {

            UserDetails userDetails = userToUserDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private User createUser(ua.edu.kordelschool.entity.User user, List<GrantedAuthority> authorities) {

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(ua.edu.kordelschool.entity.User user) {
        String[] roles = user.getRoles().stream().map(role -> role.name()).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(roles);
    }
}
