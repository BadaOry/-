package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import oracle.net.aso.b;

@DisplayName("Board 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardServiceTest {
	private BoardService service;
	
	@BeforeEach
	public void setUp() throws Exception {
		service = new BoardService();
	}

	@Test
	@Disabled
	public void nothing() {
	}
	
	@Test
	@Disabled
	public void create() {
		assertThat(service).isNotNull();
	}
	
	//@Test
	@ParameterizedTest
	@CsvSource(
		value = {
			"is, null, null, 1",
			"null, 22, null, 2",
			"null, null, 테스트, 1",
			"null, null, null, 157"},
		nullValues = "null"
	)
	@Order(1)
	@DisplayName("게시글 목록 조회(검색 기능 포함) 테스트")
	public void findAllTest(String writer, String title, String content, int expected) {
		List<Board> list = null;
		
//		// ▼ writer 를 선택하면 
//		//   title 과 cocntent 는 null 로 받아 파라미터를 처리할거라고 가정
//		String writer = null;
//		String title = null;
//		String content =null;
		
//		// ▼ 어쨌든 List 객체가 올 것이고, 찾아온게 없으면 빈 List 객체를 가져옴
		list = service.findAll(writer, title, content);
		
//		// ▼ 리스트에 잘 담겨오나 콘솔에 출력 (뭐가 엄청 많이나옴.. 150개 넘게..)
//		//   ▷ 그런데 writerId, original_filename 등은 못가져옴
//		//      왜냐하면 컬럼명과 필드명이 일치하지 않아서 자동매핑이 안되기 때문 !
//		//   ▷ resultMap 으로 명시적으로 매핑해서 가져와볼게요
//		System.out.println(list.size());  // ▷ 검색해온 게시글의 전체 개수 찍는 코드
//		System.out.println(list);         // ▷ 검색해온 게시글의 정보를 찍는 코드
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isEqualTo(expected);
	}
	
//	@Test
	@ParameterizedTest
	@MethodSource("filterProvider")
	@Order(2)
	@DisplayName("게시글 수 조회 (필터 적용) 테스트")
	public void getBoardCountTest(String[] filters) {
		int count = 0;
//		String[] filters = new String[] {"B1"};
		
		count = service.getBoardCount(filters);
		
		// ▼ 눈으로 count 확인해보는 코드
		System.out.println(count);

		assertThat(count).isPositive().isGreaterThan(0);
	}
	
	
//	@Test
	@ParameterizedTest
	@MethodSource("listProvider") // ◁ 여러 개의 매개변수를 받을 수 있음
	@Order(3)
	@DisplayName("게시글 목록 조회(필터 적용) 테스트")
	public void findAllTest(String[] filters, int currentPage, int expected) {
		// ▼ "B2", "B3"
		//   : request.getParameterValues("filter") 로 가져온 값
		//                                 * 임의로 내가 만든 체크박스의 name 속성명
//		String[] filters = new String[] {"B2", "B3"};
 		List<Board> list = null;
 		// ▼ 페이징을 위해 pageInfo 변수 선언
 		PageInfo pageInfo = null;
 		int listCount = 0;
		
 		listCount = service.getBoardCount(filters);
 		pageInfo = new PageInfo(currentPage, 10, listCount , 10);
		list = service.findAll(filters, pageInfo);
		
		System.out.println(list);
		System.out.println(list.size());
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isEqualTo(expected);
	}
		
//	@Test
	@ParameterizedTest
	@ValueSource(ints = {156, 157})
	@Order(4)
	@DisplayName("게시글 상세 조회 (댓글 포함) 테스트")
	public void findBoardByNoTest(int no) {
		Board board = null;
		
		board = service.findBoardByNo(no);  // ◁ DB 에서 댓글 달린애가 얘였음
		
		System.out.println(board);
		
		// ▼ board 를 받아와서. null 이 아닌지(잘가져오는지) 보고.
		//   여기서 no 만 추출해서. 이 no 가 157인지 확인하겠다는 뜻
		assertThat(board).isNotNull().extracting("no").isEqualTo(157);
		
		// ▼ ﻿Reply 객체 가서 보면 replies 는 Reply 를 요소로 가지는 List 임을 확인 할 수 있음
		//   ▷ 그래서 null 이 나올수 없음 (﻿데이터가 있는 List 혹은 빈 List 를 가져오기 떄문 !)
		assertThat(board.getReplies()).isNotNull();
		
		// ▼ 댓글이 잘 달렸는지 확인하는 코드
		//   ▷ 잘 달렸으면 사이즈가 무조건 0보다 클 것임
		assertThat(board.getReplies().size()).isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	@DisplayName("게시글 등록 테스트")
	public void insertBoardTest() {
		int result = 0;
		Board board = new Board();
		Board findBoard = null;
		
		board.setWriterNo(3);
		board.setTitle("mybatis 게시글");
		board.setContent("mybatis 로 게시글 등록을 해봣슴다!");
	
		result = service.save(board);
		// ▼ no 값은 mppaer의 insert 구문에서 가져옴
		//   * useGeneratedKeys, keyProperty, keyColumn
		findBoard = service.findBoardByNo(board.getNo());
		
		assertThat(result).isGreaterThan(0);
		assertThat(findBoard).isNotNull().extracting("no").isEqualTo(board.getNo());
	}
	
	@Test
	@Order(6)
	@DisplayName("게시글 수정 테스트")
	public void updateBoardTest() {
		int result = 0;
		Board board = service.findBoardByNo(158);
		Board findBoard = null;		
		
		board.setTitle("mybatis 게시글 - 수정");
		board.setContent("mybatis 로 게시글 등록을 해봣슴다! - 수정해봤슴다!");
		board.setOriginalFileName(null);
		board.setRenamedFileName(null);
		
		result = service.save(board);
		findBoard = service.findBoardByNo(board.getNo());
		
		assertThat(result).isGreaterThan(0);
		assertThat(findBoard).isNotNull().extracting("title").isEqualTo(board.getTitle());
	}
	
	@Test
	@Order(7)
	@DisplayName("게시글 삭제 테스트")
	public void deleteBoardTest() {
		int result = 0;
		Board board = null;
		
		result = service.delete(158);
		board = service.findBoardByNo(158);
		
		assertThat(result).isPositive().isEqualTo(1);
		assertThat(board).isNull();
	}
	
	public static Stream<Arguments> filterProvider(){
		return Stream.of(
			// ▼ 파라미터로 사용할 Arguments 라는 객체 생성
			//   ▷ Stream.of 로 Stream 객체 만들어서
			//      arguments() 라는 메소드에 Argument List 를 넘겨주면 됨
			Arguments.arguments((Object) new String[] {"B2", "B3"}),
			Arguments.arguments((Object) new String[] {"B1"})
		);
	}
	
	public static Stream<Arguments> listProvider(){
		return Stream.of(
				// ▼ 필터정보, currentPage 정보, 예상되는 값 받아볼거임
				//   ▷ 각각 - B2, B3 로 걸렀을때 1페이지에는 5개 나오고
				//         - B1 으로 걸렀을때 1페이지엔 10개 나오고
				//         - B1 으로 걸렀을때 16페이지엔 2개 나온다는 뜻
				Arguments.arguments((Object) new String[] {"B2", "B3"}, 1, 5),
				Arguments.arguments((Object) new String[] {"B1"}, 1, 10),
				Arguments.arguments((Object) new String[] {"B1"}, 16, 2)
				);
	}
}
