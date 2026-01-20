package com.example.apigateway.security;


import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //first disable the csrf because in jwt enabled apps, csrf is not needed
        //csrf is when we login some app and then we go to some fishy website then an attacker
        //sends a fake request from our side that is csrf
        //CSRF is when a website tricks your browser into doing something on another website without your permission.
        http.csrf(csrf->csrf.disable())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //authorization rules
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated())
                //jwt validation
                .oauth2ResourceServer(oath2->oath2.jwt(jwt->{}));
        return http.build();
        //first always jwt validation is done if it fails then it checks in authorization rules if its permitted
        //if yes then forward the request to that service if not then 401 error, if it passes the jwt
        //then it again goes to authrorization rules , it sees anyrequest.authenticated which returns true
        //and then it forwards the request to that service.
    }
}
