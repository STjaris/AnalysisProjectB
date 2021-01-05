package com.vijfmusketiers.ProjectB.authentication;


import com.vijfmusketiers.ProjectB.authentication.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/route").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/breadcr").access("hasRole('ROLE_USER')")
//                .antMatchers("/adminPage").access("hasRole('ROLE_ADMIN')")
//                .and()
//                .formLogin().loginPage("/loginPage")
//                .defaultSuccessUrl("/homePage")
//                .failureUrl("/loginPage?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/loginPage?logout");
//    }
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(BCryptPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/unity/").permitAll()
                .antMatchers("/unity/*").permitAll()
                .antMatchers("/unity/routenames").permitAll()
                .antMatchers("/unity/routenames/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .failureUrl("/login?error")
//                .loginPage("/login")
                .defaultSuccessUrl("/homeScreen.html")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder());
    }

//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//
//        authenticationMgr.inMemoryAuthentication()
//                .withUser("jduser").password("jdu@123").authorities("ROLE_USER")
//                .and()
//                .withUser("jdadmin").password("jda@123").authorities("ROLE_USER", "ROLE_ADMIN");
//    }
}