package com.kh.di.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.di.owner.Owner;
import com.kh.di.pet.Pet;

@Configuration
public class OwnerConfig {
	
	/*
	@Bean("moon") 
	public Owner owner() {
	//								▼ 지금 이 파일안에 dog() 메소드가 없어서 에러남
	//                                ▷ 메소드의 매개변수로 owner2 랑 똑같이 넣으면 됨
		return new Owner("무닌수", 22, dog());
	}
	*/
	
	@Bean("moon") 
	public Owner owner(@Autowired @Qualifier("dog") Pet pet) {

		return new Owner("무닌수", 22, pet);
	}
	
	@Bean("dang")  
	public Owner owner2(@Autowired @Qualifier("ray") Pet pet) {

		return new Owner("스댕", 67, pet);
	}


	
}
