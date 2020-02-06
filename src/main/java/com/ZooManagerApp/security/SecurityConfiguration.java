package com.ZooManagerApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.naming.AuthenticationException;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.jdbcAuthentication()
                    .dataSource(dataSource) // jesli mamy jakies zewnetrzne db, mozemy je inject za pośrednictwem tej metody
                    .usersByUsernameQuery("select username,password,enabled "
                            + "from users "
                            + "where username = ?")
                    .authoritiesByUsernameQuery("select username, authority "
                            + "from authorities "
                            + "where username = ?")
                    .passwordEncoder(new BCryptPasswordEncoder());
        } catch (AuthenticationException e) {
            throw new AuthenticationException("User not found or incorrect password");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/divisions/**").hasAnyRole("USER","ADMIN")
                .and().formLogin();
    }

}
