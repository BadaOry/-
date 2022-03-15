package com.kh.aop.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.aop.weapon.Weapon;

import lombok.Data;

@Data
public class Character {

	private String name;
	
	private int level;

	private Weapon weapon;

	public String quest(String questName) throws Exception {
	
//		// ▼ questFail 을 유도하는 코드
//		if(true) {
//			throw new Exception("Quest 처리 중...");
//		}
		
		System.out.println(questName + " 퀘스트 진행 중..");
		
		return questName + " 퀘스트 진행중";
	}
	
	public String attack(String weaponName) throws Exception {

		return weaponName + "으로 공격을 준비중입니다.";
	}
}
