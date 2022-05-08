package aloha.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration				// 이 클래스를 스프링 설정 빈으로 등록
@EnableWebSecurity			// 이 클래스에 스프링 시큐리티 기능 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//configure안에 웹 서비스가 정의 됨.
		// 인가 
		// authorizeRequests() 			: URL 요청에 대한  접근권한 설정
		// permitAll()					: 모두에게 접근 허용
		// hasRole(), hasAnyRoles()		: 특정권한을 가진 사용자만 허용
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
//				.antMatchers("/guest/**").permitAll()
//				.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
//				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
			.anyRequest().authenticated();
		
		// 로그인 설정
		// - default 로그인 화면 URL 		: /login
		// - custom  로그인 화면 URL  		: /auth/loginForm
		// - custom  로그인 처리 URL 		: /auth/login
		// - custom  로그인 에러 화면 URL		: /auth/loginError
		http.formLogin()
			.loginPage("/auth/loginForm")	//로그인 페이지
			.loginProcessingUrl("/auth/login")	//로그인 처리하는 페이지
			.failureUrl("/auth/loginError")			//로그인 시 에러나면 연결된 url은 경로를 이렇게 지정하고 
			.usernameParameter("username")		//여기엔 id를 넘겨줄 파라미터를 이름을 뭐라 할 건지(username=id)
			.passwordParameter("password")			//password넘겨주는게 파라미터
			//id는 username, 패스워드는 password로 넘기겠다. 그리고 html에 매칭시켜줘야함.
			.permitAll();
		
		// 로그아웃 설정
		// - default 로그아웃 화면 URL 		 : /logout
		// - custom  로그아웃 요청 URL 		 : /auth/logout
		// - default 로그아웃 성공 후 이동 URL  : /[로그인경로]
		// - custom  로그아웃 성공 후 이동 RUL  : /
		// - invalidateHttpSession(true) : 로그아웃 시, 세션을 유효하지 않도록 지정 ( 세션에 등록된 로그인 정보를 삭제하시 위해서 )
		http.logout()
			.logoutUrl("/auth/logout")			
			.logoutSuccessUrl("/")			//로그아웃 성공적으로 되면 메인으로
			.invalidateHttpSession(true)	
			.permitAll()			//누구나 다 요청 가능
			;
		
		//SSL 를 사용하지 않으면 true 사용
//		http.csrf().disable();
		
	}
	
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// inMemory 방식으로 인증 사용자 등록
		// ID : user   /  pw : 1234	/ 권한 : USER
		// ID :	admin  /  pw : 1234	/ 권한 : ADMIN
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	 */
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// jdbc 방식으로 인증 
		// 인증처리를 위한 쿼리 :  아이디 / 비밀번호 / 사용가능여부
		String query1 = " SELECT user_id as username, user_pw as password, enabled "
					 + " FROM aloha.member "
					 + " WHERE user_id = ? ";
		// 인가처리를 위한 쿼리 :  아이디 / 권한
		String query2 = " SELECT user_id as username, 'ROLE_USER' "
					  + " FROM aloha.member "
					  + " WHERE user_id = ? ";
				

		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(query1)
			.authoritiesByUsernameQuery(query2)
			.passwordEncoder(new BCryptPasswordEncoder());
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}


















