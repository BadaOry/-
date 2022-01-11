package com.kh.lambda.basic;

public class A_Lambda {
	
	public void method1() {
		Thread thread;
		
		// ▼ Runnable 복습 : https://blog.naver.com/bada_ory/222506591997
		//                  실습 01 2-1) 익명 구현 객체
		//                  ▷ 인터페이스는 객체 생성이 불가하므로
		//                    중괄호를 사용하여 인터페이스 내용을 직접 구현
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("익명 구현 객체를 사용한 Thread 생성");
			}
		});
		
		thread.start();
		
		// ▼ 람다식으로 Thread 만들기 : 메소드(함수)를 매개값으로 넘겨주는 중 
		//                       위 Runnable 방식보다 훨씬 간단함
		//                       단,람다식을 쓸떄는 따라가려는 인터페이스와 표현이 동일해야 함
		//                          ex. Runnable의 run 메소드와 형식이 같아야 함
		//                              ( 매개값 없음, return 키워드 없음 등 )
		thread = new Thread(() -> {
			System.out.println("람다식을 사용한 Thread 생성");
			
		});
		
		thread.start();
	}

}