package com.kh.di.character;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.RootConfig;
import com.kh.di.weapon.Sword;

@ExtendWith(SpringExtension.class)
// ▼ [ 방법1 ] xml 설정 방식
//@ContextConfiguration(locations = "classpath:spring/root-context.xml")
// ▼ [ 방법2 ] Java config 설정 방식
@ContextConfiguration(classes = RootConfig.class)
class CharacterTest {
	// ▼ @Autowired 의 required 속성값
	//		: Bean 주입이 필수로 진행되어야 하는지 설정하는 옵션
	//		  ▶ true 의 경우 (default)
	//			 주입해야 하는 Bean 이 ApplicationContext 에 존재하지 않으면 Exception 발생
	//		  ▶ false 의 경우 
	//			 주입해야 하는 Bean 이 ApplicationContext 에 존재하지 않으면 null 주입
	@Autowired(required = false)
	private Character character;
	
	@Value("${character.name}")
	private String name;
	
	@Value("${character.level}")
	private int level;
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Test
//	@Disabled
	public void test() {
		assertThat(driver).isNotNull().isEqualTo("oracle.jdbc.driver.OracleDriver");
		assertThat(url).isNotNull().isEqualTo("jdbc:oracle:thin:@localhost:1521:xe");
	}
	
	@Test
	public void create() {
		System.out.println(character);
		
		assertThat(character).isNotNull();
		assertThat(character.getName()).isNotNull();
		assertThat(character.getLevel()).isNotNull().isPositive().isGreaterThan(0);
		assertThat(character.getWeapon()).isNotNull();
//		assertThat((Sword)character.getWeapon().getName()).isNotNull();
//		assertThat((Bow)character.getWeapon().getName()).isNotNull();
	}

}
