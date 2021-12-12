package com.kh.exception.practice;

public class A_TryCatch {
	/*
	 * Try ~ Catch
	 * 
	 *  [표현법]
	 *    try {
	 *       // 예외가 발생할 가능이 있는 코드가 위치함
	 *    } catch (Exception e) {
	 *       // try 블록에서 예외가 발생하면 실행을 멈추고
	 *          catch 블록으로 이동하여 예외 처리 코드를 실행함
	 *    } [finally {
	 *       // 예외가 발생하지 않거나 예외가 발생해 catch블록을 실행한 후에
	 *          무조건 실행되는 코드 블록
	 *    }]
	 * 
	 */
	public void method1()  {
		
//		method2(); // Unhandled Exception Type 
		           //  : 예외처리할때 컴파일러가 예외처리 안됐다고 알려주는 중
		  		   //    ▷ throws Exception 넣으면 에러 없어져요
				   //    ▶ 에러 발생 안내에서 try catch문 선택
		
		
		try {
			System.out.println("method2 호출 전");
			
			method2();
			
			System.out.println("method2 호출 후");
		} catch (Exception e) {
			// method2에서 던진 Exception 자료형 객체 e를 가지고 접근할거임
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} finally {
			System.out.println("예외 발생 여부와 상관없이 무조건 수행됨");
		}
		
	}

	
	public void method2() throws Exception/*method2 호출한곳으로 예외 던질거얌*/ {
		System.out.println("method2() 호출 중에 예상치 못한 예외가 발생...");
		
		throw new Exception("강제로 예외를 발생시켰습니다."); // throws Exception 안쓰면 에러남
	}
}
