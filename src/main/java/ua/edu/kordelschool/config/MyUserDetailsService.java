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

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String user = "admin";
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(user);
        return createUser(user, authorities);
    }

    @SuppressWarnings("unchecked")
    public UserDetails userToUserDetails(String user) {

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(user);
        return createUser(user, authorities);
    }

    public void signin(String user) {

        if (user != null) {

            UserDetails userDetails = userToUserDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private User createUser(String user, List<GrantedAuthority> authorities) {

        return new User("admin", "admin", authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String user) {
        String[] roles = {"admin"};
        return AuthorityUtils.createAuthorityList(roles);
    }
}
