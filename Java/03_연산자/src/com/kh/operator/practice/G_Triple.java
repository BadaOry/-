package com.kh.operator.practice;

import java.util.Scanner;

public class G_Triple {
	/*
	 *  삼항 연산자
	 *  
	 *  [표현법]
	 *    조건식 ? 식1 (조건식이 참일 경우 수행) : 식2 (조건식이 거짓일 경우 수행);
	 *  
	 *  - 피연산자가 3개인 연산자
	 *  - 조건식은 주로 비교, 논리 연산자 사용 (조건식은 반드시 true, false가 나와야 함)
	 *  - 조건식의 결과가 true이면 식1 수행
	 *  - 조건식의 결과가 false이면 식2 수행
	 *  - 삼항 연산자는 중첩으로 사용이 가능하나, 가독성 문제가 있을수 있으므로 상황에따라 적절히 사용
	 */
	
	public void method1() {
		int num = 0;
		String result = "";
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("정수값 입력 : ");
		
		num = scanner.nextInt();
//		result = (num > 0) ? "양수이다" : "음수이다" ;
		result = (num > 0) ? "양수이다" : ((num == 0) ? "0 이다" :"음수이다") ;
		
		System.out.println(num +"은(는) " + result);
		
		result = (num % 2 == 0) ? "짝수이다" : "홀수이다";
		System.out.println(num + "은(는) " + result);
	}
	
	public void method2() {
		/*
		 * 두 정수를 입력 받고 + 또는 -를 입력받아 알맞은 계산을 출력
		 * 단, + 또는 - 외의 다른 문자를 입력하는 경우, "잘못 입력했습니다" 출력
		 * 예시)
		 *   첫 번째 수 : 3
		 *   두 번째 수 : 4
		 *   연산자 입력(+ 또는 -) : +
		 *   3 + 4 = 7
		 */
		
		int num1 = 0;
		int num2 = 0;
		char op = '\u0000';
		String result = "";	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번쨰 수 : ");
		num1 = sc.nextInt();
		
		System.out.print("두 번쨰 수 : ");
		num2 = sc.nextInt();
		sc.nextLine();	// 버퍼 비워주셔야죠
		
		System.out.println("연산자 입력(+ 또는 -) : ");
		op = sc.nextLine().charAt(0);
		
		// 숫자 데이터를 문자열로 만들기 : 숫자 + "" 
		result = (op == '+') ? (num1 + num2) + "" : 
			    ((op == '-') ? (num1 - num2) +"" : "잘못 입력했습니다");
		//String.valueOf() : ()안의 내용, 기본 자료형의 값들을 문자열로 바꿔서 반환
		
		System.out.printf("%d %c %d = %s", num1, op, num2, result);
	}
}
