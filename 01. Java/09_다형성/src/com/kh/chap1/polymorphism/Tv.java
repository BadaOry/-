package com.kh.chap1.polymorphism;

public class Tv extends Product {
	private int inch;		// 인치 (크기)
	
	public Tv() {
		// 기본 생성자
	}
	
	public Tv(String brand, String pCode, String name, int price, int inch) {
//		super(brand, pCode, name, price);
		this.setBrand(brand);
		this.setpCode(pCode);
		this.setName(name);
		this.setPrice(price);
		this.inch = inch;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}
	
	@Override
	public String information() { // 부모클래스의 information 메소드를 자식에서 다시 정의하겠다

		return super.information() + ", " + this.inch;
	}
}

