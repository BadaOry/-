package com.kh.chap4;

import java.util.List;

import com.kh.chap4.generics.MyGenerics;

public class Application {

	public static void main(String[] args) {
		String[] strArr = {"무닌수","김성욥","기미영","바찬의","섯민"};
		Integer[] intArr = {11, 22, 33, 44, 55};
		Double[] doubleArr = {11.4, 22.8, 33.2, 44.7, 55.6};
		
//		MyGenerics<String> myGenerics = new MyGenerics<>(strArr);
		MyGenerics<Integer> myGenerics2 = new MyGenerics<>(intArr);
		MyGenerics<Double> myGenerics3 = new MyGenerics<>(doubleArr);
		
		
//		myGenerics.print();
		System.out.println();
		myGenerics2.print();
		System.out.println();
		myGenerics3.print();
		System.out.println();
		
//		List<String> list = myGenerics.toList();
		List<Integer> list2 = myGenerics2.toList();
		List<Double> list3 = myGenerics3.toList();
		
		
//		for(String str : list) {
//			System.out.println(str);
//		}
//		System.out.println();
		
		for(Integer i : list2) {
			System.out.println(i);
		}
		System.out.println();
		
		for(Double d : list3) {
			System.out.println(d);
		}
		
	}

}
