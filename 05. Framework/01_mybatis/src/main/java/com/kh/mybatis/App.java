package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;
import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
	// ▼ 로그를 찍을 때 선언하는 변수
	//   LoggerFactory 클래스를 의 getLogger 메소드를 사용해서
	//   매개변수로 App.class 넣어줌 (App 이라는 클래스에서 로그 찍는다는 뜻)
	//
	//    * Logger, LoggerFactory 는 SLF4J 에서 가져오는 것
	//      ▷ SLF4J가 직접 로그를 찍는건 아니고, SLF4J 메소드를 써도 내부적으로는 log4j를 사용해서 로그 찍어줌 
	//    * lombok 의 @Slf4j 덕분에 아래 선언문은 생략 가능
//	private static Logger log = LoggerFactory.getLogger(App.class);
	

    public static void main( String[] args ){
    	// ▼ log. 하고 보면 여러가지 레벨이 있음
    	//   ▷ 로그가 일정한 규칙을 가지고 찍을 수 있게 log 변수 사용
    	//     ▷ 개발단계 로그는 debug, 에러에 대한 로그는 error로, 배포 단계에서는 info 찍는식으로 활용
    	log.info("INFO 로깅 테스트");
//    	log.debug("DEBUG 로깅 테스트");
//    	log.error("ERROR 로깅 테스트");
    	
        System.out.println( "Hello World!" );
        
        // ▼ Session 객체가 잘 만들어졌는지 "눈으로" 확인하는 방법
        //   ▷ 아래 작성 후 Run As > Java Application > App - com.kh.mybatis
        //   ▷ 문제없이 객체가 만들어졌으면 콘솔에 Session 객체의 toString 값을 찍어줌
        //   ▶ 만약 mybatis-config.xml 파일의 내용에 문제가 있으면 에러남
        //      ex1. sqlSessionTemplate.java 의 InputStream 선언에서 없는 파일명을 가져옴
        //           ▷ null 찍히면서 IOException : Could not find resource 에러 남
        //      ex2. mybatis-config.xml 의 property의 username, password 가 다를 경우
        //           ▷ 쿼리문을 수행시킬 때 에러남
        
//        SqlSession session = sqlSessionTemplate.getSession();
//   
//        System.out.println(session);
    }
}
