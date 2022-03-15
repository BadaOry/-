package com.kh.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Repeat {
	
	// ▼ count 속성의 값은 int 가 들어가고,
	//   속성값을 따로 설정하지 않았을 경우 디폴트 값은 1이라는 뜻
	int count() default 1;
}
