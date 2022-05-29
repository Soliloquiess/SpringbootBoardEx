package com.main.controller;

//out클래스는 	 콘솔로 데이터 확인 하기 위해 지우지 않는다. 그 외 맨 처음 자바안의 com을 옮긴 이후에는 controller를 메인 controller패키지 이외에 다 지운다.
import com.util.io.Out;

public class ExecuteService {

	private static boolean log = true; // true : 로그를 출력한다.-개발자가 확인을 위해. false : 로그를 출력하지 않는다. 실제운영
	
	// public static 서비스를 실행하면 나오는 결과리턴타입 execute(실행할 생성된 서비스 객체, 서비스 실행시 전달된는 값) throws Exception
	public static Object execute(ServiceInterface service, Object obj) throws Exception{
		// 권한 체크 - 로그인 여부 / 권한 여부
		
		// 시작 시작을 저장하는 처리문
		long startTime = System.currentTimeMillis();
		if(log == true) { // 실행 앞에서 실행할 내용.
			System.out.println();
			Out.title("log print(ExecuteService.execute())", "#", 15);
			Out.line("-", 60);
			// 실행하는 클래스 이름과 메서드이름
			System.out.println("+  실행 : " + service.getClass().getSimpleName() + ".service()");
			// 넘어가는 데이터 확인 출력
			System.out.println("+ 넘어가는 데이터 : " + obj);
		}
		// 실행 - 결과가 result에 저장된다.
		Object result = service.service(obj);
		if(log == true) { // 실행 뒤에서 실행할 내용.
			System.out.println("+ 실행결과 데이터 : " + result);
			//처리 시간 출력
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			System.out.println("+ 실행 시간 : " + time);
//			System.out.println("+ 현재 위치(경로) : " + System.getProperty("user.dir"));
//			System.out.println("+ JAVA_HOME : " + System.getenv("JAVA_HOME"));

			Out.line("-", 60);
			System.out.println();
		}
		return result;
	}
	
}
