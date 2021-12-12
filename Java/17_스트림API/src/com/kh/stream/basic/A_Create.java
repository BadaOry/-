package com.kh.stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.kh.stream.model.vo.Student;

public class A_Create {
	/*
	 * 스트림의 종류
	 * 	- java.util.stream 패키지에 존재하고, 
	 *    BaseStream 인터페이스를 부모로 하는 자식 인터페이스들이 상속 관계를 이루고 있음
	 *  - Stream, IntStream, LongStream, DoubleStream
	 *   
	 * 스트림의 생성
	 *  1) 컬렉션으로부터 스트림 얻기
	 *    : 컬렉션 stream() 메소드로부터 얻어옴 (java.util.Collection.stream())
	 *  2) 배열로 스트림 얻기
	 *    - Arrays.stream(배열) 메소드 혹은 
	 *      각 스트림 (Stream, IntStream, ...)의 of() 메소드를 통해 얻어올 수 있음
	 *  3) 숫자 범위로 스트림 얻기
	 *    - IntStream 의 range(), rangeClosed() 메소드를 이용해
	 *      첫 번째 매개 값부터 두 번째 매개값까지, 혹은 이전까지 제공하는 스트림을 얻어올 수 있음
	 */
	
	// 1) 컬렉션으로부터 스트림 얻기
	public void method1() {
		List<Student> list = Arrays.asList(
			new Student("무닌수", 20, "남자", 80, 70),
		 	new Student("이몽룡", 20, "남자", 50, 60),
		 	new Student("성춘향", 20, "여자", 100, 100)
		 );
		
		
		// 1-1) for문 사용하여 Student 객체 출력 
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println();
		
		
		// 1-2) Stream 사용하여 Student 객체 출력  ▶ 순차 처리
		Stream<Student> stream = list.stream();
		stream.forEach(s -> System.out.println(s));
		
		System.out.println();
		
		
		// 1-3) parallelStream 사용하여 Student 객체 출력  ▶ 병렬 처리 
		//     : 내부적으로 스레드를 만들어 병렬적으로 처리함
		//       데이터가 한.. 백만건 정도 되면 병렬처리가 훨씬 빠름
		list.parallelStream().forEach(s -> System.out.println(s));
		
		System.out.println();
		
		
		// 1-4) 메소드 참조 
		list.stream().forEach(System.out::println);
 	}
	
	
	
	// 2) 배열로부터 스트림 얻기
	public void method2() {
		String[] names = {"무닌수", "이몽룡", "성춘향"};
		
		// 2-1-1) Arrays 객체에서 stream 을 가져와서 배열의 문자열 요소에 순차적으로 접근
//		Stream<String> stream = Arrays.stream(names);
		
		// 2-1-2) 스트림 클래스로부터 of() 메소드를 사용하여 배열의 문자열 요소에 순차적으로 접근
		Stream<String> stream = Stream.of(names);
		
		// 2-2) Consumer 표준 함수적 인터페이스를 매개변수로 갖는 stream 생성
		stream.forEach(str -> System.out.println(str));
		
	}
	
	
	// 3) 숫자 범위로부터 스트림 얻기
	public void method3() {
		// 3-1) IntStream 의 range 메소드 사용
		// ▼ 우선 정수 형태로 값을 얻어오기 위해 IntStream을 사용 후, 
		//   range 메소드로 1 ~ 9 까지 순차적으로 접근할 수 있는 stream 객체 생성 
		//   ▷ 첫 번째 매개 값 ~ 두 번째 매개 값 이전까지의 값을 요소로 갖는 스트림 객체를 생성
		IntStream stream = IntStream.range(1, 10);
		
		// ▼ stream 객체 순서대로 출력해보기
//		stream.forEach(i -> System.out.print(i + " "));
		
		// ▼ stream 객체의 합계를 구해 출력해보기
		//   ▷ 마지막에 sum 메소드를 사용하여 int 값으로 출력
		//   * peak : 반복 메소드, forEach 는 최종 처리 메소드라서 일단 바꿔놓음
		int sum = stream.peek(i -> System.out.print(i + " ")).sum();
		
		System.out.println("\nSum : " + sum);
		
		
		// 3-2) IntStream 의 rangeClosed 메소드 사용
		// ▼ 우선 정수 형태로 값을 얻어오기 위해 IntStream을 사용 후, 
		//   rangeClosed 메소드로 1 ~ 10 까지 순차적으로 접근할 수 있는 stream 객체 생성 
		//   ▷ 첫 번째 매개값 ~ 두 번째 매개값까지 값을 요소로 갖는 스트림 객체를 생성
		stream = IntStream.rangeClosed(1, 10);
		sum = stream.sum();
		
		System.out.println("Sum : " + sum);
	}
	
}
