package com.kh.aop.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Bow implements Weapon {

	private String name;
	
	@Override
	public String attack() throws Exception {
		
//		if (true) {
//			throw new Exception();
//		}
		
		return "민첩하게 활을 쏜다.";
	}

}