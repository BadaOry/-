package com.kh.exception;

import java.io.IOException;

import com.kh.exception.practice.A_TryCatch;
import com.kh.exception.practice.B_Throws;
import com.kh.exception.practice.D_UnCheckedException;
import com.kh.exception.practice.E_CheckedException;

public class Application {

	public static void main(String[] args) throws IOException, Exception {
//		new A_TryCatch().method1();

//		new B_Throws().method1(); // add throws declaration으로 에러 해결
		// 실행해보면 실행될때도 있고, IOException과 Exception이 번갈아 나올거임
		// main 에서도 throws 넘기면 JVM이 그냥 콘솔에다 어디어디 에러난다고 알려만 줌
		
//		new D_UnCheckedException().method1();
//		new D_UnCheckedException().method2();
		
		new E_CheckedException().method1(); // main에서 이미 던지고있어서 에러 안남
	}

}
