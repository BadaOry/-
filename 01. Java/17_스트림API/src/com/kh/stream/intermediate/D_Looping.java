package com.kh.stream.intermediate;

import java.util.Arrays;

public class D_Looping {
	/*
	 * 루핑
	 *  - 요소 전체를 반복하는 것
	 *  - peek ()
	 *    - 중간 처리 단계에서 전체 요소를 반복하면서 추가적인 작업을 하기 위해 사용함
	 *    - 최종 처리 메소드가 호출되어야 동작 (모든 중간 처리 메소드에 적용)
	 *  - forEach()
	 *    - 최종 처리 단계에서 전체 요소를 반복하면서 추가적인 작업을 하기 위해 사용
	 *    - 최종 처리 메소드이므로 이후에 sum()과 같은 다른 최종 처리 메소드 호출 가능
	 */

	public void method1() {
		int sum = 0;
		int[] values = {1, 2, 3, 4, 5};
		
		// ▼ 동작 안함 : 최종 처리 메소드가 실행되지 않으면 실행되지 않기 때문
		//            ▷ 최종 처리 메소드가 호출되어야 동작할 것임
//		Arrays.stream(values)
//		      .filter(value -> value % 2 == 0)
//		      .peek(value -> System.out.println(value));
		
		sum = Arrays.stream(values)
				    .filter(value -> value % 2 == 0)
				    .peek(value -> System.out.println(value))
				    .sum();
		
		System.out.println("Sum : " + sum);
		
		System.out.println();
		
		// ▼ forEach 는 최종 처리 메소드이고 리턴해주는 값이 없어서 (void)
		//   sum 을 호출할 수 없음
		Arrays.stream(values)
		      .filter(value -> value % 2 != 0)
		      .forEach(value -> System.out.println(value));
//		      .sum();  // 에러남
	}
}
