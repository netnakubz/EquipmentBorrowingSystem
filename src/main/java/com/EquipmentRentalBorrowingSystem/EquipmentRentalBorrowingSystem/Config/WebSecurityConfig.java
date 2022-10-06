package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    ObjectMapper objectMapper;
    public WebSecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            Map<String, Object> errorObject = new HashMap<String, Object>();
            int errorCode = 401;
            Date date =new Date();
            errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
            errorObject.put("error", HttpStatus.UNAUTHORIZED);
            errorObject.put("code", errorCode);
//            errorObject.put("timestamp", new Timestamp(date.getTime()));
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(errorCode);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
            System.out.println(errorObject);
        };
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/api/v1/**").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http
//                .oauth2Login()
//                .authorizationEndpoint()
//                .authorizationRequestResolver(new CustomAuthorizationRequestResolver(this.clientRegistrationRepository))
//                .and()
//                .and()
//                .rememberMe()
//                .and().authorizeRequests().antMatchers("/api/v1/**")
//                .fullyAuthenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2ResourceServer().jwt().and().and().cors().and().csrf().disable();

        http

                .authorizeRequests()
                .antMatchers("/*")
                .fullyAuthenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer()
                .jwt()
                .and()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();

    }
}
