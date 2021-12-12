package com.kh.array.practice;

import java.util.Arrays;

public class B_ArrayCopy {
	/* 배열의 복사
	 *  - 얕은 복사 : 배열의 주소만을 복사 
	 *  - 깊은 복사 : 동일한 새로운 배열을 하나 생성ㅎ서 실제 내부 값들을 복사
	 *     System.arraycopy() : 복사하고자 하는 배열의 인덱스와 개수를 지정하고 싶을 때 사용
	 *     					    복사되는 배열을 미리 생성해서 전달해야 함
	 *     Arrays.copyOf()	  : 복사하고자 하는 배열의 크기를 다시 지정해서 복사하고 싶을 떄 사용
	 *     						복사되는 배열을 미리 생성하지 않고 메소드에 배열의 크기를 전달해서 생성
	 *     배열명.clone()		  : 원본 배열을 통째로 복사할 때 사용
	 *     						시작 인덱스를 지정할 수 없고, 복사본 배열의 크기도 지정할 수 없음
	 */
	
	public void method1() {
		// 얕은 복사
		int[] origin = {1, 2, 3, 4, 5};
		int[] copy = origin; // 주소값만 복사
		

		/* 
		 *  향상된 for 문
		 *  
		 *   [표현법]
		 *     for(타입변수 : 배열) {
		 *        .. 수행 문장 ..
		 *     }
		 *     
		 *     - 배열에서 순차적으로 꺼낸 항목을 저장할 변수 선언과 
		 *       콜론(:) 그리고 배열을 나란히 작성
		 *     - for 문이 처음 수행될 때 배열에서 가져올 첫 번째 값이 존재하면 
		 *       해당 값을 변수에 저장 후 수행문장을 실행
		 *     - 수행문장을 실행한 후, 다시 배열에서 가져올 값이 존재하면
		 *       해당 값을 변수에 저장 후 수행문장을 다시 실행하고
		 *       다음 값이 없으면 for 문을 종료 
		 */
		System.out.println("원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		copy[3] = 44; // copy -> 1, 2, 3, 44, 5
		
		System.out.println("\n원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		System.out.println("\norigin의 주소 값 : " + origin.hashCode());
		System.out.println("copy의 주소 값 : " + copy.hashCode());	
	}

	public void method2() {
		// 1. for문을 이용한 깊은 복사
		int[] origin = {1, 2, 3, 4, 5};
		int[] copy = new int[5];
		
		// 반복문을 통해 origin에 있는 값을 copy에 대입
		int index = 0;
		
//		// 향상된 for문 쓰느니 그냥 일반 for문을 쓰는게 낫겠다 !
//		int index = 0;
		
//		for(int value:origin) {
//			copy[index] = value;
//			
//			index++;
//		}
		for(int i =0; i < origin.length; i++) {
			copy[i] = origin[i];
		}
		
		
		
		System.out.println("원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		copy[3] = 44; // copy -> 1, 2, 3, 44, 5
		
		System.out.println("\n원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		System.out.println("\norigin의 주소 값 : " + origin.hashCode());
		System.out.println("copy의 주소 값 : " + copy.hashCode());	
	}
	
	public void method3() {
		// 2. System 클래스에서 제공하는 arraycopy() 메소드를 이용한 깊은 볷
		
		int[] origin = {1, 2, 3, 4, 5};
		int[] copy = new int[10];
		
//		System.arraycopy(원본 배열명, 복사 시작 인덱스, 복사본 배열명, 복사본 배열의 복사 시작위치, 복사할 길이);
		System.arraycopy(origin, 0, copy, 2, origin.length);
//		System.arraycopy(origin, 1, copy, 2, 4); testㅎㅎ
		
		System.out.println("원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		System.out.println("\norigin의 주소 값 : " + origin.hashCode());
		System.out.println("origin의 길이 : " + origin.length);
		System.out.println("copy의 길이 : " + copy.hashCode());
		System.out.println("copy의 길이 : " + copy.length);
	}

	public void method4() {
		// 3. Arrays 클래스에서 제공하는 copyOf() 메소드를 이용한 깊은 복사
		int[] origin = {1, 2, 3, 4, 5};
		
		// Arrays.copyOf(원본 배열명, 복사할 길이);
		// copy를 직접 생성하지 않아도 됨
		int[] copy = Arrays.copyOf(origin, 10);
		
		System.out.println("원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		System.out.println("\norigin의 주소 값 : " + origin.hashCode());
		System.out.println("origin의 길이 : " + origin.length);
		System.out.println("copy의 길이 : " + copy.hashCode());
		System.out.println("copy의 길이 : " + copy.length);
	}
	
	public void method5() {
		// 4. clone 메소드 이용한 깊은 복사
		// 원본 배열을 통째로 복사해서 새로운 배열을 생성 (시작 인덱스 지정 x, 복사본 배열 크기 지정 x)
		int[] origin = {1, 2, 3, 4, 5};
		int[] copy = origin.clone();
		
		System.out.println("원본 배열 출력");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println("\n복사본 배열 출력");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		System.out.println("\norigin의 주소 값 : " + origin.hashCode());
		System.out.println("origin의 길이 : " + origin.length);
		System.out.println("copy의 길이 : " + copy.hashCode());
		System.out.println("copy의 길이 : " + copy.length);
	}
}