package com.dusanweb.beba.security;

import com.dusanweb.beba.enumeration.RoleType;
import com.dusanweb.beba.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //Spring Configuration annotation indicates that the class has @Bean definition methods
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

/*
    Cross-site request forgery

    CSRF attacks can occur when there are sessions and when
    we are using cookies to authenticate session information
    As we are using REST APIs which are stateless by definition
    and as we're using JWT for authorisation we can safely disable this feature
*/

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    //Whenever we're injecting (autowiring) this bean we get an instance of BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /*
    //RAMESH
        @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("alexmartin@beba.com").password("pass").roles(RoleType.ROLE_ADMIN.getRole());
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/api/**")
                //.hasAuthority(RoleType.ROLE_ADMIN.getRole())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();
    }
}
