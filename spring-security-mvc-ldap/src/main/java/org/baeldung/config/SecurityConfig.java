package org.baeldung.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Configuration - LDAP and HTTP Authorizations.
 */
//@EnableAutoConfiguration
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.ldapAuthentication()
        	.userSearchBase("ou=people")
        	.userSearchFilter("(uid={0})")
        	.groupSearchBase("ou=groups")
        	.groupSearchFilter("member={0}")
        	.contextSource()
        	.root("dc=baeldung,dc=com")
        	.ldif("classpath:users.ldif");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        	//.antMatchers("/", "/home").permitAll().anyRequest()
        	.anyRequest().hasRole("XXXX");
        http.formLogin()
        	.loginPage("/login")
        		.permitAll()
        	.and()
        	.logout()
        	.logoutSuccessUrl("/");
    }

}
