package com.kh.chap1.thread;
/*
 * 스레드 생성 방법
 * 
 * 1. Thread 클래스를 상속받는 방법
 *   - Thread 클래스를 상속 후에 run() 메소드를 오버라이딩 함
 *   - Thread 클래스를 상속한 자식 클래스의 객체를 생성 후 start() 메소드 호출
 */

public class Thread1 extends Thread {
	// alt shift s > Overriding에서 run 클릭하여 생성
	@Override
	public void run() {
		// 작업하고자 하는 코드 작성
		
		// for문도 for + ctrl + space해서 빠르게 생성 가능.....
		for (int i = 0; i < 100; i++) {
			System.out.println(this.getName() + "[" + i + "]");
			
		}
	}	
}