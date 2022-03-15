package com.kh.di.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Primary // ◀ 동일한 타입의 Bean 이 여러 개 존재하는 경우 기본으로 주입될 Bean 지정
@Component
public class Sword implements Weapon {
	@Value("집행검")
	private String name;
	
	@Override
	public String attack() {
		
		return "검을 휘두른다.";
	}
}
