package com.kh.chap2._interface;

public interface Basic2 {
	// 인터페이스에서 필드는 무조건 상수이다
	/*public static final*/ int NUM = 10;
	
	// 인터페이스에서 메소드는 무조건 추상 메소드임
	/*public abstract*/ void turnOn();
	/*public abstract*/ void turnOff();
	
	// > Product에서 interface의 turnOn, turnOff 만들거얌

}
