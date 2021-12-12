package com.kh.array.practice;

import java.util.Scanner;

public class A_Array {
	/*
	 * 배열
	 * 
	 *  [표현법]
	 *    1) 배열의 선언
	 *       자료형[] 배열명;
	 *       자료형 배열명[];
	 *       
	 *    2) 배열의 할당
	 *       배열명 = new 자료형[배열크기];
	 *        
	 *    3) 배열의 선언과 할당을 동시에 진행
	 *       자료형[] 배열명 = new 자료형[배열크기];
	 *       자료형 배열명[] = new 자료형[배열크기];
	 *       
	 *    4) 배열의 초기화
	 *       4-1) 인덱스를 이용한 초기화
	 *            배열명[0] = 값1;
	 *            배열병[1] = 값2;
	 *            ...
	 *            
	 *       4-2) 선언과 동시에 초기화
	 *       	  자료형[] 배열명 = {값1, 값2, 값3, ...};
	 *     	      자료형 배열명[] = {값1, 값2, 값3, ...};
	 *       	  자료형[] 배열명 = new 자료형[] {값1, 값2, 값3, ...};
	 *     	      자료형 배열명[] = new 자료형[] {값1, 값2, 값3, ...};
	 *       
	 *       4-3) 반복문을 이용한 초기화
	 */
	
	// 배열을 사용하지 않을 때 (쏘 불편~~)
	public void method1() {
		// 다량의 데이터를 기록해두고자 할 때
		int sum = 0;
		int num1 = 1;
		int num2 = 2;
		int num3 = 3;
		int num4 = 4;
		int num5 = 5;
		int num6 = 6 ;
		int num7 = 7 ;
		
		// 출력문도 마찬가지로..
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		System.out.println(num4);
		System.out.println(num5);
		System.out.println(num6);
		System.out.println(num7);
		
		// 합계도 마찬가지로..
		sum = num1 + num2 + num3 + num4 + num5 + num6 + num7;
		System.out.println("sum : " + sum);
		
		// 데이터형이 int로 모두 같기 때문에 array 사용 가능
	}

	// 배열의 선언과 할당 
	public void method2() {
		// 변수 선언 (로컬 변수)
		int num; // 메모리(stack)에 자료형 만큼의 크기로 값을 답을 수 있는 공간이 할당 됨
		
		// 배열 선언
		int[] iArray1; // 메모리(stack)에 정수형 배열의 주소를 보관할 공간 할당 됨 (=참조변수) 
		int iArray2[];  // 메모리(stack)에 정수형 배열의 주소를 보관할 공간 할당 됨 (=참조변수) 
		double[] dArray1;  // 메모리(stack)에 실수형 배열의 주소를 보관할 공간 할당 됨 (=참조변수) 
		double dArray2[]; // 메모리(stack)에 실수형 배열의 주소를 보관할 공간 할당 됨 (=참조변수) 
		// stack에 들어가는 참조변수의 용량은 시스템에 따라 4, 8바이트 등임
		
		// 배열 할당
//		iArray1 = new int[]; // 배열 크기 지정하지 않으면 에러 발생
		iArray1 = new int[5]; 	// 메모리(heap)에 5개의 정수형 데이터를 저장할 수 있는 배열 생성 ▶ 배열의 시작주소를 참조변수에 전달
		iArray2 = new int[10];	// 메모리(heap)에 10개의 정수형 데이터를 저장할 수 있는 배열 생성 ▶ 배열의 시작주소를 참조변수에 전달
		dArray1 = new double[5];	// 메모리(heap)에 5개의 실수형 데이터를 저장할 수 있는 배열 생성 ▶ 배열의 시작주소를 참조변수에 전달
		dArray2 = new double[10];	// 메모리(heap)에 10개의 실수형 데이터를 저장할 수 있는 배열 생성 ▶ 배열의 시작주소를 참조변수에 전달
			
		System.out.println("iArray1 : " + iArray1);
		System.out.println("iArray2 : " + iArray2);
		System.out.println("dArray1 : " + dArray1);
		System.out.println("dArray2 : " + dArray2);
		System.out.println();
		
		// 배열을 생성하고 초기화해주지 않아도 문제없이 출력 됨
		// 배열은 따로 초기화하지 않으면, JVM이 지정한 기본 값으로 배열이 초기화
		// (정수형 : 0, 실수형 : 0.0, 문자형 : '\u0000', 논리형 : false, 참조형 : null)
		for(int i = 0; i < iArray1.length ; i++) {
			System.out.printf("iArray1[%d] : %d\n", i, iArray1[i]);
		}
		System.out.println();
		
		// 배열명.length를 통해 배열의 크기를 알 수 있음 (= 배열 선언할때 배열 크기에 넣은 값)
		for(int i = 0; i < dArray1.length ; i++) {
			System.out.printf("dArray1[%d] : %f\n", i, dArray1[i]);
		}
		System.out.println();
		
		// 배열의 값을 초기화 하는 for문 작성 (2의 배수를 넣어보겠습니다)
		for(int i = 0; i < iArray2.length; i++) {
			iArray2[i] = (i + 1) * 2;
		}
		
		// 값을 출력하는 for문 
		for(int i = 0; i < iArray2.length; i++) {
			System.out.printf("iArray2[%d] : %d\n", i, iArray2[i]);
		}
		
	}

