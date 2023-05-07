package com.PogressGroup.pgprojet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.PogressGroup.pgprojet.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private UserService userService;

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(
						(authorize) -> authorize.requestMatchers("/registration**").permitAll()
						.requestMatchers("/home/**").authenticated()
						.requestMatchers("/").authenticated()
						.requestMatchers("/page/**").authenticated()
						.requestMatchers("/showNewEmployeeForm/**").authenticated()
						.requestMatchers("/saveEmployee/**").authenticated()
						.requestMatchers("/showFormForUpdate/**").authenticated()
						.requestMatchers("/deleteEmployee/**").authenticated()
						.requestMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
						.requestMatchers("/js/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/img/**").permitAll()
				 )
				.formLogin(form -> form.loginPage("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
				.successHandler((request, response, authentication) -> {
	                response.sendRedirect("/home");
	            })
				.permitAll())
				.logout(logout -> logout
				.invalidateHttpSession(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
	
				);
		
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}


}
