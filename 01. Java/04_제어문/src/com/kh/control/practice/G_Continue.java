package com.kh.control.practice;

public class G_Continue {
	/*
	 * continue
	 *  [표현법]
	 *    반복문(조건식) {
	 *      [continue;]
	 *    }
	 *   
	 *   - 반복문 안에서만 사용 가능하고
	 *     continue를 만나게 되면 다음 구문은 실행하지 않고 
	 *     가장 가까운 반복문의 조건식 or 증감식으로 이동
	 */
	
	public void method1() {
		// 1부터 100까지의 정수들의 합 출력
		// 단, 5의 배수 제외하고 덧셈
		
		int sum = 0;
		
		for(int i = 1; i <= 100; i++) {
			
			if(i % 5 ==0) {
				continue;
			}	
			
			sum += i;
			
		}
		
		System.out.println("합계 : " + sum);
	}
	
	public void method2() {
		// 2 ~ 9단까지의 구구단 출력
		// 단, 홀수단은 빼고 출력
		
		
		for(int dan = 2; dan <= 9; dan++) {
			if(dan %2 == 1) { // dan % 2 !=0 도 됨
				continue;
			}
			System.out.printf("== %d단 ==\n", dan);
			for (int i = 1; i <= 9; i++) {
				System.out.printf("%d * %d = %d\n", dan, i, dan*i);
			}
			System.out.println();
		}
		
	}
	

}
