package aloha.auth;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	protected void configure(HttpSecurity http) throws Exception {
		//url에 대한 허용 여부 설정
		
		//configure안에 웹 서비스가 정의 됨.
		http.authorizeRequests()
				.antMatchers("/").permitAll()	//루트 경로는 어느 사용자던 접근 허용
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/guest/**").permitAll()		//비회원 페이지
				.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")	//유저나 어드민의 권한잇는 사람만 접근
				.antMatchers("/admin/**").hasRole("ADMIN")		//어드민만 접근 가능.
				.antMatchers("/**").permitAll()		//
			.anyRequest().authenticated();
		
		// 로그인 설정
			
				http.formLogin()
					.loginPage("/auth/loginForm")	//로그인 페이지
					.loginProcessingUrl("/auth/login")	//로그인 처리하는 페이지
					.failureUrl("/auth/loginError")			//로그인 시 에러나면 연결된 url은 경로를 이렇게 지정하고 
					.usernameParameter("username")		//여기엔 id를 넘겨줄 파라미터를 이름을 뭐라 할 건지(username=id)
					.passwordParameter("password")			//password넘겨주는게 파라미터
					.permitAll();
				
				
				// - invalidateHttpSession(true) : 로그아웃 시, 세션을 유효하지 않도록 지정 ( 세션에 등록된 로그인 정보를 삭제하시 위해서 )
				http.logout()
					.logoutUrl("/auth/logout")			
					.logoutSuccessUrl("/")			//로그아웃 성공적으로 되면 메인으로
					.permitAll();			//누구나 다 요청 가능
					
				//ssl사용 안하면 TRUE사용
//				http.csrf().disable();
	}
	
	//디비랑 연동 안되면 임시로 메모리와 접근해서 동작.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// inMemory 방식으로 인증 사용자 등록
		// ID : user   /  pw : 1234	/ 권한 : USER
		// ID :	admin  /  pw : 1234	/ 권한 : ADMIN
		auth.inMemoryAuthentication() //암호화 한 값을 비밀번호로 등록해서 주고 inMemory방식으로 등록할 것(디비가 없을 떄)
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")	//비밀번호 암호화
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {	// 이 객체 이용하면 encode라는 메서드를 쓰면 특정 객체를 암호화가 가능하다.
		//암호화 한 값을 비밀번호로 등록해서 주고 inMemory방식으로 등록할 것(디비가 없을 떄)
		return new BCryptPasswordEncoder();
	}
	
}
