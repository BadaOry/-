package com.kh.aop.character;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:root-context.xml"})
class CharacterTest {
	/*
	 * AOP 용어 정리
	 * 	1. Aspect
	 * 		- AOP 횡단 관심사(한 애플리케이션 내에서 여러부분에 공통적으로 사용하고 있는 기능) 를
	 *        Aspect 라는 특별한 클래스로 모듈화하여 관리함
	 *      - Aspect = Advice + PointCut 
	 *      
	 *      
	 *  2. JoinPoint
	 *  	- Advice 를 적용할 수 있는 모든 지점
	 *  	- 즉, JoinPoint 는 애플리케이션 실행에 공통적인 기능을 끼워 넣을 수 있는 지점 (Point)
	 *  	- ex. 메소드 호출 지점, 예외 발생 등
	 *  
	 *  
	 *  3. Advice
	 *  	- Aspect 가 해야하는 작업(=공통적으로 사용하고 있는 기능)과
	 *        언제 그 작업을 수행해야 하는지 정의하는 것을 AOP 용어로 지칭하는 것
	 *  	- Spring AOP 는 5 종류의 Advice 제공
	 *  		1) Before Advice          : 어드바이스 대상 메소드 호출 전에 어드바이스 기능 수행
	 *  		2) After Advice           : 결과와 상관 없이, 대상 메소드가 완료된 후에 어드바이스 기능 수행
	 *  		3) After-Returning Advice : 어드바이스 대상 메소드가 성공적으로 완료된 후에 어드바이스 기능 수행
	 *  		4) After-Throwning Advice : 어드바이스 대상 메소드가 예외를 던진 후에 어드바이스 기능 수행
	 *  		5) Around Advice          : 어드바이스 대상 메소드 호출 전과 후에 어드바이스 기능을 수행
	 *  
	 *  
	 *  4. PointCut
	 *  	- Advice 가 적용 될 JoinPoint 의 영역을 좁히는 역할
	 *  	- Advice 는 Aspect 가 해야하는 '작업' & '언제' 그 작업을 수행해야하는지 정의한다면
	 *        PointCut 은 '어디'에 Advice를 적용할 지 정의하는 것
	 *      - PointCut 지정을 위해서는 AspectJ PointCut 표현식을 통해서 지정 가능
	 *      
	 *      
	 * * AspectJ 포인트커트 표현식
	 * 		- Spring AOP 에서 PointCut는 AspectJ 의 포인트 커트 표현식을 이용해서 정의
	 * 		- Spring AOP 에서 지원되는 AspectJ 의 포인트 커트 표현식
	 *        ▷ execution([접근지정자] 리턴타입 [클래스이름].메소드명(파라미터)) : 메소드 실행에 대한 조인 포인트 지정
	 *      		ㄴ 접근 지정자     : public, private, .. 값을 적어줌 (생략 가능)
	 *      		ㄴ 리턴 타입      : 메소드의 반환값 의미
	 *      		ㄴ 클래스 이름     : 클래스의 풀 패키지명이 포함된 이름을 작성
	 *      			ex1. *   : 모든 값 표현
	 *      			ex2. ..  : 0개 이상	
	 *              ㄴ args(파라미터) : 타겟 메소드에 전달되는 파라미터 값을 어드바이스에 전달하기 위한 파라미터 지정
	 *              ㄴ bean(빈ID)   : 포인트 커트 표현식 내에서 빈 id 를 특정하여 빈을 지정할 수 있음
	 *              ㄴ @annotation (어노테이션이름(풀패키지명)) : 주어진 어노테이션을 갖는 조인 포인트를 지정
	 */

	@Autowired
	private Character character;
	
	@Test
	void test() {
	}
	
	@Test
	void create() {
//		System.out.println(character);
		
		assertThat(character).isNotNull();
		assertThat(character.getWeapon()).isNotNull();
	}
	
	@Test
	void questTest() throws Exception {
//		System.out.println(character.quest("일시점검"));
		
		// ▼ quest 메소드에 "일시점검" 넘기면 이게 포함되어서 출력되는지 보는 코드
		assertThat(character.quest("일시점검")).contains("[일시점검]");
	}
	
	@Test
	void attackTest() throws Exception {
		
//		System.out.println(character.getWeapon().attack());
		assertThat(character.getWeapon().attack()).isNotNull();
	}
	


}
