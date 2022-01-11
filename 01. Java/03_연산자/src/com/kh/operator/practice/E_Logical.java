package com.kh.operator.practice;

import java.util.Scanner;

public class E_Logical {
	/*
	 *  논리 연산자
	 *   - 두 개의 논리값을 연산하는 연산자
	 *   - && (and) : 왼쪽, 오른쪽 피연산자가 모두 true일 경우 참(true) 
	 *   				true && true => true
	 *   				true && false => false
	 *   				false && true => false
	 *   				false && false => false
	 *   - || (or) : 왼쪽, 오른쪽 피연산자 중 하나라도 true일 경우 참(true)
	 *					true || true => true
	 *   				true || false => true
	 *   				false || true => true
	 *   				false || false => false
	 */
	
	public void method1() {
		// 사용자가 입력한 정수값이 1부터 100 사이의 값인지 확인하기
		int num = 0;
		boolean result = false;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("임의의 정수를 입력해주세요.");
		num = scanner.nextInt();
		
		// 1 <= num <= 100
//		result = 1 <= num <= 100; 은 에러남 // 1 <= num이 true 나오고, true <= 100 은 연산이 안되지 (논리값은 크기비교 불가)
		result = (num >= 1) && (num <= 100);
		
		System.out.println("사용자가 입력한 값이 1 이상, 100 이하의 값인가요? : " + result);
		
		
		
		
	}

	public void method2() {
		// 사용자가 입력한 값이 y 인지 Y인지 확인하기
		char ch = '\u0000';
		boolean result = false;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("계속 진행하시려면 y 혹은 Y를 입력하세요. : ");
		// 문자열을 변수에 담아서 쓰냐
//		String str = scanner.nextLine();
//		str = line.charAt(0);
		
		// 문자열 메소드를 바로 출력하냐 (= 메소드 반환형) : 생성된 문자열에서 String클래스의 charAt메소드 바로 소환
//		ch = scanner.nextLine().charAt(0);
//		result = (ch == 'y') || (ch == 'Y');
		
		String line = scanner.nextLine();
		ch = line.charAt(0);
		result = (line.length() == 1) && ((ch == 'y') || (ch == 'Y'));
		System.out.println("사용자가 입력한 값이 y 또는 Y 입니까? : " + result);
	}
	
	public void method3() { 
		// Short Cut 연산 테스트 : 뒤에 있는 연산을 따로 하지 않고 결과값을 내는 것 (java 실행 때)
		int num = 10;
		boolean result = false;
		
		System.out.println("&& 연산 전의 num의 값 : " + num);
		
		// && 연산자를 기준으로 앞에서 이미 false가 나왔으므로 굳이 뒤쪽의 연산을 수행하지 않음
		// & 하나만 쓰면 short cut 안함
		result = (num < 5) && (++num > 0);  
		
		System.out.println("result : " + result);
		System.out.println("&& 연산 전의 num의 값 : " + num);
		System.out.println();
		
		System.out.println("|| 연산 전의 num의 값 : " + num);
		
		// short cut 연산 나올 것이고, 
		// | 하나만 쓰면 short cut 안함
		result = (num < 20) || (++num > 0);
		
		System.out.println("result : " + result);
		System.out.println("|| 연산 후의 num의 값 : " + num);
		
		
		
		
		
		
	}
}
