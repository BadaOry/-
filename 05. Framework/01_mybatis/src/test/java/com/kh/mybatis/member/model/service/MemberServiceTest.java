package com.kh.mybatis.member.model.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

// ▼ 테스트 돌리고, jupiter 라이브러리 안쓸거니까 지우면 됨
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

@DisplayName("Member 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceTest {
	private MemberService service;
	
	// ▼ 이걸 쓰면 순서가
	//   setUp() - nothing() - create() 로 되면서 테스트 성공할 것임
	@BeforeEach
	public void setUp() {
		service = new MemberService();
	}
	
	// ▼ 테스트 가능 환경인지 확인하는 메소드
	@Test
	@Disabled
	public void nothing() {
		
	}
	
	@Test
	@Disabled // ▷ 기본적인 사항 확인했으니 동작 안하게 함
	public void create() {
		assertThat(service).isNotNull();
	}

	@Test
	@DisplayName("회원 수 조회 테스트")
	@Order(1)
	public void getMemberCountTest() {
		int count = service.getMemberCount();
		
		 // ▼ count 는 정수값을 리턴할거고, 이건 양수이면서, 2보다 크거가 같을 것임
         //   ▷ 테스트 통과시키려고 만든 값
		assertThat(count).isPositive().isGreaterThanOrEqualTo(2);
	}
	
	// ▼ 모든 멤버를 조회할 수 있는 메소드를 만들고 싶음
	@Test
	@DisplayName("모든 회원 조회 테스트")
	@Order(2)
	public void findAllTest() {
		List<Member> members = null;
		
		members = service.findAll();
		
		assertThat(members).isNotNull()
						   .isNotEmpty()
						   .extracting("id")
						   .isNotNull()
						   .contains("admin2","aeae");
		
		System.out.println(members);
	}
	
//	@Test
	@ParameterizedTest
	@ValueSource(strings = {"admin2", "aeae"})
	@DisplayName("회원 조회 테스트(ID로 검색)")
	@Order(3)
	public void findMemberByIdTest(String userId) {
		// ▼ 너무 없어보여서 리팩토링 좀 해볼게요 !
//		String userId = "aeae";
//		
//		Member member = service.findMemberById(userId);
//		
//		assertThat(member).isNotNull();
		
		// test 를 두 번 하고싶은데, 아이디를 각각 바꿔서 진행하는 것은 비효율적이라
		// jupiter 를 사용해서 String 배열으로 가져오면 알아서 두번 테스트해줌		
		Member member = service.findMemberById(userId);
		
		assertThat(member).isNotNull()
						  .extracting("id")     // ▷ member 에서 id 라는 값만 추출해서
						  .isEqualTo(userId);   // ▷ userId 와 일치하는지 확인
	}
	

	// ▼ 회원가입하는 메소드 만들고 싶음
	//   근데 똑같은 save 메소드로 insert, update 두가지를 해야해서 나눠서 볼 것임
//	@Test // ▷ 한 개 이상의 값을 넣기 위해서 ParameterizedTest 로 변경
	@ParameterizedTest
	@CsvSource({
		"test1, 1234, 앵도",
		"test2, 1234, 왱두짹짹"
	})
	@DisplayName("회원 등록 테스트")
	@Order(4)
	public void insertMemberTest(String id, String password, String name) {
		int result = 0;
		Member findMember = null;  // ▷ DB 에 데이터가 들어갔는지 확인하는 용도
		
		// ▼ 일일히 set 하기 귀찮아서 생성자 만들고옴
		Member member = new Member(id, password, name);
		
		// ▼ notNull 안걸려있는 것들만 넣어줌 (no는 default로 넣어주니까 패스)
//		member.setId("test1");
//		member.setPassword("1234");
//		member.setName("앵도");
		
		// ▼ save 하기 전에, member 객체의 NO 데이터를 가져와보는 코드
		System.out.println("save 전의 NO : " + member.getNo());
		
		result = service.save(member); 
		// ▼ 실제로 DB 에 Member 가 저장되었는지 확인하기 위해 다시 Memeber를 ID 로 조회
		findMember = service.findMemberById(id);
		
		// ▼ save 후, member 객체의 NO 데이터를 가져와보는 코드
		System.out.println("save 후의 NO : " + member.getNo());
		
		assertThat(result).isGreaterThan(0); // ▷ result가 일단 0 보다는 큰걸 알기 때문
		
		// ▼ member 의 NO 값을 가져오려면..?
		//  : getNo 는 int 값 리턴해주는데, 
		//    객체의 필드값을 아직 초기화 안해서 default 값인 0을 가져올것임
		//    ▷ save 후에는 NO 값이 0보다 당연히 클 테니, isGreaterThan 으로 테스트	
		assertThat(member.getNo())./*isEqualTo(0)*/isGreaterThan(0);
	
		// ▼ DB 에 데이터가 들어갔는지 확인하는 용도
		assertThat(findMember).isNotNull().extracting("name").isEqualTo(name);
	}
	
//	@Test
	@ParameterizedTest
	@CsvSource({
		"test1, 5678, 킹갓세종",
		"test2, 0000, 킹광개토"
	})
	@DisplayName("회원 정보 수정 테스트")
	@Order(5)
	public void updateMemberTest(String id, String password, String name) {
		int result = 0;
		Member member = null;
		Member findMember = null;
		
		member = service.findMemberById(id);
//		member = service.findMemberById("test1");
		
		member.setPassword(password);
		member.setName(name);
		
//		member.setPassword("5678");
//		member.setName("킹갓세종");
		
		result = service.save(member);
		
		// ▼ 실제로 DB 에 Member 가 저장되었는지 확인하기 위해 다시 Memeber를 ID 로 조회
		findMember = service.findMemberById(id);
//		findMember = service.findMemberById("test1");
		
		assertThat(result).isGreaterThan(0);
		
		// ▼ DB 에 데이터가 잘 들어갔는지 확인하는 용도
		assertThat(findMember.getName()).isNotNull().isEqualTo(name);
		assertThat(findMember.getPassword()).isNotNull().isEqualTo(password);
	}
	
//	@Test
	@ParameterizedTest
	@ValueSource(strings = {"test1", "test2"})
	@DisplayName("회원 삭제 테스트")
	@Order(6)
	public void deleteTest(String id) {
		int result = 0;
		Member findMember = null;
		Member member = service.findMemberById(id);
		
				
		result = service.delete(member.getNo());
		
		// ▼ 실제로 DB 에 Member 가 저장되었는지 확인하기 위해 다시 Memeber를 ID 로 조회
		findMember = service.findMemberById(id);
		
		assertThat(result).isPositive().isEqualTo(1);
		assertThat(findMember).isNull();
	}
	
	
}
