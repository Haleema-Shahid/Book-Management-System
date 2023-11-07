package com.springboot.bookmanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().
                requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"));
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
                    .requestMatchers(new AntPathRequestMatcher("/h2-console")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/users/login")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/authors")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/authors/books/{id}")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/authors/{id}")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/books/{id}")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/books/find/title/{title}")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/books")).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/swagger-ui-custom.html")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll();

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

