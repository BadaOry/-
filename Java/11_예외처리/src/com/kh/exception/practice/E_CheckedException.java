package com.kh.exception.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E_CheckedException {
	/*
	 * CheckedException
	 *   - Exception을 상속하고 있는 예외들은 CheckedException이라고 부름
	 *   - 컴파일 시 예외 처리 코드가 있는지 검사하는 예외 상황
	 *   - 예외 처리가 되어있지 않으면 컴파일 에러 발생 (try-catch, throws 사용)
	 *   - 조건문이나 소스코드 수정으로는 해결 불가 ▶ 주로 외부에 매개체와 입출력 일어날 때 발생
	 */
	
	public void method1() throws IOException {
		method2(); // throws로 에러 해결
	}
	
	public void method2() throws IOException { 
		String str = null;
		
		// 안배운거니 일단 따라쓰는 내용
		// : Scanner처럼 키보드로 값을 입력 받을 수 있는 객체 (무조건 문자열만 받아옴)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("아무 문자열을 입력해 주세요. : ");
		str = br.readLine(); // readLine을 들여다보면 IOException 던지고있는 내용 포함 > 
							 // throws로 에러 해결
		
		System.out.println(str);
	}
}
