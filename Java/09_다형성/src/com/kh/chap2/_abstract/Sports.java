package com.kh.chap2._abstract;

public abstract class Sports {
	// 참여하는 사람의 수
	private int numberOfPlayers; 
	
	
	// 기본 생성자
	public Sports() {
	}

	// 매개 변수를 가지는 생성자
	public Sports(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	// 게터세터
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	// 각 스포츠마다 그에 따른 룰을 반드시 정의해야 함
	public abstract void rule(); /*{
		
	}*/ // 바디가 있으니 당연히 에러나죠 ~~ 

	
	
}
