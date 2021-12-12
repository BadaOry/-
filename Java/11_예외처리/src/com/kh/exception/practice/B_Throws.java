package com.kh.exception.practice;

import java.io.IOException;

public class B_Throws {
	
	public void method1() throws IOException, Exception {
		System.out.println("method1() 호출");
		
		method2();
		
		System.out.println("method1() 종료");
	}
	
	public void method2() throws IOException, Exception {
		System.out.println("method2() 호출");
		
		method3(); // IOException, Exception 예외처리 안하고있다고 에러남
				   // throws IOException, Exception 추가해서 해결(add throws declaration)
		
		System.out.println("method2() 종료");
	}

	public void method3() throws IOException, Exception { // Exception이 최상위라 Exception만 넣어도 괜찮긴 함
		System.out.println("method3() 호출");
		
		int random = (int) (Math.random() * 3 + 1);
		
		if (random == 1) {
			throw new Exception();
		} else if (random == 2) {
			throw new IOException();
		}
		
		System.out.println("method3() 종료");
	}

}
