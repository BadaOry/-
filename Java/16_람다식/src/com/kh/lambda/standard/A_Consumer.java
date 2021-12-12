package com.kh.lambda.standard;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.BiConsumer;


public class A_Consumer {
	/*
	 * Consumer
	 *  - Consumer 함수적 인터페이스는 리턴 값이 없는 accept() 메소드를 가짐
	 *  - accept() 메소드는 단지 매개값을 소비하는 역할을 함
	 *    (사용만 하고 리턴 값이 없음)
	 */
	
	public void method1() {
		Consumer<String> consumer = (String str) -> {
			System.out.println(str);
		};
		
		consumer.accept("Consumer : 한 개의 매개값 (제네릭스로 지정된 타입)을 받아서 소비함.");
	
		
		
		BiConsumer<String, String> biConsumer = (str1, str2) -> {
			System.out.println(str1 + str2);
		};
		
		biConsumer.accept("BiConsumer : ", "두 개의 매개값(제네릭스로 지정된 타입)을 받아서 소비함.");
			
		
		
		DoubleConsumer dConsumer = (value) -> {
			System.out.println("DoubleConsumer : 하나의 double 값을 받아서 소비함. \tvalue " + value);
		};
		
		dConsumer.accept(3.14);
		
		
		
		ObjIntConsumer<String> objIntConsumer = (str, i) -> {
			System.out.println("ObjIntConsumer : 두 개의 매개값 (제네릭으로 지정된 타입, int)을 받아서 소비함.");
			System.out.println("\t" + str + i);
		};
		
		objIntConsumer.accept("Java", 11);
	}
	
}