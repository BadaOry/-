package com.kh.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *  Annotation
 *  	- JDK5 부터 추가된 기능으로, 자바 코드에 추가적인 정보를 제공하는 메타 데이터
 *  	- 비즈니스 로직에 영향을 주지는 않지만, 컴파일 과정에서 
 *  	  유효성을 체크하거나, 코드를 어떻게 컴파일하고 처리할 지 알려주는 정보를 제공함
  *        ▶ 컴파일러에게 정보를 주는 역할
 *  	- 어노테이션을 클래스, 메소드, 변수, 매개변수 등에 추가할 수 있음
 */

// ▼ Target : 어노테이션을 적용할 위치 (대상) 지정
@Target({ElementType.METHOD, ElementType.TYPE})

// ▼ Retention : 어노테이션의 유효 범위를 지정할 때 사용
//               ▷ 어느 시점까지 어노테이션이 영향을 미치는 지 결정
//		ㄴ RetentionPolicy.RUNTIME : 컴파일 이후에도 JVM 에 의해 참조가 가능
//		ㄴ RetentionPolicy.CLASS   : 컴파일러 클래스를 참조할 때 까지 유효
//		ㄴ RetentionPolicy.SOURCE  : 코드 상에서만 유효
@Retention(RetentionPolicy.RUNTIME)

// ▼ Inherited : 부모 클래스에서 어노테이션을 선언하면 자식 클래스에도 상속됨
//@Inherited
public @interface NoLogging {

}
