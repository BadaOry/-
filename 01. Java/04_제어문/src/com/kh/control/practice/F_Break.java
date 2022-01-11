package com.kh.control.practice;

import java.util.Scanner;

public class F_Break {
	/* 
	 * break 문
	 * 
	 * [표현법]
	 *    반복문/switch(조건식) {
   	 *     [break;] // 해당 구문을 빠져나가는 역할
	 *    }
	 *    
	 * - 반복문의 경우 중첩 되어있으면 가장 가까운 반복문 탈출
	 *    단, switch 문 안의 break는 switch 문을 빠져나가는 역할
	 */
	
	public void method1() {
		// 사용자에게 문자열을 입력받고 문자열의 길이를 출력
		// 단, exit 입력시 반복 종료
		String str = "";
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("문자열을 입력하세요 : ");
			str = sc.nextLine();
			
			if(str.equals("exit")) {
				break;
			}
			
			System.out.printf("입력하신 문자열 %s의 길이는 %d 입니다. \n", str, str.length());
		}
		
		System.out.println("프로그램이 종료되었습니다");
	}
	
	
	
	public void method2() {
		// 1부터 랜덤값(1 ~ 10)까지의 합계
		// 단, 랜덤 값이 숫자 5가 나오면 프로그램을 종료
		
//		if(random == 5) { // 목적에 따라 위치가 달라지겠지
//			break;
//		}
//		
		int sum ;
		int random = 0;
		
		while(true) {
			sum = 0;
			random = (int)(Math.random() * 10 + 1);
			
			if(random == 5) { // 목적에 따라 위치가 달라지겠지
				break;
			}
			
			for(int i = 0; i <= random; i++) {
				sum += i;
			}
			
			System.out.printf("1부터 %d까지의 합계 : %d\n" , random, sum);
		}
		System.out.println("5가 나와 프로그램을 종료하니다.");
	}
	
}
