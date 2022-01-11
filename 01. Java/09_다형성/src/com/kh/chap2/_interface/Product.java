package com.kh.chap2._interface;

// 여길 abstract 쓰는순간 다른 가전제품 클래스에서 에러 > 각 가전제품 클래스에서 ctrl 1 해서 오버라이딩 고
// Basic2 여도 에러 안나는 이유 : 실제 사용되는건 구현이 된 메소드이기 때문 ▶ 다중상속처럼 구현
public abstract class Product implements Basic, Basic2 {
	private String brand; 	// 브랜드
	
	private String pCode; 	// 상품 코드
	
	private String name;	// 상품명
	
	private int price;		// 가격
	
	public Product() {
		
	}
	
	public Product(String brand, String pCode, String name, int price) {

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
