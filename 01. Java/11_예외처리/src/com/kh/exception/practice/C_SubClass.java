package com.kh.exception.practice;

import java.io.FileNotFoundException;
import java.io.IOException;

public class C_SubClass extends C_SuperClass{

//	@Override
//	public void method() {
//		// 오버라이딩 시 throws를 작성하지 않아도 됨
//	}
	
	
//	@Override
//	public void method() throws IOException { // 부모랑 동일한 IOException 던질거야 !
//		// 같은 예외를 던져주는 구문으로 오버라이딩하여 작성 가능
//	}
	
	
//	@Override
//	public void method() throws Exception { // Exception 던지면 예외 나옴 
//										    // : 부모 클래스보다 상위 타입의 예외는 throws 불가
//	}
	
	
	@Override
	public void method() throws FileNotFoundException {
		// 부모 클래스으 메소드보다 더 하위 타입의 Exception을 trhows하는 것은 가능
	}
	
	
	
}
