package com.kh.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.kh.di.weapon.Weapon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
// ▼ Bean 생성시 별도 id 지정하지 않으면,
//   클래스 명에서 앞글자만 소문자로 바꾼 값으로 id 를 지정
@Component
// ▼ @PropertySource 어노테이션을 사용해서 properties 파일의 값을 읽어오는 방법
//   1. Environment 객체 사용
//   2. Spring Property Placeholder 사용 ▷ ${key:기본값}
//@PropertySource("classpath:character.properties")

// @PropertySource 생략 후 properties 파일의 값을 읽어오는 방법
//	1. XML 설정 파일의 경우, <context:property-placeholder > 추가
//  2. Java 설정 파일의 경우, PropertySourcesPlaceholderConfigurer Bean 등록
public class Character {
//	@Value("무닌수")
//	@Value("${character.name}")
	@Value("${character.name:홍길동}")
	private String name;
	
//	@Value("100")
	@Value("${character.level:99}")
	private int level;

	@Autowired
	@Qualifier("windForce")
	private Weapon weapon;
	
//	public Character (/*@Autowired*/ Environment env) {
//		this.name = env.getProperty("character.name");
//		this.level = Integer.parseInt(env.getProperty("character.level"));
//	}
}
