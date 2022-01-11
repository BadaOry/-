package com.kh.stream.intermediate;

import java.util.Arrays;
import java.util.List;

import com.kh.stream.model.vo.Student;

public class B_Mapping {
	/*
	 * 매핑
	 *  : 중간 처리 기능으로, 스트림의 요소를 다른 요소로 대체하는 작업을 함
	 *    1) mapXXX()         : 요소를 대체하는 요소로 구성된 새로운 스트림 생성
	 *    2) flatMapXXX()     : 하나의 요소를 복수의 요소들로 구성된 새로운 스트림 생성
	 *    3) asDoubleStream() : IntStream, LongStream -> DoubleStream으로 변환해서 리턴
	 *    4) asLongStream()   : IntStream, DoubleStream -> LongStream 으로 변환하여 리턴
	 *    5) boxed()          : int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 스트림 생성
	 */

	// 1) mapXXX() 
	public void method1() {
		List<Student> list = Arrays.asList(
			new Student("무닌수", 20, "남자", 80, 70),
		 	new Student("이몽룡", 20, "남자", 50, 60),
		 	new Student("성춘향", 20, "여자", 100, 100)
		);
		
		// ▼ 각각의 Student 타입 객체를 map 에 통과시키면 name 만 리턴하고,
		//   forEach를 사용해 name 을 요소로 가지는 새로은 stream 을 생성하여 리턴
		list.stream()
		    .map(student -> student.getName())
		    .forEach(name -> System.out.println(name));
	
		System.out.println();
		
		
		// ▼ 각각의 Student 타입 객체를 map 에 통과시키면 수학점수 만 리턴하고,
		//   int 형으로 바뀐 점수를 average 메소드를 써서 평균을 구한 후 
		//   getAsDouble 으로 double 형태로 가져옴
		double avg = list.stream()
					     .mapToInt(student -> student.getMath())
					     .average()
					     .getAsDouble();
		
		System.out.println("수학점수의 평균 : " + avg);
	}
	
	
	
	// 2) flatMapXXX()
	public void method2() {
		List<String> list = Arrays.asList("Java11 lmabda", "StreamAPI Filtering Mapping");
	
		// ▼ forEach 로 출력
		list.stream().forEach(str -> System.out.println(str));
		
		System.out.println();
		
		// ▼ 공백 별로 쪼개서 새로운 stream 으로 만들 것임
		//   ▷ 첫 번쨰 요소는 2개, 두 번째 요소는 3개의 문자열로 쪼개서 한 개의 stream 생성 후 반환할것임
		//   ▷ 새로운 stream 생성 후 forEach 로 요소를 한 개씩 가져와서 출력
		list.stream()
		    .flatMap(str -> Arrays.stream(str.split(" ")))
		    .forEach(str -> System.out.println(str));
	}
	
	
	
	public void method3() {
		int[] array = {1, 2, 3, 4, 5};
		
	// 3) asDoubleStream()
	// 4) asLongStream()
		Arrays.stream(array)
	      	  .asDoubleStream()
	      	  .forEach(value -> System.out.println(value));
		
		System.out.println();
		
	// 5) boxed()
		Arrays.stream(array)
		      .boxed()
		      .forEach(value -> System.out.println(value.intValue()));
	}
	
}
