package com.kh.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;

import static com.kh.mvc.common.jdbc.JDBCTemplate.*;

public class BoardDao {

	// ▼ 게시판에 총 몇 개의 글이 있는지 세보는 메소드
	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM BOARD WHERE STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// ▼ "행" 은 한 개만 나오므로 if 문 사용
			if(rs.next()) {
				// ▼ ResultSet 에 나온 첫 번째 행의 값을 count 에 담아줌
				//                ▽ 컬럼명 혹은 컬럼 순번 적어주면 됨
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}
	
	public Board findBoardByNo(Connection connection, int no) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		String query =
				"SELECT "
	            +    "B.NO, "
	            +   "B.TITLE, "
	            +   "M.ID, "
	            +   "B.READCOUNT, "
	            +   "B.ORIGINAL_FILENAME, "
	            +   "B.RENAMED_FILENAME, "
	            +   "B.CONTENT, "
	            +   "B.CREATE_DATE, "
	            +   "B.MODIFY_DATE "
	            + "FROM BOARD B "
	            + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
	            + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	// ▼ 조회되는 것이 있으면, 결과를 list 에 담아 보내줌
	// ▼ 조회되는 것이 없으면, null을 list 에 담아 보내줌 ▷ NullPointException 발생할 수 있음
	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query =  "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
				+ "FROM ("
				+    "SELECT ROWNUM AS RNUM, "
				+           "NO, "
				+ 			"TITLE, "
				+ 			"ID, "
				+ 			"CREATE_DATE, "
				+ 			"ORIGINAL_FILENAME, "
				+  			"READCOUNT, "
				+     		"STATUS "
				+ 	 "FROM ("
				+ 	    "SELECT B.NO, "
				+ 			   "B.TITLE, "
				+  			   "M.ID, "
				+ 			   "B.CREATE_DATE, "
				+ 			   "B.ORIGINAL_FILENAME, "
				+ 			   "B.READCOUNT, "
				+ 	   		   "B.STATUS "
				+ 		"FROM BOARD B "
				+ 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ 		"WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
				+ 	 ")"
				+ ") WHERE RNUM BETWEEN ? and ?";;
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
		
			pstmt.setInt(1, board.getWriterNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}
	
	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET STATUS=? WHERE NO =?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}






}
