package com.kh.chap2._interface;

public class SmartPhone extends Product {
	private String mobileAgency; 	// 통신사
	
	public SmartPhone() {
		// 기본 생성자
	}
	
	public SmartPhone(String brand, String pCode, String name, int price, String mobileAgency) {
		super(brand, pCode, name, price);
		
		this.mobileAgency = mobileAgency;
	}

	public String getMobileAgency() {
		return mobileAgency;
	}

	public void setMobileAgency(String mobileAgency) {
		this.mobileAgency = mobileAgency;
	}
	
	@Override
	public String information() { // 부모클래스의 information 메소드를 자식에서 다시 정의하겠다

		return super.information() + ", " + this.mobileAgency;
	}
	
	@Override
	public void turnOn() {
		System.out.println("스마트폰을 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("스마트폰을 끕니다.");
		
	}
	
	
}
