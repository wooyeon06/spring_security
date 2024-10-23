package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/test/**").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated());

        http.formLogin((auth)-> auth
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")
            .permitAll()
            
            );

        //Http Basic 인증 방식은 아이디와 비밀번호를 Base64 방식으로 인코딩한 뒤 HTTP 인증 헤더에 부착하여 서버측으로 요청을 보내는 방식이다.
        //username:password > dXNlcm5hbWU6cGFzc3dvcmQ=
        /**
        GET /protected/resource HTTP/1.1
        Host: example.com
        Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
         */
        http.httpBasic(Customizer.withDefaults());

        http
            .sessionManagement((session) -> session
                .sessionFixation((sessionFixation) -> sessionFixation
                    .newSession()
                )
            );
    

        //http.csrf((auth)-> auth.disable());

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //inMemory방식
    /*@Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("user1")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }*/
}