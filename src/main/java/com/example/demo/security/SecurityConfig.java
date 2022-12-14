package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.UserDetailsServiceImpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/assets/**").permitAll()
				.antMatchers(HttpMethod.GET, "/about").permitAll()
				.antMatchers(HttpMethod.GET, "/contact").permitAll()
				.antMatchers(HttpMethod.GET, "/signup").permitAll()
				.antMatchers(HttpMethod.GET, "/homepage").permitAll()
				.antMatchers(HttpMethod.GET, "/dashboard").permitAll()
				.antMatchers(HttpMethod.GET, "/index").permitAll()
				.antMatchers(HttpMethod.GET, "/thankyou").permitAll()
				.antMatchers(HttpMethod.GET, "/profile").permitAll()
				.antMatchers(HttpMethod.GET, "/verify").permitAll()
				.antMatchers(HttpMethod.GET, "/jobs").permitAll()
				.antMatchers(HttpMethod.GET, "/verify-fail").permitAll()
				.antMatchers(HttpMethod.GET, "/forgot-password").permitAll()
				.antMatchers(HttpMethod.POST, "/forgot-password").permitAll()
				.antMatchers(HttpMethod.GET, "/verify-reset-password").permitAll()
				.antMatchers(HttpMethod.GET, "/reset-password").permitAll()
				.antMatchers(HttpMethod.POST, "/reset-password").permitAll()
				.antMatchers(HttpMethod.POST, "/process_signup").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/signin")
				.loginProcessingUrl("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/signin-error")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/signin")
				.deleteCookies("JSESSIONID")
				.permitAll();
		
	

		
		return http.build();
	}
}
