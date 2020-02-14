package com.ZooManagerApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.naming.AuthenticationException;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
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
        http.csrf().disable();
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/divisions").hasAnyRole("ADMIN","USER")
                .antMatchers("/home").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

}
