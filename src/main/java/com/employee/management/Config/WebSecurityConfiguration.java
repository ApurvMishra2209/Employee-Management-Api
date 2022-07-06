package com.employee.management.Config;

import com.employee.management.Security.UserDetailsServiceImpl;
import com.employee.management.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtFilter;

    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService);
       auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/v2/api-docs","/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/**").permitAll()
////                 .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "MANAGER")
////                .antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "MANAGER")
////                .antMatchers(HttpMethod.DELETE).hasAnyRole("MANAGER")
////                .antMatchers(HttpMethod.GET, "/api/employee").hasAnyRole("ADMIN", "MANAGER", "USER")
////                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().authenticated()
                       .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

     http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
       http.headers().frameOptions().disable();





    }
}
