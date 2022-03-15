package com.kh.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.Repeat;

@Aspect
@Component
public class OwnerAspect {

//	@Around("execution(* com.kh.aop.pet.*.bark())")
//	@Around("execution(* com.kh.aop.pet.*.bark()) && !bean(dog)")
//	@Around("execution(* com.kh.aop.pet.*.bark()) && !@annotation(com.kh.aop.annotation.NoLogging)")
/*
	public String barkAdvice(ProceedingJoinPoint jp) {
		String result = null;
		
		try {
			// ▼ before
			System.out.println("짖어..볼래?");
			
			result = (String) jp.proceed();
			
			System.out.println(result);
			
		} catch (Throwable e) {
			// ▼ error
			System.out.println("왜..안짖니? ^.ㅜ...");
		}
		
		return result;
		
	}
	*/
	
// [ 어노테이션 실험용 barkAdvice 메소드 ]
	@Around("@annotation(com.kh.aop.annotation.Repeat)")
	public String barkAdvice(ProceedingJoinPoint jp) {
		String result = null;
		MethodSignature signature = (MethodSignature)jp.getSignature(); // ◀ Signature : 어노테이션 선언부
		Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);
	
		// ▼ @Repeat 정보를 제대로 가져오는지 확인하는 syso 들
		System.out.println(repeat);
		System.out.println(repeat.count());
		
		try {
			// ▼ before
			System.out.println("짖어..볼래?");
			
			result = (String) jp.proceed();
			
			for (int i =0; i < repeat.count(); i++) {
				
				System.out.println(result);
			}
			
		} catch (Throwable e) {
			// ▼ error
			System.out.println("왜..안짖니? ^.ㅜ...");
		}
		
		return result;
		
	}
}
