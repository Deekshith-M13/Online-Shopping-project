package com.project.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){

        // we dont want tokens when we are accessing css or static data since eureka/** is end point for pringing static style to the website(If doubt check the application.properties).
        //But any other calls must be authenticated.
        return serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable())
                        .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtSpec -> {}))
                .build();



    }
}
