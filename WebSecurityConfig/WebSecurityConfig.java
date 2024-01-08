package com.smm.StudentManagement001.WebSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.servlet.AuthorizeHttpRequestsDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final String authenticateSQL = "SELECT username,password,enabled FROM account WHERE username = ?";
	private final String authorizeSQL = "SELECT username,authority FROM authorities WHERE username = ?";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(jdbcTemplate.getDataSource())
		.usersByUsernameQuery(authenticateSQL)
		.authoritiesByUsernameQuery(authorizeSQL)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	 @Bean
	   protected BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
   
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf)-> csrf.disable())
		.authorizeHttpRequests((authz)-> authz
		.antMatchers("/index").hasRole("ADMIN")
		.antMatchers("/css/**","/img/**","/js/**","/singUpButton","/signUp","/login","/customError").permitAll()
		.antMatchers("/login").hasAnyAuthority("USER","ADMIN")
		.anyRequest().authenticated()
		)
		
		.formLogin((form)->form
		.loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.loginProcessingUrl("/doLogin")
		.defaultSuccessUrl("/", true)
		.failureUrl("/login?error = true")
		.permitAll()
		)
//		.failureHandler(new AuthenticationFailureHandler() {
//			
//			@Override
//			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException exception) throws IOException, ServletException {
//				
//				
//			}
//		})
			
	
	
		.logout((logout) -> logout
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
        ;
//		.logoutSuccessHandler(new LogoutSuccessHandler() {
//			
//			@Override
//			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//					throws IOException, ServletException {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		return http.build();

			

			
}
}
		
	