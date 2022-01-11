package com.kh.control.practice;

import java.util.Scanner;

public class D_While {
	/*
	 * while 문
	 * 
	 *  [표현법]
	 *    
	 *    [초기식] 
	 *    
	 *    while(조건식) {
	 *      .. 반복적으로 실행 될 코드 ..
	 *      
	 *      [증감식, 분기문]
	 *    }
	 *    
	 *  - 조건식이 true일 경우 해당 실행 코드를 실행
	 *  - 조건식이 false일 경우 해당 반복문을 종료
	 */
	
	public void method1() {
		// 1부터 랜덤값(1 ~ 10)까지의 합계
		
		int sum = 0;
		/*
		 * java.lang.Math 클래스에서 제공하는 random() 메소드를 사용하여 랜덤값 발생
		 *
		 * Math.random() 호출 시 : 0.0 ~ 0.99999 사이의 랜덤값 발생 (0.0 <= 랜덤값 < 1.0)
		 */
		
//		int random = Math.random(); // Math.random() 호출 시 랜덤값이 double 타입이라 대입 불가
//		int random = Math.random() * 10 + 1 ; // 1.0 ~ 10.999999 
		int random = (int)(Math.random() * 10 + 1); // 1 ~ 10
		
		int i = 1; 
		
		while(i <= random) {
			sum += i;
			
			i++;
		}
		
		System.out.println("1부터 " + random + "까지의 합 : " + sum);
		
		
	}

	public void method2() {

		// 사용자에게 계속 문자열 입력 받고 그 문자열을 출력
		// 단, "exit" 문자열 입력 시 프로그램 종료
		
		String str = "";
		Scanner sc = new Scanner(System.in);
		
		
//		// # 첫 번째 방법
//		while(true) { // 무조건 반복할 예정
//			System.out.print("문자열 입력 : ");
//			
//			str = sc.nextLine();
//			
//			System.out.println(str);
//			
//			if(str.equals("exit")) {
////				System.out.println("프로그램을 종료합니다.");
//				break;
//			}
//		}
		
		// # 두 번째 방법
		while(!str.equals("exit")) { 
			System.out.print("문자열 입력 : ");
			
			str = sc.nextLine();
			
			System.out.println(str);
			}
		
		System.out.println("프로그램을 종료합니다.");
	}

	public void method3() {
		// 사용자가 입력한 단의 구구단을 출력
		// 단, 2~ 9 사이의 정수가 아니면 "2 ~ 9 사이의 정수를 입력해야 합니다." 출력
		// 2~9 사이의 정수를 입력하시오 : 2
		// == 2단 ==
		// 2 * 1 = 2
		// ...
		// 2 * 9 = 18
		
		int num = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("2 ~ 9 사이 정수 입력 : ");
		num = sc.nextInt();
		
		if(num >=2 && num <=9) {
			int i = 1;
			System.out.printf("== %d단 ==\n", num);
			while(i <= 9) {
				System.out.printf("%d * %d = %d\n", num, i, (num * i));
			
				i++;
			}
			
		} else {
			System.out.println("2 ~ 9 사이의 정수를 입력해야 합니다.");
		}		
	}
}