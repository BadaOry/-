package com.kh.control.practice;

import java.util.Scanner;

public class A_If {
	/*
	 * if 문
	 * 
	 * [표현법]
	 *   if(조건식) {
	 *     .. 실행할 코드 ..
	 *   }
	 *   
	 * - 보통 조건식에는 비교 연산자, 논리 연산자를 사용
	 * - 조건식의 결과가 참(true)이면 중괄호 안의 코드 실행
	 * - 조건식의 결과가 거짓(false)이면 중괄호 안의 코드를 무시하고 넘어감
	 */

	public void method1() {
		// 입력받은 정수값이 홀수인지 짝수인지 판별하라 (삼항 연산자를 if로 변경)
		int num =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수값 입력 : ");
		num = sc.nextInt();
		
		if(num % 2 == 0) {
			System.out.printf("%d은(는) 짝수이다.\n", num);
		}
		
		if(num % 2 != 0) {
			System.out.printf("%d은(는) 홀수이다.\n", num);
		}
	 
		
	}

	public void method2() {
		// 주민등록번호를 입력받아서
		// 뒷 자리의 첫 번째 값이 1 또는 3이면 "남자입니다." 출력
		// 뒷 자리의 첫 번째 값이 2 또는 4이면 "여자입니다." 출력
		// 뒷 자리의 첫 번째 값이 1,3 또는 2,4가 아니면 "잘못 입력했습니다." 출력
		// 예시)
		//   주민번호를 입력하세요 (-포함) : 000525-33333333
		//   남자입니다.
		
		Scanner sc = new Scanner(System.in);
		char pId = '\u0000';
		
		System.out.print("주민번호를 입력하세요 (-포함) : ");
		pId = sc.nextLine().charAt(7);
		
		if (pId == '1' || pId == '3') {
			System.out.println("남자입니다.");
		}
		
		if (pId == '2' || pId == '4') {
			System.out.println("여자입니다.");
		}
		
		if (pId != '1' && pId != '2' && pId != '3' && pId != '4')
			System.out.println("잘못 입력했습니다.");
		}
		
	/*
	 * if-else 문
	 * [표현법] 
	 *   if(조건식) {
	 *    .. 실행 코드 1..
	 *    } else {
	 *    .. 실행 코드 2..
	 *    }
	 *    
	 *  - 조건식의 결과가 참(true)인 경우 "실행 코드 1" 수행,
	 *  - 조건식의 결과가 거짓(false)인 경우 "실행 코드 2" 수행
	 */
	
	public void method3() {
		// 사용자의 이름을 받아서 본인 이름이 맞는지 확인
		String name = ""; 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();

//		System.out.println("문인수" == "문인수");
//		System.out.println("문인수" == new String("문인수"));
//		System.out.println(name == "문인수");
		
		// name == "문인수" 하면 else 출력되는 이유 
		// : String은 참조값이므로, 데이터를 비교하는게 아니라 주소값을 비교하므로 false 나옴
		//   >> 문자열의 동등 비교는 String 클래스에서 제공하는 equals() 메소드 사용
		if(name.equals("문인수")) {
			System.out.println("본인이 맞습니다.");
		} else {
			System.out.println("본인이 아닙니다.");
		}
		
		
		
	}
	
	/*
	 * if-else if문
	 * [표현법]
	 *   if(조건식1) {
	 *    .. 실행 코드 1..
	 *   } else if(조건식2) {
	 *    .. 실행 코드 2..
	 *   } else if(조건식3){
	 *    .. 실행 코드 3..
	 *   } else {
	 *     ..위의 조건들이 모두 거짓(false)일 경우 실행할 코드.. 
	 *   }
	 *   
	 * - if-else if 문은 여러개의 조건을 제시할 경우 사용
	 * - 단, else 문이 제시되어 있을 경우, 위의 조건이 모두 false가 나오면 else문 실행
	 */
	
	public void method4() {
		// 사용자가 입력한 영문자가 대문자인지 소문자인지 판별하라
		char ch = '\u0000';
		Scanner sc = new Scanner(System.in);
		
		System.out.println("영문자 입력 : ");
		ch = sc.nextLine().charAt(0);
		
		// A <= ch <= Z 으로 !
		if(ch >= 65 && ch <= 'Z') {
			System.out.printf("%c는 대문자이다.\n ", ch);
		} else if(ch >= 'a' && ch <= 'z') {
			System.out.printf("%c는 소문자이다.\n ", ch);	
		} else {
			System.out.printf("%c는 영문자가 아닙니다.\n", ch);
		}
//		System.out.println((int)ch); 
	}
	
	public void method5() {
		/*
		 * 두 정수를 입력 받고 + 또는 -를 입력받아 알맞은 계산을 출력
		 * 단, + 또는 - 외의 다른 문자를 입력하는 경우, "잘못 입력했습니다." 출력
		 * 예시)
		 *   첫 번째 수 : 3
		 *   두 번째 수 : 4
		 *   연산자 입력(+ 또는 -) : +
		 *   3 + 4 = 7
		 */
		
		int num1 = 0;
		int num2 = 0;
		char op = '\u0000';
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번쨰 수 : ");
		num1 = sc.nextInt();
		
		System.out.print("두 번쨰 수 : ");
		num2 = sc.nextInt();
		sc.nextLine();	// 버퍼 비워주셔야죠
		
		System.out.println("연산자 입력(+ 또는 -) : ");
		op = sc.nextLine().charAt(0);
		
		if(op == '+') {
			System.out.printf("%d %c %d = %d\n", num1, op, num2, (num1 + num2));
		} else if(op == '-') {
			System.out.printf("%d %c %d = %d\n", num1, op, num2, (num1 - num2));
		} else {
			System.out.println("잘못 입력했습니다.");
		}
	}
}