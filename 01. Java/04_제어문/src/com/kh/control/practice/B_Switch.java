package com.kh.control.practice;

import java.util.Scanner;

public class B_Switch {
	/*
	 * switch 문
	 * 
	 *  [표현법]
	 *   switch(조건식) {
	 *   	case 값1 :
	 *     		.. 실행 코드 1..;
	 *     		break;
	 *      case 값2 : 
	 *      	.. 실행 코드 2..;
	 *     		break;
	 *      case 값3 : 
	 *      	.. 실행 코드 3..;
	 *     		break;
	 *      default : 
	 *      	위 조건을 모두 만족하지 않을 경우 실행하게 될 코드
	 *   }
	 *   
	 *  - switch 문은 if 문과 마찬가지로 조건문
	 *  - switch 문은 if 문 처럼 조건식이 true일 경우에 실행문을 실행하는 것이 아니라,
	 *    조건시그이 결과 값(정수, 문자, 문자열)에 따라 실행문이 선택 됨
	 *  - if 문과 다르게 실행 코드를 실행하고 자동으로 빠져나가지 못함 ▶ break 필요
	 */
	
	public void method1() {
		/*
		 * 사용자로부터 과일 이름을 입력받음
		 * 입력된 과일의 값을 지정해서 출령
		 * 지정한 과일이 없으면 "안 팔아요~" 출력
		 */
		
		String fruit = "";
		int price = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("먹고 싶은 과일을 말해봐 : ");
		fruit = sc.nextLine();
		
		switch(fruit) {
		case "복숭아" :
			price = 1000;
			break; // 여기 안쓰면 price 가 3000이 나올 것
		case "귤" :
			price = 500;
			
			break;
		case "수박" :
			price = 20000;
			
			break;
		case "망고" :
			price = 3000;
			
			break;
		default :
			System.out.println("안 팔아요~");
			
			// return 키워드 : 메소드를 호출한 곳으로 다시 돌아가고 밑에는 실행 안함
			// Application 클래스의 new B_Switch().method1();로 돌아감
			return;
		}
		
		System.out.printf("%s의 가격은 %d원 입니다.\n", fruit, price);
		// return 안쓰면 price가 초기화 값(0)이 나옴	
	}
	
	public void method2() {
		// 1 ~ 12월까지 입력받아 해당하는 달의 마지막 날짜 출력
		int month = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1월 ~ 12월 중 하나를 입력하세요 : ");
		month = sc.nextInt();
		
		// 조건 변경해서 실습해보기
		if(!(month >= 1 && month <= 12)) {		// ! (정상범위) ▷ !(정상범위 이외의 값 입력시 false) ▷ true
			System.out.println("1월 ~ 12월까지 입력하셔야 합니다.");
			
			return;
		}
		
		/* 31일까지 존재하는 월 : 1, 3, 5, 7, 8, 10, 12
		 * 30일까지 존재하는 월 : 4, 6, 9, 11
		 * 28일 또는 29일까지 존재하는 월 : 2
		 */
		switch(month) {
		case 1 : 
		case 3 : 
		case 5 : 
		case 7 : 
		case 8 : 
		case 10 : 
		case 12 : 
			System.out.println("입력하신 월은 31일 까지 입니다.");
			break;
		case 4 :
		case 6 :
		case 9 :
		case 11 :
			System.out.println("입력하신 월은 30일 까지 입니다.");
			break;
		case 2 :
			System.out.println("입력하신 월은 28일 혹은 29일 까지 입니다.");
			break;
		}
	}
}
