package com.pharmacy.optican.demo.config;

import com.pharmacy.optican.demo.security.SecurityProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:static/property/security.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin().loginPage(sp().getLoginPage())
                .defaultSuccessUrl(sp().getUserPage(), true)
                .and()
                .authorizeRequests()
                .mvcMatchers(sp().getLoginPage(), sp().getRegistrationPage()).anonymous()
                .mvcMatchers("/", sp().getCssPath(), sp().getMainPage(), sp().getImgPath(), sp().getJsPath()).permitAll()
                .anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityProperty sp() {
        return new SecurityProperty();
    }
}
