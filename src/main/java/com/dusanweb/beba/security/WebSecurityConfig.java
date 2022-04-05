package com.dusanweb.beba.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //Spring Configuration annotation indicates that the class has @Bean definition methods
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        /*
            Cross-site request forgery

            CSRF attacks can occur when there are sessions and when
            we are using cookies to authenticate session information
            As we are using REST APIs which are stateless by definition
            and as we're using JWT for authorisation we can safely disable this feature
         */
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        /*
            All request which does not match the pattern "/api/auth/**" should be identified
         */
    }

    //Whenever we're injecting (autowiring) this bean we get an instance of BCrypt
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
