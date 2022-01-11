package com.kh.chap1;

import com.kh.chap1.thread.Thread1;
import com.kh.chap1.thread.Thread2;

public class Application {

	public static void main(String[] args) {
		Thread1 th1 = new Thread1();
		// 여기서 위에서 아래로 흐르는 실행이 메인 스레드
		
		th1.setPriority(Thread.MAX_PRIORITY);	
		
//		th1.run(); // Thread1 클래스의 run()오버라이드로 가서 (run으로 들어감)
				   // 10번 출력하고 다시 메일 스레드로 돌아와서
				   // 메인스레드에 "메인 스레드 종료" 출력문 실행

		th1.start(); // 메인메소드가 start() 호출하면
		             // 원래 흐름대로 아래로 내려가서 "메인 스레드 종료" 출력문 실행하고
		             // "동시에" start()가 만든 새로생긴 작업 스레드는 run으로 가서 출력 10번 함
				     // 스레드가 2개가 됨
	
		// Runnable 인터페이스를 구현한 객체를 Thread 타입 객체를 만들 때 넣어줌
		Thread th2 = new Thread(new Thread2());
		
		th2.setPriority(Thread.MIN_PRIORITY);
		
//		th2.run(); // 이건 main에서 실행하는거라 스레드 이름이 main으로 나올거임
		th2.start(); // "메일 스레드 종료" 출력문과 start들이 동시에 출력 됨	
		
		/* 2-1)익명 구현 객체
		* [표현법] 
		*    new 인터페이스() {구현내용}
		*    ▷ 중괄호 {...} 에는 인터페이스에 선언된 모든 추상메소드를 재정의해서 작성해야 함
		* 
		*  - 자바에서 소스 파일을 만들지 않고도 구현 객체를 만들수 있는 방법
		*  - 인터페이스는 직접 객체로 만들 수 없음 (미완성 클래스라서)
		*  - 대신, 표현법 처럼 중괄호 넣고 인터페이스 내용을 직접 구현하면 인터페이스도 객체로 만들 수 있음
		* - 클래스에 이름이 없어서 익명 구현 객체 ▶ 1회성 처리할 때 필요
		*/
//		Thread th3 = new Thread(new Runnable()); // Runnable은 인터페이스라 객체생성 불가
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + "[" + i + "]");
				}
			}
		});
		
		th3.start();	// Thread2도 실행
		
		/* 2-2) 람다식을 통한 익명 구현 객체
		 * 
		 */
		Thread th4 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + "[" + i + "]");
			}
		});
		
		th4.setDaemon(true);
		th4.start(); 	// Thread3도 실행
		
		
		
		System.out.println(Thread.currentThread().getName());
		System.out.println("메인 스레드 종료");
		
	}

}
