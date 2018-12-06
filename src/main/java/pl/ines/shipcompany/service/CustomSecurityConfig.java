package pl.ines.shipcompany.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .formLogin()
                .and()
                .csrf().disable();

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //super.configure(auth);
//        UserDetails user=User.withUsername("user2").password("pass2").roles("USER").build();
//        auth.inMemoryAuthentication().withUser(user);
//    }
}
