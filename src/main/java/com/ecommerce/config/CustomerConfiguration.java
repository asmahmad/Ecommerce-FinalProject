package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("customer")
public class CustomerConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomerServiceConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // Allow access to all URLs
            .antMatchers("/*").permitAll()
            // Allow access to URLs starting with "/customer/*" only for users with "CUSTOMER" authority
            .antMatchers("/customer/*").hasAuthority("CUSTOMER")
            .and()
            .formLogin()
            // Specify the custom login page URL
            .loginPage("/login")
            // Specify the URL where the login form should be submitted
            .loginProcessingUrl("/do-login")
            // Specify the URL to redirect to after successful login
            .defaultSuccessUrl("/index")
            .and()
            .logout()
            // Invalidate the HTTP session on logout
            .invalidateHttpSession(true)
            // Clear the authentication on logout
            .clearAuthentication(true)
            // Specify the URL to match for logout request
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            // Specify the URL to redirect to after successful logout
            .logoutSuccessUrl("/login?logout")
            // Allow access to the logout URL
            .permitAll();
    }

}