	public void method3() {
		int[] iArray = new int[5];
		
		iArray[0] = 2;
		iArray[1] = 4;
		iArray[2] = 6;
		iArray[3] = 8;
		iArray[4] = 10;
		// ArrayIndextOutOfBoundException 에러  >Index 5 out of bounds for length 5
//		iArray[5] = 12; 
		
		for(int i = 0; i < iArray.length; i++) {
			System.out.print(iArray[i] + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println(iArray.length);
		// hashCode() : 주소값을 10진수 값으로 반환 (자바 객체에서 해시코드는 객체를 식별하는 하나의 정수값)
		System.out.println(iArray.hashCode()); 	
		
		// 배열에서 한번 지정한 크기는 변경할 수 없음
		// 따라서 크기를 변경하려면, 다시 배열크기를 지정해서 새로 생성해야 함 (heap에 이미 있기 떄문)
		iArray = new int[6];
		
		// 기존에 참조하고 있던 연결이 끊기고 새로운 배열을 참조하게 됨
		// 기존에 참조하고 있던 배열은 더이상 참조하는 변수가 없으므로 Garbage Collector의 대상이 됨
		// ▶ 특정 시점에 Garbage Collector에 의해 정리됨
		System.out.println(iArray.length);
		System.out.println(iArray.hashCode()); 
		
		
		// 참조 타입 변수는 heap 영역의 배열(객체)을 참조하지 않는다는 뜻으로 null값 가질 수 있음 
		// 참조 변수에 null 값을 넣어주면 참조하고 있는 주소가 없어지는데, 이 때 heap에 존재하는 배열은 더이상 참조되지 않는 배열
		// 더 이상 사용되지 않는 배열은 Garbage Collector에 의해 일정 시간 후에 삭제됨
		// ▶ null 값 입력하여 Garbage Collector로 보내버리기 
		iArray = null;
		
		// null 값을 가지고 있는 참조 변수를 사용하면 NullPointerException 발생
		// 연결이 끊겼으므로 아래는 에러 남
		System.out.println(iArray.length);
		System.out.println(iArray.hashCode()); 	
	}
	
	public void method4() {
		// 배열의 선언, 할당, 초기화를 동시에 진행하기
		int[] iArray1 = new int[] {1, 2, 3, 4, 5}; // 값의 개수만큼 자동으로 크기 할당
		int[] iArray2 = {6, 7, 8, 9, 10};	// 중괄호 블럭 사용 경우 : new 연산자 생략 가능, 값의 개수 만큼 자동으로 크기 할당됨
//		// 에러나는 경우 : 중괄호로 배열 초기화 하려면 선언과 동시에 해야함
//		int[] iArray2;
//		i Array2 = {6, 7, 8, 9, 10} 
		
		// 사용자한테 입력 받아서 값을 넣는 과일배열 만들어볼게용
		String[] fruitArray = new String[3];
		Scanner sc = new Scanner(System.in);
		
		System.out.println("iArray1 : " + iArray1.length);
		System.out.println("iArray2 : " + iArray2.length);
		
		//0번 인덱스 부터 마지막까지 사용자에게 문자열을 입력 받아 해당 인덱스에 사용자가 입력한 값 받기
		for(int i = 0; i < fruitArray.length; i++) {
			System.out.print("과일 입력 : ");
			
			fruitArray[i] = sc.nextLine();
		}
		
		for(int i =0; i < fruitArray.length; i++)
			System.out.printf("fruitArray[%d] : %s\n", i, fruitArray[i]);	
	}
	
}