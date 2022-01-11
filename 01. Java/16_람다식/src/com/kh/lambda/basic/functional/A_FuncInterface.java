package com.kh.lambda.basic.functional;

// ▼ 이 어노테이션이 없다고 해서 함수적 인터페이스가 아닌 것은 아님
//   단, 어노테이션을 달 경우 컴파일러가 추상 메소드를 두개 만들지 않도록 확인해 줌
@FunctionalInterface
public interface A_FuncInterface {
	// 매개변수와 반환값이 없는 추상 메소드
	public void method();
	
	// ▼ 이거 쓰면 추상메소드가 2개가 되어서 추상메소드가 아니게되어 에러남
//	public void otherMethod();
}
