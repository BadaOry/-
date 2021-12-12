package com.kh.chap2._interface;

public class Tv extends Product {
	private int inch;		// 인치 (크기)
	
	public Tv() {
		// 기본 생성자
	}
	
	public Tv(String brand, String pCode, String name, int price, int inch) {
		super(brand, pCode, name, price);
	
		this.setInch(inch);
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

	@Override
	public void turnOn() {
		System.out.println("tv를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("tv를 끕니다.");
		
	}
	
}

