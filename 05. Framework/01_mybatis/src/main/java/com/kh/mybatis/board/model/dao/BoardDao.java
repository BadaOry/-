package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public class BoardDao {
	
	public int getBoardCount(SqlSession session, List<String> filters) {
		
		return session.selectOne("boardMapper.getBoardCountByFilters", filters);
	}

	public List<Board> findAll(SqlSession session, String writer, String title, String content) {
		// ▼ 여러 개의 파라미터를 전달하기 위해, Map 객체에 데이터를 담아 select 메소드에 넘김
		//   ▷ select 메소드는 파라미터를 받을때 Object 로 받기 때문에 가능
		Map<String, String> map = new HashMap<>();
		
		//      ▼ 키       ▼ 값
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return session.selectList("boardMapper.selectAll", map);
	}

	public List<Board> findAll(SqlSession session, String[] filters, PageInfo pageInfo) {
	      /*
	       * List 타입이나 Array 타입의 데이터를 파라미터로 전달하면 내부적으로 Map 으로 타입이 변환되어 저장되므로
	       * Mapper 에서는 list (또는 collection) 나 array 라는 이름으로 사용해야 함
	       * 
	       *    [ Dao.java ]
	       *       session.selectList("boardMapper.selectBoardListByFilters", map);
	       * 
	       *    [ Mapper.xml ]
	       *       <if test="array != null">
	       *          ...
	       *     </if>
	       *  
	       *  만약에 filters 라는 이름을 Mapper 에서 사용하고 싶으면,
	       *  map 에 담아서 파라미터로 전달하면 됨
	       */
	      
	      Map<String, Object> map = new HashMap<>();
	      
	      map.put("filters", filters);
	      
	      /* 
	       * 기존에 Servlet/JSP 에서는 쿼리문에서 rownum 컬럼과 서브쿼리를 통해 페이징을 처리함
	       * 하지만 mybatis 에서는 페이징 처리를 위해 RowBounds 라는 클래스 제공
	       * 
	       * [ 우리와 RowBounds 의 비전 ]
	       *  쿼리문을 건들지 않고, 쿼리문이 조회해온 결과에서 RowBounds 를 만들어서 session 객체에 넘겨주면
	       *  session 객체에서 쿼리문을 조회한 다음, RowBounds 객체에 있는 내용을 가지고 우리가 원하는 데이터만 가져다줄거임
		   *
	       * RowBounds 의 객체를 생성할 떄 offset과 limit 값을 전달해서 페이징 처리를 쉽게 구현
	       *  ▷ offset 만큼 건더뛰고 limit 만큼 가져옴
	       *  
	       * ex1) offset = 0, limit = 10
	       *      ▶ 0개를 건너뛰고 10개를 가져옴 (1~10) 
	       * ex2) offset = 10, limit = 10
	       *      ▶ 10개를 건너뛰고 10개를 가져옴 (11~20) 
	       * ex3) offset = 20, limit = 10
	       *      ▶ 20개를 건너뛰고 10개를 가져옴 (21~30) 
	       */
	      
	      int limit = pageInfo.getListLimit();
	      int offset = (pageInfo.getCurrentPage() - 1) * limit;
	      RowBounds rowBounds = new RowBounds(offset, limit);
	      
	      return session.selectList("boardMapper.selectBoardListByFilters", map, rowBounds);
	   }

	public Board findBoardByNo(SqlSession session, int no) {
		
		
		return session.selectOne("boardMapper.selectBoardByNo",no);
	}

	public int insertBoard(SqlSession session, Board board) {
		
		return session.insert("boardMapper.insertBoard", board);
	}

	public int updateBoard(SqlSession session, Board board) {
		
		return session.update("boardMapper.updateBoard", board);
	}

	public int updateStatus(SqlSession session, int no, String status) {
		// ▼ 파라미터 여러개 전달할거라 Map 객체로 만들어서 넘겨줌
		Map<String, Object> map = new HashMap<>();
		
		map.put("no", no);
		map.put("status", status);
		
		return session.update("boardMapper.updateStatus", map);
	}

   
	}