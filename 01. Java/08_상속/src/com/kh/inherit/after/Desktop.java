package com.kh.inherit.after;

/*
 * 상속
 *  [표현법]
 *    [접근 제한자] class 클래스명 extends 부모클래스명 { ... }
 *    
 *  상속의 장점
 *    - 적은 양의 코드로 새로운 클래스 작성이 가능
 *    - 코드를 공통적으로 관리하기 때문에 코드 추가&변경에 용이
 *    - 코드의 중복을 제거하여 프로그램의 생산성과 유지 보수에 크게 기여 
 */

public class Desktop extends Product {
	private boolean allInOne;	// 일체 여부
	
	public Desktop() {
		// super(); 숨어있음
	}
	
	/*
	 * 부모 필드를 초기화 할 수 있는 방법
	 * 
	 * 1. super.를 통해 부모 필드에 직접 접근
	 *   - 부모의 필드가 private이면 직접 접근 불가
	 *   - 부모의 필드가 default면 동일한 패키지 경로에 있어야만 직접 접근 가능
	 *   - 부모의 필드가 protected 면 상속 관계일 때 자식 객체에서 직접 접근이 가능
	 *   - 부모의 필드가 public이면 어디서든 접근 가능
	 *  
	 * 2. 부모의 setter 메소드를 통해서 초기화
	 *   - 부모 클래스에서 setter 메소드를 제공하면 setter 메소드를 통해 필드 초기화 가능
	 *   - setter는 public 접근 제한자를 갖기 때문에 가능
	 *   
	 * 3. 부모 생성자 호출해서 초기화
	 *   - super([매개값, ...])를 통해서 자식의 생성자에서 부모의 생성자 호출 가능
	 */
	
	public Desktop(String brand, String pCode, String name, int price, boolean allInOne) {
		super(brand, pCode, name, price); // super()는 무조건 맨 위
		
//		//super() 대신 부모의 게터세터를 상속받아서 접근 가능
//		super.name = "홍길동";
//		this.setBrand(brand);
//		this.setpCode(pCode);
//		this.setName(name);
//		this.setPrice(price);
		
//		super.name = "무닌수"; // Product의 name 필드가 protected면 바로 접근 가능
		System.out.println("자식 객체 매개변수 생성자");
		this.allInOne = allInOne;
	}

	public boolean getAllInOne() {
		return allInOne;
	}

	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}

	@Override // ctrl shift s (Override/Implement method에서 information만 클릭)
	public String information() { // 부모클래스의 information 메소드를 자식에서 다시 정의하겠다

		return super.information() + ", " + this.allInOne;
	}

	


}
