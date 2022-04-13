package com.dusanweb.beba.security;

import com.dusanweb.beba.enumeration.RoleType;
import com.dusanweb.beba.model.Role;
import com.dusanweb.beba.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
    @Override
    public void configure(HttpSecurity http) throws Exception {

            Cross-site request forgery

            CSRF attacks can occur when there are sessions and when
            we are using cookies to authenticate session information
            As we are using REST APIs which are stateless by definition
            and as we're using JWT for authorisation we can safely disable this feature
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        //All request which does not match the pattern "/api/auth/**" should be identified

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeRequests()
                //.antMatchers("/").hasAnyAuthority("USER", "ASSISTANT", "ADMIN")
                //.antMatchers("/new").hasAnyAuthority("ADMIN", "ASSISTANT")
                //.antMatchers("/edit/**").hasAnyAuthority("ADMIN","ASSISTANT")
                //.antMatchers("/delete/**").hasAuthority("ADMIN")
                .antMatchers("/").hasAnyAuthority(RoleType.ROLE_ADMIN.getRole(), RoleType.ROLE_ASSISTANT.getRole(), RoleType.ROLE_USER.getRole())
                .antMatchers("/new").hasAnyAuthority(RoleType.ROLE_ADMIN.getRole(), RoleType.ROLE_ASSISTANT.getRole())
                .antMatchers("/edit/**").hasAnyAuthority(RoleType.ROLE_ADMIN.getRole(), RoleType.ROLE_ASSISTANT.getRole())
                .antMatchers("/delete/**").hasAuthority(RoleType.ROLE_ADMIN.getRole())
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")


         */
        /*
        Cross-site request forgery

        CSRF attacks can occur when there are sessions and when
        we are using cookies to authenticate session information
        As we are using REST APIs which are stateless by definition
        and as we're using JWT for authorisation we can safely disable this feature

         */
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/api/auth/**")
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        //All request which does not match the pattern "/api/auth/**" should be identified

        //http.authorizeRequests().antMatchers("/").permitAll(); //DISABLING SPRING SECURITY
        ;
    }

}
