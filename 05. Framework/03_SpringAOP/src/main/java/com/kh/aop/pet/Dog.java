package com.kh.aop.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.Repeat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Dog implements Pet{
	@Value("갱얼쥐")
	private String name;
	
	@Override
	@Repeat(count = 3)
	public String bark() {
		
//		if(true) {
//		throw new Exception();
//	}
		return "멍멍";
	}
}
