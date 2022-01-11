package com.kh.chap2.sync;

public class Buffer {
	private int data;
	
	// 플래그 변수 : 객체의 상태를 저장하고 흐름을 제어하기 위한 변수
	private boolean empty = true;  // 상품이 비었냐 물어보는 것
	
	/*
	 * synchronized
	 *   - 동기화 메소드 , 동기화 블럭에 사용되는 키워드로
	 *     동기화 메소드 선언에 synchronized 키워드를 붙임 
	 *     (인스턴스, 정적 메소드 등 어디든 사용 가능)
	 *   - 동기화 메소드는 스레드가 메소드를 실행하면 
	 *     동기화 메소드 "전체"에 즉시 락을 걸고, 메소드가 종료되면 락이 풀림
	 *   - 메소드 전체가 아니라 일부 내용에만 락을 걸고싶다면
	 *     동기화 블록을 만들면 됨 (synchronized(공유객체) {...})
	 */
	
		   // ▼ 동기화 메소드로 만듦	
	public synchronized void getData() {
		
		while(empty) {
			try {
				wait(); 	// - empty가 true면 스레드 못쓰게 정지시킬거임
						 	// - 예외처리도 해줄겡
							// - 동기화 메소드 안에서 wait 메소드 실행 시 
							//   객체 잠금을 풀고 스레드를 일시 정지상태로 만듦
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.empty = true; // 생산했으니 차있다고 상태값을 바꿔줘야
						   // getData 반복문 빠져나올거임
		
		System.out.println("소비자가 " + this.data + "번 상품을 소비하셨습니다.");
	
		notify(); // wait()에 의해 일시정지된 스레드 중 한 개를 실행 대기 상태로 만듦
	
	}
	
	
	       // ▼ 동기화 메소드로 만듦
	public synchronized void setData(int data) {
		
		while(!empty) {
			try {
				wait(); // 객체 잠금을 풀고 스레드를 일시 정지상태로 만듦
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
		
		this.data = data;
		this.empty = false; // 소비했으니 비어있다고 상태값을 바꿔줘야
							// setData 반복문 빠져나올거임
		
		System.out.println("생산자가 " + this.data + "번 상품을 생산하였습니다.");
	
		notify(); // wait()에 의해 일시정지된 스레드 중 한 개를 실행 대기 상태로 만듦
		
	}

}
