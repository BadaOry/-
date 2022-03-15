package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	
	// ▼ sqlS 까지 입력 후 ctrl + space 로 org.apache에 있는 sqlSession 인터페이스 import
	//   ▷ sqlSession
	//      : 실제로 DB와 연결되어있고, mapper를 참조해서 쿼리문을 수행할 수 있게 하는 객체
	public static SqlSession getSession() {
		InputStream is = null;
		SqlSessionFactoryBuilder builder = null;
		SqlSessionFactory factory = null;
		SqlSession session = null;
		
		/* [ try~catch문 작성 로직 ]
		 * 
		 * 1. SqlSessionFactoryBuilder 타입의 변수 builder 선언
		 * 2. SqlSessionFactoryBuilder 타입의 변수 builder 의 build 메소드를 사용해서
    	 *    SqlSessionFactory 타입의 변수 factory 선언
		 * 3. 파일을 읽어오기 위해, mybatis에서 제공해주는 util 클래스인 Resources 클래스에서
		 *    클래스패스를 기준으로해서 내가 원하는 리소스를 쉽게 읽어오는
	     *    getResourceAsStream() 메소드를 작성 후, 안에 읽어올 파일 명을 넘겨줌
		 *    ▷ ; 앞에서 alt + shift + L 눌러서 InputStream 타입의 변수 추출
		 * 4. is 이후에 빨간줄 나는데 try~catch 로 예외처리 해주면 됨   
		 *    ▷ 여기까지가 Factory 객체를 얻어오는 과정
		 * 5. 이제 SqlSession 타입의 변수 session 에서
		 *    SqlSessionFactory 의 openSession() 메소드를 사용하면,
		 *    mybatis-config.xml 파일의 내용을 참고해서 
         *    ▷ DB에 연결된, SqlSession 객체 리턴
		 */
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			builder = new SqlSessionFactoryBuilder();
			
			// ▼ inputStream 만 그냥 넘겨주면 default 로 저장된 environment 내용을 읽어옴
			//   ▷ 여러개의 environment를 선언하고 다른걸 가져오고싶으면, 매개값으로 is, "아이디값"  넘겨주면 됨
			factory = builder.build(is);
			// factory = builder.build(is, "kh");
		
			// ▼ true 지정 시 오토커밋 활성, false 지정시 오토커밋 비활성
			session = factory.openSession(false);
			
			// ▼ 원래는 session 객체가 만들어졌는지 확인하려면,
			//   main 메소드가 있는 App.java 같은데 가서 직접 돌려서 사람눈으로 확인
			//   ▷ 이제는 TDD 라는걸 만들어서 시스템적으로 확인할거임.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
