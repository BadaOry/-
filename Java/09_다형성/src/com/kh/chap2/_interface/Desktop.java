package com.kh.chap2._interface;

public class Desktop extends Product {
	private boolean allInOne;	// 일체 여부
	
	public Desktop() {
		// super(); 
	}
	
	public Desktop(String brand, String pCode, String name, int price, boolean allInOne) {
		super(brand, pCode, name, price); 
		
		this.allInOne = allInOne;
	}

	public boolean getAllInOne() {
		return allInOne;
	}

	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}

	@Override 
	public String information() {
		return super.information() + ", " + this.allInOne;
	}
	
	@Override
	public void turnOn() {
		System.out.println("데스크탑을 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("데스크탑을 끕니다.");
		
	}
	
}
