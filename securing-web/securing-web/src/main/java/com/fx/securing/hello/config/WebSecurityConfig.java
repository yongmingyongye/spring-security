package com.fx.securing.hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //启用Spring Security的Web安全支持，并提供Spring MVC集成
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//configure(HttpSecurity)方法定义了哪些URL路径应该被保护，哪些不应该
	//具体为“/”和“/ home”路径被配置为不需要任何身份验证。所有其他路径必须经过身份验证。
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")//当用户成功登录时，它们将被重定向到先前请求的需要身份认证的页面。有一个由 loginPage()指定的自定义“/登录”页面，每个人都可以查看它。
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	//对于configureGlobal(AuthenticationManagerBuilder) 方法，它将单个用户设置在内存中。该用户的用户名为“user”，密码为“password”，角色为“USER”。
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth
			.inMemoryAuthentication()
				.passwordEncoder(new BCryptPasswordEncoder())//实现PasswordEncorder
				.withUser("user").password(passwordEncoder.encode("password")).roles("USER");
	}
	

}
