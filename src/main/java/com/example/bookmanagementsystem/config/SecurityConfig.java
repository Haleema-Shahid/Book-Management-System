package com.example.bookmanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().
                requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,  HandlerMappingIntrospector introspector) throws Exception {
        //http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        //http.httpBasic(Customizer.withDefaults());
        //http.csrf(AbstractHttpConfigurer::disable);
        //http.cors(AbstractHttpConfigurer::disable);
        http.headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.authorizeHttpRequests(auth ->
        {
            auth
                    .requestMatchers(new AntPathRequestMatcher("/users/signup")).permitAll()
                    //.requestMatchers(new AntPathRequestMatcher("/h2-console")).permitAll()


            ;
            //auth.requestMatchers("/signup").permitAll();
            //auth.anyRequest().authenticated();
        });
//                .requestMatchers("/swagger-ui-custom.html").permitAll()
//                .requestMatchers("/swagger-ui/index.html").permitAll()
//                .requestMatchers("/swagger-ui/**").permitAll()
//                .requestMatchers("/v3/api-docs/**").permitAll());
//        http.httpBasic(Customizer.withDefaults());
       // http.csrf(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        //http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers(toH2Console()));
        http.addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

