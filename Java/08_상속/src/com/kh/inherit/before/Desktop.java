package com.kh.inherit.before;

public class Desktop {
	private String brand; 	// 브랜드
	
	private String pCode; 	// 상품 코드
	
	private String name;	// 상품명
	
	private int price;		// 가격
	
	private boolean allInOne;	// 올일원 타입의 데스크탑인지 일체 여부
	
	// 필드 입력 후 > ctrl + space > Constructor 클릭하면 기본 생성자 바로 만들 수 있음
	// 생성자 #1. 기본 생성자
	public Desktop() {
		// TODO Auto-generated constructor stub
	}

	// 혹은 ctrl + shift + s > constructor using field > 변수 자동 생성 쨘
	// 생성자 #2. 매개변수가 있는 생성자
	public Desktop(String brand, String pCode, String name, int price, boolean allInOne) {
		this.brand = brand;
		this.pCode = pCode;
		this.name = name;
		this.price = price;
		this.allInOne = allInOne;
	}

	// 게터 세터 자동 생성으로 생성
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAllInOne() {
		return allInOne;
	}

	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}
	
	public String information() {
		return this.brand + ", " + this.name + ", " + this.pCode + ", "
				+ this.price + ", " +this.allInOne;
 	}
	
}
