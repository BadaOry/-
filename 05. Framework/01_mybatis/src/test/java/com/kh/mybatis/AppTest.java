package com.kh.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kh.mybatis.common.template.SqlSessionTemplate;

@DisplayName("첫 번째 테스트 코드 작성")
public class AppTest {
//    @Test
//    public void shouldAnswerWithTrue() {
//        assertTrue( false );
//    }
	
	/* [ 테스트 구동 과정 ]
	 * 
	 *  1. 우리 프로젝트가 테스트를 구동할 수 있는 환경인지 확인부터 함
	 *     ▷ nothing() JUnit으로 성공하면 됨
	 *  2. 테스트하고자 하는 객체가 만들어 졌는지 확인  
	 *  3. 여러가지 어노테이션을 지정하여 동작 가능
	 *     ex. DisplayName, BeforeEach, BeforeAll ...
	 */
	
	private SqlSession session = null;
	
	// ▼ 아래의 테스트 메소드들이 실행되기 전에 가장 먼저 딱 한 번 실행되는 메소드
	@BeforeAll
	public static void init() {
		System.out.println("@BeforeAll");
	}
	
	// ▼ 아래의 테스트 메소드들이 실행되기 전에 무조건 실행되는 메소드
	@BeforeEach
	public void setup() {
		System.out.println("@BeforeEach");
		
		session = SqlSessionTemplate.getSession();
	}
	
	// ▼ 내용이 없는 테스트 메소드 nothing() 을 통해
	//   해당 프로젝트가 테스트 가능한 환경인지 확인
	//   ▷ 처음에만 하면 되니까 @Disalbed 를 통해 이후에는 테스트에서 제외 가능
	@Test
	@Disabled
	public void nothing() {
	}
	
	// ▼ 방금 sqlSessionTemplate.java 에서 만든 
	//   sqlSessio 객체가 존재하는지 확인하는 메소드 
	@Test
	@DisplayName("SqlSession 생성 테스트")
	public void create() {
		assertNotNull(session);
	}
}
