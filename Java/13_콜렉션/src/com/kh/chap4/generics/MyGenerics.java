package com.kh.chap4.generics;

import java.util.ArrayList;
import java.util.List;

public class MyGenerics<T extends Number> { // 타입을 아직 지정하지 않았고, 객체 생성시 타입 파라미터를 통해 그 때 지정해줄거임
	private T[] array;
	
	public MyGenerics() {
	}
	
	public MyGenerics(T[] array) {
		this.array = array;
	}
	
	public void print() {
		for(T t : array) {
			System.out.println(t);
		}
		
	}
	
	public List<T> toList() {
		List<T>	 list = new ArrayList<>();
		
		for(T t : array) {
			list.add(t);
		}
		
		return list;
	}
}
