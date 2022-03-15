package com.kh.di.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kh.di.owner.Owner;
import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;
import com.kh.di.pet.Pet;

@Configuration // ◀ 해당 클래스가 자바 설정 파일임을 선언
@Import(value = {
		OwnerConfig.class,
		PetConfig.class
})
@ComponentScan("com.kh.di") // ◀ 베이스 패키지를 정해주지 않으면 지금 경로 (config 파일)에서만 찾을거임
public class RootConfig {
	/*
	
	@Bean
	public Dog dog() {
		return new Dog("댕댕이");
	}
	
	@Bean("ray")
//	@Primary // ◀ <bean primary="true" /> 와 같음
	public Cat cat() {
		Cat cat = new Cat();
		
		cat.setName("레이");
		
		return cat;
	}
	
	// [ 방법1 ] 메소드에 어노테이션 붙여 Bean 객체 만들기
	@Bean("moon") // ◀ ("Bean 아이디 지정"), 별도로 Bean id 를 지정해주지 않으면, 메소드명으로 id 를 지정함
	public Owner owner() {
		// ▼ dog() 메소드는 Bean 으로 등록되어있어서,
		//   호출 시마다 객체를 생성하는 것이 아닌, 
		//   ApplicationContext 에서 등록된 Bean 객체를 리턴
		return new Owner("무닌수", 22, dog());
	}
	
	// [ 방법2 ] 메소드의 매개변수에 어노테이션 붙이기
	@Bean("dang") // ◀ ("Bean 아이디 지정"), 별도로 Bean id 를 지정해주지 않으면, 메소드명으로 id 를 지정함
	public Owner owner2(@Autowired @Qualifier("ray") Pet pet) {
	//                                               ▲ Qualifier 없이 선언 시, Bean 이 2개 이상이면 못찾아옴 
		return new Owner("스댕", 67, pet);
	}
	
	// [ 방법3 ] 메소드 매개변수에 @Autowired 생략 가능
	//		: Bean 만드는 메소드의 매개값에 객체가 있으면
	//        ApplicationContext 상에 Pet 타입의 Bean 이 있는지 보고 주입해줄 것이기 때문
	//        * @Qualifier 도 생략하고 싶으면 메소드에 @Primary 붙이면 됨
	@Bean("dang") 
	public Owner owner3( /@Autowired/ @Qualifier("ray") Pet pet) {
		// ▼ dog() 메소드는 Bean 으로 등록되어있어서,
		
		return new Owner("스댕", 67, pet);
	}
	 */
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeholeConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer 
				= new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[] {
			new ClassPathResource("character.properties"),
			new ClassPathResource("driver.properties"),
			
		};
		
		configurer.setLocations(resources);
		
		return configurer;
	}
}
