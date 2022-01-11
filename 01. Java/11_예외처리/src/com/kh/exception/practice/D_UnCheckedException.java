package com.kh.exception.practice;

import java.util.Scanner;

public class D_UnCheckedException {
	
	/*
	 * UnCheckedException
	 *   - RuntimeException을 상속하고 있는 예외들을 UnCheckedException이라고 함
	 *   - 컴파일 시 예외 처리 코드가 있는지 검사하지 않는 예외
	 *   - RuntimeException 같은 경우엔 프로그램 실행 시 문제가 발생하는 것이므로
	 *     충분히 예측 가능하고, 조건문을 이용해 처리가 가능
	 *     
	 * RuntimeException 후손들
	 *   - ArrayIndexOutOfBoundsException
	 *       : 배열의 접근에 잘못된 인덱스 값을 사용하는 경우 예외 발생
	 *   - NegativeArraySizeException
	 *       : 배열 크기를 음수로 지정한 경우 발생
	 *   - ArithmeticException
	 *       : 나누기 연산에서 분모가 0인 경우 예외 발생
	 *   - NullPointerException
	 *       : Null인 참조 변수로 객체의 멤버 참조 시도 시 예외 발생
	 *   - ClassCastingException
	 *       : 잘못된 Cast 연산자 사용 시 예외 발생 
	 */
	
	public void method1() {
		// ArithmeticException
		int num1 = 0;
		int num2 = 0;
		int result = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 1 : ");
		num1 = sc.nextInt();
		
		System.out.print("정수 2 : ");
		num2 = sc.nextInt();
		
//		if(num2 == 0) {
//			System.out.println("0으로는 나눌 수 없습니다.");
//			
//			return;
//		}
//		
		// ▼ 여기서 에러나는거니까 해결 방법
		//   #1. if문 (원래 하던거)
		//   #2. trycatch에 넣어볼게용  (try쓰고 ctrl space로 블록 자동생성 가능)
//		result = num1 / num2;  
		
		try {
			result = num1 / num2;
			
			System.out.println("result : " + result);
		} catch(ArithmeticException e) {
//			System.out.println(e.getMessage());
			System.out.println("0으로는 나눌 수 없습니다.");
			
		}
		
//		System.out.println("result : " + result);
	}

	public void method2() {
		// ArrayIndexOutOfBoundsException, NegativeArraySizeException
		int size = 0;
		int[] arr = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("배열의 길이 : ");
		size = sc.nextInt();
		
//		if(size <= 0) {
//			System.out.println("양수를 입력해주세요.");
//			
//			return;
//		}
		
//		// [예외상황 1] NegativeArraySizeException
//		// ▼ arr = new int[size] 여기서 에러나는거니까 해결 방법
//		//   #1. if문 (원래 하던거)
//		//   #2. trycatch에 넣어볼게용 (try쓰고 ctrl space로 블록 자동생성 가능)
//		try {
//			arr = new int[size];
//			
//			for(int i = 0; i < arr.length; i++) { 
//				arr[i] = size - i;
//			}
//			
//			for(int i = 0; i < arr.length; i++) {
//				System.out.print(arr[i] + " ");
//			}
//		} catch (NegativeArraySizeException e) {
//			System.out.println("양수를 입력해주세요.");
//		}
		
////		// 원래 썼던 for문 메소드
////		for(int i = 0; i < arr.length; i++) {
////			arr[i] = size - i;
////		}
////		
////		for(int i = 0; i < arr.length; i++) {
////			System.out.print(arr[i] + " ");
////		}
		
//		// [예외상황 2] ArrayIndexOutOfBoundsException
//		// <= 로 바꿔보겠습니다 ~
//		try {
//			arr = new int[size];
//			
//			for(int i = 0; i <= arr.length; i++) { // <= 하면 ArrayIndexOutOfBoundsException나옴
//				arr[i] = size - i;
//			}
//			
//			for(int i = 0; i < arr.length; i++) {
//				System.out.print(arr[i] + " ");
//			}
//			
//		} catch (NegativeArraySizeException e) {
//			System.out.println("양수를 입력해 주세요.");
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("잘못된 인덱스에 접근하셨습니다.");
//		} catch (Exception e) { 
//			System.out.println("오류가 발생했습니다. 관리자에게 문의해주세요.");
//		}
		
		
		// 예외상황 1 + 2를 한방에 처리해 볼게요 : 대신 어떤 예외가 발생했는지 모르지요..
		// Java 1.7부터 하나의 catch 블록에서 여러 개의 예외를 처리 할 수 있도록 멀티 catch 기능 추가
		try {
			arr = new int[size];
			
			for(int i = 0; i <= arr.length; i++) { 
				arr[i] = size - i;
			}
			
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			
		} catch (NegativeArraySizeException | ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 배열의 길이를 입력하셨거나 잘못된 인덱스에 접근하셨습니다.");
		} catch (Exception e) { 
			System.out.println("오류가 발생했습니다. 관리자에게 문의해주세요.");
		}
		
	}
			
}
