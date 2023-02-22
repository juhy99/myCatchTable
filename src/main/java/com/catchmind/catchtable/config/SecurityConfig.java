package com.catchmind.catchtable.config;

import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.security.CatchPrincipal;
import com.catchmind.catchtable.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// 스프링 시큐리티 설정 클래스

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests(auth -> auth.anyRequest().permitAll())
                .formLogin()
                .loginPage("/login")            // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/")            // 로그인 성공 후 이동 페이지
                .failureUrl("/login/error")            // 로그인 실패 후 이동 페이지
                .usernameParameter("prHp")            // 아이디 파라미터명 설정
                .passwordParameter("prUserpw")            // 패스워드 파라미터명 설정
                .loginProcessingUrl("/loginOk")            // 로그인 Form Action Url
                //                    .successHandler(loginSuccessHandler())      // 로그인 성공 후 핸들러
                //                    .failureHandler(loginFailureHandler())      // 로그인 실패 후 핸들러
                .and()
                .logout()
                .logoutSuccessUrl("/")      //로그아웃 페이지
                .and()
                .build();

        // 권한을 부여해주지 않으면 어느 페이지를 가든 로그인으로 가기 떄문에 권한부여를 위해 작성함
    }
    @Bean
    public UserDetailsService userDetailsService(ProfileRepository profileRepository) {    // 유저 정보를 가져오는 인터페이스
        return prHp -> profileRepository.findByPrHp(prHp)
                .map(ProfileDto::from)
                .map(CatchPrincipal::from)                // 인증된 사용자의 계정 데이타를 저장할 레코드 클래스
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - prHp : " + prHp));
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}