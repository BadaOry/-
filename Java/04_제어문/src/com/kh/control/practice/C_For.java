package com.kh.control.practice;

import java.util.Scanner;

public class C_For {
	/* 
	 * for 문
	 *  
	 *  [표현법]
	 *    for(초기식; 조건식; 증감식) {
	 *        .. 반복적으로 실행시키고자 하는 실행 구문 ..
	 *    }
	 *    
	 *  - 초기식 : 반복문이 수행될 때 단 한번만 실행되는 구문으로, 
	 *          반복문 안에서 사용될 변수를 선언하고 초기값을 대입
	 *  - 조건식 : 반복문이 수행될 때 조건을 작성하는 구문으로
	 *           조건식이 참(true)이면 실행구문을 실행하고
	 *           조건식이 거짓(false)이 되는 순간 반복을 멈추고 빠져나온다.
	 *  - 증감식 : 반복문을 제어하는 변수값을 증감시키는 부분
	 *           주로 초기식에 제시한 변수를 가지고 증감 연산자를 사용
	 *  - 초기식, 조건식, 증감식은 생략 가능하고, 모두 생략하면 무한 루프에 빠짐.
	 */

	public void method1() {
		/*
		 * System.out.println("안녕하세요.");
		 * System.out.println("안녕하세요.");
		 * System.out.println("안녕하세요.");
		 * System.out.println("안녕하세요.");
		 * System.out.println("안녕하세요.");
		 */
		
		System.out.println("i++로 반복" );
		
		for(int i = 0; i < 5; i++ ) { // 0 1 2 3 4
			System.out.println("Hello.");
		}
		
		System.out.println();
		System.out.println("i--로 반복");
		
		for (int i = 20; i > 15 ; i--) { // 20 19 18 17 16 
			System.out.println("안녕하세요.");
		}
		
		// 무한 반복
//		for(; ; ) {
//			System.out.println("안녕하세요.");
//		}
		
		// 1 ~ 10 중 짝수만 출력
		System.out.println();
		System.out.println("1부터 10 까지 숫자 중 짝수인 경우만 출력");
		
		// #1. 내 버전 : 회전을 10번이나 하네..
		for(int i =1; i < 11; i++) {
			if(i % 2 == 0) {
				System.out.printf("%d는 짝수이므로 출력합니다.\n", i);
			}
		}
		// #2. 강사님 버전 : 회전을 최소화
		for(int i = 2; i <= 10; i +=2) { // i = 2, 4, 6, 8, 10
			System.out.println(i + " ");
		}
	}

	public void method2() {
		// 1부터 10까지 정수들의 합계를 출력
		// 1 + 2 + 3 + 4 + ... + 10
		// sum += 1
		// sum += 2
		// ...
		// sum += 10
		int sum = 0;
		
		for(int i = 1; i <= 10; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}

	public void method3() {
		/*
		 * 중첩 for문
		 *  
		 *  [표현법] 
		 *    for(초기식1; 조건식1; 증감식1) {
		 *    	  .. 수행할 문장1; ..
		 *        for(초기식2; 조건식2; 증감식2) {
		 *            .. 수행할 문장2; ..
		 *        }
		 *        .. 수행할 문장3; ..
		 *    }
		 *  - 반복문도 중첩해서 사용할 수 있는데, 바깥쪽 반복문이 한 번 실행할 때 마다
		 *    중첩된 반복문은 지정된 횟수만큼 반복해서 문장을 수행하고
		 *    다시 바깥쪽 반복문으로 돌아간다
		 */
		
		// 2단 ~ 9단
		// 단 : 2부터 9까지 1씩 증가 ▶ 바깥쪽 for 문 
		// 곱해지는 수 : 매 단마다 1~9까지 1씩 증가 ▶ 안쪽 for문
		// 2 * 1 = 2
		// 2 * 2 = 4
		// ...
		// 2 * 9 = 18
		
		System.out.println("구구단 시작");
		
		for(int i = 2; i < 10; i++) {
			System.out.printf("== %d단 ==\n", i);
			for(int j =  1; j < 10 ; j++) {
				System.out.printf("%d * %d = %d\n", i, j, (i * j));
			}
			System.out.println();
		}
		
		
	}

	public void method4() {
		/*
		 * ===============
		 *   Q 또는 q: 종료
		 * ===============
		 */
		
		char ch = '\u0000';
		Scanner sc = new Scanner(System.in);
		
		for(;;) {
			System.out.println("===============");
			System.out.println("  Q 또는 q: 종료  ");
			System.out.println("===============");
			
			// next 메소드는 사용자한테 입력 받을 때 까지 대기 > 입력
			ch = sc.nextLine().charAt(0); 
			
			if(ch == 'q' || ch == 'Q') {
				System.out.println("프로그램을 종료합니다.");
				break;   // 이 메소드에서 더이상 실행할 게 없으면 return도 사용 가능 
			}
		}
	}
}
