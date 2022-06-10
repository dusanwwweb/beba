package com.dusanweb.beba.security;

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
import org.springframework.web.cors.CorsConfiguration;

//Spring Configuration annotation indicates that the class has @Bean definition methods
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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
    //IN-MEMORY AUTH
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("alexmartin@beba.com")
                .password(passwordEncoder().encode("pass")).roles("ADMIN");
    }

 */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                //.hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
        ;
        http.csrf().disable();  //Must be disabled

    }

    //Always add the ROLE_ while using the hasAuthority() method (e.g hasAuthority("ROLE_CUSTOMER")).
    //While using hasRole(), do not add the ROLE_ prefix as it will be added automatically by Spring security (hasRole("CUSTOMER")).

/*
    //OSNOVNI VID PROVERE SVIH ZAHTEVA
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
        ;
    }

 */


}
