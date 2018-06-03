package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup", "/about","/login").permitAll().anyRequest()
		.authenticated().and().formLogin().loginPage("/login")//覆盖自带的login页面
				.and().httpBasic()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}