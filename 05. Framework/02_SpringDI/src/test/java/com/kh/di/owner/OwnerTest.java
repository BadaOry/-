package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.OwnerConfig;
import com.kh.di.config.PetConfig;
import com.kh.di.config.RootConfig;
import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;
import com.kh.di.pet.Pet;

// ▼ @ExtendWith 
//   	: JUNIT 에서 Spring 을 사용할 수 있도록 SpringExtension.class 를 사용하여 기능을 확장하는 것 
//        ▷ 해당 설정이 있어야 @ContextConfiguration() 을 통해 설정파일을 읽고, ApplicationContext 생성 가능
@ExtendWith(SpringExtension.class)

// ▼ @ContextConfiguration 
//		: ExtendWith 의 매개변수가 실행될 때 사용할 설정 파일의 경로를 알려주는 어노테이션
//@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})

// ▼ Java config 방식
//		: RootConfig.java 를 불러오는 코드
//@ContextConfiguration(classes = {RootConfig.class})

@ContextConfiguration(classes =  {
		OwnerConfig.class,
		PetConfig.class
})
class OwnerTest {
// ▼ @Autowired
//		: ApplicationContext 상에서 클래스 타입과 일치하는 bean 을 자동으로 주입시켜줌
	@Autowired

//  ▼ @Qualifier
//		: 이 때, 동일한 클래스 타입에 bean 이 여러 개 존재할 경우 @Qualifier("bean 아이디")를 명시
	@Qualifier("dang")
	private Owner owner;
	
	@Test
	@Disabled
	public void nothing() {
	}
	
	@Test
	public void create() {
		/*
		 * 기존 Java Application 에서는 다형성(인터페이스, 추상클래스)과 생성자 주입을 통해
		 * 객체간의 결합을 느슨하게 만들어 줌
		 */
		
//		Owner owner = new Owner("무닌수", 20, new Dog("댕댕이"));
		
		// ▼ 고양이 키우고싶어요!
		//   ▷ 근데 매번 펫을 바꾸려면 Owner 를 새로 생성해야하고,
		//      Pet의 다형성을 이용해도 귀찮다..
		//   ▶ 아래 contextTest 에서 더 느슨하게 하는 방법 확인 
		Owner owner = new Owner("무닌수", 20, new Cat("나비"));
		
		assertThat(owner).isNotNull();
//		assertThat(owner.getCat()).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	public void contextTest() {
		// ▼ 스프링의 ApllicationContext 를 활용하여 객체간의 결합을 더욱 느슨하게 만듦
		//  ▷ new GenericXmlApplicationContext("클래스패스 상의 xml 파일의 위치 지정");
		//    ▷ 우리가 만든 설정 파일을 넘겨주면
		//       ApplicationContext 가 설정 파일을 참고해서 객체를 만든 후 소중히 가지고 있을 것임
		//  ▶ 이제 Pet 을 바꾸고 싶으면,
		//     여기서 코드가 아니라 설정파일을 바꿔주면 됨!! 
//		GenericXmlApplicationContext context = 
			ApplicationContext context = 
//			new GenericXmlApplicationContext("spring/root-context.xml");
		//                                        ▲ target/classes 밑에 바로 있기 때문   
//			new GenericXmlApplicationContext("classpath:spring/root-context.xml");
//			new GenericXmlApplicationContext("file:src/main/resources/spring/root-context.xml");
//			new GenericXmlApplicationContext("spring/owner-context.xml","spring/pet-context.xml");
			new AnnotationConfigApplicationContext(RootConfig.class);	
		//                                          ▲ 같은 프로젝트 내고, import 하면 되므로 이렇게만 써도 괜춘 
		
		// 방법 1. 형변환하면서 가져오기
//		Object owner = (Owner) context.getBean("owner");
		
		// 방법 2. 형변환 안하고, 객체 타입 지정해서 가져오기
		Owner owner = context.getBean("moon", Owner.class);
		
		// ▼ 눈으로 owner 확인해보기
		System.out.println(owner);
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	public void autoWiredTest() {
		System.out.println(owner);
		System.out.println(owner.getPet().bark());
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}

}
