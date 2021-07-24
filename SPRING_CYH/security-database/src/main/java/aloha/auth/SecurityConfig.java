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
		// - default 로그아웃 화면 URL 		 : /logout(설정 안하면 기본경로는 여기)
		// - custom  로그아웃 요청 URL 		 : /auth/logout
		// - default 로그아웃 성공 후 이동 URL  : /[로그인경로]
		// - custom  로그아웃 성공 후 이동 RUL  : /
		// - invalidateHttpSession(true) : 로그아웃 시, 세션을 유효하지 않도록 지정 ( 세션에 등록된 로그인 정보를 삭제하시 위해서 )
		http.logout()
			.logoutUrl("/auth/logout")			
			.logoutSuccessUrl("/")			//로그아웃 성공적으로 되면 메인으로
			.invalidateHttpSession(true)	//세션도 비활성화 할 것인지 여부 설정
			.permitAll()			//누구나 다 요청 가능
			;
		//접근 거부 처리(accessError라는 페이지로 이동하게)
		
		//SSL 를 사용하지 않으면 true 사용
		
		http.csrf().disable();
		// csrf - 비활성화하면 어떤 문제가 생기나?
		//CSRF(CROSS SITE REQUEST FORGERY) 공격
		//사용자의 의지오 ㅏ무관해게 크로스사이트(타사이트) 로의부터의 서버에 공격적인 요청을 하는 것.
		//이것을 방지하는게 기본값으로 들어있는데 csrf를 비활성화 하면 이 공격에 대해 공격방지를 하지 않는다(보안적인 문제)
		
		
//		서버 띄우고 회원가입하고 로그인 하고 인증 정보가 세션에 등록됨. 세션은 한 브라우저가 켜져있는 기준으로 돈다. 꺼버리면 로그인도 꺼지는 이유가 그거.
//    브라우저가 떠있는 기간동안 세션에 등록되어 있음.
//    만약 내가 만든 사이트에서 로그인 한뒤 다른 사이트로 가면 만약 불법 사이트면 브라우저에 세션정보가 들어있던 내 정보를 막 긁어갈 수 있다
//		csrf는 크로스 사이트 리퀘스트 포져 다른 사이트 간의 요청을 시큐리티에 등록된 정보를 가져와서 아이디 로그인 하면 이메일 주소나 사용자 정보가 다 있으면
		//불법 사이트가 스캔해가면 안됨.
		//그래서 이 세션에 대한 정보는 이 사이트에서 만들어지는거라 인증해 줄 필요가 있다.
		// 접근거부 처리
		http.exceptionHandling()
			.accessDeniedPage("/auth/accessError");
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
		String query1 = " SELECT user_id as username, user_pw as password, enabled "	//enabled는 허용 가능 여부. 만약 비활성화 되어있거나 휴면계정(값이 0이거나 다른 숫자면 안됨. 1이나 true같은 긍정의 값으로 사용 가능한 지 여부가 가증 )
					 + " FROM member "		//회원 테이블 안에 userid,userpassword로 주고 시큐리티에서 인식 시킬떄는 파라미터의 이름을 아이디는 유저네임으로, 
				
					 + " WHERE user_id = ? ";
		// 인가처리를 위한 쿼리 :  아이디 / 권한
		//아이디가 가진 권한 테이블. 권한을 임의로 ROLE_USER로 만듬.
		String query2 = " SELECT user_id as username, 'ROLE_ADMIN' "		//기본적으로 처리할 떄 ROLE_USER이런식으로 붙음. 디비에서도 권한 테이블 만들떄 이런식으로 등록
					  + " FROM member "																		//권한테이블 만들면 이거도 바꿔야
					  + " WHERE user_id = ? ";
				
		

		auth.jdbcAuthentication()			//jdbc로 인증방식
			.dataSource(dataSource)			//디비에 대한 정보
			.usersByUsernameQuery(query1)		//첫번째 쿼리 문자열로 넣어주고
			.authoritiesByUsernameQuery(query2)		//두번쨰 쿼리를 넣어주는데 인가에 대한 처리를 넣어줌.
			.passwordEncoder(new BCryptPasswordEncoder());			//패스워드 방식 지정(이 암호화를 해서 회원 가입 시 이 방식으로 암호화 해서 인증처리 할 때도 같은 암ㅎ화 방식 넘기기 가능.
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}


















