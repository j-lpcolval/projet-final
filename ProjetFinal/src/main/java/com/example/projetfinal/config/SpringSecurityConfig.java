package com.example.projetfinal.config;

import com.example.projetfinal.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserPrincipalDetailsService userPrincipalDetailsService;

    public SpringSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private CustomAcessDeniedHandler customAcessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/employes/delete/{id}").hasRole("ADMIN")
                .antMatchers("/tickets/delete/{id}").hasRole("ADMIN")
                .antMatchers("/tickets/moveToArchive{id}").hasRole("ADMIN")
                .antMatchers("/tickets").hasRole("ADMIN")
                .antMatchers("/employes/addemploye").hasRole("ADMIN")
                .antMatchers("/employes").hasRole("ADMIN")
                .anyRequest().authenticated() // config bloc des ressources et urls
                .and()//fin de la configuration des ressources
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(customAcessDeniedHandler)
                .and().csrf().disable();
    }

    @Autowired
    public void WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler)
    {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }


    @Autowired
    public void WebSecurityConfig(CustomAcessDeniedHandler customAcessDeniedHandler)
    {
         this.customAcessDeniedHandler = customAcessDeniedHandler;
    }
}
