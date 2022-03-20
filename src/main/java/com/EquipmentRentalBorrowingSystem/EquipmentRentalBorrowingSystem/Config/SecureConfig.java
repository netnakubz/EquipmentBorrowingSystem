package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ObjectMapper objectMapper;
    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 AuthenticationException e) throws IOException, ServletException {
                Map<String, Object> errorObject = new HashMap<String, Object>();
                int errorCode = 401;
                errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
                errorObject.put("error", HttpStatus.UNAUTHORIZED);
                errorObject.put("code", errorCode);
                errorObject.put("timestamp", new Timestamp(new Date().getTime()));
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.setStatus(errorCode);
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
            }
        };
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/temp").permitAll()
                .anyRequest()
                .authenticated();

        http
                .oauth2ResourceServer()
                .jwt();
    }


}
