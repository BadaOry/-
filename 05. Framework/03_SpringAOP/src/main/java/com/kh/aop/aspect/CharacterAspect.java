package com.kh.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // ◀ 일반적인 자바 클래스가 아닌 Aspect 임을 나타냄
public class CharacterAspect {
	
	// ▼ 공통되는 PointCut 를 한 번 정의하고, 필요할 때 마다 참조할 수 있는 어노테이션 제공
	@Pointcut("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	public void questPointCut(String questName) {
	}
	
//	@Before("execution(* com.kh.aop.character.Character.quest(..))")
	public void beforeQuest(JoinPoint jp) {
		// ▼ Signature : 메소드의 선언부 ▷ 여기서는 quest 가 출력 됨
		System.out.println(jp.getSignature().getName());
		// ▼ DeclaringType : 클래스 타입 가져옴 ▷ 여기서는 class com.kh.aop.character.Character 출력 됨
		System.out.println(jp.getSignature().getDeclaringType());
		// ▼ JoinPoint 를 매개값으로 넣어서 정보를 가져올 수 있음
		System.out.println(jp.getArgs()[0] + " 퀘스트 준비중...");	
	}
	
//	@After("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	// ■ 퀘스트 수행 결과에 상관 없이, 수행 후에 필요한 부가 작업 수행
	public void afterQuest(String questName) {
		System.out.println(questName + " 퀘스트 수행 완료 !");
	}
	
	
	// ■ 퀘스트 후에는 성공 & 실패가 있으니 각각 작성해볼 것임
	
//	@AfterReturning(
////			value = "execution(* com.kh.aop.character.Character.quest(..)) && args(questName)",
//			value = "questPointCut(questName)",
//          // ▼﻿ returning : 타겟 메소드가 실행된 후 리턴해주는 변수의 이름 작성
//			returning = "result"
//	)
	public void questSuccess(String questName, String result) {
		// ■ 퀘스트 수행 완료 후에 필요한 부가 작업 수행
		System.out.println(result);
		System.out.println(questName + " 퀘스트 수행 완료 !");
	}
	
//	@AfterThrowing(
////			value = "execution(* com.kh.aop.character.Character.quest(..)) && args(questName)",
//			value = "questPointCut(questName)",
//			throwing = "exception"		
//	)
	public void questFail(String questName, Exception exception) {
		// ■ 퀘스트 수행 중에 예외를 던졌을 때 필요한 부가 작업 수행
		System.out.println(exception.getMessage());
		
		System.out.println("에러가 발생했습니다. 다시 시도해주세요.");
	}
	
	@Around("execution(* com.kh.aop.character.Character.quest(..))")
	public String questAdvice(ProceedingJoinPoint jp) {
		String result = null;
		String questName = "[" + (String) jp.getArgs()[0] + "]";
		
//		System.out.println(questName);
		
		try {
			// ▼ before 어드바이스에 대한 기능을 수행
			System.out.println("퀘스트 준비중...");
			
			// ▼ 타겟 객체의 메소드 실행 (= before & after 의 기준)
//			jp.proceed();
			
			// ▼ 타겟 객체의 메소드에 리턴값이 있을 경우
			//   ▷ 근데 Object 타입으로 반환해주므로, String 으로 강제 형변환 필요
//			result = (String) jp.proceed();
			
			// ▼ 타겟 객체의 메소드에 파라미터 값을 변경해서 전달해줄 경우
			//   ▷ 매개값으로 Object 의 배열로 넘겨주고,
			//      그 배열의 첫 번째 요소로서 questName 을 넣어주는 것임 (즉석 초기화!)
			result = (String) jp.proceed(new Object[] {questName});
			
			// ▼ after-returning 어드바이스에 대한 기능을 수행'
			System.out.println("퀘스트 수행 완료 !");
			
		} catch (Throwable e) {
			
			// ▼ after-throwing 어드바이스에 대한 기능을 수행
			System.out.println("에러가 발생했습니다. 다시 시도해주세요.");
		}
		
		return result;
	}
	
	
	/* [ 실습 문제 ]
 	 *  Sword, Bow 의 attack 메소드 실행 시
	 *  @Around 어드바이스를 사용하여 코드 실행
	 * 1. attack 메소드 정상 동작시, 아래 내용 순서대로 출력
	 *   - 공격을 준비중 입니다.
	 * 	 - "검을 휘두른다" or "민첩하게 활을 쏜다." 
	 *   - 공격을 성공했습니다.
	 *   
	 * 2. attack 메소드 실행 중 예외 발생 시, 아래 내용 순서대로 출력
	 *   - 공격을 준비중 입니다.
	 *   - 에러가 발생했습니다...!
	 */  
	
	@Around("execution(* com.kh.aop.weapon.*.attack())")
	public String attackAdvice(ProceedingJoinPoint jp) {
		String result = null;
		
		
		try {
			System.out.println("공격을 준비중입니다.");
			
			result = (String) jp.proceed();
			
			// ▼ 출력을 원하는 순서대로 하기 위해 한 짓들
			//   1. 공격 준비중 출력하고
			//	 2. jp.proceed() 로 attack() 실행해서 result에 담고
			//      그걸 출력 (지금 아래에 있는 코드)
			//   3. 공격 성공 출력
			System.out.println(result);
			
			System.out.println("공격을 성공했습니다.");
		} catch (Throwable e) {
			System.out.println("에러가 발생했습니다.");
		}

		return result;
	}
	
}
