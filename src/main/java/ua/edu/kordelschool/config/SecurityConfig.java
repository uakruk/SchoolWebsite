package ua.edu.kordelschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * @author Yaroslav Kruk on 12/8/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    private final static String[] PERMITTED_ALL = {
            "/auth",
            "/auth/**",
            "/activate/**",
            "/404",
            "/forgot/**",
    };

    private final static String[] PERMITTED_AUTHENTICATED = {
            "/dashboard/**"
    };

    private final static String[] PERMITTED_ACTIVE = {
            "/settings"
    };

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers(PERMITTED_ALL).permitAll()
//                .antMatchers(PERMITTED_ACTIVE).hasRole("USER")
//                .antMatchers(PERMITTED_AUTHENTICATED).hasRole("ADMIN") //todo rename array names
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("appLogin")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/404")
//                .accessDeniedHandler(((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendRedirect("/404")))
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(20);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
