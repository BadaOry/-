package com.kh.inherit.after;

// 가전제품 세 클래스 모두 공통적으로 가지고 있는 요소만 추출하여 부모 클래스를 만들었음
public class Product {
	private String brand; 	// 브랜드
	
	private String pCode; 	// 상품 코드
	
	private String name;	// 상품명
	
	private int price;		// 가격
	
	public Product() {
		System.out.println("부모 객체 기본 생성자");
	}
	
	// 부모 객체의 매개 변수 있는 생성자는 상속이 안됨
	public Product(String brand, String pCode, String name, int price) {
		System.out.println("부모 객체 매개변수 생성자");
		this.brand = brand;
		this.pCode = pCode;
		this.name = name;
		this.price = price;
	}


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



	public String information() {
		return this.brand + ", " + this.name + ", " + this.pCode + ", "
				+ this.price ;
 	}
}
