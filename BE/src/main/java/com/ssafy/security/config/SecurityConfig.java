package com.ssafy.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.security.auth.JwtAuthenticationFilter;
import com.ssafy.security.auth.JwtProvider;
import com.ssafy.security.auth.MemberDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JwtProvider jwtProvider;
	private final MemberDetailsService memberDetailsService;
	
	public SecurityConfig(JwtProvider jwtProvider, MemberDetailsService memberDetailsService) {
		this.jwtProvider = jwtProvider;
		this.memberDetailsService = memberDetailsService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/member/join", "/member/login", "/member/join/**", "/member/find-password").permitAll()
			.antMatchers(HttpMethod.GET, "/article/{article_id}", "/article", "/plan/{plan_id}", "/plan", "/attraction", "/attraction/{content-id}", "/notice", "/notice/view/{notice_id}").permitAll()
			.anyRequest().authenticated()
//			 인증 잠깐 멈춰놓음
//			.anyRequest().permitAll()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtProvider, memberDetailsService), UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 출처에 대해 HTTP 통신을 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); //파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 URL 앞에서 구성한 CORS 정책을 적용
        return source;
    }
	
}