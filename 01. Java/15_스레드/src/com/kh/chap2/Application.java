package com.kh.chap2;

import com.kh.chap2.sync.Buffer;
import com.kh.chap2.sync.Consumer;
import com.kh.chap2.sync.Producer;

public class Application {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		Thread producer = new Producer(buffer); 				// 데이더 공급
		Thread consumer	= new Thread(new Consumer(buffer));		// 데이터 소비
		
		// producer에서 setData로 1 ~ 10번 공급하고
		// comsumer가 getData로 1 ~ 10번 소비하는
		// 생산 > 소비 > 생산 > 소비.... 처럼 돌리고싶음
		// 그런데 각자 스케쥴에 따라 스레드가 맘대로 일하니까 원하는대로 나오지 않음
		producer.start();
		consumer.start();
		
		try {
			
			// 아래 스레드의 join() 메소드를 호출한 스레드(main스레드)는
			// 아래의 스레드(producer, consumer)가 종료될 때 까지 기다렸다가 
			// 아래의 스레드가 종료되면 join() 메소드를 호출한 스레드를 다시 실행함
			producer.join();
			consumer.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("메인 스레드 종료");

	}

}
